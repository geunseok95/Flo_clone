package com.professionalandroid.apps.flo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator

class SlideViewPager : Fragment() {

    companion object {
        val albumList = arrayListOf(
            album_list("thestray", "farewell", "jooho", "너, 너 (piano Ver.) - 스트레이 (The Stray)"),
            album_list("propose", "forthefirsttime", "today", "프로포즈 - 길구봉구"),
            album_list("happy", "maktub", "fallin", "Happy - 태연 (TAEYEON)"),
            album_list("phonenumber", "half", "wait", "너의 번호를 누르고 (Prod. 영화처럼) - #안녕"),
            album_list("linda", "nuna", "summerhate", "LINDA (Feat. 윤미래) - 린다G"),
            album_list("watermelon", "honeysuckle", "paperplanes", "Watermelon Sugar - Harry Styles"),
            album_list("band", "kcm", "how", "이것도 사랑이니 - 이동은 (라이어밴드)")
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
        mViewPager = view.findViewById(R.id.fragment_viewpager)
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
