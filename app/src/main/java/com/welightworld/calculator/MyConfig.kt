package com.welightworld.calculator

import android.graphics.Color

/**
 * Created by heyue on 2017/12/25.
 */
var configOperatorBgColor by Preference(MyApplication.get(), "configOperatorBgColor", Color.parseColor("#ee1e86"))
var configOpenSound by Preference(MyApplication.get(), "configOpenSound", true)

