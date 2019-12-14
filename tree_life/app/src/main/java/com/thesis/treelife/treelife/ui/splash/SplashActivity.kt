package com.thesis.treelife.treelife.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.thesis.treelife.R
import com.thesis.treelife.treelife.ui.landing.LandingActivity
import com.thesis.treelife.treelife.ui.login.LoginActivity
import com.thesis.treelife.treelife.ui.main.MainActivity

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
            gotoLoginScreen()
        }, SPLASH_TIME)
    }

    private fun gotoLoginScreen(){
        val mainActivityIntent = Intent(applicationContext, LoginActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }
}
