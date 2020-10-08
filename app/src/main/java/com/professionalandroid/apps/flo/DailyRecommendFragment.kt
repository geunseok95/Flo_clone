package com.professionalandroid.apps.flo

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DailyRecommendFragment : Fragment() {

    lateinit var mRecyclerView: RecyclerView
    private var mRecyclerAdapter: MiniRecyclerViewAdapter = MiniRecyclerViewAdapter(
        mini_musiclist
    )

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_daily_recommend, container, false)
        mRecyclerView = view.findViewById(R.id.mini_recyclerview) as RecyclerView
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = mRecyclerAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRecyclerAdapter.notifyDataSetChanged()

    }


}