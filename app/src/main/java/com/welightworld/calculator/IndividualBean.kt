package com.welightworld.calculator


data class IndividualBean(var incomeMoney: Float = 0F) {
    var default_tax_value: Int = 3500

    var gjj_ratio: Float = 0F
    fun getGjj_value(): Float {
        return incomeMoney * gjj_ratio
    }

    var yl_ratio: Float = 0F
    fun getYl_value(): Float {
        return incomeMoney * yl_ratio
    }

    var ylao_ratio: Float = 0F
    fun getYlao_value(): Float {
        return incomeMoney * ylao_ratio
    }

    var sy_ratio: Float = 0F
    fun getSy_value(): Float {
        return incomeMoney * sy_ratio
    }

    var gs_ratio: Float = 0F
    fun getGs_value(): Float {
        return incomeMoney * gs_ratio
    }

    var syu_ratio: Float = 0F
    fun getSyun_value(): Float {
        return incomeMoney * syu_ratio
    }

    fun getTax_value(): Float {
        if (incomeMoney <= 3500) {
            return 0F
        }
        var salary: Float = incomeMoney
        salary -= getWelfare()//去掉福利后剩余金额
        salary -= default_tax_value
        val section = arrayOf(80000F, 55000F, 35000F, 9000F, 4500F, 1500F, 0F)
        val sectionRatio = arrayOf(0.45, 0.35, 0.3, 0.25, 0.2, 0.1, 0.03)
        var tax: Double = 0.0
        for (index in 0..6) {
            if (salary > section[index]) {
                tax += (salary - section[index]) * sectionRatio[index]
                salary = section[index]
            }
        }
        return tax.toFloat()
    }

    fun getWelfare(): Float {
        var value = getGjj_value() + getYl_value() + getYlao_value() + getSy_value() + getGs_value() + getSyun_value()
        return value
    }

    fun getRemain_value(): Float {
        return incomeMoney - getWelfare() - getTax_value()
    }

}