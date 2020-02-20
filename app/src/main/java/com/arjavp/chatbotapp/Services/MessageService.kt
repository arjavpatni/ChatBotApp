package com.arjavp.chatbotapp.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.arjavp.chatbotapp.Model.MessageUi
import com.arjavp.chatbotapp.Utilities.API_KEY
import com.arjavp.chatbotapp.Utilities.BASE_URL
import com.arjavp.chatbotapp.Utilities.chatBotID
import org.json.JSONException

object MessageService {

    val messages = mutableListOf<MessageUi>()

    fun getMessages(msgText: String, context: Context, complete: (Boolean)-> Unit){
        val url = "${BASE_URL}?apiKey=${API_KEY}&chatBotID=${chatBotID}&externalID=Arjav&message=${msgText}"
        Log.d("URL","${url}")
        val messagesRequest = object: JsonObjectRequest(Method.GET, url, null, Response.Listener {response ->
            try{
                val messageBody = response.getJSONObject("message")
                val messageContent = messageBody.getString("message")
                val newMessage = MessageUi(messageContent, MessageUi.RECEIVED_MSG)
                messages.add(newMessage)
                complete(true)
            }catch(e: JSONException){
                Log.d("JSON", e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {
            it.printStackTrace()
            Log.d("ERROR","${it.networkResponse}")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(messagesRequest)

    }

}