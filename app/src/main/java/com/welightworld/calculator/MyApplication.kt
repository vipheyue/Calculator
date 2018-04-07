package com.welightworld.calculator

import android.app.Application
import com.alibaba.sdk.android.feedback.impl.FeedbackAPI
import com.tencent.bugly.Bugly
import io.realm.Realm
import io.realm.RealmConfiguration


/**
 * Created by heyue on 2017/12/19.
 */

class MyApplication : Application() {
    override fun onCreate() {
        INSTANCE = this
        super.onCreate()
        Realm.init(this)

        val config = RealmConfiguration.Builder().name("myrealmV2.realm").build()
        Realm.setDefaultConfiguration(config)


        Bugly.init(getApplicationContext(), "27bc8c201e", false);
        FeedbackAPI.init(this,"24843125","ea4da7caa2d8dbb362a3bcb79374ec84")

    }

    companion object {
        private lateinit var INSTANCE: MyApplication

        fun get(): MyApplication = INSTANCE
    }
}