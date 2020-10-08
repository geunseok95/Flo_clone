package com.professionalandroid.apps.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.mini_recyclerview_item.view.*

class MiniRecyclerViewAdapter(private var items: MutableList<list_item_data>):
    RecyclerView.Adapter<MiniRecyclerViewAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MiniRecyclerViewAdapter.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.mini_recyclerview_item, parent, false)
        return MiniRecyclerViewAdapter.ViewHolder(inflateView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MiniRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.image?.setImageResource(items[position].image)
        holder.title?.text = items[position].title
        holder.artist?.text = items[position].artist
    }

    class ViewHolder: RecyclerView.ViewHolder{
        var parentview: View? = null
        var image: RoundedImageView? = null
        var title: TextView? = null
        var artist: TextView? = null

        constructor(view: View):super(view){
            parentview = view
            image = view.mini_recycler_view_image
            title = view.mini_recycler_view_title
            artist = view.mini_recycler_view_artist
        }
    }
}