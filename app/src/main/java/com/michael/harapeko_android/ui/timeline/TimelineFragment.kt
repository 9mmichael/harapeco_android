package com.michael.harapeko_android.ui.timeline

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.TimelineFragmentBinding
import com.michael.harapeko_android.model.TimelineItem

class TimelineFragment : Fragment() {
  private lateinit var binding: TimelineFragmentBinding
  private val adapter = TimelineAdapter()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.timeline_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    adapter.onClickListener = object : TimelineAdapter.OnClickTimelineItemListener {
      override fun onClickRoot() {
        Toast.makeText(context, "tap, item root", Toast.LENGTH_SHORT).show()
      }

      override fun onClickAgreeButton() {
        Toast.makeText(context, "tap, item agree button", Toast.LENGTH_SHORT).show()
      }
    }

    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.recyclerView.adapter = adapter

    val timelineList = listOf(
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
    adapter.timelineList.addAll(timelineList.map {
      TimelineItem(
          it.imageUrl,
          it.userName,
          it.postTime
      )
    })
  }

  companion object {
    fun newInstance() = TimelineFragment()
  }
}
