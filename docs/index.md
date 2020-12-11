
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
1. Navigate to `app/res/layout/activity_main.xml`. This is the file that we will be using to create the main page layout.
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
12. Now, navigate back to the `Palette` and add a `LinearLayout (vertical)` to the layout. Again, click on the `Attributes` pain. Set the `layout_width` to `411 dp` and the `layout_height` to `645dp`. Set both the `layout_constaintStart_toStartOf` and `layout_constaintTop_topTopOf` to `parent`. Make sure the orientation says `vertical`. 
13. Under the `Layout` section, add a top and left constraint of `0`.
14. Now, we are going to add a recycler view. Go to `Common` under the `Palette` tab. Nest a `RecyclerView` in the `LinearLayout` by dragging `RecyclerView` under `LinearLayout`.
15. Under the `Attributes` pane, add an ID for the RecyclerView. Name the ID `rvImages`. Set the `layout_width` and `layout_height` to `match_parent`.



## Creating the recycler view row - imageview_row.xml
1. Navigate to `app/res/layout/`. Right click on the `layout` folder, click `New` -> `Layout Resource File`. Name it `imageview_row.xml`. Copy and paste the following code into the code section of your xml file. This will save some time. This code sets the correct constaints and creates an ImageView and TextView for each row in the RecyclerView. The ID for the text is `recycler_item_text` and the ID for the ImageView is `recycler_item_image`.




    ``` kotlin
    <?xml version="1.0" encoding="utf-8"?>

    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/recyler_item"
    android:layout_width="match_parent"
    android:layout_height="150dp"
    android:animateLayoutChanges="false"
    android:background="#00AEE1F4"
    android:visibility="visible">

    <ImageView
        android:id="@+id/recyler_item_image"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="20dp"
        android:layout_marginTop="20dp"
        android:layout_marginEnd="20dp"
        android:layout_marginBottom="20dp"
        android:contentDescription="@string/image_view_image"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toStartOf="@+id/recyler_item_text"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <TextView
        android:id="@+id/recyler_item_text"
        android:layout_width="212dp"
        android:layout_height="212dp"
        android:layout_marginStart="5dp"
        android:layout_marginTop="5dp"
        android:layout_marginEnd="5dp"
        android:layout_marginBottom="5dp"
        android:background="#03FFFFFF"
        android:contentDescription="@string/image_view_text"
        android:gravity="center_vertical"
        android:textAlignment="textStart"
        android:textSize="22sp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toEndOf="@+id/recyler_item_image"
        app:layout_constraintTop_toTopOf="@id/recyler_item_image"
        tools:ignore="RtlCompat" />
    </androidx.constraintlayout.widget.ConstraintLayout>
    ```
    
    

## Opening the camera, taking an image, and creating an image  - MainActivity.kt
1. Navigate to `GradleScripts/build.gradle (Module: MyProgressApp.app)`
2. Scroll to the bottom where you see `dependencies`. In between the `brackets {}` add the following code if they do not already exist. These dependencies will be needed prior to starting the project.



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
    
    

3.  Navigate to `app/manifests/AndroidManifest.xml` in the `Project Layout Navigation Panel`. In `AndroidManifest.xml` you must add the following permissions under the line containing the package name `package=".com.example.myprogressapp">`. These permissions allow access to the camera and read/write to external storage.  


    ``` kotlin  
    <uses-permission android:name="android.permission.CAMERA"/>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"
        android:maxSdkVersion="18"/>
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    <uses-feature android:name="android.hardware.camera.autofocus" />
    ```  


