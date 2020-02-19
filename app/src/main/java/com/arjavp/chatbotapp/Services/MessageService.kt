package com.arjavp.chatbotapp.Services

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.arjavp.chatbotapp.Model.MessageUi
import com.arjavp.chatbotapp.Utilities.BASE_URL
import org.json.JSONException

object MessageService {

    val messages = mutableListOf<MessageUi>()

    fun getMessages(context: Context, complete: (Boolean)-> Unit){
        val url = BASE_URL
        val messagesRequest = object: JsonArrayRequest(Method.GET, url, null, Response.Listener { response ->
            try{
                for(x in 0 until response.length()){
                    val message = response.getJSONObject(x)
                    val messageBody = message.getString("message")
                    val chatBotId = message.getString("chatBotID")

                    val newMessage = MessageUi(messageBody, MessageUi.RECEIVED_MSG)
                    messages.add(newMessage)
                }
                complete(true)
            }catch(e: JSONException){
                Log.d("JSON", e.localizedMessage)
                complete(false)
            }
        }, Response.ErrorListener {
            Log.d("ERROR","Could not ")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }
        }
        Volley.newRequestQueue(context).add(messagesRequest)

    }

}