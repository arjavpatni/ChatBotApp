package com.arjavp.chatbotapp.Model

class MessageUi(val content: String, val messageType: Int) {
    companion object{
        const val SENT_MSG = 0
        const val RECEIVED_MSG = 1
    }
}