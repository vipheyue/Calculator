package com.welightworld.calculator

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tones.*

class TonesActivity : AppCompatActivity() {
    lateinit var mSoundPool: SoundPool

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tones)
        initView()

    }

    private fun initView() {

        val mActionBar = supportActionBar
        mActionBar!!.setHomeButtonEnabled(true)
        mActionBar.setDisplayHomeAsUpEnabled(true)
        mActionBar.title = "音效"
        mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        var tones_1 = mSoundPool.load(this, R.raw.dingding, 1)
        var tones_2 = mSoundPool.load(this, R.raw.mo, 1)
        var tones_3 = mSoundPool.load(this, R.raw.tata, 1)
        var tones_4 = mSoundPool.load(this, R.raw.piano_9, 1)

        rbtn_no.setOnClickListener {
            configSoundTones=tones_state_mute
      }

        rbtn_ding.setOnClickListener {
            configSoundTones= tones_state_ding
            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)
        }
        rbtn_mo.setOnClickListener {
            configSoundTones= tones_state_mo
            mSoundPool.play(2, 1f, 1f, 0, 0, 1f)

        }
        rbtn_tata.setOnClickListener {
            configSoundTones= tones_state_tata
            mSoundPool.play(3, 1f, 1f, 0, 0, 1f)

        }
        rbtn_piano_9.setOnClickListener {
            configSoundTones= tones_state_piano9
            mSoundPool.play(4, 1f, 1f, 0, 0, 1f)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }
    override fun onDestroy() {
        mSoundPool.release()
        super.onDestroy()
    }
}
