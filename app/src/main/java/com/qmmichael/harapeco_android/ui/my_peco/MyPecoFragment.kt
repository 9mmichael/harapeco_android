package com.qmmichael.harapeco_android.ui.my_peco

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
import com.qmmichael.harapeco_android.databinding.MyPecoFragmentBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class MyPecoFragment : Fragment() {
  private lateinit var binding: MyPecoFragmentBinding
  private val adapter = MyPecoAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.my_peco_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    adapter.onClickListener = object : MyPecoAdapter.OnClickMyPecoJoinItemListener {
      override fun onClickDenyButton() {
        Toast.makeText(context, "tap, item deny button", Toast.LENGTH_SHORT).show()
      }

      override fun onClickAllowButton() {
        Toast.makeText(context, "tap, item allow button", Toast.LENGTH_SHORT).show()
      }
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.recyclerView.adapter = adapter

    val myPecoList = listOf(
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
    adapter.myPecoList.addAll(myPecoList.map {
      TimelineItem(
          it.imageUrl,
          it.userName,
          it.postTime
      )
    })

    val myPeco = TimelineItem(null, "おおおお", "11:11")
    binding.timelineItem = myPeco

    val image = myPeco.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(binding.headerImageView)
  }

  companion object {
    fun newInstance() = MyPecoFragment()
  }
}
