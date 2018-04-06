package com.welightworld.calculator.db

import android.content.Context
import io.realm.Realm


class DataBaseRepository(mContext: Context) : IDataBaseSource {

    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"

    //可以 remote
    //可以 local
    override fun loadData(): List<HistoryTable> {
        val query = realm.where(HistoryTable::class.java)
        val findAll = query.findAll().toList()
        return findAll
    }

    override fun saveItem(item: String): HistoryTable {
        //存入数据库
        realm.beginTransaction()
        val historyTable = HistoryTable(item)
        val realmUser = realm.copyToRealm(historyTable)
        realm.commitTransaction()
        return historyTable
    }

}