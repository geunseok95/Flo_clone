package com.professionalandroid.apps.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import kotlinx.android.synthetic.main.slide_view_pager_item.view.*

class SlideViewPagerAdapter(private val list: ArrayList<album_list>):PagerAdapter(){

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(container.context)
        val view = inflater.inflate(R.layout.slide_view_pager_item, container, false)

        view.viewpager_image1.setImageResource(list[position].getImage1Id(container.context))
        view.viewpager_image2.setImageResource(list[position].getImage2Id(container.context))
        view.viewpager_image3.setImageResource(list[position].getImage3Id(container.context))

        view.viewpager_image1_title.text = list[position].image1_title

        container.addView(view)
        return view
    }


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View?)
    }

}