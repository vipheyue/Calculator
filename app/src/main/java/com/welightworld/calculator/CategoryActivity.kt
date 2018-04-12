package com.welightworld.calculator

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {
    var dataCenter = ArrayList<CategoryBean>()
    var categoryAdapter: CategoryAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        initView()
    }

    private fun initView() {
        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = getString(R.string.app_name)

//        dataCenter.add(CategoryBean(CalculatorEnum.NORMAL,getString(R.string.normal_cal), MainActivity::class.java.name))
//        dataCenter.add(CategoryBean(CalculatorEnum.HOUSE, getString(R.string.house_debt_cal), HouseTaxActivity::class.java.name))
        dataCenter.add(CategoryBean(CalculatorEnum.SALARY, getString(R.string.salary_tax), IndividualActivity::class.java.name))
//        dataCenter.add(CategoryBean(CalculatorEnum.YEARBONUS, getString(R.string.year_end_bonus), YearBonusActivity::class.java.name))
//        dataCenter.add(CategoryBean(CalculatorEnum.UNIVERSAL, getString(R.string.universal_expression), UniversalExpressionActivity::class.java.name))
        dataCenter.add(CategoryBean(CalculatorEnum.CAPITAL, getString(R.string.Capital_RMB), TraditionalActivity::class.java.name))
        dataCenter.add(CategoryBean(CalculatorEnum.SKIN, getString(R.string.skin), "", true))
//        dataCenter.add(CategoryBean(CalculatorEnum.SOUND, getString(R.string.open_close_sound), "", true))
        dataCenter.add(CategoryBean(CalculatorEnum.SOUND, getString(R.string.open_close_sound), TonesActivity::class.java.name))


        dataCenter.add(CategoryBean(CalculatorEnum.CLEAN, getString(R.string.clean_History), "", true))
        dataCenter.add(CategoryBean(CalculatorEnum.SHARE, getString(R.string.share), "", true))
        dataCenter.add(CategoryBean(CalculatorEnum.FEEDBACK, getString(R.string.feedback), "", true))
        dataCenter.add(CategoryBean(CalculatorEnum.OPENSOURCE, getString(R.string.opensource), "", true))

        recyclerView_category.setLayoutManager(GridLayoutManager(this, 2))
        categoryAdapter = CategoryAdapter(this, R.layout.item_category, dataCenter)
        recyclerView_category.adapter = categoryAdapter
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
