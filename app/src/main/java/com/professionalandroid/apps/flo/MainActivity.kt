package com.professionalandroid.apps.flo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    var mMyService: MyService? = null

    companion object{
        var MESSAGE_KEY:String = "TRUE"
        const val TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT"
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

        play.setOnClickListener {
            if(it.id == R.id.play){
                intent.putExtra(MESSAGE_KEY, true)
            }
            else{
                intent.putExtra(MESSAGE_KEY, false)
            }
            startService(intent)
        }

        play.setOnClickListener {
            play.isSelected = !play.isSelected
            Log.d("test", "button")
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
        Log.d("test", "destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("test", "restart")
    }
}