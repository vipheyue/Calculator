package com.welightworld.calculator

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_individual.*

class IndividualActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual)
        initView()
    }

    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = getString(R.string.salary_tax)

        editText_salary.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) {
                    cal(s.toString().toFloat())

                }

            }
        })

    }

    private fun cal(incomeMoney: Float) {
        //公积金
        var individualBean = IndividualBean(incomeMoney)
        individualBean.gjj_ratio = et_gjj_ratio.text.toString().toFloat()/100
        tv_gjj_value.setText(individualBean.getGjj_value().toString())

        individualBean.yl_ratio = et_yl_ratio.text.toString().toFloat()/100
        tv_yl_value.setText(individualBean.getYl_value().toString())

        individualBean.ylao_ratio = et_ylao_ratio.text.toString().toFloat()/100
        tv_ylao_value.setText(individualBean.getYlao_value().toString())

        individualBean.sy_ratio = et_sy_ratio.text.toString().toFloat()/100
        tv_sy_value.setText(individualBean.getSy_value().toString())

        individualBean.gs_ratio = et_gs_ratio.text.toString().toFloat()/100
        tv_gs_value.setText(individualBean.getGs_value().toString())

        individualBean.syu_ratio = et_syu_ratio.text.toString().toFloat()/100
        tv_syu_value.setText(individualBean.getSyun_value().toString())


        tv_person_tax_value.setText(individualBean.getTax_value().toString())
        tv_remain_value.setText(individualBean.getRemain_value().toString())



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
