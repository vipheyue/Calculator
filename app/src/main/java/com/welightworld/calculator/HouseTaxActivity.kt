package com.welightworld.calculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_house_tax.*
import org.jetbrains.anko.toast

class HouseTaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house_tax)
        initView()
    }

    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = "房贷计算器"
        btn_calculate.setOnClickListener {
            //[贷款本金×月利率×（1+月利率）^还款月数]÷[（1+月利率）^还款月数－1]
            var debtString = et_debt.text.toString()
            var debt_time = et_debt_time.text.toString()
            var debt_rate = et_debt_rate.text.toString()
            var debt_double = 0.0
            var debt_time_double = 0.0
            var debt_rate_double = 0.0
            if (debtString.isNotEmpty() && debt_time.isNotEmpty() && debt_rate.isNotEmpty()) {
                debt_double = debtString.toDouble() * 10000
                debt_time_double = debt_time.toDouble() * 12
                debt_rate_double = debt_rate.toDouble() / 12 / 100
            } else {
                toast("请填写各项数据")
                return@setOnClickListener
            }
            if (toggleButton.isChecked) {
                lixi(debt_rate_double, debt_time_double, debt_double)
            } else {
                benjin(debt_rate_double, debt_time_double, debt_double)

            }
        }
    }

    private fun benjin(debt_rate_double: Double, debt_time_double: Double, debt_double: Double) {
        //每月还款金额= （贷款本金/ 还款月数）+（本金 — 已归还本金累计额）×每月利率
        var monthPay = (debt_double / debt_time_double) + (debt_double - 0) * debt_rate_double
        var monthPayDesc = (monthPay / debt_time_double).toInt()
        tv_result.text = "每月应该付:" + monthPay.toInt().toString() + "元 \n" + "每月递减:" + monthPayDesc.toString() + "元 \n 一共:" + debt_time_double.toInt().toString() + "个月"
    }

    private fun lixi(debt_rate_double: Double, debt_time_double: Double, debt_double: Double) {
        var pow = Math.pow((1 + debt_rate_double), debt_time_double)
        var monthPay = debt_double * debt_rate_double * pow / (pow - 1)
        tv_result.text = "每月应该付:" + monthPay.toInt().toString() + "元 \n" + "一共:" + debt_time_double.toInt().toString() + "个月"
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
