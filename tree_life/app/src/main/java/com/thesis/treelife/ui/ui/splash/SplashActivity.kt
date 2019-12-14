package com.thesis.treelife.ui.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.thesis.treelife.R
import com.thesis.treelife.ui.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME = 1500L
    private lateinit var myHandler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startSplashScreen()
    }

    private fun startSplashScreen() {
        myHandler = Handler()
        myHandler.postDelayed({
            goToMainActivity()
        }, SPLASH_TIME)
    }

    private fun goToMainActivity(){
        val mainActivityIntent = Intent(applicationContext, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}
