package com.professionalandroid.apps.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import kotlinx.android.synthetic.main.slide_view_pager_fragment.*


class SlideViewPager : Fragment() {

    companion object {
        val albumList = arrayListOf(
            album_list("thestray", "farewell", "jooho", "너, 너 (piano Ver.) - 스트레이 (The Stray)"),
            album_list("propose", "forthefirsttime", "today", "프로포즈 - 길구봉구")
        )
    }

    private lateinit var mViewPager: ViewPager
    lateinit var mdots_indicator:DotsIndicator
    private var adapter = SlideViewPagerAdapter(albumList)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.slide_view_pager_fragment,container,false)
        mViewPager = view!!.findViewById(R.id.fragment_viewpager)
        mViewPager.adapter = adapter
        mdots_indicator = view.findViewById(R.id.dots_indicator)
        mdots_indicator.setViewPager(mViewPager)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val context = view.context
        mViewPager.adapter = adapter
    }
}
