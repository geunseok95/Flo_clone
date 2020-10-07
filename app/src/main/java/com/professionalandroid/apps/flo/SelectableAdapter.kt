package com.professionalandroid.apps.flo

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.MotionEventCompat
import androidx.recyclerview.widget.RecyclerView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.update_recyclerview_item.view.*
import java.util.*

class SelectableAdapter(private var items: MutableList<list_item_data>):
    RecyclerView.Adapter<SelectableAdapter.ViewHolder>(), PlayListItemTouchHelperCallback.OnItemMoveListener {

    interface OnListItemSelelctedInterface{
        fun onItemSelected(v: View, position: Int)
    }

    interface OnStartDragListener{
        fun onStartDrag(viewholder: ViewHolder)
    }

    // position 별 선택상태를 저장
    private val mSelectedItems: SparseBooleanArray = SparseBooleanArray(0)

    var mContext: Context? = null
    // listener
    private var mListener: OnListItemSelelctedInterface? = null
    private var mStartDragListener: OnStartDragListener? = null
    var musiclist: MutableList<list_item_data>? = null

    constructor(context: Context, listener: OnListItemSelelctedInterface, listener2: OnStartDragListener, musiclist: MutableList<list_item_data>) : this(musiclist) {
        this.mContext = context
        this.mListener = listener
        this.musiclist = musiclist
        this.mStartDragListener = listener2
    }

    fun setData(mmusiclist: MutableList<list_item_data>){
        musiclist = mmusiclist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.update_recyclerview_item, parent, false)

        return ViewHolder(inflateView)
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var parentview = view
        var image: RoundedImageView? = null
        var title: TextView? = null
        var artist: TextView? = null
        var move_btn: ImageView? = null
        init {
            parentview = view.update_recyclerview_item
            image = view.recycler_view_image
            title = view.recycler_view_title
            artist = view.recycler_view_artist
            move_btn = view.move_btn
            parentview.setOnClickListener{
                val position = adapterPosition
                toggleItemSelectied(position)

                // 리스너 실행
                mListener?.onItemSelected(view, adapterPosition)

                // 클릭시 변화
            }
        }

        override fun toString(): String {
            return super.toString() + " '" + title!!.text +" " + artist!!.text  + "'"
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.image!!.setImageResource(items[position].image)
        holder.title!!.text = items[position].title
        holder.artist!!.text = items[position].artist

        holder.parentview.checkBox.isChecked = isItemSelected(position)

        holder.move_btn?.setOnTouchListener { view, motionEvent ->
            if(MotionEventCompat.getActionMasked(motionEvent) == MotionEvent.ACTION_DOWN){
                mStartDragListener?.onStartDrag(holder)
            }
            return@setOnTouchListener false
        }

    }

    fun toggleItemSelectied(position:Int){
        if(mSelectedItems[position, false]){
            Log.d("test", "put")
            mSelectedItems.delete(position)
        }
        else{
            mSelectedItems.put(position, true)
            Log.d("test", "put")
        }
        notifyItemChanged(position)
    }

    fun isItemSelected(position: Int): Boolean{
        return mSelectedItems[position, false]
    }

    fun clearSelectedItem(){
        var position:Int? = null
        for(i in 0 until mSelectedItems.size()){
            position = mSelectedItems.keyAt(i)
            mSelectedItems.put(position,false)
            notifyItemChanged(position)
        }
        mSelectedItems.clear()
    }

    fun checkAllItem(){
        for(i in 0 until 20){
            mSelectedItems.put(i,true)
        }
        notifyDataSetChanged()
    }

    // 하나라도 체크가 있으면 true, 전부 다 해제이면 false
    fun checkItemSelected():Boolean{
        for(i in 0 until mSelectedItems.size()) {
            if (mSelectedItems.valueAt(i)) {
                Log.d("test", "true")
                return true
            }
        }
        Log.d("test", "false")
        return false
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

}