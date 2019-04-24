package com.qmmichael.harapeco_android.ui.agree_peco

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
import com.qmmichael.harapeco_android.databinding.AgreePecoFragmentBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class AgreePecoFragment : Fragment() {
  private lateinit var binding: AgreePecoFragmentBinding
  private val adapter = AgreePecoAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.agree_peco_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    adapter.onClickListener = object : AgreePecoAdapter.OnClickAgreePecoItemListener {
      override fun onClickImageView() {
        Toast.makeText(context, "tap, item image view", Toast.LENGTH_SHORT).show()
      }
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    binding.recyclerView.adapter = adapter

    val agreePecoList = listOf(
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
    adapter.agreePecoList.addAll(agreePecoList.map {
      TimelineItem(
          it.imageUrl,
          it.userName,
          it.postTime
      )
    })

    val parentPeco = TimelineItem(null, "aaaaaaaa", "11:11")
    binding.timelineItem = parentPeco

    val image = parentPeco.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(binding.headerImageView)
  }

  companion object {
    fun newInstance() = AgreePecoFragment()
  }
}
