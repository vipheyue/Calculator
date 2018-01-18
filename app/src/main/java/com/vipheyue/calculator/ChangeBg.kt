package com.vipheyue.calculator

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.view.View

/**
 * Created by heyue on 2018/1/17.
 */

class ChangeBg {
    fun change(view: View, mContext: Context) {
        try {
            val mySelectorGrad = view.background as StateListDrawable
            val slDraClass = StateListDrawable::class.java
            val getStateCountMethod = slDraClass.getDeclaredMethod("getStateCount", *arrayOfNulls(0))
            val getStateSetMethod = slDraClass.getDeclaredMethod("getStateSet", Int::class.javaPrimitiveType)
            val getDrawableMethod = slDraClass.getDeclaredMethod("getStateDrawable", Int::class.javaPrimitiveType)
            val count = getStateCountMethod.invoke(mySelectorGrad, *arrayOf()) as Int//对应item标签
            for (i in 0 until count) {
                val drawable = getDrawableMethod.invoke(mySelectorGrad, i) as GradientDrawable//这就是你要获得的Enabled为false时候的drawable
                if (i == 1) {
                    drawable.setColor(Color.BLACK)
                } else {
                    drawable.setColor(configOperatorBgColor)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
