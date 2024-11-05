package com.example.myvideocall

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig

class LoginActivity : AppCompatActivity() {
    private lateinit var myUserId: EditText
    private lateinit var loginButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        myUserId = findViewById<EditText>(R.id.myUserId)
        loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val myUserId = myUserId.text.toString()
            if (myUserId.isNotEmpty()){
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                intent.putExtra("userID", myUserId)
                startActivity(intent)

                setupZegoUIKit(myUserId)
            }
        }
    }

    private fun setupZegoUIKit(userId: String) {
        val application: Application = application
        val appId: Long = 191497033
        val appSign: String = "deb2d0ec19132ec19950832630cbbe502689025c8448c7e179e24974cd7716ed"
        val userName: String = userId
        val callInvitationConfiq = ZegoUIKitPrebuiltCallInvitationConfig()
        ZegoUIKitPrebuiltCallService.init(
            application,
            appId,
            appSign,
            userId,
            userName,
            callInvitationConfiq
        )


    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallService.unInit()
    }
}