package com.michael.harapeko_android.ui.mypage

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.MypageFragmentBinding

class MypageFragment : Fragment() {
  private lateinit var binding: MypageFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.mypage_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    val image = R.mipmap.ic_launcher
    Glide.with(binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(binding.headerImageView)

    binding.headerUserName.text = "おおおおお"

    binding.viewPager.offscreenPageLimit = 2
    binding.viewPager.adapter = MypageTabFragmentPagerAdapter(childFragmentManager)
    binding.tabLayout.setupWithViewPager(binding.viewPager)
  }

  companion object {
    fun newInstance() = MypageFragment()
  }
}
