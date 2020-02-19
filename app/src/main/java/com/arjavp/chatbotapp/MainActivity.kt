package com.arjavp.chatbotapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.arjavp.chatbotapp.Model.MessageUi
import com.arjavp.chatbotapp.Services.MessageService
import com.arjavp.chatbotapp.Utilities.BASE_URL
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    lateinit var messageAdapter : MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageAdapter = MessageAdapter(this, MessageService.messages)
        messageListView.adapter = messageAdapter
        val layoutManager = LinearLayoutManager(this)
        messageListView.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    fun sendMsgBtnClick(view:View){
        if(messageText.text.isNotEmpty()){
            val newMessage = MessageUi(messageText.text.toString(),MessageUi.SENT_MSG)
            MessageService.messages.add(newMessage)
            messageAdapter.notifyDataSetChanged()
            messageText.text.clear()
            MessageService.getMessages(messageText.text.toString(),this){complete->
                if(complete){
                    messageAdapter.notifyDataSetChanged()
                    if (messageAdapter.itemCount>0){
                        messageListView.smoothScrollToPosition(messageAdapter.itemCount-1)
                    }
                }
            }

        }
    }
}
