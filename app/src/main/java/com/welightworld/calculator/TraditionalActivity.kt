package com.welightworld.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_traditional.*
import org.jetbrains.anko.toast

class TraditionalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traditional)
        initView()
    }

    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = "人民币大写"

        button_clear.setOnClickListener {
            editText_input.setText("")
            tv_result.setText("")
        }
        button_calculate.setOnClickListener {
            try {
                var inputString = editText_input.text.toString().trim()
                val result = NumToRMB.getRMB(inputString)
                tv_result.setText(result)
                val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val mClipData = ClipData.newPlainText("人民币", result)
                cm.primaryClip = mClipData
                toast("已经自动复制到剪切板")
            } catch (e: Exception) {
                toast("表达式有问题哦")
            }
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
