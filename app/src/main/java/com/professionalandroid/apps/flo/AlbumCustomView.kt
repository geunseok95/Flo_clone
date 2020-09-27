package com.professionalandroid.apps.flo

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.customview_album.view.*

class AlbumCustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttributes: Int = 0
): LinearLayout(context, attrs, defStyleAttributes){

    private lateinit var src: RoundedImageView
    private lateinit var title: TextView
    private lateinit var singer: TextView

    init {
        //LayoutInflater.from(context).inflate(R.layout.customview_album, this, false)
        val inflater: LayoutInflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.customview_album, this, false)
        addView(view)

        src = view.image
        title = view.title
        singer = view.singer
    }
    @SuppressLint("Recycle")
    private fun getAttrs(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlbumCustomView)
        setTypeArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.AlbumCustomView, defStyleAttr, 0)
        setTypeArray(typedArray)
    }

    private fun setTypeArray(typedArray: TypedArray){
        src.setImageResource(typedArray.getResourceId(R.styleable.AlbumCustomView_src,R.drawable.help))
        title.text = typedArray.getString(R.styleable.AlbumCustomView_title)
        singer.text = typedArray.getString(R.styleable.AlbumCustomView_singer)
    }

    fun settitletext(text: String){
        title.text = text
    }

    fun settitletext(text_resID: Int){
        title.text = context.getString(text_resID)
    }

    fun setsingertext(text:String){
        singer.text = text
    }

    fun setsingertext(text_resID: Int){
        singer.text = context.getString(text_resID)
    }

    fun setsrcimage(image_resID: Int){
        src.setImageResource((image_resID))
    }

}