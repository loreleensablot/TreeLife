package com.thesis.treelife.treelife.ui.classifier

import android.graphics.Bitmap
import com.thesis.treelife.treelife.ui.classifier.Result

interface Classifier {
    fun recognizeImage(bitmap: Bitmap): Result
}