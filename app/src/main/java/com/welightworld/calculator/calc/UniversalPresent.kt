package com.welightworld.calculator.calc

/**
 * Created by heyue on 2018/3/31.
 */
class UniversalPresent(universalView: UniversalContract.View) : UniversalContract.Presenter {
    init {
        universalView.setPresenter(this)
    }
    override fun start() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun calcResult(express: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}