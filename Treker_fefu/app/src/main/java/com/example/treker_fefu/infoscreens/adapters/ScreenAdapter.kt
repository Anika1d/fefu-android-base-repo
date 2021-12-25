package com.example.treker_fefu.infoscreens.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.treker_fefu.infoscreens.fragmentscreens.FriendsFragment
import com.example.treker_fefu.infoscreens.fragmentscreens.UserInfoFragment

class ScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private var masivF = arrayListOf(UserInfoFragment(), FriendsFragment())
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return masivF[position]
    }
}