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
    var equationStr: String = ""
    var havedResult: Boolean = false
    var lastResult: String = ""
    var dataCenter = ArrayList<HistoryTable>()
    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
    lateinit var historyAdapter: HistoryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        dosomeFunDb()
    }

    private fun dosomeFunDb() {


        // Copy the object to Realm. Any further changes must happen on realmUser

    }

    private fun initView() {
        btn_digital_del.setOnLongClickListener {
            equationStr = ""
            tv_equation_panel.setText(equationStr)
            true
        }
        recyclerView_history.setLayoutManager(LinearLayoutManager(this))


        val query = realm.where(HistoryTable::class.java)
        var findAll = query.findAll().toList()

        dataCenter.addAll(findAll)
        historyAdapter = HistoryAdapter(R.layout.history_item, dataCenter)

        recyclerView_history.adapter = historyAdapter

        historyAdapter.setOnItemClickListener { adapter, view, position ->

            val historyTable = dataCenter.get(position)
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText(historyTable.comment, historyTable.result)
            cm.primaryClip = mClipData
            toast("复制成功---> "+historyTable.result)
        }
        recyclerView_history.smoothScrollToPosition(dataCenter.size)

    }

    fun operatorOnClick(view: View) {
        val button = view as Button
        val message = button.text.toString()
        if (havedResult) {//如果之前有绩结果 则在之前的基础上进行运算
            equationStr = lastResult
            havedResult = false//新表达式开始 这个表达式还没有结果
        }

        equationStr += message
        tv_equation_panel.setText(equationStr)
    }

    fun equalOnClick(view: View) {
        val button = view as Button

        try {


            //计算表达式
            val expression = Expression(equationStr)

            var expressionResult = expression.eval()
                    .toPlainString()

            //添加 等号 和 结果
            val message = button.text.toString()
            equationStr += message + expressionResult
            tv_equation_panel.setText(equationStr)
            //计算状态重置和记录结果
            havedResult = true
            lastResult = expressionResult

//存入数据库
            realm.beginTransaction()
            val historyTable = HistoryTable(equationStr)
            val realmUser = realm.copyToRealm(historyTable)
            realm.commitTransaction()
            historyAdapter?.addData(historyTable)
            recyclerView_history.smoothScrollToPosition(dataCenter.size)
        } catch (e: Exception) {
            toast("输入的表达式有问题哦!")
            havedResult = false
        }

    }

    fun digitalOnClick(view: View) {
        val button = view as Button
        if (havedResult) equationStr = ""//新一轮计算 重置表达式
        havedResult = false //新一轮计算 重置

        val message = button.text.toString()
        equationStr += message
        tv_equation_panel.setText(equationStr)
    }

    fun delOnclick(view: View) {
        if (havedResult) equationStr = ""
        if (equationStr.length >= 1) equationStr = equationStr.substring(0, equationStr.length - 1)
        tv_equation_panel.setText(equationStr)
    }

    override fun onDestroy() {
        realm.close()
        super.onDestroy()

    }
}
