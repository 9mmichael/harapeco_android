package com.michael.harapeko_android.ui.timeline

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.TimelineItemBinding
import com.michael.harapeko_android.model.TimelineItem
import com.michael.harapeko_android.ui.timeline.TimelineAdapter.TimelineViewHolder

class TimelineAdapter() : RecyclerView.Adapter<TimelineViewHolder>() {
  val timelineList = ArrayList<TimelineItem>()
  lateinit var onClickListener: OnClickTimelineItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): TimelineViewHolder =
    TimelineViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.timeline_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: TimelineViewHolder,
    position: Int
  ) {
    val item = timelineList[position]
    holder.binding.timelineItem = item

    holder.binding.root.setOnClickListener {
      onClickListener.onClickRoot()
    }

    holder.binding.itemAgreeButton.setOnClickListener {
      onClickListener.onClickAgreeButton()
    }
    val image = item.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(holder.binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.itemImageView)
  }

  override fun getItemCount(): Int = timelineList.size

  inner class TimelineViewHolder(val binding: TimelineItemBinding) : RecyclerView.ViewHolder(
      binding.root
  )

  interface OnClickTimelineItemListener {
    fun onClickRoot()
    fun onClickAgreeButton()
  }
}
