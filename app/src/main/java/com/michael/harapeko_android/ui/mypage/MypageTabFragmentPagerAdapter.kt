package com.michael.harapeko_android.ui.mypage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.michael.harapeko_android.ui.mypage.MypageTabFragment.MypageListType

class MypageTabFragmentPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager
) {

  private val titles = arrayOf("マイペコリスト", "同意ペコリスト")
  override fun getPageTitle(position: Int): CharSequence? = titles[position]

  override fun getItem(position: Int): Fragment? =
    when (position) {
      0 -> MypageTabFragment.newInstance(MypageListType.MyPeko)
      1 -> MypageTabFragment.newInstance(MypageListType.AgreePeko)
      else -> null
    }

  override fun getCount(): Int = titles.size
}
