package com.vipheyue.calculator

import android.content.Context
import android.graphics.Color
import com.flask.colorpicker.ColorPickerView
import com.flask.colorpicker.builder.ColorPickerDialogBuilder
import org.jetbrains.anko.toast

/**
 * Created by heyue on 2018/1/17.
 */
class ColorPicker {
    public fun pick( mContext: Context) {
            ColorPickerDialogBuilder
                    .with(mContext)
                    .setTitle("设置主页背景")
                    .initialColor(Color.parseColor("#FFFFFF"))
                    .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                    .density(12)
//                    .setOnColorSelectedListener { selectedColor -> toast("onColorSelected: 0x" + Integer.toHexString(selectedColor)) }
                    .setPositiveButton("ok") { dialog, selectedColor, allColors ->
                        mContext.toast("设置成功,下次启动 APP 时生效")
                        configOperatorBgColor = selectedColor
                    }
                    .setNegativeButton("cancel") { dialog, which -> }
                    .build()
                    .show()
    }


}