
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
1.  Open Android Studio and select `File` -> `New Project`  
2.  Under the section heading `Phone and Tablet` click the activity labeled `Empty Activity` and click `Next`.    
3.  On the `Configure Your Project` screen, name the application `MyProgressApp`. The `Package name` and `Save location` should update accordingly. If you wish to save into a different spot, click the folder icon on the right.  
4.  Under `Language`, make sure `Kotlin` is chosen from the drop down menu and set the `Minimum SDK` to `API 19: Android 4.4 (KitKat)` to ensure that your application will run on a majority of devices. This can be changed in other projects, however, for this project we are going to stick with `API 19: Android 4.4 (KitKat)`.   
5.  If you have done the steps correctly, you Android Studio will take you to the main editing window on the `MainActivity.kt` file.  

## Start Coding
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
        
 8. 
        

