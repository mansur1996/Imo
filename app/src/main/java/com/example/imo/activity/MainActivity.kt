package com.example.imo.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.example.imo.Adapter.RoomAdapter
import com.example.imo.Adapter.ViewPagerAdapter
import com.example.imo.R
import com.example.imo.fragment.ChatFragment
import com.example.imo.fragment.ContactFragment
import com.example.imo.fragment.RoomFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews(){
        val ll = findViewById<LinearLayout>(R.id.ll_my_profile)
        openMe(ll)

        val viewPager = findViewById<ViewPager>(R.id.vp_viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tl_tabLayout)

        setViewPager(viewPager,tabLayout)
    }

    private fun openMe(ll:LinearLayout){
        ll.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this, MeActivity::class.java))
        })
    }

    private fun setIcons(tabLayout:TabLayout){
        val icons= arrayListOf<Int>(
            R.drawable.ic_baseline_chat_24,
            R.drawable.ic_baseline_room_service_24,
            R.drawable.ic_baseline_contact_phone_24
        )

        tabLayout.getTabAt(0)?.setIcon(icons[0])
        tabLayout.getTabAt(1)?.setIcon(icons[1])
        tabLayout.getTabAt(2)?.setIcon(icons[2])
    }

    private fun setViewPager(viewPager:ViewPager, tabLayout : TabLayout){
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.add(ChatFragment())
        adapter.add(RoomFragment())
        adapter.add(ContactFragment())

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)

        setIcons(tabLayout)
    }
}