package com.michael.harapeko_android.ui.launch

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.LaunchFragmentBinding

class LaunchFragment : Fragment() {
  private lateinit var binding: LaunchFragmentBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.launch_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    binding.facebookLoginButton.setOnClickListener {
      // TODO: Facebookログイン

    }
  }

  companion object {
    fun newInstance() = LaunchFragment()
  }

}