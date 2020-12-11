
# Overview

This is a demonstration application to get familiar with Android [Camera](https://developer.android.com/training/camera)/[Camerax](https://developer.android.com/training/camerax). 
The demonstration assumes that the user has a basic understanding of coding in Kotlin in Android Studio as well as creating layouts in Android Studio. This page includes a step-by-step tutorial of how to complete the "My Progress Application".

##Android Camera/CameraX

Android's Camra and CameraX are two essential topics in Android Mobile Development that every developer should know. Most applications today use one or both in some sort of sense. Every time you open an app and take a picture on an Android phone or upload images from your Android's photo galley, Android Camera and/or CameraX is involved. In a few short sentences, CameraX is a JetPack support library that is built to make Android camera development easier. Camera development on Android can be tricky at times, but you still need to know Android's core "Camera" functiionality. 

## My Progress App

This application is used to track progress of crafts, buildings, weightloss, and so much more - just in pictures. This application allows users to take images with their Android enabled device within the application. The images are sorted by date allowing users to easily see the beginning "before" picture and the end "after" picture. A demoonstration video of the application can be found here. Pleae watch the video before starting, so that you can better understand the features.

## Future Considerations

This application is simple in order to focus on the main topic at hand: Android's Camera/CameraX. This application can easily be modified to include mulitple progresses that a user can keep track of. This can be done by including a recycler view containing a text view. The user can click a button to name a progress. Upon clicking the name in the recycler view, another activity will start, bringing the recycler view that is demonstrated in this application.


## Features
Some of the main features of this application include:
- Opening application to main page that includes a `CAPTURE BUTTON`
- Upon clicking the `CAPTURE BUTTON`, Android's camera application will open after asking for permissions from user for access to their camera and files
- User is able to take an image, retake the image, or exit the camera application
- Upon taking the image, if approved by the user, a recycler view will be populated with a small preview of the image and the date and time of when the image was captured
- Upon clicking a row in the recycler view, an activity pops up with a larger size of the image along with the date the image was taken
- Upon clicking anywhere on the page, the user will be navigated back to the main page with the populated recycler view
- The user is able to capture more images that will continue to populate the recycler view


# Getting started

In order to setup the "My Progress App", you will need a few things including:  
- Android Studio downloaded onto your computer ([Download](https://developer.android.com/studio)).
- Basic knowledge of Kotlin Mobile Development. The documentation resources for Kotlin are [here](https://kotlinlang.org/docs/reference/), but some basic tutorials to help get you started can be found [here](https://kotlinlang.org/docs/tutorials/).  


# Step-by-Step: Let's code.

## Creating the project
1.  Open Android Studio and select `File` -> `New Project`.  
2.  Under the section heading `Phone and Tablet` click the activity labeled `Empty Activity` and click `Next`.  
3.  On the `Configure Your Project` screen, name the application `MyProgressApp`. The `Package name` and `Save location` should update accordingly. If you wish to save into a different spot, click the folder icon on the right. 
4.  Under `Language`, make sure `Kotlin` is chosen from the drop down menu and set the `Minimum SDK` to `API 30`.  This can be changed in other projects, however, for this project we are going to stick with `API 30`. 
5.  If you have done the steps correctly, your Android Studio will take you to the main editing window on the `MainActivity.kt` file. 
6. On the left hand side of the window, you will see `1: Project`. You can click this to collapse and open the layout of the project. Making sure that the `Project Layout` is not collapsed, ensure in the top left of the panel that `Android` is chosen from the drop down menu. This will help simplify what files you need to look at. 

## Creating the main page  layout - activity_main.xml
1. Navigate to `app/rese/layout/activity_main.xml`. This is the file that we will be using to create the main page layout.
2. Make sure you are in the `Design` pane. If you are not, you can change this in the top right. There will be three different options: `Code`, `Split`, and `Design`.
3. The first thing we want to do is add a `Constraint Layout`. This can be found in the pane under `Palette` -> `Layouts`. Drag the `Constraint Layout` onto the layout.
4. Next thing we want to do is add a button that is going to be used to open the camera. To do this go to the pane under `Palette` -> `Buttons`. Add a normal `Button` to the bottom of the layout.
5. Next, in the `Attributes` pane on the right hand side of your screen (if you do not see this, click the button on your layout). We need to give an ID to the button so we can refer to it later. Name the ID `btn_capture` at the top of the `Attributes pane`.
6. Under the `Declared Attributes` sectoin, set the `layout_width` to `135dp` and the  `layout_height` to `80dp`.
7. Set both `layout_constraintBottom_toBottomOf` and `layout_constaintEnd_toEndOf` to `parent`. Also set the `layout_marginEnd` to `136dp` and `layout_marginBottom` to `4dp`.
8. If you would like, you can also change the color of the button by clicking the `backgroundTint`. We chose a blueish color.
9. Ensure the `clickable` field is clicked `true`. 
10. Set the `cornerRadius` to `100dp` and `focusable` to `true`.
11. Set the `text` field to the string `CAPTURE`. If you would like to change the text color, edit the `textColor` field.
12. Next, scroll down to the `Transforms` section and make sure the view `Rotation z` is set to `0` on the slider bar.
13. Now, navigate back to the `Palette` and add a `LinearLayout (vertical) 




## Creating the recycler view 


## Capturing images with the camera
7. Navigate to `GradleScripts/build.gradle (Module: MyProgressApp.app)`
8. Scroll to the bottom where you see `dependencies`. In between the `brackets {}` replace the code with the following code. These dependencies will be needed prior to starting the project.



    ``` kotlin
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.2.0'

    implementation 'com.google.android.material:material:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    ```
    
    

## Accessing the camera and taking pictures 
1.  Navigate to `app/manifests/AndroidManifest.xml` in the `Project Layout Navigation Panel`. In `AndroidManifest.xml` you must add the following permissions under the line containing the package name `package=".com.example.myprogressapp">`. These permissions allow access to the camera and read/write to external storage.  


    ``` kotlin  
    <uses-permission android:name="android.permission.CAMERA"/>  
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"  
          android:maxSdkVersion="18"/>  
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    ```  


2.  Also in `app/manifests/AndroidManifest.xml`, since using the camera is one of the main features of the app or an essential function, it is best to restrict its visibility on [Google Play](https://play.google.com/store) to devices that have a camera. To do this, we will need to add the following code to the `AndroidManifest.xml` file right under the `<uses-permission .... />` code we added in step 7.  


    ``` kotlin  
    <uses-feature android:name="android.hardware.camera"  
         android:required="true"/>  
    ```  
    
 3. Now navigate back to `app/java/com.example.myprogressapp/MainActivity.kt`. There will be multiple `com.example.myprogressapp` packages. Click the one that does not say `(android)` or `(test)`.
 4. We now need to insert imports in `MainActivity class`. We are going to import most right now to get it out of the way for later. Feel free to copy and paste to save time!
 
 

    ``` kotlin
    import android.Manifest.permission.CAMERA
    import android.Manifest.permission.READ_EXTERNAL_STORAGE
    import android.app.Activity
    import android.content.Intent
    import android.content.pm.PackageManager
    import android.graphics.Bitmap
    import android.graphics.BitmapFactory
    import android.net.Uri
    import android.os.Build
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
    ```
 
 
 5. We are going to end up checking the permissions in a `Capture Button` click. If the user has already given permissions to access the camera and external storage, then we can directly open the camera to take a picture. However, we will need the function `onRequestPermissionsResult()` to open the camera after the request for permissions and if the user accepted it. Insert the following function into the `MainActivity class`. Leave `takePicture()` commented out for now. Also insert the `val` variables at the top of your class. 
 
 
    ``` kotlin
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
       super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_GALLERY_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                //takePicture()
            } else {
                Toast.makeText(this@MainActivity, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    ```
    
    
    
    ``` kotlin
    private val CAMERA_REQUEST_CODE = 12345
    private val REQUEST_GALLERY_CAMERA = 12345
    ```
    
    
    
    
6. Now, we will be creating a function `private fun createFile() : File` to create a file that stores an image that is taken by the camera before we make the method that takes the picture. Insert the following function into your `MainAcitvity.kt class`. In addition, there will be an error due `currentPhotoPath = absolutePath`. We will fix this in the following step.
    
 
 
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
            currentPhotoPath = absolutePath
        }
    }
    ```
    
 
 
7. Insert the following line in the beginning of the`MainActivity class` 



    ``` kotlin
    private var currentPhotoPath: String? = null;
    ```
    
    
    
8. Next, we need to configure the `FileProvider` in the `AndroidManifest.xml` located in `app/manifests/` in order to add a provider to our application. This code will be inserted between `<application>` and `</application>`. Do not worry if you see errors.



    ``` kotlin
    <provider
            android:name="androidx.core.content.FileProvider"
            android:authorities="com.example.android.fileprovider"
            android:exported="false"
            android:grantUriPermissions="true">
            <meta-data
                android:name="android.support.FILE_PROVIDER_PATHS"
                android:resource="@xml/file_path"> 
            </meta-data>
        </provider>
    ```
    
    
    
9. Create a new xml file called `file_path.xml` under the `app/res/xml` folder. The `xml` folder is made when you create the file. Right click on `app/res`. Choose `New` -> `Android Resource File`. In the `File name` field, insert `file_path`. For the `Resource Type` choose `XML`, then click `OK` in the bottom right hand corner. Insert the code after clicking `Code` in the toolbar above the `Attribute Pane`, replacing what was default in the file. You MUST add your package name in the path.
    
    
    ``` kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <external-path name="my_images"
            path="Android/data/com.example.myprogressapp/files/Pictures" />
    </paths>
    ```
    

10. Navigate back to the `MainActivity class`. We will now insert the take picture function using the code below. This insert starts the camera to capture an image. Uncomment the `takePicture()` line of code in the `onRequestsPermissionsResult(...)` function.
    
    
    
    ``` kotlin
     private fun takePicture() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!= null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }
    ```
    
    
   
11. Now, after the function `takePicture()` opens the camera, we will need to grab the image. We will then need to show this on our `imageView`. In order to get the image into the `ImageView`, add the method to the `MainActivity class`. There will be an error. 



    ``` kotlin
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                CAMERA_REQUEST_CODE -> {
                    val extras = data?.getExtras()
                    val imageBitmap = extras?.get("data") as Bitmap

                    imageView.setImageBitmap(imageBitmap)

                }
            }
        }
    }
    ```
    
    
    
12. Add the followings line of code under where you defined the `var` and `val` variables at the top of the class. This will be the `Image View` that the picture shows up on. The `Button` will be used in another function.
    

    ``` kotlin
    lateinit var imageView: ImageView
    lateinit var captureButton: Button
    ```
    
    
13. Add the following code to your `override fun onCreate(savedInstanceState: Bundle?)` function. You can replace the `R.id.image_view` and `R.id.btn_capture` later when we create our `ButtonID` and `ImageViewID`. when the `Capture Button` is pressed, permissions are checked. You will need to add the `checkPermission()` function in the next step.



    ``` kotlin
    imageView = findViewById(R.id.image_view)
    captureButton = findViewById(R.id.btn_capture)
    captureButton.setOnClickListener(View.OnClickListener {
          if(Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED                        || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) !=              PackageManager.PERMISSION_GRANTED) {
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
    ```
    
    
    
14. At this time, there should only be errors at the lines containing `imageView = findViewById(R.id.image_view)` and `captureButton = findViewById(R.id.btn_capture)` in the `onCreate(savedInstanceState: Bundle?)` because we have not created any of our layout yet in order to get our `ButtonID` and `ImageViewID`.
    
    
    
    
    
    
    
    
    
    
        

