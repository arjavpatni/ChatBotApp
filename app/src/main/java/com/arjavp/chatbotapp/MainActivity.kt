package com.arjavp.chatbotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arjavp.chatbotapp.Utilities.BASE_URL
class MainActivity : AppCompatActivity() {


    lateinit var messageAdapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
