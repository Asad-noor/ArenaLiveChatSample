package com.example.arenalivechatsample

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.fragment.app.FragmentActivity
import im.arena.chat.ChatViewModel
import im.arena.chat.activity.ChatActivity
import im.arena.commons.LogLevel
import im.arena.realtimedata.Environment
import im.arena.realtimedata.model.ExternalUser
import kotlin.random.Random


class ActivityChat : FragmentActivity() {
    private lateinit var activityLauncher: ActivityResultLauncher<Intent>
    private val activityResultCallback = ActivityResultCallback<ActivityResult> {
        if (it.resultCode == RESULT_FIRST_USER) {
            Toast
                .makeText(this, "Login with your system", Toast.LENGTH_SHORT)
                .show()

            ChatViewModel
                .externalUser(
                    ExternalUser(
                        "123123",
                        "Roberto",
                        "roberto@gmail.com",
                        "https://randomuser.me/api/portraits/women/${
                            Random.nextInt(
                                0,
                                50
                            )
                        }.jpg",
                        "Silva",
                        "Lima"
                    )
                )

            ChatActivity
                .start(this, activityLauncher)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        activityLauncher = registerForActivityResult()

        showAlertDialogChat()
    }

    private fun registerForActivityResult() = (this as ComponentActivity).registerForActivityResult(
        ActivityResultContracts.StartActivityForResult(), activityResultCallback
    )

    private fun showAlertDialogChat() {
        ChatActivity
            .logLevel(LogLevel.DEBUG)
            .environment(Environment.PRODUCTION)
            .configure(
                application,
                "bongo-15773",
                "58r1"
            )

        ChatActivity
            .start(this, activityLauncher)

        finish()
    }
}