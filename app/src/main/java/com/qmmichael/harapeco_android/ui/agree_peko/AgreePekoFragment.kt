package com.qmmichael.harapeco_android.ui.agree_peko

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
import com.qmmichael.harapeco_android.databinding.AgreePekoFragmentBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class AgreePekoFragment : Fragment() {
  private lateinit var binding: AgreePekoFragmentBinding
  private val adapter = AgreePekoAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.agree_peko_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    adapter.onClickListener = object : AgreePekoAdapter.OnClickAgreePekoItemListener {
      override fun onClickImageView() {
        Toast.makeText(context, "tap, item image view", Toast.LENGTH_SHORT).show()
      }
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(context)
    binding.recyclerView.adapter = adapter

    val agreePekoList = listOf(
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
    adapter.agreePekoList.addAll(agreePekoList.map {
      TimelineItem(
          it.imageUrl,
          it.userName,
          it.postTime
      )
    })

    val parentPeko = TimelineItem(null, "aaaaaaaa", "11:11")
    binding.timelineItem = parentPeko

    val image = parentPeko.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(binding.headerImageView)
  }

  companion object {
    fun newInstance() = AgreePekoFragment()
  }
}
