package com.welightworld.calculator.db

import io.realm.DynamicRealm
import io.realm.RealmMigration


class MyMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        var oldVersion = oldVersion

        val schema = realm.schema
        if (oldVersion == 0L) {
            schema.get("HistoryTable")!!
                    .addField("time", Long::class.java)
            oldVersion++
        }
    }
}