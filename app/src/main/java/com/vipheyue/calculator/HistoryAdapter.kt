package com.vipheyue.calculator

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
class HistoryAdapter(layoutResId: Int, data: List<ResultBean>?): BaseQuickAdapter<ResultBean, BaseViewHolder>(layoutResId, data){

    override fun convert(helper: BaseViewHolder, item: ResultBean) {
        helper.setText(R.id.tv_history, item.comment + item.result)
    }
}
