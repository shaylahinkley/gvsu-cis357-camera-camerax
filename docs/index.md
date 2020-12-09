
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
1. Open Android Studio and select `File` -> `New Project`
2. Under the section heading `Phone and Tablet` click the activity labeled `Empty Activity` and click `Next`
3. On the `Configure Your Project` screen, name the application `MyProgressApp`. The `Package name` and `Save location` should update accordingly. If you wish to save into a different spot, click the folder icon on the right.
4. Under `Language`, make sure `Kotlin` is chosen from the drop down menu and set the `Minimum SDK` to `API 19: Android 4.4 (KitKat)` to ensure that your application will run on a majority of devices. This can be changed in other projects, however, for this project we are going to stick with `API 19: Android 4.4 (KitKat)`.
5. If you have done the steps correctly, you Android Studio will take you to the main editing window on the `MainActivity.kt` file.

## Start Coding
6. On the left hand side of the window, you will see `1: Project`. You can click this to collapse and open the layout of the project. Making sure that the `Project Layout` is not collapsed, ensure in the top left of the panel that `Android` is chosen from the drop down menu. This will help simplify what files you need to look at.
7. Navigate to `app/manifests/AndroidManifest.xml` in the `Project Layout Navigation Panel`. In `AndroidManifest.xml` you must add the following permissions under the line containing the package name `package=".com.example.myprogressapp">`. These permissions allow access to the camera and read/write to external storage.
~~~ kotlin
<uses-permission android:name="android.permission.CAMERA"/>  
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
~~~
