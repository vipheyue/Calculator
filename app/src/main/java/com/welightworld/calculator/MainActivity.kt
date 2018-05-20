package com.welightworld.calculator

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Canvas
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.Button
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback
import com.chad.library.adapter.base.listener.OnItemDragListener
import com.chad.library.adapter.base.listener.OnItemSwipeListener
import com.udojava.evalex.Expression
import io.realm.Realm
import kotlinx.android.synthetic.main.app_bar_drawer.*
import kotlinx.android.synthetic.main.content_drawer.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity() {
    var waitCalculateStr: String = ""
    var havedResult: Boolean = false
    var lastResult: String = ""
    var dataCenter = ArrayList<HistoryTable>()
    val realm = Realm.getDefaultInstance() // opens "myrealm.realm"
    lateinit var historyAdapter: HistoryAdapter
    lateinit var mSoundPool: SoundPool
    private var mItemTouchHelper: ItemTouchHelper? = null
    private var mItemDragAndSwipeCallback: ItemDragAndSwipeCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        initView()

    }

    private fun initView() {
        mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        var piano_1 = mSoundPool.load(this, R.raw.piano_1, 1)
        var piano_2 = mSoundPool.load(this, R.raw.piano_2, 1)
        var piano_3 = mSoundPool.load(this, R.raw.piano_3, 1)
        var piano_4 = mSoundPool.load(this, R.raw.piano_4, 1)
        var piano_5 = mSoundPool.load(this, R.raw.piano_5, 1)
        var piano_6 = mSoundPool.load(this, R.raw.piano_6, 1)
        var piano_7 = mSoundPool.load(this, R.raw.piano_7, 1)
        var piano_8 = mSoundPool.load(this, R.raw.piano_8, 1)
        var piano_9 = mSoundPool.load(this, R.raw.piano_9, 1)
        var piano_10 = mSoundPool.load(this, R.raw.piano_0, 1)
        var piano_c = mSoundPool.load(this, R.raw.piano_c, 1)
        var changeBg = ChangeBg()
        changeBg.change(btn_operator_div, this)
        changeBg.change(btn_operator_plus, this)
        changeBg.change(btn_operator_sub, this)
        changeBg.change(btn_operator_add, this)
        changeBg.change(btn_operator_equal, this)
        btn_digital_del.setOnLongClickListener {
            waitCalculateStr = ""
            tv_equation_panel.setText(waitCalculateStr)
            havedResult = false
            true
        }
        fab.setOnClickListener { view ->
            //            drawer_layout.openDrawer(GravityCompat.START)
            startActivity<CategoryActivity>()
            finish()
        }
        recyclerView_history.setLayoutManager(LinearLayoutManager(this))
        val query = realm.where(HistoryTable::class.java)
        val findAll = query.findAll().toList()
        dataCenter.addAll(findAll)
        historyAdapter = HistoryAdapter(R.layout.history_item, dataCenter)
        mItemDragAndSwipeCallback = ItemDragAndSwipeCallback(historyAdapter)
        mItemTouchHelper = ItemTouchHelper(mItemDragAndSwipeCallback)
        mItemTouchHelper!!.attachToRecyclerView(recyclerView_history)
        mItemDragAndSwipeCallback!!.setSwipeMoveFlags(ItemTouchHelper.START or ItemTouchHelper.END)
        historyAdapter.enableSwipeItem()
        historyAdapter.setOnItemSwipeListener(onItemSwipeListener)
        historyAdapter.enableDragItem(mItemTouchHelper!!)
        historyAdapter.setOnItemDragListener(listener)

        recyclerView_history.adapter = historyAdapter
        historyAdapter.setOnItemClickListener { adapter, view, position ->
            val historyTable = dataCenter.get(position)
            val cm = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText(historyTable.comment, historyTable.result)
            cm.primaryClip = mClipData
            toast(getString(R.string.copy_success) + historyTable.result)
        }

        recyclerView_history.smoothScrollToPosition(dataCenter.size)
    }

    fun operatorOnClick(view: View) {
        val symbol = (view as Button).text.toString()
        if (havedResult) {//如果之前有绩结果 则在之前的基础上进行运算
            waitCalculateStr = lastResult
            havedResult = false//新表达式开始 这个表达式还没有结果
        }
        waitCalculateStr += symbol
        tv_equation_panel.setText(waitCalculateStr)
    }

    fun equalOnClick(view: View) {
        val symbol = (view as Button).text.toString()
        if (havedResult) return
        try {
            //计算表达式
            val expression = Expression(waitCalculateStr)
            val result = expression.eval().toPlainString()
            //添加 等号 和 结果
            waitCalculateStr += symbol + result
            tv_equation_panel.setText(waitCalculateStr)
            //计算状态重置和记录结果
            havedResult = true
            lastResult = result
            //存入数据库
            realm.beginTransaction()
            val historyTable = HistoryTable(waitCalculateStr)
            val realmUser = realm.copyToRealm(historyTable)
            realm.commitTransaction()
            historyAdapter.addData(historyTable)
            recyclerView_history.smoothScrollToPosition(dataCenter.size)
        } catch (e: Exception) {
            toast(getString(R.string.toast_calculate_problem))
            havedResult = false
        }
    }

    fun digitalOnClick(view: View) {
        val symbol = (view as Button).text.toString()
        if (havedResult) waitCalculateStr = ""//新一轮计算 重置表达式
        havedResult = false //新一轮计算 重置
        waitCalculateStr += symbol
        tv_equation_panel.setText(waitCalculateStr)

        createNewSoundPool(symbol)

    }

    private fun createNewSoundPool(symbol: String) {
        if (configOpenSound && symbol.toCharArray()[0].isDigit()) {
            mSoundPool?.play(symbol.toCharArray()[0].toString().toInt(), 1f, 1f, 0, 0, 1f)
        }
    }


    fun delOnclick(view: View) {
        if (havedResult) waitCalculateStr = ""
        havedResult = false
        if (waitCalculateStr.isNotEmpty()) waitCalculateStr = waitCalculateStr.substring(0, waitCalculateStr.length - 1)
        tv_equation_panel.setText(waitCalculateStr)
    }


    override fun onResume() {
        super.onResume()
        ClipboardManagerHelper.discernSymbol(this)
    }

    override fun onDestroy() {
        realm.close()
        mSoundPool.release()
        super.onDestroy()
    }


    val onItemSwipeListener = object : OnItemSwipeListener {
        override fun onItemSwipeStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            val holder = viewHolder as BaseViewHolder
            //                holder.setTextColor(R.id.tv, Color.WHITE);
        }

        override fun clearView(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            val holder = viewHolder as BaseViewHolder
            //                holder.setTextColor(R.id.tv, Color.BLACK);
        }

        override fun onItemSwiped(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            var waitDealObj = realm.where(HistoryTable::class.java).equalTo("result", dataCenter.get(pos).result).findFirst()
            realm.executeTransaction {
                waitDealObj?.deleteFromRealm()
            }
            historyAdapter.remove(pos)
            recyclerView_history.smoothScrollToPosition(dataCenter.size)

        }

        override fun onItemSwipeMoving(canvas: Canvas, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, isCurrentlyActive: Boolean) {
            //                canvas.drawText("Just some text", 0, 40, paint);
        }
    }

    val listener = object : OnItemDragListener {
        override fun onItemDragStart(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            val holder = viewHolder as BaseViewHolder
            //                holder.setTextColor(R.id.tv, Color.WHITE);
        }

        override fun onItemDragMoving(source: RecyclerView.ViewHolder, from: Int, target: RecyclerView.ViewHolder, to: Int) {
        }

        override fun onItemDragEnd(viewHolder: RecyclerView.ViewHolder, pos: Int) {
            val holder = viewHolder as BaseViewHolder
            //                holder.setTextColor(R.id.tv, Color.BLACK);
        }
    }
}
