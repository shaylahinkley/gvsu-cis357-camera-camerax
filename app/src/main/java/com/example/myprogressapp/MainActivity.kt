package com.example.myprogressapp

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //Might need to change the ID's of these (they are also used in other methods)
    lateinit var imageView: ImageView
    //lateinit var imageView: ImageView
    lateinit var captureButton: Button

    //codes that are used for image capture by camera
    private val CAMERA_REQUEST_CODE = 12345
    private val REQUEST_GALLERY_CAMERA = 12345
    private val PERMISSION_REQUEST_CODE: Int = 101

    var adapter: ImageRecyclerView? = null
    val allFilePaths = ArrayList<String>()

    //variable that is
    private var currentPhotoPath: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set up the recycler view.
        var rView: RecyclerView = findViewById(R.id.rvImages)
        rView.layoutManager = (LinearLayoutManager(this))
        adapter = ImageRecyclerView(allFilePaths)
        rView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rView.getContext(), 0)
        rView.addItemDecoration(dividerItemDecoration)

        //change ID's to real ones when layout is complete
        //imageView = findViewById(R.id.image_view)
        captureButton = findViewById(R.id.btn_capture)
        captureButton.setOnClickListener(View.OnClickListener {
            if(Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_GALLERY_CAMERA)
                } else {
                    takePicture()
                }
            } else {
                takePicture()
            }

        })


    }

    //this should be kept the same (I believe)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_GALLERY_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //opens camera and takes a picture. should be kept the same
    private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!= null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

    }

    //this might have to change. this is actually where you send the data over to the view and set it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val extras = data?.getExtras()
                    val imageBitmap = extras?.get("data") as Bitmap

            //To get the File for further usage
            val auxFile = File(currentPhotoPath)
            //var bitmap : Bitmap = BitmapFactory.decodeFile(CurrentPhotoPath)
            //imageView.setImageBitmap(bitmap)

            allFilePaths.add(currentPhotoPath.toString())
            adapter?.notifyDataSetChanged()
        }
    }

    @Throws(IOException::class)
    private fun createFile(): File {

        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        //gets the directroy of picures to store
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }
}

