package com.example.myprogressapp

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.Toast
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
    //lateinit var imageView: ImageView
    lateinit var captureButton: Button

    //codes that are used for image capture by camera
    private val CAMERA_REQUEST_CODE = 12345
    private val REQUEST_GALLERY_CAMERA = 12345
    private val PERMISSION_REQUEST_CODE: Int = 101

    // Adapater used to control the recycler view.
    var adapter: ImageRecyclerView? = null

    // These are shared with the recycler view, they contain the paths to each of the image files.
    private val allFilePaths = ArrayList<String>()

    //variable that is storing the latest file created.
    private var currentPhotoPath: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set's up the recycler view. This is where images are stored for now on.
        var rView: RecyclerView = findViewById(R.id.rvImages)
        rView.layoutManager = (LinearLayoutManager(this))
        adapter = ImageRecyclerView(allFilePaths)
        rView.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(rView.context, 0)
        rView.addItemDecoration(dividerItemDecoration)


        captureButton = findViewById(R.id.btn_capture)
        captureButton.setOnClickListener(View.OnClickListener {
            if(Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_GALLERY_CAMERA)
                } else {
                    takePicture()
                }
            } else {
                takePicture()
            }

        })
    }

    fun sortImagesByDate() {

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

            // We create a file here because that's how we reference what to display for the user on
            // the recycler view and activity page.
            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            val file: File = createFile()

            val uri: Uri = FileProvider.getUriForFile(
                    this,
                    "com.example.android.fileprovider",
                    file
            )
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }

    }

    //this might have to change. this is actually where you send the data over to the view and set it
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    // Add the file path to the recycler view.
                    allFilePaths.add(currentPhotoPath.toString())
                    adapter?.notifyDataSetChanged()
                }
            }
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

