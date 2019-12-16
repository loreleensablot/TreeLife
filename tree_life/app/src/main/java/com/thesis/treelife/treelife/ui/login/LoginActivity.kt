package com.thesis.treelife.treelife.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thesis.treelife.R
import com.thesis.treelife.treelife.ui.main.MainActivity
import com.thesis.treelife.treelife.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initUI()
    }

    fun initUI() {
        btnLogin.setOnClickListener(this)
        tvRegister.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            btnLogin.id -> launchHome()
            tvRegister.id -> RegisterActivity.launchRegister(this)
        }
    }


    private fun launchHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    companion object {
        fun launchLoginScreen(activity: Activity) {
            val intent = Intent(activity, LoginActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}