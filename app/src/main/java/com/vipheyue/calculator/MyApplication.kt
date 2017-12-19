package com.vipheyue.calculator

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration



/**
 * Created by heyue on 2017/12/19.
 */

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("myrealm.realm").build()
        Realm.setDefaultConfiguration(config)
    }
}