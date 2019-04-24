package com.qmmichael.harapeco_android.ui.my_peko

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.MyPekoFragmentBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class MyPekoFragment : Fragment() {
  private lateinit var binding: MyPekoFragmentBinding
  private val adapter = MyPekoAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.my_peko_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    adapter.onClickListener = object : MyPekoAdapter.OnClickMyPekoJoinItemListener {
      override fun onClickDenyButton() {
        Toast.makeText(context, "tap, item deny button", Toast.LENGTH_SHORT).show()
      }

      override fun onClickAllowButton() {
        Toast.makeText(context, "tap, item allow button", Toast.LENGTH_SHORT).show()
      }
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.recyclerView.adapter = adapter

    val myPekoList = listOf(
        TimelineItem(
            null, "aaaaaaaa", "11:11"
        ),
        TimelineItem(
            null, "bbbbbbbb", "22:22"
        ),
        TimelineItem(
            null, "cccccccc", "33:33"
        )
    )
    adapter.myPekoList.addAll(myPekoList.map {
      TimelineItem(
          it.imageUrl,
          it.userName,
          it.postTime
      )
    })

    val myPeko = TimelineItem(null, "おおおお", "11:11")
    binding.timelineItem = myPeko

    val image = myPeko.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(binding.headerImageView)
  }

  companion object {
    fun newInstance() = MyPekoFragment()
  }
}
