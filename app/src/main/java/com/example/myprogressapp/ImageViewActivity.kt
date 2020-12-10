package com.example.myprogressapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class ImageViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imageview_activity)

        val image = intent.getStringExtra("Image")
        val bitmap : Bitmap = BitmapFactory.decodeFile(image)
        findViewById<ImageView>(R.id.image_view_activity_image).setImageBitmap(bitmap)
    }

}