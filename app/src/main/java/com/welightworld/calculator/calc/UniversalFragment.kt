package com.welightworld.calculator.calc


import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.welightworld.calculator.ChangeBg
import com.welightworld.calculator.ClipboardManagerHelper
import com.welightworld.calculator.HistoryAdapter
import com.welightworld.calculator.R
import com.welightworld.calculator.db.HistoryTable
import kotlinx.android.synthetic.main.content_drawer.*
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 */
class UniversalFragment : Fragment(), UniversalContract.View {
    override lateinit var presenter: UniversalContract.Presenter
    lateinit var historyAdapter: HistoryAdapter

    override fun addItem(table: HistoryTable) {
        historyAdapter.addData(table)
        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)
    }

    override fun removeItem(table: HistoryTable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

        btn_digital_del.setOnLongClickListener {
            presenter.clearInput()
            return@setOnLongClickListener true
        }
        var digitalClickListener: View.OnClickListener = View.OnClickListener { view ->
            val symbol = (view as Button).text.toString()
            presenter.inputDigital(symbol)
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
            val cm = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText(historyTable?.comment, historyTable?.result)
            cm.primaryClip = mClipData
            activity!!.toast(getString(R.string.copy_success) + historyTable?.result)
        }

        recyclerView_history.smoothScrollToPosition(historyAdapter.data.size)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
        ClipboardManagerHelper.discernSymbol(activity as Context)
    }

    override fun onDestroy() {
//        mSoundPool.release()
        super.onDestroy()
    }

}// Required empty public constructor
