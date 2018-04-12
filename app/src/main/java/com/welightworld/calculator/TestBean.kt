package com.welightworld.calculator

class TestBean {
    var incomeMoney: Int = 0
    var gjj_ratio: Int = 0
    internal var gjj_value: Int = 0

    fun getGjj_value(): Int {
        return gjj_value + 1
    }

    fun setGjj_value(gjj_value: Int) {
        this.gjj_value = gjj_value
    }
}
