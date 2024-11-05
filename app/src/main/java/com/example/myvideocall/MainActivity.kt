package com.example.myvideocall

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton
import com.zegocloud.uikit.service.defines.ZegoUIKitUser

class MainActivity : AppCompatActivity() {
    private lateinit var targetUserIdET:EditText
    private lateinit var myUserIdTV:TextView
    private lateinit var videoCallBtn:ZegoSendCallInvitationButton
    private lateinit var voiceCallBtn:ZegoSendCallInvitationButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        targetUserIdET = findViewById(R.id.targetUserIdET)
        myUserIdTV = findViewById(R.id.myUserIdTV)
        videoCallBtn = findViewById(R.id.videoCallBtn)
        voiceCallBtn = findViewById(R.id.voiceCallBtn)

        val myUserId = intent.getStringExtra("userID")
        myUserIdTV.text = "Hi $myUserId, \n Whom do you want to call ?"

        targetUserIdET.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
               val targetUserIdET = targetUserIdET.text.toString()
                if (targetUserIdET.isNotEmpty()){
                    startVideoCall(targetUserIdET)
                    startVoiceCall(targetUserIdET)
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })
    }

    private fun startVideoCall(targetUserId:String){
        val targetUserName:String = targetUserId
        videoCallBtn.setIsVideoCall(true)
        videoCallBtn.resourceID = "zego_uikit_call"
        videoCallBtn.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserName)))

    }

    private fun startVoiceCall(targetUserId:String){
        val targetUserName:String = targetUserId
        voiceCallBtn.setIsVideoCall(false)
        voiceCallBtn.resourceID = "zego_uikit_call"
        voiceCallBtn.setInvitees(listOf(ZegoUIKitUser(targetUserId,targetUserName)))

    }



}