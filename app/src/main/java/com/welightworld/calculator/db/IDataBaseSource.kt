package com.welightworld.calculator.db

interface IDataBaseSource {
    fun loadData(): List<HistoryTable>
    fun saveItem(item: String): HistoryTable
}