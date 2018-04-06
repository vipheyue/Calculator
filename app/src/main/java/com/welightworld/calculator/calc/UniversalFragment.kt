package com.welightworld.calculator.calc


import android.content.ClipboardManager
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.welightworld.calculator.*
import com.welightworld.calculator.db.HistoryTable
import kotlinx.android.synthetic.main.content_drawer.*
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 */
class UniversalFragment : Fragment(), UniversalContract.View {
    override lateinit var presenter: UniversalContract.Presenter
    lateinit var historyAdapter: HistoryAdapter
    lateinit var mSoundPool: SoundPool

    override fun addItem(table: HistoryTable) {
        historyAdapter.addData(table)
        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)
    }

    override fun removeItem(position: Int) {
        historyAdapter.remove(position)
        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)
    }

    override fun loadData(mData: List<HistoryTable>) {
        historyAdapter.replaceData(mData)
        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)
    }


    override fun changeBg() {
        var changeBg = ChangeBg()
        changeBg.change(btn_operator_div, this!!.activity!!)
        changeBg.change(btn_operator_plus, this!!.activity!!)
        changeBg.change(btn_operator_sub, this!!.activity!!)
        changeBg.change(btn_operator_add, this!!.activity!!)
        changeBg.change(btn_operator_equal, this!!.activity!!)
    }

    override fun setInputPlaneValue(mContent: String, complish: Boolean) {
        tv_equation_panel.text = mContent
        if (complish) {
            btn_digital_del.text = getString(R.string.digital_clear)
        } else {
            btn_digital_del.text = getString(R.string.digital_del)
        }
    }


    override fun toastMsg(msgId: Int) {
        activity?.toast(getString(msgId))
    }

    override fun showSuccessDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.content_drawer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        tv_equation_panel.setOnClickListener {
            activity?.let { it1 -> ClipboardManagerHelper.copy2Clipboard(it1, tv_equation_panel.text.toString()) }
        }
        tv_equation_panel.setOnLongClickListener {
            if (tv_equation_panel.text.toString().isEmpty()) {
                //获取 剪切板
                val cm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val copyText = cm.primaryClip?.getItemAt(0)?.text
                tv_equation_panel.setText(copyText)
                presenter.pasteExpress(copyText.toString())
            } else {
                activity?.let { it1 -> ClipboardManagerHelper.copy2Clipboard(it1, tv_equation_panel.text.toString()) }
            }

            return@setOnLongClickListener true
        }
        btn_digital_del.setOnLongClickListener {
            presenter.clearInput()
            return@setOnLongClickListener true
        }
        var digitalClickListener: View.OnClickListener = View.OnClickListener { view ->
            val symbol = (view as Button).text.toString()
            presenter.inputDigital(symbol)
            createNewSoundPool(symbol)
            return@OnClickListener
//        createNewSoundPool(symbol)
        }
        btn_digital_1.setOnClickListener(digitalClickListener)
        btn_digital_2.setOnClickListener(digitalClickListener)
        btn_digital_3.setOnClickListener(digitalClickListener)
        btn_digital_4.setOnClickListener(digitalClickListener)
        btn_digital_5.setOnClickListener(digitalClickListener)
        btn_digital_6.setOnClickListener(digitalClickListener)
        btn_digital_7.setOnClickListener(digitalClickListener)
        btn_digital_8.setOnClickListener(digitalClickListener)
        btn_digital_9.setOnClickListener(digitalClickListener)
        btn_digital_0.setOnClickListener(digitalClickListener)
        btn_digital_point.setOnClickListener(digitalClickListener)
        btn_digital_parenthesis_left.setOnClickListener(digitalClickListener)
        btn_digital_parenthesis_right.setOnClickListener(digitalClickListener)

        btn_digital_del.setOnClickListener {
            presenter.delOneInput()
            return@setOnClickListener
        }


        var operatorClickListener: View.OnClickListener = View.OnClickListener { view ->
            val symbol = (view as Button).text.toString()
            presenter.inputOperator(symbol)
            return@OnClickListener
        }

        btn_operator_div.setOnClickListener(operatorClickListener)
        btn_operator_plus.setOnClickListener(operatorClickListener)
        btn_operator_sub.setOnClickListener(operatorClickListener)
        btn_operator_add.setOnClickListener(operatorClickListener)

        btn_operator_equal.setOnClickListener {
            presenter.calcResult()
            return@setOnClickListener
        }

        recyclerView_history.setLayoutManager(LinearLayoutManager(activity))
        historyAdapter = HistoryAdapter(R.layout.history_item, null)
        recyclerView_history.adapter = historyAdapter

        historyAdapter.setOnItemClickListener { adapter, view, position ->
            val historyTable = historyAdapter.getItem(position)
            historyTable?.result?.let { activity?.let { it1 -> ClipboardManagerHelper.copy2Clipboard(it1, it) } }
        }


        historyAdapter.setOnItemLongClickListener { adapter, view, position ->


            var itemData = historyAdapter.data.get(position)
            var dataId: String = itemData.id
            activity?.let { it1 -> ClipboardManagerHelper.copy2Clipboard(it1, itemData.result) }
            presenter.removeItem(dataId, position)
            true
        }

        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)

        mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        var piano_1 = mSoundPool.load(activity, R.raw.piano_1, 1)
//        var piano_2 = mSoundPool.load(activity, R.raw.piano_2, 1)
//        var piano_3 = mSoundPool.load(activity, R.raw.piano_3, 1)
//        var piano_4 = mSoundPool.load(activity, R.raw.piano_4, 1)
//        var piano_5 = mSoundPool.load(activity, R.raw.piano_5, 1)
//        var piano_6 = mSoundPool.load(activity, R.raw.piano_6, 1)
//        var piano_7 = mSoundPool.load(activity, R.raw.piano_7, 1)
//        var piano_8 = mSoundPool.load(activity, R.raw.piano_8, 1)
//        var piano_9 = mSoundPool.load(activity, R.raw.piano_9, 1)
//        var piano_10 = mSoundPool.load(activity, R.raw.piano_0, 1)
//        var piano_c = mSoundPool.load(activity, R.raw.piano_c, 1)
    }

    private fun createNewSoundPool(symbol: String) {
        if (configOpenSound && symbol.toCharArray()[0].isDigit()) {
            mSoundPool.play(symbol.toCharArray()[0].toString().toInt(), 1f, 1f, 0, 0, 1f)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        ClipboardManagerHelper.discernSymbol(activity as Context)
    }

    override fun onDestroy() {
        mSoundPool.release()
        super.onDestroy()
    }

}// Required empty public constructor
