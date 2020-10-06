package com.professionalandroid.apps.flo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView

class RecyclerAdapter(private var items: MutableList<list_item_data>):
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapter.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return RecyclerAdapter.ViewHolder(inflateView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        holder.image!!.setImageResource(items[position].image)
        holder.title!!.text = items[position].title
        holder.artist!!.text = items[position].artist
    }

    class ViewHolder: RecyclerView.ViewHolder {
        var parentview: View? = null
        var image: RoundedImageView? = null
        var title: TextView? = null
        var artist: TextView? = null
        constructor(view:View):super(view){
            parentview = view
            image = view.findViewById(R.id.recycler_view_image)
            title = view.findViewById(R.id.recycler_view_title)
            artist = view.findViewById(R.id.recycler_view_artist)
        }

        override fun toString(): String {
            return super.toString() + " '" + title!!.text +" " + artist!!.text  + "'"
        }
    }


    // List 변경 처리
    class ContentDiffUtil(private val oldList: MutableList<list_item_data>, private val currentList: MutableList<list_item_data>) : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].title == currentList[newItemPosition].title
        }

        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return currentList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == currentList[newItemPosition]
        }

    }
}