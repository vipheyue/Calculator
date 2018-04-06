package com.welightworld.calculator.calc

import com.udojava.evalex.Expression
import com.welightworld.calculator.R
import com.welightworld.calculator.db.IDataBaseSource

/**
 * Created by heyue on 2018/3/31.
 */
class UniversalPresent(var dataRepository: IDataBaseSource, val universalView: UniversalContract.View) : UniversalContract.Presenter {

    init {
        universalView.presenter = this
    }

    var waitCalSB: StringBuffer = StringBuffer()
    var havedResult: Boolean = false
    var lastResult: String = ""

    override fun start() {
        universalView.changeBg()
        universalView.loadData(dataRepository.loadData())
    }

    override fun inputDigital(digitalNum: String) {
        if (havedResult) {
            waitCalSB.setLength(0)
            havedResult = false //新一轮计算 重置
        }
        waitCalSB.append(digitalNum)
        universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)
    }

    override fun inputOperator(operator: String) {
        if (havedResult) {//如果之前有绩结果 则在之前的基础上进行运算
            waitCalSB.setLength(0)//清空之前的表达式
            waitCalSB.append(lastResult)
            havedResult = false//新表达式开始 这个表达式还没有结果
        }
        waitCalSB.append(operator)
        universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)
    }

    override fun delOneInput() {
        if (havedResult) {//如果有结果
            clearInput()
            return
        }
        if (waitCalSB.isNotEmpty()) {
            waitCalSB.deleteCharAt(waitCalSB.length - 1)
            universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)
        }
    }

    override fun clearInput() {
        waitCalSB.setLength(0)
        havedResult = false
        universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)

    }

    override fun calcResult() {
        try {
            if (havedResult) return
            val expression = Expression(waitCalSB.toString())
            lastResult = expression.eval().toPlainString()
            waitCalSB.append("=")
            waitCalSB.append(lastResult)
            havedResult = true
            universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)
            //TODO InputPlane清空 recyclerView 最后一个字号变大 最好加一个动画
            var saveItem = dataRepository.saveItem(waitCalSB.toString())
            //通知界面变更
            universalView.addItem(saveItem)
        } catch (e: Exception) {
            universalView.toastMsg(R.string.toast_calculate_problem)
            havedResult = false
            universalView.setInputPlaneValue(waitCalSB.toString(),havedResult)
        }
    }

    override fun copyResult() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun removeItem() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}