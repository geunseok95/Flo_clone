package com.professionalandroid.apps.flo

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_update_music__play_list.*
import kotlinx.android.synthetic.main.fragment_update_music__play_list.view.*


class UpdateMusic_PlayList : Fragment(), SelectableAdapter.OnListItemSelelctedInterface {

    lateinit var mRecyclerView: RecyclerView
    private var mRecyclerAdapter:SelectableAdapter? =null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mRecyclerAdapter = SelectableAdapter(context, this, musiclist)
    }

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

        // 확인 버튼
        val confirm_btn = view.findViewById<TextView>(R.id.confirm_btn)
        confirm_btn.setOnClickListener {
            val fm = (activity as MainActivity).supportFragmentManager
            (activity as MainActivity).supportFragmentManager.beginTransaction().remove(this).commit()
            fm.popBackStack()
        }

        val update_selectall_btn = view.update_selectAll_btn
        update_selectall_btn.setOnClickListener {
            if(update_selectall_btn.isChecked){
                mRecyclerAdapter?.checkAllItem()
            }
            else{
                mRecyclerAdapter?.clearSelectedItem()
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerAdapter
        mRecyclerAdapter?.notifyDataSetChanged()
    }

    override fun onItemSelected(v: View, position: Int) {
        val viewholder: SelectableAdapter.ViewHolder = mRecyclerView.findViewHolderForAdapterPosition(position) as SelectableAdapter.ViewHolder
        Toast.makeText(activity, viewholder.title?.text.toString(), Toast.LENGTH_SHORT).show()
        update_selectAll_btn.isChecked = mRecyclerAdapter!!.checkItemSelected()
    }

}