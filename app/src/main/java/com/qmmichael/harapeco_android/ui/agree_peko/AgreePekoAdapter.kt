package com.qmmichael.harapeco_android.ui.agree_peko

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.AgreePekoJoinItemBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class AgreePekoAdapter : RecyclerView.Adapter<AgreePekoAdapter.AgreePekoViewHolder>() {
  val agreePekoList = ArrayList<TimelineItem>()
  lateinit var onClickListener: OnClickAgreePekoItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AgreePekoViewHolder =
    AgreePekoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.agree_peko_join_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: AgreePekoViewHolder,
    position: Int
  ) {
    val item = agreePekoList[position]
    holder.binding.timelineItem = item

    holder.binding.itemImageView.setOnClickListener {
      onClickListener.onClickImageView()
    }

    val image = item.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(holder.binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.itemImageView)
  }

  override fun getItemCount(): Int = agreePekoList.size

  inner class AgreePekoViewHolder(val binding: AgreePekoJoinItemBinding) : RecyclerView.ViewHolder(
      binding.root
  )

  interface OnClickAgreePekoItemListener {
    fun onClickImageView()
  }
}
