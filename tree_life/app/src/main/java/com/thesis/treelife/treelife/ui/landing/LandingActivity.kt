package com.thesis.treelife.treelife.ui.landing

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thesis.treelife.R
import com.thesis.treelife.treelife.ui.login.LoginActivity
import com.thesis.treelife.treelife.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_landing.*

class LandingActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing)
        initUI()
    }

    fun initUI() {
        btnLogin.setOnClickListener(this)
        btnRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            btnLogin.id -> launchLogin()
            btnRegister.id -> launchRegister()
        }
    }

    private fun launchRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun launchLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}