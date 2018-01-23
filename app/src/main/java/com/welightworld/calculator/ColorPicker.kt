package com.welightworld.calculator

import android.app.Activity
import android.graphics.Color
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import org.jetbrains.anko.startActivity

/**
 * Created by heyue on 2018/1/17.
 */
class ColorPicker {
     fun pick( mContext: Activity) {
            ColorPickerDialogBuilder
                    .with(mContext)
                    .setTitle("设置主页背景")
                    .initialColor(Color.parseColor("#FFFFFF"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
//                    .setOnColorSelectedListener { selectedColor -> toast("onColorSelected: 0x" + Integer.toHexString(selectedColor)) }
                    .setPositiveButton("ok") { dialog, selectedColor, allColors ->
                        configOperatorBgColor = selectedColor
                        mContext.startActivity<MainActivity>()
                        mContext.finish()
                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
    }


}