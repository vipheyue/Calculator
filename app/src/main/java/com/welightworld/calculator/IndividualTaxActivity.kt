package com.welightworld.calculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_individual_tax.*

class IndividualTaxActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_tax)
        initView()
    }

    /*
    *
    i = int(raw_input('净利润:'))
    arr = [1000000,600000,400000,200000,100000,0]
    rat = [0.01,0.015,0.03,0.05,0.075,0.1]
    r = 0
    for idx in range(0,6):
        if i>arr[idx]:
            r+=(i-arr[idx])*rat[idx]
            print (i-arr[idx])*rat[idx]
            i=arr[idx]
    print r

    * */
    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = getString(R.string.salary_tax)

        btn_calculate.setOnClickListener {
            var salary = 0
            var salaryTemp = 0
            if (editText.text.toString().isNotEmpty()) {
                salary = editText.text.toString().toInt()
                salaryTemp = salary
            } else {
                return@setOnClickListener
            }
            var other_welfare_str = et_other_welfare.text.toString()
            var other_welfare = 0
            if (other_welfare_str.isNotEmpty()) {
                other_welfare = other_welfare_str.toInt()
            }
            if (salary <= 3500) {
                println(tax_result.setText(getString(R.string.should_tax) + "0"))
                return@setOnClickListener
            }
            salary -= other_welfare
            salary -= 3500
            val section = arrayOf(80000, 55000, 35000, 9000, 4500, 1500, 0)
            val sectionRatio = arrayOf(0.45, 0.35, 0.3, 0.25, 0.2, 0.1, 0.03)
            var tax: Double = 0.0
            for (index in 0..6) {
                if (salary > section[index]) {
                    tax += (salary - section[index]) * sectionRatio[index]
                    salary = section[index]
                }
            }
            val handIncomSalary = salaryTemp - tax - other_welfare
            tax_result.setText(getString(R.string.should_tax) + tax.toString() + "\n" + getString(R.string.getHandSalary) + handIncomSalary)
        }

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
