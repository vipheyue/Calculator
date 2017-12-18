package com.vipheyue.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Button
import com.udojava.evalex.Expression
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    var equationStr: String = ""
    var havedResult: Boolean = false
    var lastResult: String = ""
    var dataCenter = ArrayList<ResultBean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        btn_digital_del.setOnLongClickListener {
            equationStr = ""
            tv_equation_panel.setText(equationStr)
            true
        }
        recyclerView_history.setLayoutManager(LinearLayoutManager(this))
        dataCenter.add(ResultBean("result","comment"))
        dataCenter.add(ResultBean("result22"))
        dataCenter.add(ResultBean("result22333"))
        var historyAdapter = HistoryAdapter(R.layout.history_item, dataCenter)

        recyclerView_history.adapter = historyAdapter
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


//            var calResult = Expression(equationStr).eval()
//            calResult = calResult.setScale(1, BigDecimal.ROUND_HALF_UP)

            //计算表达式
            val expression = Expression(equationStr)

            var expressionResult = expression.eval()
//                    .setScale(4, BigDecimal.ROUND_HALF_UP)
//                    .toDouble()
//                    .toString()
                    .toPlainString()

            //添加 等号 和 结果
            val message = button.text.toString()
            equationStr += message + expressionResult
            tv_equation_panel.setText(equationStr)
            //计算状态重置和记录结果
            havedResult = true
            lastResult = expressionResult
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
}
