package com.professionalandroid.apps.flo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object{
        var MESSAGE_KEY:String = "TRUE"
        const val TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT"
    }

    private val mMediaReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if(intent.action == AudioManager.ACTION_AUDIO_BECOMING_NOISY){
                play.isSelected = false
                val intent1 = Intent(context, MyService::class.java)

                intent1.putExtra(MESSAGE_KEY, false)
                startService(intent1)
                Log.d("test", "receiver")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var mslideviewpager:SlideViewPager? = null

        val fm = supportFragmentManager

        if(savedInstanceState == null){
            mslideviewpager = SlideViewPager()
            fm.beginTransaction().replace(R.id.viewpager, mslideviewpager, TAG_LIST_FRAGMENT).commitNow()

        }
        else{
            mslideviewpager = fm.findFragmentByTag(TAG_LIST_FRAGMENT) as SlideViewPager
        }

        // 오디오소리 감지 리시버
        val intentfilter = IntentFilter()
        intentfilter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY)
        registerReceiver(mMediaReceiver, intentfilter)


        // 플레이버튼.
        Log.d("test", "create")

    }

    override fun onStart() {
        super.onStart()
        Log.d("test", "start")
    }

    override fun onResume() {
        super.onResume()

        val intent = Intent(this, MyService::class.java)

        // 버튼 클릭시 음악 재생, 일시정지
        play.setOnClickListener {
            it.isSelected = !it.isSelected
            Log.d("test", "button")

            if(it.isSelected){
                intent.putExtra(MESSAGE_KEY, true)
        }
            else{
                intent.putExtra(MESSAGE_KEY, false)
            }
            startService(intent)
        }

        Log.d("test", "resume")
    }

    override fun onPause() {
        super.onPause()


        Log.d("test", "pause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("test", "stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // BroadcastReceiver 해제
        unregisterReceiver(mMediaReceiver)
        Log.d("test", "destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("test", "restart")
    }
}