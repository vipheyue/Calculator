package com.welightworld.calculator

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.welightworld.calculator.calc.UniversalFragment
import com.welightworld.calculator.calc.UniversalPresent
import com.welightworld.calculator.db.DataBaseRepository
import kotlinx.android.synthetic.main.activity_drawer.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        initView()

    }

    private fun initView() {
        //创建 MVP 关系

        var dataBaseRepository = DataBaseRepository()
        var universalFragment = UniversalFragment()

        var universalPresent = UniversalPresent(dataBaseRepository,universalFragment)

        supportFragmentManager.beginTransaction().replace(R.id.fl_coninter, universalFragment).commit()



        fab.setOnClickListener { view ->
            //            drawer_layout.openDrawer(GravityCompat.START)
            startActivity<CategoryActivity>()
            finish()
        }

 }






    override fun onDestroy() {
        super.onDestroy()
    }
}
