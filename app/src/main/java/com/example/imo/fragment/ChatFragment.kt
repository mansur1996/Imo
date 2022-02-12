package com.example.imo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imo.Adapter.ChatAdapter
import com.example.imo.R
import com.example.imo.model.Chat
import com.example.imo.model.Message
import com.example.imo.model.Room

class ChatFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container, false)

        initViews(view)

        return view
    }

    private fun initViews(view: View){
        recyclerView = view.findViewById(R.id.rv_chat)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 1)

        refreshAdapter(getAllItems())
    }

    private fun refreshAdapter(chats : ArrayList<Chat>){
        val adapter = ChatAdapter(requireContext(), chats)
        recyclerView.adapter = adapter
    }

    private fun getAllItems():ArrayList<Chat>{
        val rooms : ArrayList<Room> = ArrayList()

        for(i in 0..15){
            rooms.add(Room(R.drawable.img_profile_2, "Mansur Mirzayev$i", "$i"))
        }

        val chats : ArrayList<Chat> = ArrayList()

        //Room
        chats.add(Chat(rooms))

        for (i in 1..7){
            chats.add(Chat(Message(R.drawable.img_profile_2, "Mansur Mirzayev$i", "Hello world$i",true)))
            chats.add(Chat(Message(R.drawable.img_profile_2, "Mansur Mirzayev$i","Hello world$i")))
        }

        return chats
    }
}