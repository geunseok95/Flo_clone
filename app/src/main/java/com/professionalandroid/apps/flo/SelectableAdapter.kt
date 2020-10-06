package com.professionalandroid.apps.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class SelectableAdapter(private var items: MutableList<list_item_data>):
    RecyclerView.Adapter<SelectableAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.update_recyclerview_item, parent, false)

        return SelectableAdapter.ViewHolder(inflateView)
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var parentview: View? = view
        var image: RoundedImageView? = null
        var title: TextView? = null
        var artist: TextView? = null

        init {
            image = view.findViewById(R.id.recycler_view_image)
            title = view.findViewById(R.id.recycler_view_title)
            artist = view.findViewById(R.id.recycler_view_artist)
        }

        override fun toString(): String {
            return super.toString() + " '" + title!!.text +" " + artist!!.text  + "'"
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image!!.setImageResource(items[position].image)
        holder.title!!.text = items[position].title
        holder.artist!!.text = items[position].artist
    }
}