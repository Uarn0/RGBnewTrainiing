package com.example.dekan_training

import HexInput
import HistoryFragment
import RGBInput
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HexInput()
            1 -> RGBInput()
            2 -> HistoryFragment()
            else -> throw IllegalStateException("Unknown position: $position")
        }
    }
}