package com.professionalandroid.apps.flo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_music__play_list.*

class Music_PlayList : Fragment() {

    var musiclist:MutableList<list_item_data> =
        mutableListOf(
            list_item_data(R.drawable.propose, "프로포즈", "길구봉구"),
            list_item_data(R.drawable.thestray, "너, 너 (Piano.Ver .)", "스트레이 (The Stray)"),
            list_item_data(R.drawable.happy, "Happy", "태연 (TAEYEON)"),
            list_item_data(R.drawable.fallin, "fallin (feat.Liss)", "죠지"),
            list_item_data(R.drawable.half, "반만", "진민호"),
            list_item_data(R.drawable.anne, "2002", "Anne-Marie"),
            list_item_data(R.drawable.watermelon, "Watermelon Sugar", "Harry Styles"),
            list_item_data(R.drawable.singmetosleep, "Sing Me to Sleep", "Alan Walker"),
            list_item_data(R.drawable.today, "하루하루 아프니까", "길구봉구")
        )


    val mainactivity = MainActivity
    lateinit var mRecyclerView: RecyclerView
    private var mRecyclerAdapter:RecyclerAdapter = RecyclerAdapter(musiclist!!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_music__play_list, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView

        val back_btn = view.findViewById<ImageView>(R.id.back_btn)

        back_btn.setOnClickListener {
            (activity as MainActivity).closeFragment(this)

            Log.d("test", "back_btn")
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerAdapter.notifyDataSetChanged()

    }

    fun setmeeting(item:MutableList<list_item_data>){
        for(i in item){
            if(!musiclist!!.contains(i)){
                musiclist.add(i)
                mRecyclerAdapter.notifyItemInserted(musiclist.indexOf(i))
            }
        }
    }

}