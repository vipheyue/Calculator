package com.welightworld.calculator

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder


class HistoryAdapter(layoutResId: Int, data: List<HistoryTable>?) : BaseQuickAdapter<HistoryTable, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder, item: HistoryTable) {

        if (item.comment.isNotEmpty()) {
            helper.setText(R.id.tv_history, item.comment +" : "+ item.result)
        }else{
            helper.setText(R.id.tv_history, item.comment+ item.result)
        }
//        helper.setOnClickListener(R.id.tv_history, view-> { xxx })
//        helper.setOnClickListener(R.id.tv_history) {
//            Log.i("xxxx",item.result)
//
//        }
    }
}
