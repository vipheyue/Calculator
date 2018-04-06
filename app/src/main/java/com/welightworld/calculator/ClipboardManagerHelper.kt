package com.welightworld.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.udojava.evalex.Expression
import com.welightworld.calculator.db.HistoryTable
import io.realm.Realm
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import java.util.regex.Pattern

/**
 * Created by heyue on 2018/1/2.
 */
object ClipboardManagerHelper {
    /**
     * 检索剪切板 分割身份 进入跟踪页面
     * */
    fun discernSymbol(mContext: Context) {
        //获取 剪切板
        val cm = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val copyText = cm.primaryClip?.getItemAt(0)?.text
        copyText?.let {
            if (judgeContainsStr(it.toString())) {
                return
            }
            if (it.contains('+') || it.contains('-') || it.contains('*') || it.contains('/')) {
                //计算表达式
                try {
                    var waitCalculateStr = it.toString()
                    if (waitCalculateStr.contains("=")) {
                        val splitList = waitCalculateStr.split("=")
                        waitCalculateStr = splitList[0]
                    }
                    val expression = Expression(waitCalculateStr)
                    val result = expression.eval().toPlainString()

                    var messageResult = waitCalculateStr + "=" + result

                    //存入数据库
                    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
                    realm.beginTransaction()
                    val historyTable = HistoryTable(messageResult,System.currentTimeMillis())
                    val realmUser = realm.copyToRealm(historyTable)
                    realm.commitTransaction()

                    //弹框 (退出应用,留在应用)
                    mContext.alert(messageResult, "自动识计算结果") {
                        negativeButton("复制结果") {
                            val mClipData = ClipData.newPlainText("计算结果", messageResult)
                            cm.primaryClip = mClipData
                            mContext.toast("记算结果已自动复制到剪切板")
                        }
                        positiveButton("关闭") { }
                    }.show()

                } catch (e: Exception) {
                }


                //重置剪切板
                val mClipData = ClipData.newPlainText("", "")
                cm.primaryClip = mClipData
//                mContext.startActivity<TrackMapActivity>()
            }
        }
    }

    /**
     * 分割身份ID
     * */
    fun splitSymbol(copyText: CharSequence?): String {
        copyText?.let {
            if (it.contains('¥') && copyText.endsWith('¥')) {
                var split: List<String> = copyText.split('¥')
                return split[1]
            }
        }
        return copyText.toString()
    }

    fun judgeContainsStr(cardNum: String): Boolean {
        val regex = ".*[a-zA-Z]+.*"
        val m = Pattern.compile(regex).matcher(cardNum)
        return m.matches()
    }
}