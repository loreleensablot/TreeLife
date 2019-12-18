package com.thesis.treelife.treelife.ui.camera

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.thesis.treelife.treelife.uri.getCroppedBitmap
import com.thesis.treelife.treelife.utils.getUriFromFilePath
import com.thesis.treelife.R
import com.thesis.treelife.treelife.ui.classifier.Classifier
import com.thesis.treelife.treelife.ui.classifier.Result
import kotlinx.android.synthetic.main.fragment_camera.*
import java.io.File

private const val REQUEST_PERMISSIONS = 1
private const val REQUEST_TAKE_PICTURE = 2

class CameraFragment : Fragment() {

    private lateinit var classifier: Classifier
    private var photoFilePath = ""
    private val handler = Handler()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        checkPermissions()
        return inflater.inflate(R.layout.fragment_camera, container, false)
    }

    private fun checkPermissions() {
        if (arePermissionAlreadyGranted()) {
            takePhoto()
        } else {
            requestPermissions()
        }
    }

    private fun arePermissionAlreadyGranted() =
        context?.let {
            ContextCompat.checkSelfPermission(
                it,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        } == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_PERMISSIONS)
    }

    private fun takePhoto() {
        photoFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).absolutePath + "/${System.currentTimeMillis()}.jpg"
        val currentPhotoUri = context?.let { getUriFromFilePath(it, photoFilePath) }

        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri)
        takePictureIntent.flags = Intent.FLAG_GRANT_READ_URI_PERMISSION

        if (activity?.packageManager?.let { takePictureIntent.resolveActivity(it) } != null) {
            this.startActivityForResult(takePictureIntent, REQUEST_TAKE_PICTURE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS && arePermissionGranted(grantResults)) {
            takePhoto()
        } else {
            requestPermissions()
        }
    }

    private fun arePermissionGranted(grantResults: IntArray) =
        grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val file = File(photoFilePath)
        if (requestCode == REQUEST_TAKE_PICTURE && file.exists()) {
//            classifyPhoto(file)
        }
    }

    private fun classifyPhoto(file: File) {
        val photoBitmap = BitmapFactory.decodeFile(file.absolutePath)
        val croppedBitmap = getCroppedBitmap(photoBitmap)
        classifyAndShowResult(croppedBitmap)
        imagePhoto.setImageBitmap(photoBitmap)
    }

    private fun classifyAndShowResult(croppedBitmap: Bitmap) {
        runInBackground(
            Runnable {
                val result = classifier.recognizeImage(croppedBitmap)
                showResult(result)
            })
    }

    @Synchronized
    private fun runInBackground(runnable: Runnable) {
        handler.post(runnable)
    }

    private fun showResult(result: Result) {
        textResult.text = result.result.toUpperCase()
        layoutContainer.setBackgroundColor(getColorFromResult(result.result))
    }

    @Suppress("DEPRECATION")
    private fun getColorFromResult(result: String): Int {
        return if (result == getString(R.string.trees)) {
            resources.getColor(R.color.light_green)
        } else {
            resources.getColor(R.color.red)
        }
    }
}
