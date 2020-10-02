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

    private var mroundedimageview: RoundedImageView
    private var mtitletext: TextView
    private var msingertext: TextView

    init {
        //LayoutInflater.from(context).inflate(R.layout.customview_album, this, false)
        val inflater: LayoutInflater = getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.customview_album, this, false)
        addView(view)

        mroundedimageview = view.roundedimageiew
        mtitletext = view.titletext
        msingertext = view.singertext
        getAttrs(attrs, defStyleAttributes)
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
        mroundedimageview.setImageResource(typedArray.getResourceId(R.styleable.AlbumCustomView_srcimage,R.drawable.help))
        mtitletext.text = typedArray.getString(R.styleable.AlbumCustomView_title)
        msingertext.text = typedArray.getString(R.styleable.AlbumCustomView_singer)
    }

    fun settitletext(text: String){
        mtitletext.text = text
    }

    fun settitletext(text_resID: Int){
        mtitletext.text = context.getString(text_resID)
    }

    fun setsingertext(text:String){
        msingertext.text = text
    }

    fun setsingertext(text_resID: Int){
        msingertext.text = context.getString(text_resID)
    }

    fun setsrcimage(image_resID: Int){
        mroundedimageview.setImageResource((image_resID))
    }

}