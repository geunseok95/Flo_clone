package com.professionalandroid.apps.flo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class UpdateMusic_PlayList : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    private var mRecyclerAdapter:SelectableAdapter = SelectableAdapter(musiclist)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_music__play_list, container, false)
        mRecyclerView = view.findViewById(R.id.recycler_view) as RecyclerView

        val confirm_btn = view.findViewById<TextView>(R.id.confirm_btn)
        confirm_btn.setOnClickListener {
            val fm = (activity as MainActivity).supportFragmentManager
            (activity as MainActivity).supportFragmentManager.beginTransaction().remove(this).commit()
            fm.popBackStack()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerAdapter.notifyDataSetChanged()
    }


}