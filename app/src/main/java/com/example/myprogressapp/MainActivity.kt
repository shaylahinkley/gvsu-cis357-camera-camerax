package com.example.myprogressapp

import android.Manifest.permission.CAMERA
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var captureButton: Button

    private val PERMISSION_REQUEST_CODE = 1
    private var CurrentPhotoPath: String? = null;
    val REQUEST_IMAGE_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //change ID's to real ones when layout is complete
        //imageView = findViewById(R.id.image_view)
        //captureButton = findViewById(R.id.btn_capture)
        captureButton.setOnClickListener(View.OnClickListener {
            if (checkPersmission()) takePicture() else requestPermission()
        })
    }
    private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }
   private fun checkPermission(): Boolean {
       return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
               PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
               android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
   }

    private fun requestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
        PERMISSION_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        &&grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                   takePicture()
                } else {
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
            else -> {
            }
        }
    }
    private fun takePicture() {

        val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val file: File = createFile()

        val uri: Uri = FileProvider.getUriForFile(
                this,
                "com.example.android.fileprovider",
                file
        )
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
            val auxFile = File(CurrentPhotoPath)
            var bitmap : Bitmap = BitmapFactory.decodeFile(CurrentPhotoPath)
            imageView.setImageBitmap(bitmap)

        }
    }

    @Throws(IOException::class)
    private fun createFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            CurrentPhotoPath = absolutePath
        }
    }
}

