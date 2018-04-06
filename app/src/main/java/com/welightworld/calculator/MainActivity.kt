package com.welightworld.calculator

import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.welightworld.calculator.calc.UniversalFragment
import com.welightworld.calculator.calc.UniversalPresent
import com.welightworld.calculator.db.DataBaseRepository
import kotlinx.android.synthetic.main.app_bar_drawer.*
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    lateinit var mSoundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)
        initView()

    }

    private fun initView() {
        //创建 MVP 关系

        var dataBaseRepository = DataBaseRepository(this)
        var universalFragment = UniversalFragment()

        var universalPresent = UniversalPresent(dataBaseRepository,universalFragment)

        supportFragmentManager.beginTransaction().replace(R.id.fl_coninter, universalFragment).commit()
        return

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

        fab.setOnClickListener { view ->
            //            drawer_layout.openDrawer(GravityCompat.START)
            startActivity<CategoryActivity>()
            finish()
        }

 }


    private fun createNewSoundPool(symbol: String) {
        if (configOpenSound && symbol.toCharArray()[0].isDigit()) {
            mSoundPool?.play(symbol.toCharArray()[0].toString().toInt(), 1f, 1f, 0, 0, 1f)
        }
    }



    override fun onDestroy() {
//        mSoundPool.release()
        super.onDestroy()
    }
}
