package com.professionalandroid.apps.flo

import android.content.Context

class album_list(
    var image1: String,
    var image2: String,
    var image3: String,
    var image1_title: String
) {
    fun getImage1Id(context: Context): Int {
        return context.resources.getIdentifier(image1, "drawable", context.packageName)
    }
    fun getImage2Id(context: Context): Int {
        return context.resources.getIdentifier(image2, "drawable", context.packageName)
    }
    fun getImage3Id(context: Context): Int {
        return context.resources.getIdentifier(image3, "drawable", context.packageName)
    }
}
