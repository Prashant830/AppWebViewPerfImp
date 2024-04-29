#include <jni.h>
#include <string>
#include <android/log.h>
#include <chrono>

extern "C" JNIEXPORT double JNICALL
Java_com_example_nativelib_NativeLib_nativeShowLog(JNIEnv* env, jobject thiz, jstring message) {
    if (message == nullptr ) {
        __android_log_print(ANDROID_LOG_ERROR, "NativeLib", "Invalid parameters");
        double O;
    }

    // Perform multiple iterations
    const int iterations = 10000000;
    std::chrono::duration<double, std::milli> totalDuration(0);
    for (int i = 0; i < iterations; ++i) {
        // Get current time before executing the function
        auto startTime = std::chrono::high_resolution_clock::now();

        // Get current time after executing the function
        auto endTime = std::chrono::high_resolution_clock::now();

        // Calculate duration
        auto duration = endTime - startTime;

        // Accumulate total duration
        totalDuration += duration;
    }

    // Calculate average duration
    double averageDuration = totalDuration.count() / iterations;
    const char* messageStr = env->GetStringUTFChars(message, nullptr);

    // Log the message instead of showing the toast
//    __android_log_print(ANDROID_LOG_DEBUG, "NativeLib", "Log message: %s", messageStr);

    // Release the JNI string
    env->ReleaseStringUTFChars(message, messageStr);

    // Log the average duration
    __android_log_print(ANDROID_LOG_DEBUG, "NativeLib", "Average execution duration: %f ms", averageDuration);

    return averageDuration;

}
