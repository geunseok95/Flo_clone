package com.professionalandroid.apps.flo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_music__play_list.*

class Music_PlayList : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    private var mRecyclerAdapter:RecyclerAdapter = RecyclerAdapter(musiclist)


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

        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerAdapter
        val back_btn = view.findViewById<ImageView>(R.id.back_btn)
        val update_btn = view.findViewById<TextView>(R.id.update_btn)
        back_btn.setOnClickListener {
            (activity as MainActivity).closeFragment(this)

            Log.d("test", "back_btn")
        }

        val updatemusic_playlist = UpdateMusic_PlayList()
        update_btn.setOnClickListener {
            (activity as MainActivity).addFragment(updatemusic_playlist)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = view.context

        mRecyclerAdapter.notifyDataSetChanged()

    }

    fun setmeeting(item:MutableList<list_item_data>){
        for(i in item){
            if(!musiclist.contains(i)){
                musiclist.add(i)
                mRecyclerAdapter.notifyItemInserted(musiclist.indexOf(i))
            }
        }
    }

}