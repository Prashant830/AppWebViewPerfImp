package com.example.nativelib

import android.content.Context

object NativeLib {
    /**
     * A method to show a toast message using native code.
     */
    fun showLog(context: Context?, message: String) {
        // Call the native method through JNI to show the toast
        nativeShowLog(message)
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
    private external fun nativeShowLog(message: String)
}
