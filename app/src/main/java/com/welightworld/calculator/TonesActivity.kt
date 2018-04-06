package com.welightworld.calculator

import android.content.Intent
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_tones.*

class TonesActivity : AppCompatActivity() {

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


        rbtn_no.setOnClickListener {
            configSoundTones=tones_state_mute
      }

        rbtn_ding.setOnCheckedChangeListener { compoundButton, b ->
            configSoundTones= tones_state_ding
            var mSoundPool = SoundPool(1, AudioManager.STREAM_MUSIC, 0);
            var piano_1 = mSoundPool.load(this, R.raw.dingding, 1)
            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)

        }
//        rbtn_ding.setOnClickListener {
//            configSoundTones= tones_state_ding
//            var mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
//            var piano_1 = mSoundPool.load(this, R.raw.dingding, 1)
//            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)
//        }
        rbtn_mo.setOnClickListener {
            configSoundTones= tones_state_mo
            var mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            var piano_1 = mSoundPool.load(this, R.raw.mo, 1)
            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)

        }
        rbtn_tata.setOnClickListener {
            configSoundTones= tones_state_tata
            var mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            var piano_1 = mSoundPool.load(this, R.raw.tata, 1)
            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)

        }
        rbtn_piano_9.setOnClickListener {
            configSoundTones= tones_state_piano9
            var mSoundPool = SoundPool(10, AudioManager.STREAM_MUSIC, 0);
            var piano_1 = mSoundPool.load(this, R.raw.piano_9, 1)
            mSoundPool.play(1, 1f, 1f, 0, 0, 1f)

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        return super.onSupportNavigateUp()
    }
}
