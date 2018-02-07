package com.welightworld.calculator

import android.app.Activity
import android.content.Intent
import android.widget.Button
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import io.realm.Realm
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class CategoryAdapter(layoutResId: Int, data: List<CategoryBean>?) : BaseQuickAdapter<CategoryBean, BaseViewHolder>(layoutResId, data) {


    override fun convert(helper: BaseViewHolder, item: CategoryBean) {
        var button = helper.getView<Button>(R.id.button)
        button.setText(item.name)
        if (item.directDeal) {//哈哈 偷懒了 应该用 mulAdapter
            //TODO 明天改一下 用 item 的值 来判断
            when (item.type) {
                CalculatorEnum.SKIN -> {
                    button.setOnClickListener {
                        var picker = ColorPicker()
                        picker.pick(button.context as Activity)
                    }
                }
                CalculatorEnum.SOUND -> {
                    button.setOnClickListener {
                        configOpenSound = !configOpenSound
                        button.context.toast(button.context.getString(R.string.set_success))
                        button.context.startActivity<MainActivity>()
                        var cateActivity = button.context as Activity
                        cateActivity.finish()
                    }
                }
                CalculatorEnum.CLEAN -> {
                    button.setOnClickListener {
                        val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
                        val results = realm.where(HistoryTable::class.java).findAll()
                        realm.executeTransaction {
                            results.deleteAllFromRealm()
                            button.context.startActivity<MainActivity>()
                            var cateActivity = button.context as Activity
                            cateActivity.finish()
                        }

                    }
                }
                CalculatorEnum.SHARE -> {
                    button.setOnClickListener {
                        var textIntent = Intent(Intent.ACTION_SEND)
                        textIntent.setType("text/plain");
                        textIntent.putExtra(Intent.EXTRA_TEXT, button.context.getString(R.string.share_content))
                        button.context.startActivity(Intent.createChooser(textIntent, button.context.getString(R.string.app_name)))
                    }
                }
                CalculatorEnum.FEEDBACK -> {
                    button.setOnClickListener {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/html"
                        intent.putExtra(Intent.EXTRA_EMAIL, button.context.getString(R.string.email))
                        intent.putExtra(Intent.EXTRA_SUBJECT, button.context.getString(R.string.feedback) + button.context.getString(R.string.app_name))
                        intent.putExtra(Intent.EXTRA_TEXT, button.context.getString(R.string.feedback))
                        button.context.startActivity(Intent.createChooser(intent, "Send Email"))
                        button.context.toast("请加入QQ群:469859289 email: " + button.context.getString(R.string.email))
                    }
                }

            }
            return
        }
        button.setOnClickListener {
            var intent = Intent()
            intent.setClassName(button.context, item.cls)
            button.context.startActivity(intent)
            var cateActivity = button.context as Activity
            cateActivity.finish()
        }

    }
}
