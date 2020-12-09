
# Overview

This is a demonstration application to get familiar with Android [Camera](https://developer.android.com/training/camera)/[Camerax](https://developer.android.com/training/camerax). 
The demonstration assumes that the user has a basic understanding of coding in Kotlin in Android Studio.

## My Progress App

This application is used to track progress of crafts, buildings, weightloss, and so much more - just in pictures. This application allows users to take images with their Android enabled device and import them into the application. The images are sorted by date allowing users to easily see the beginning "before" picture and the end "after" picture.


# Getting started

In order to setup the "My Progress App", you will need a few things including:  
- Android Studio downloaded onto your computer ([Download](https://developer.android.com/studio)).
- Basic knowledge of Kotlin Mobile Development. The documentation resources for Kotlin are [here](https://kotlinlang.org/docs/reference/), but some basic tutorials to help get you started can be found [here](https://kotlinlang.org/docs/tutorials/).  


# Step-by-Step: Let's code.

## Creating the project
1.  Open Android Studio and select `File` -> `New Project`.  
2.  Under the section heading `Phone and Tablet` click the activity labeled `Empty Activity` and click `Next`.  
3.  On the `Configure Your Project` screen, name the application `MyProgressApp`. The `Package name` and `Save location` should update accordingly. If you wish to save into a different spot, click the folder icon on the right. 
4.  Under `Language`, make sure `Kotlin` is chosen from the drop down menu and set the `Minimum SDK` to `API 19: Android 4.4 (KitKat)` to ensure that your application will run on a majority of devices. This can be changed in other projects, however, for this project we are going to stick with `API 19: Android 4.4 (KitKat)`. 
5.  If you have done the steps correctly, you Android Studio will take you to the main editing window on the `MainActivity.kt` file.  

## Accessing the camera and taking pictures
1.  On the left hand side of the window, you will see `1: Project`. You can click this to collapse and open the layout of the project. Making sure that the `Project Layout` is not collapsed, ensure in the top left of the panel that `Android` is chosen from the drop down menu. This will help simplify what files you need to look at.  
2.  Navigate to `app/manifests/AndroidManifest.xml` in the `Project Layout Navigation Panel`. In `AndroidManifest.xml` you must add the following permissions under the line containing the package name `package=".com.example.myprogressapp">`. These permissions allow access to the camera and read/write to external storage.  


    ``` kotlin  
    <uses-permission android:name="android.permission.CAMERA"/>  
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  
          android:maxSdkVersion="18"/>  
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>  
    ```  


3.  Also in `app/manifests/AndroidManifest.xml`, since using the camera is one of the main features of the app or an essential function, it is best to restrict its visibility on [Google Play](https://play.google.com/store) to devices that have a camera. To do this, we will need to add the following code to the `AndroidManifest.xml` file right under the `<uses-permission .... />` code we added in step 7.  


    ``` kotlin  
    <uses-feature android:name="android.hardware.camera"  
         android:required="true"/>  
    ```  
    
 4. Now navigate back to `app/java/com.example.myprogressapp/MainActivity.kt`. There will be multiple `com.example.myprogressapp` packages. Click the one that does not say `(android)` or `(test)`.
 5. We now need to set up permissions in `MainActivity.kt`. We need to do this because from Android 6.0, Google introduced permissions. They need to be requested at runtime for the user to approve or decline. Insert the following functions into the `MainActivity.kt class` in order to check permissions and request permissions. Errors in the code will be handled in following steps, do not worry!
 
 
     ``` kotlin
     private fun checkPermission(): Boolean {
          return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                   PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                  android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
      }

      private fun requestPermission() {
          ActivityCompat.requestPermissions(this, arrayOf(READ_EXTERNAL_STORAGE, CAMERA),
         PERMISSION_REQUEST_CODE)
     }
    ```
    
    
 6. There will be errors after inserting the code from step 5. You need to import the needed statements by pressing `Alt+Enter` on the errors, or insert the following imports at the top of the `Mainfest.kt`. We will import a couple more that will relate to other functions. It is easier to do a chunk of them all at once. Make sure all the statements are imported below (watch for repeats).
    

    ``` kotlin
    import android.Manifest.permission.CAMERA
    import android.Manifest.permission.READ_EXTERNAL_STORAGE
    import android.content.pm.PackageManager
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import androidx.core.app.ActivityCompat
    import androidx.core.content.ContextCompat
     ```
       
       
 7. There will still be an error in the `private fun requestPermission()` function because of the val `PERMISSION_REQUEST_CODE`. In order to solve this error, insert the following code in the `MainActivity.kt class` outside of the functions, before the `override fun onCreate(savedInstanceState: Bundle?)` function.
     
        
    ``` kotlin
    private val PERMISSION_REQUEST_CODE = 1
    ```
 
 
 8. We are going to end up checking the permissions in a Capture `Button` click. If the user has already given permissions to access the `Camera` and `External Storage`, then we can directly open the camera to take a picture. However, we will need to manage the function `onRequestPermissionsResult()` to open the camera after the request for permissions and if the user accepted it. Insert the following function into the `MainActivity.kt class`.
 
 
    ``` kotlin
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {

                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        &&grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    
                   //We will uncomment this function once it is created  
                   // takePicture()

                } else {
                    Toast.makeText(this,"Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }

            else -> {

            }
        }
    }
    ```
    
    
9. Now, we will be creating a function `private fun createFile() : File` to create a file that stores an image that is taken by the camera before we make the method that takes the picture. Insert the following function into your `MainAcitvity.kt class`. You will need insert a couple of imports at the top of the file. In addition, int There will be an error due `CurrentPhotoPath = absolutePath`. We will fix this in the following steps.
    
 
 
    ``` kotlin
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
            mCurrentPhotoPath = absolutePath
        }
    }
    ```
    
    
    
    ``` kotlin
    import java.io.IOException
    import java.io.File
    import java.text.SimpleDataFormat
    import android.os.Environment
    import java.util.*
    ```
    
    
    
10. Insert the following line in `MainActivity.kt class` right after where you declared `private val PERMISSION_REQUEST_CODE = 1`



    ``` kotlin
    private var CurrentPhotoPath: String? = null;
    ```
    
    
    
11. Next, we need to configure the `FileProvider` in the `AndroidManifest.xml` located in `app/manifests/` in order to add a provider to our application. This code will be inserted between `<application>` and `</application>`. Do not worry if you see errors.



    ``` kotlin
    <provider
            android:name="android.support.v4.content.FileProvider"
            android:authorities="com.example.android.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_path">
                
            </meta-data>
        </provider>
    ```
    
    
    
12. Create a new xml file called `file_path.xml` under the `app/res/xml` folder. the `xml` folder is made when you create the file. Right click on `app/res`. Choose `New` -> `Android Resource File`. In the `File name` field, insert `file_path`. For the `Resource Type` choose `XML`, then click `OK` in the bottom right hand corner. Insert the code after clicking `Code` in the toolbar above the `Attribute Pane`, replacing what was default in the file. You MUST add your package name in the path.
    
    
    ``` kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <external-path name="my_images"
            path="Android/data/com.example.myprogressapp/files/Pictures" />
    </paths>
    ```
    

13. Navigate back to the `MainActivity.kt class`. We will now insert the take picture function using the code below. This insert starts the camera to capture an image. You will need to have more imports at the top of the file. Import all the following imports at the top of the file as well (the ones you do not already have). Uncomment the `takePicture()` line of code in the `onRequestsPermissionsResult(...)` function.
    
    
    ``` kotlin
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
    ```
    
    
    
    ``` kotlin
    import android.app.Activity
    import android.content.Intent
    import android.graphics.Bitmap
    import android.graphics.BitmapFactory
    import android.net.Uri
    import android.provider.MediaStore
    import android.widget.Button
    import android.widget.ImageView
    import androidx.core.content.FileProvider
    ```
    
    
14. Insert of following line of code at the top of the `MainActivity.kt class ` outside all the functions where you defined other `val` and `var` variables.
 
 
 
    ``` kotlin
    val REQUEST_IMAGE_CAPTURE = 1
    ```
   
   
15. Now, after the function `takePicture()` opens the camera, we will need to capture an image. We will then need to show this on our `ImageView`. In order to get the `ImageView`, add the method to the `MainActivity.kt class`. There will be an error. 



    ``` kotlin
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {

            //To get the File for further usage
            val auxFile = File(CurrentPhotoPath)


           var bitmap :Bitmap=BitmapFactory.decodeFile(CurrentPhotoPath)
            imageView.setImageBitmap(bitmap)

        }
    }
    ```
    
    
    
16. Add the followings line of code under where you defined the `var` and `val` variables at the top of the class. This will be the `Image View` that the picture shows up on. The `Button` will be used in another function.
    

    ``` kotlin
    lateinit var imageView: ImageView
    lateinit var captureButton: Button
    ```
    
    
17.  Add the following code to your `override fun onCreate(savedInstanceState: Bundle?)` function. You can replace the `R.id.image_view` and `R.id.btn_capture` later when we create our `ButtonID` and `ImageViewID`. when the `Capture Button` is pressed, permissions are checked. You will need to add the `checkPermission()` function in the next step.


    ``` kotlin
    imageView = findViewById(R.id.image_view)
    captureButton = findViewById(R.id.btn_capture)
    captureButton.setOnClickListener(View.OnClickListener {
        if (checkPersmission()) takePicture() else requestPermission()
    })
    ```
    
    
18. Add the `checkPermission()` function in the `MainActivity.kt` class. This checks if we have the right permissions to access the camera.


    ``` kotlin
        private fun checkPersmission(): Boolean {
        return (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
    }
    ```
    
    
    
    
    
    
    
    
    
    
        

