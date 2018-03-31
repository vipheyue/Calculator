package com.welightworld.calculator.calc


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.welightworld.calculator.R


/**
 * A simple [Fragment] subclass.
 */
class UniversalFragment : Fragment(),UniversalContract.View {
    override lateinit var presenter: UniversalContract.Presenter



    override fun toastMsg(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showSuccessDialog() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_drawer, container, false)
    }

}// Required empty public constructor
