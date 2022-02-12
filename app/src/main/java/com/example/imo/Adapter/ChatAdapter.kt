package com.example.imo.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imo.R
import com.example.imo.model.Chat
import com.example.imo.model.Room

class ChatAdapter(val context: Context, val items : ArrayList<Chat>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_ITEM_ROOM = 0
    private val TYPE_ITEM_MESSAGE = 1

    override fun getItemViewType(position: Int): Int {
        val feed = items[position]

        if(feed.rooms.size > 0) return TYPE_ITEM_ROOM
        return TYPE_ITEM_MESSAGE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType == TYPE_ITEM_ROOM){
            val view = LayoutInflater.from(context).inflate(R.layout.item_chat_room, parent, false)
            return RoomViewHolder(context, view)
        }
        val view = LayoutInflater.from(context).inflate(R.layout.item_chat_contact,parent,false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = items[position]

        if(holder is RoomViewHolder){
            val recyclerView = holder.recyclerView
            refreshAdapter(message.rooms, recyclerView)
        }

        if(holder is MessageViewHolder){
            holder.apply {
                iv_profile.setImageResource(message.message!!.profile)
                tv_fullname.text = message.message!!.fullname
                tv_message.text = message.message!!.message
                if(message.message!!.isOnline){
                    is_online.visibility = View.VISIBLE
                }else{
                    is_online.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount() = items.size

    fun refreshAdapter(rooms : ArrayList<Room>, recyclerView: RecyclerView){
        val adapter = RoomAdapter(context, rooms)
        recyclerView.adapter = adapter
    }

    class RoomViewHolder(context: Context, view : View) : RecyclerView.ViewHolder(view){
        var recyclerView : RecyclerView = view.findViewById(R.id.recyclerView_room)

        init {
            val manager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            recyclerView.layoutManager = manager
        }
    }

    class MessageViewHolder(view : View) : RecyclerView.ViewHolder(view){
        var iv_profile = view.findViewById<ImageView>(R.id.iv_profile)
        var tv_fullname = view.findViewById<TextView>(R.id.tv_fullname)
        var tv_message = view.findViewById<TextView>(R.id.tv_massage)
        var is_online = view.findViewById<LinearLayout>(R.id.is_online)
    }
}