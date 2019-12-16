package com.thesis.treelife.treelife.ui.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.thesis.treelife.R
import com.thesis.treelife.treelife.ui.login.LoginActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        initUi()
    }

    fun initUi() {
        tvAlreadyRegistered.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            tvAlreadyRegistered.id -> LoginActivity.launchLoginScreen(this)
            tvRegister.id -> TODO()
        }
    }

    companion object {
        fun launchRegister(activity: Activity) {
            val intent = Intent(activity, RegisterActivity::class.java)
            activity.startActivity(intent)
            activity.finish()
        }
    }
}