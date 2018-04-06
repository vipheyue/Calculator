package com.welightworld.calculator

import android.app.Activity
import android.content.Intent
import android.widget.Button
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.welightworld.calculator.db.HistoryTable
import io.realm.Realm
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class CategoryAdapter : BaseQuickAdapter<CategoryBean, BaseViewHolder> {
    var mActivity: Activity

    constructor(mActivity: Activity, layoutResId: Int, data: List<CategoryBean>?) : super(layoutResId, data) {
        this.mActivity = mActivity
    }

    override fun convert(helper: BaseViewHolder, item: CategoryBean) {
        var button = helper.getView<Button>(R.id.button)
        button.setText(item.name)
        if (item.directDeal) {//哈哈 偷懒了 应该用 mulAdapter
            //TODO 明天改一下 用 item 的值 来判断
            when (item.type) {
                CalculatorEnum.SKIN -> {
                    button.setOnClickListener {
                        var picker = ColorPicker()
                        picker.pick(mActivity)
                    }
                }
                CalculatorEnum.SOUND -> {
                    button.setOnClickListener {
                        configOpenSound = !configOpenSound
                        mActivity.toast(mActivity.getString(R.string.set_success))
                        mActivity.startActivity<MainActivity>()
                        var cateActivity = mActivity
                        cateActivity.finish()
                    }
                }
                CalculatorEnum.CLEAN -> {
                    button.setOnClickListener {
                        val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
                        val results = realm.where(HistoryTable::class.java).findAll()
                        realm.executeTransaction {
                            results.deleteAllFromRealm()
                            mActivity.startActivity<MainActivity>()
                            var cateActivity = mActivity
                            cateActivity.finish()
                        }

                    }
                }
                CalculatorEnum.SHARE -> {
                    button.setOnClickListener {
                        var textIntent = Intent(Intent.ACTION_SEND)
                        textIntent.setType("text/plain");
                        textIntent.putExtra(Intent.EXTRA_TEXT, mActivity.getString(R.string.share_content))
                        mActivity.startActivity(Intent.createChooser(textIntent, mActivity.getString(R.string.app_name)))
                    }
                }
                CalculatorEnum.FEEDBACK -> {
                    button.setOnClickListener {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/html"
                        intent.putExtra(Intent.EXTRA_EMAIL, mActivity.getString(R.string.email))
                        intent.putExtra(Intent.EXTRA_SUBJECT, mActivity.getString(R.string.feedback) + mActivity.getString(R.string.app_name))
                        intent.putExtra(Intent.EXTRA_TEXT, mActivity.getString(R.string.feedback))
                        mActivity.startActivity(Intent.createChooser(intent, "Send Email"))
                        mActivity.toast("请加入QQ群:469859289 email: " + mActivity.getString(R.string.email))
                    }
                }

            }
            return
        }
        button.setOnClickListener {
            var intent = Intent()
            intent.setClassName(mActivity, item.cls)
            mActivity.startActivity(intent)
            var cateActivity = mActivity
            cateActivity.finish()
        }

    }
}
