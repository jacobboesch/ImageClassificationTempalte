package com.google.tflite.imageclassification.sample.tflite

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.test.platform.app.InstrumentationRegistry

import org.junit.Test
import org.junit.Assert.*
import java.io.InputStream
import org.junit.Assert


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ClassifierTest {


    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    val testContext = InstrumentationRegistry.getInstrumentation().context

    @Test
    fun useAppContext() {
        assertEquals("com.riis.parrot.cattlecounter", appContext.packageName)
    }

    @Test
    fun testClassifyImage(){
        val classifier:Classifier = Classifier(
                           appContext.assets,
                "model.tflite",
                 "labels.txt",
                  224
        )
        val testImageStream: InputStream = this.testContext.assets.open("test_image.bmp")
        val testImage:Bitmap = BitmapFactory.decodeStream(testImageStream)
        val recognitions = classifier.recognizeImage(testImage)
        Assert.assertNotEquals(0, recognitions.size)
        Assert.assertEquals(recognitions.get(0).title, "yorkshire terrier")
    }

}
