package com.qmmichael.harapeco_android.ui.mypage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.qmmichael.harapeco_android.ui.mypage.MypageTabFragment.MypageListType

class MypageTabFragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager
) {

  private val titles = arrayOf("マイペコリスト", "同意ペコリスト")
  override fun getPageTitle(position: Int): CharSequence? = titles[position]

  override fun getItem(position: Int): Fragment? =
    when (position) {
      0 -> MypageTabFragment.newInstance(MypageListType.MyPeco)
      1 -> MypageTabFragment.newInstance(MypageListType.AgreePeco)
      else -> null
    }

  override fun getCount(): Int = titles.size
}
