/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.welightworld.calculator.calc

import com.example.android.architecture.blueprints.todoapp.BasePresenter
import com.example.android.architecture.blueprints.todoapp.BaseView
import com.welightworld.calculator.db.HistoryTable


/**
 * This specifies the contract between the view and the presenter.
 */
interface UniversalContract {

    interface View : BaseView<Presenter> {

        fun setInputPlaneValue(mContent: String,complish:Boolean)

        fun toastMsg(msgId: Int)

        fun showSuccessDialog()

        fun changeBg()

        fun addItem(table: HistoryTable)

        fun removeItem(table: Int)

        fun loadData(mData: List<HistoryTable>)

    }

    interface Presenter : BasePresenter {

        fun inputDigital(digitalNum: String)
        fun inputOperator(operator: String)
        fun delOneInput()
        fun clearInput()
        fun calcResult()
        fun copyResult()
        fun removeItem(dataId: String, position: Int)
        fun pasteExpress(express:String)
    }
}
