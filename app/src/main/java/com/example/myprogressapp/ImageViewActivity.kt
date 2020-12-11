package com.example.myprogressapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ImageViewActivity : AppCompatActivity() {

    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.imageview_activity)


        // Get's the image path from the view, then loads the image, thus displaying it.
        val image = intent.getStringExtra("ImagePath")
        imageView = findViewById(R.id.image_view_activity_image)

        val imageDate = File(image).lastModified();

        findViewById<TextView>(R.id.image_view_date).text = "Date Taken: " + SimpleDateFormat("EEE, MMM d, h:mm:ss a").format(imageDate).toString()

        val bitmap : Bitmap = BitmapFactory.decodeFile(image)
        //findViewById<ImageView>(R.id.image_view_activity_image).setImageBitmap(bitmap)
        imageView.setImageBitmap(bitmap)


        findViewById<ImageView>(R.id.image_view_activity_image).setOnClickListener{
            // Return us back to the previous screen.
            finish()
        }
    }



}