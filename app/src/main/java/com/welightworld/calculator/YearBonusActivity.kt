package com.welightworld.calculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_year_bonus.*

class YearBonusActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_year_bonus)
        initView()
    }

    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = getString(R.string.year_end_bonus)
        btn_calculate.setOnClickListener {

            var salary: Double = 0.0
            var salaryTemp: Double = 0.0
            var tax: Double = 0.0

            val section = arrayOf(80000, 55000, 35000, 9000, 4500, 1500, 0)
            val sectionRatio = arrayOf(0.45, 0.35, 0.3, 0.25, 0.2, 0.1, 0.03)
            val sectionNum = arrayOf(13500, 5505, 2755, 1005, 555, 105, 0)
            if (editText.text.toString().isNotEmpty()) {
                salary = editText.text.toString().toDouble()
                salaryTemp = editText.text.toString().toDouble()
            } else {
                return@setOnClickListener
            }
            for (index in 0..6) {
                if ((salary / 12) > section[index]) {
                    tax = salary * sectionRatio[index] - sectionNum[index]
                    break
                }
            }
            val handIncomSalary = salaryTemp - tax
            tax_result.setText(getString(R.string.should_tax) + tax.toInt().toString() + "元 \n" + getString(R.string.getHandSalary) + handIncomSalary+"元")

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
