package com.arjavp.chatbotapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjavp.chatbotapp.Model.MessageUi
import com.arjavp.chatbotapp.Model.MessageUi.Companion.RECEIVED_MSG
import com.arjavp.chatbotapp.Model.MessageUi.Companion.SENT_MSG

class MessageAdapter(val context: Context, var data: MutableList<MessageUi>): RecyclerView.Adapter<MessageViewHolder<*>>() {

    class SentViewHolder(val view: View) : MessageViewHolder<MessageUi>(view) {
        private val messageContent = view.findViewById<TextView>(R.id.message)

        override fun bind(item: MessageUi) {
            messageContent.text = item.content
        }


    }
    class ReceivedViewHolder(val view: View) : MessageViewHolder<MessageUi>(view) {
        private val messageContent = view.findViewById<TextView>(R.id.message)

        override fun bind(item: MessageUi) {
            messageContent.text = item.content
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder<*> {
        val context = parent.context
        return when(viewType){
            SENT_MSG -> {
                val view = LayoutInflater.from(context).inflate(R.layout.message_right, parent, false)
                SentViewHolder(view)
            }
            RECEIVED_MSG -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.message_left, parent, false)
                ReceivedViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }


    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int = data[position].messageType

    override fun onBindViewHolder(holder: MessageViewHolder<*>, position: Int) {
        val item = data[position]
        Log.d("adapter View", position.toString() + item.content)
        when (holder) {
            is SentViewHolder -> holder.bind(item)
            is ReceivedViewHolder -> holder.bind(item)
            else -> throw IllegalArgumentException()
        }
    }
}