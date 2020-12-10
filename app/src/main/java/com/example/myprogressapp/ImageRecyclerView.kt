package com.example.myprogressapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class ImageRecyclerView(private val dataSet: ArrayList<String>)  : RecyclerView.Adapter<ImageRecyclerView.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var myImagePath: String? = null
        var myImage: ImageView? = null
        var myTitle: TextView? = null

        init {
            myImage = itemView.findViewById(R.id.recyler_item_image)
            myTitle = itemView.findViewById(R.id.recyler_item_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.imageview_row, parent, false)

        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(it.context, ImageViewActivity::class.java).apply {
                putExtra("Image", holder.myImagePath)
            }
            startActivity(it.context, intent, null)
        }

        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentFile = File(dataSet[position])
        val parsedName = SimpleDateFormat("EEE, MMM d, h:m a").format(currentFile.lastModified()).toString()
        holder.myTitle?.text = parsedName

        val bitmap : Bitmap = BitmapFactory.decodeFile(currentFile.path)
        holder.myImage?.setImageBitmap(bitmap)

        holder.myImagePath = dataSet[position]
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}
