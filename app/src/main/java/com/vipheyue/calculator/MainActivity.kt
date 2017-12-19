package com.vipheyue.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.udojava.evalex.Expression
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    var waitCalculateStr: String = ""
    var havedResult: Boolean = false
    var lastResult: String = ""
    var dataCenter = ArrayList<HistoryTable>()
    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
    lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_digital_del.setOnLongClickListener {
            waitCalculateStr = ""
            tv_equation_panel.setText(waitCalculateStr)
            havedResult = false
            true
        }
        recyclerView_history.setLayoutManager(LinearLayoutManager(this))


        val query = realm.where(HistoryTable::class.java)
        val findAll = query.findAll().toList()

        dataCenter.addAll(findAll)
        historyAdapter = HistoryAdapter(R.layout.history_item, dataCenter)

        recyclerView_history.adapter = historyAdapter

        historyAdapter.setOnItemClickListener { adapter, view, position ->

            val historyTable = dataCenter.get(position)
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText(historyTable.comment, historyTable.result)
            cm.primaryClip = mClipData
            toast("复制成功---> " + historyTable.result)
        }
        recyclerView_history.smoothScrollToPosition(dataCenter.size)

    }

    fun operatorOnClick(view: View) {
        val symbol = (view as Button).text.toString()
        if (havedResult) {//如果之前有绩结果 则在之前的基础上进行运算
            waitCalculateStr = lastResult
            havedResult = false//新表达式开始 这个表达式还没有结果
        }

        waitCalculateStr += symbol
        tv_equation_panel.setText(waitCalculateStr)
    }

    fun equalOnClick(view: View) {
        val symbol = (view as Button).text.toString()

        try {
            //计算表达式
            val expression = Expression(waitCalculateStr)
            val result = expression.eval().toPlainString()

            //添加 等号 和 结果
            waitCalculateStr += symbol + result
            tv_equation_panel.setText(waitCalculateStr)
            //计算状态重置和记录结果
            havedResult = true
            lastResult = result
            //存入数据库
            realm.beginTransaction()
            val historyTable = HistoryTable(waitCalculateStr)
            val realmUser = realm.copyToRealm(historyTable)
            realm.commitTransaction()
            historyAdapter.addData(historyTable)
            recyclerView_history.smoothScrollToPosition(dataCenter.size)
        } catch (e: Exception) {
            toast("输入的表达式有问题哦!")
            havedResult = false
        }

    }

    fun digitalOnClick(view: View) {
        val symbol = (view as Button).text.toString()

        if (havedResult) waitCalculateStr = ""//新一轮计算 重置表达式
        havedResult = false //新一轮计算 重置

        waitCalculateStr += symbol
        tv_equation_panel.setText(waitCalculateStr)
    }

    fun delOnclick(view: View) {
        if (havedResult) waitCalculateStr = ""
          havedResult = false

        if (waitCalculateStr.isNotEmpty()) waitCalculateStr = waitCalculateStr.substring(0, waitCalculateStr.length - 1)
        tv_equation_panel.setText(waitCalculateStr)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()
    }
}
