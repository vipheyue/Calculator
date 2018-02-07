package com.welightworld.calculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.method.ScrollingMovementMethod
import com.udojava.evalex.Expression
import kotlinx.android.synthetic.main.activity_universal_expression.*
import org.jetbrains.anko.toast

class UniversalExpressionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_universal_expression)
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = getString(R.string.universal_expression)
        initView()
    }

    private fun initView() {
        tv_introduce.setMovementMethod(ScrollingMovementMethod.getInstance());
        btn_digital_parenthesis_left.setOnClickListener {
            editText_input.setText(editText_input.text.toString().trim()+"(")
            editText_input.setSelection(editText_input.length());
        }
        btn_digital_parenthesis_right.setOnClickListener {
            editText_input.setText(editText_input.text.toString().trim()+")")
            editText_input.setSelection(editText_input.length());

        }
        button_clear.setOnClickListener {
            editText_input.setText("")
            tv_result.setText("")
        }
        button_calculate.setOnClickListener {
            try {
                var inputString = editText_input.text.toString().trim()
                val expression = Expression(inputString)
                val result = expression.eval().toPlainString()
                tv_result.setText(inputString + "=" + result)
            } catch (e: Exception) {
                toast(getString(R.string.toast_calculate_problem))
            }

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
