package com.qmmichael.harapeco_android.ui.my_peco

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.MyPecoJoinItemBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class MyPecoAdapter : RecyclerView.Adapter<MyPecoAdapter.MyPecoViewHolder>() {
  val myPecoList = ArrayList<TimelineItem>()
  lateinit var onClickListener: OnClickMyPecoJoinItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MyPecoViewHolder =
    MyPecoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.my_peco_join_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: MyPecoViewHolder,
    position: Int
  ) {
    val item = myPecoList[position]
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

  override fun getItemCount(): Int = myPecoList.size

  inner class MyPecoViewHolder(val binding: MyPecoJoinItemBinding) : RecyclerView.ViewHolder(
    binding.root
  )

  interface OnClickMyPecoJoinItemListener {
    fun onClickDenyButton()
    fun onClickAllowButton()
  }
}