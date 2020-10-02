package com.professionalandroid.apps.flo

import android.content.*
import android.media.AudioManager
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    companion object{
        var MESSAGE_KEY:String = "TRUE"
        const val TAG_LIST_FRAGMENT = "TAG_LIST_FRAGMENT"
    }

    lateinit var myService: MyService
    private var mBound: Boolean = false
    private var isPlaying:Boolean = false
    private var saved_play_music = 0.toString()
    private var saved_play_music_int = 0


    // Bind Service 설정
    private val connection = object : ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(classname: ComponentName?, service: IBinder?) {
            val binder = service as MyService.LocalBinder
            myService = binder.getService()
            mBound = true
            myService.mMediaPlayer?.let {

            }
        }

    }

    // seek Bar가 움직일 Thread
    inner class MyThread: Thread() {
        override fun run() {
            while(isPlaying){
                seekBar.max = myService.mMediaPlayer!!.duration
                seekBar.progress = myService.mMediaPlayer!!.currentPosition
            }
        }
    }

//
//    class AudioDataViewModel : ViewModel() {
//        // String 타입의 MutableLiveData 생성, by lazy로 초기화는 뒤에
//        val textValue: MutableLiveData<Album> by lazy {
//            MutableLiveData<Album>()
//        }
//    }

    // 리시버 정의
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

//    private lateinit var model: AudioDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        // 로컬 DB에 대한 권한 체크와 요청
//        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions(this,arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), 1000)
//        }
//
//        lateinit var now_music:Album
//        // view모델 가져오기
//        model = ViewModelProvider(this).get(AudioDataViewModel::class.java)
//        val testObserver = Observer<Album>{ album_value ->
//            now_music = album_value
//        }
//        model.textValue.observe(this, testObserver)

        // fragment 연결
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

        saved_play_music = getSharedPreferences("test", Context.MODE_PRIVATE).getString("inputText","0")!!
        saved_play_music_int = saved_play_music.toInt()

        Log.d("test", saved_play_music)
        Log.d("test", "$saved_play_music_int")

        // 플레이버튼.
        Log.d("test", "create")

    }

    override fun onStart() {
        super.onStart()
        // 서비스 연결
        Intent(this,MyService::class.java).also{
            intent -> bindService(intent, connection, Context.BIND_AUTO_CREATE)
        }
        Log.d("test", "start")
    }

    override fun onResume() {
        super.onResume()

        // 동기...?
//        myService.now_song = saved_play_music_int
//        now_title.text = myService.titlelist[myService.now_song]
//        now_singer.text = myService.artistlist[myService.now_song]

        // 버튼 클릭시 음악 재생, 일시정지
        play.setOnClickListener {
            it.isSelected = !it.isSelected
            Log.d("test", "button")

            if(it.isSelected){
                myService.musicRestart()
                isPlaying = true
                val thread = MyThread()
                thread.start()
            }
            else{
                myService.musicPause()
                isPlaying = false
            }
        }

        // 다음 곡
        next.setOnClickListener {
            myService.musicNext()
            if(!play.isSelected) play.isSelected = true
            // 진행중인 음악 제목 가수 변경
            now_title.text = myService.titlelist[myService.now_song]
            now_singer.text = myService.artistlist[myService.now_song]
            //스레드 유지
            isPlaying = true
            Log.d("test", "music ${myService.musiclist[myService.now_song]}")

        }

        // 이전 곡
        previous.setOnClickListener {
            myService.musicPrevious()
            if(!play.isSelected) play.isSelected = true
            // 진행중인 음악 제목 가수 변경
            now_title.text = myService.titlelist[myService.now_song]
            now_singer.text = myService.artistlist[myService.now_song]
            //스레드 유지
            isPlaying = true
        }
        Log.d("test", "resume")
    }

    override fun onPause() {
        super.onPause()


        Log.d("test", "pause")
    }

    override fun onStop() {
        super.onStop()
        // 서비스 해제
        unbindService(connection)
        mBound = false
        Log.d("test", "stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        // sharedPreferences를 이용한 데이터 저장
        val sharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE) // test 이름의 기본모드 설정
        val editor = sharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언
        editor.putString("inputText", myService.now_song.toString()) // key,value 형식으로 저장
        editor.apply() //최종 커밋. 커밋을 해야 저장이 된다.

        // BroadcastReceiver 해제
        unregisterReceiver(mMediaReceiver)
        Log.d("test", "destroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("test", "restart")
    }


    // 권한 획득 확인
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//        }
//    }
//
//    fun getAudioListFromMediaDatabase(){
//
//    }

}