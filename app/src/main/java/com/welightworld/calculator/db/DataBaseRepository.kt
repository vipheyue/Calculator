package com.welightworld.calculator.db

import android.content.Context
import io.realm.Realm


class DataBaseRepository(mContext: Context) : IDataBaseSource {
    override fun deleItem(itemId: String): Boolean {
        //从数据库中删除
        realm.executeTransaction {
            var waitDealObj = realm.where(HistoryTable::class.java).equalTo("id", itemId).findFirst()
            waitDealObj?.deleteFromRealm()
        }

        return true

    }

    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"

    //可以 remote
    //可以 local
    override fun loadData(): List<HistoryTable> {
        val query = realm.where(HistoryTable::class.java).sort("time")
        val findAll = query.findAll()
                .toList()
        return findAll
    }

    override fun saveItem(item: String): HistoryTable {
        //存入数据库
        realm.beginTransaction()
        val historyTable = HistoryTable(item, System.currentTimeMillis())
        val realmUser = realm.copyToRealm(historyTable)
        realm.commitTransaction()
        return historyTable
    }

}