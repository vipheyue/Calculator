package com.welightworld.calculator

import android.graphics.Color

/**
 * Created by heyue on 2017/12/25.
 */


    var tones_state_mute = 0
    var tones_state_ding = 1
    var tones_state_mo = 2
    var tones_state_tata = 3
    var tones_state_piano9 = 4
    var configOperatorBgColor by Preference(MyApplication.get(), "configOperatorBgColor", Color.parseColor("#ee1e86"))

    var configOpenSound by Preference(MyApplication.get(), "configOpenSound", true)

    var configSoundTones by Preference(MyApplication.get(), "configOpenSound", tones_state_mute)