4.  Also in `app/manifests/AndroidManifest.xml`, since using the camera is one of the main features of the app or an essential function, it is best to restrict its visibility on [Google Play](https://play.google.com/store) to devices that have a camera. To do this, we will need to add the following code to the `AndroidManifest.xml` file right under the `<uses-permission .... />` code we added in step 7.  



    ``` kotlin  
    <uses-feature android:name="android.hardware.camera"  
         android:required="true"/>  
    ```  
   
   
 5. Now navigate back to `app/java/com.example.myprogressapp/MainActivity.kt`. There will be multiple `com.example.myprogressapp` packages. Click the one that does not say `(android)` or `(test)`.
 6. We now need to insert imports in `MainActivity class`. We are going to import most right now to get it out of the way for later. Feel free to copy and paste to save time!
 
 

    ``` kotlin
    import android.app.Activity
    import android.content.Intent
    import android.content.pm.PackageManager
    import android.net.Uri
    import android.os.Build
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
    import androidx.recyclerview.widget.LinearLayoutManager
    import androidx.recyclerview.widget.RecyclerView
    import java.io.File
    import java.io.IOException
    import java.text.SimpleDateFormat
    import java.util.*
    import kotlin.collections.ArrayList
    ```
 
 
 7. We are going to end up checking the permissions in a `Capture Button` click. If the user has already given permissions to access the camera and external storage, then we can directly open the camera to take a picture. However, we will need the function `onRequestPermissionsResult()` to open the camera after the request for permissions and if the user accepted it. Insert the following function into the `MainActivity class`. Leave `takePicture()` commented out for now. Also insert the `val` variables at the top of your class. 
 
 
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
    private val PERMISSION_REQUEST_CODE: Int = 101
    ```
    
    
    
    
8. Now, we will be creating a function `private fun createFile() : File` to create a file that stores an image that is taken by the camera. We need this before we make the method that takes the picture. This file allows us to write and read different images from a file. This is how we get the path of the file in order to update our `RecyclerView` accordingly. Insert the following function into your `MainAcitvity.kt class`. In addition, there will be an error due `currentPhotoPath = absolutePath`. We will fix this in the following step.
    
 
 
    ``` kotlin
    @Throws(IOException::class)
    private fun createFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        // Gets the directroy of picures to store
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
    
 
 
8. Insert the following line in the beginning of the`MainActivity class` . This variable holds file of the photo. In order to access the path, we do it as so: `currentPhotoPath.path`.



    ``` kotlin
    private var currentPhotoPath: String? = null;
    ```
    
    
    
10. Next, we need to configure the `FileProvider` in the `AndroidManifest.xml` located in `app/manifests/` in order to add a provider to our application. This code will be inserted between `<application>` and `</application>`. Do not worry if you see errors.



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
    
    
    
11. Create a new xml file called `file_path.xml` under the `app/res/xml` folder. The `xml` folder is made when you create the file. Right click on `app/res`. Choose `New` -> `Android Resource File`. In the `File name` field, insert `file_path`. For the `Resource Type` choose `XML`, then click `OK` in the bottom right hand corner. Insert the code after clicking `Code` in the toolbar above the `Attribute Pane`, replacing what was default in the file. You MUST add your package name in the path.
    
    
    ``` kotlin
    <?xml version="1.0" encoding="utf-8"?>
    <paths xmlns:android="http://schemas.android.com/apk/res/android">
        <external-path name="my_images"
            path="Android/data/com.example.myprogressapp/files/Pictures" />
    </paths>
    ```
    

12. Navigate back to the `MainActivity class`. We will now insert the take picture function using the code below. This insert starts the camera to capture an image through an intent. Don't worry about the recycler view yet. We will connect it shortly. Uncomment the `takePicture()` line of code in the `onRequestsPermissionsResult(...)` function.
    
    
    
    ``` kotlin
     private fun takePicture() {
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
    ```
    
    
   
13. Now, after the function `takePicture()` opens the camera, we will need to grab the image. We will then need to show this on our `imageView`. In order to get the image into the `ImageView`, add the method to the `MainActivity class`. There will be an error. Insert the second snippet of code at the top of your class.



    ``` kotlin
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
    ```
    
    
    
    ``` kotlin
     // Adapter used to control the recycler view.
    var adapter: ImageRecyclerView? = null

    // These are shared with the recycler view, they contain the paths to each of the image files.
    private val allFilePaths = ArrayList<String>()

    //variable that is storing the latest file created.
    private var currentPhotoPath: String? = null
    ```
    
    
    
14. Add the followings line of code under where you defined the `var` and `val` variables at the top of the class. This will be the button that calls `takePicture()` in order for the user to capture an image. It connects the layout with the backend code.
    

    ``` kotlin
    lateinit var captureButton: Button
    ```
    
    
15. Add the following code to your `override fun onCreate(savedInstanceState: Bundle?)` function. This code sets a listener for when the `Capture Button` is clicked. Once the button is clicked, the user is asked for permission to access the camera (`android.Manifest.permission.CAMERA`) as well as their photos (`READ_EXTERNAL_STORAGE`). The SDK must be greater than or equal to 23 due to Android's newer code. If permission is granted, the `takePhoto()` function is called.



    ``` kotlin
    //Set's up the recycler view. This is where images are stored for now on.
        var rView: RecyclerView = findViewById(R.id.rvImages)
        rView.layoutManager = (LinearLayoutManager(this))
        adapter = ImageRecyclerView(allFilePaths)
        rView.adapter = adapter

        captureButton = findViewById(R.id.btn_capture)
        captureButton.setOnClickListener(View.OnClickListener {
            if(Build.VERSION.SDK_INT >= 23) {
                if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this,                         android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_GALLERY_CAMERA)
                } else {
                    takePicture()
                }
            } else {
                takePicture()
            }

        })}
    })
    ```
    
    

    
    
    
    
    
    
    
    
    
    
        

