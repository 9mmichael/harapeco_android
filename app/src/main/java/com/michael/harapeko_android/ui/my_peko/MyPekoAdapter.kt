package com.michael.harapeko_android.ui.my_peko

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.MyPekoJoinItemBinding
import com.michael.harapeko_android.model.TimelineItem

class MyPekoAdapter : RecyclerView.Adapter<MyPekoAdapter.MyPekoViewHolder>() {
  val myPekoList = ArrayList<TimelineItem>()
  lateinit var onClickListener: OnClickMyPekoJoinItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MyPekoViewHolder =
    MyPekoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.my_peko_join_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: MyPekoViewHolder,
    position: Int
  ) {
    val item = myPekoList[position]
    holder.binding.timelineItem = item

    holder.binding.itemDenyButton.setOnClickListener {
      onClickListener.onClickDenyButton()
    }
    holder.binding.itemAllowButton.setOnClickListener {
      onClickListener.onClickAllowButton()
    }
    val image = item.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(holder.binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.itemImageView)
  }

  override fun getItemCount(): Int = myPekoList.size

  inner class MyPekoViewHolder(val binding: MyPekoJoinItemBinding) : RecyclerView.ViewHolder(
    binding.root
  )

  interface OnClickMyPekoJoinItemListener {
    fun onClickDenyButton()
    fun onClickAllowButton()
  }
}