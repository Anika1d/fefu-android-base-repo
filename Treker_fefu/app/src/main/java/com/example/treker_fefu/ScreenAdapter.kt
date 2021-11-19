package com.example.treker_fefu

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ScreenAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {


    private var masivF = arrayListOf(UserInfoFragment(), FriendsFragment())
    override fun getItemCount(): Int = 2;

    override fun createFragment(position: Int): Fragment {
        return masivF[position];
    }
}