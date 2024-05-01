package com.example.nativelib

import android.content.Context

object NativeLib {
    /**
     * A method to show a toast message using native code.
     */

    // log method
    fun showLog(context: Context?, message: String) : Double {
        // Call the native method through JNI to show the toast
       return nativeShowLog(message)
    }

    // Toast method
    fun showToast(context: Context?, message: String) {
        // Call the native method through JNI to show the toast
        return nativeShowToast(context,message)
    }

    /**
     * Load the native library.
     */
    init {
        System.loadLibrary("nativelib")
    }




    /**
     * Native method declaration.
     */

    // log method declaration in cpp
    private external fun nativeShowLog( message: String) : Double

    // Toast method declaration in cpp
    private external fun nativeShowToast( context: Context?, message: String)

}
