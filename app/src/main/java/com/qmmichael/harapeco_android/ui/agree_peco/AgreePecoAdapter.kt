package com.qmmichael.harapeco_android.ui.agree_peco

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.AgreePecoJoinItemBinding
import com.qmmichael.harapeco_android.model.TimelineItem

class AgreePecoAdapter : RecyclerView.Adapter<AgreePecoAdapter.AgreePecoViewHolder>() {
  val agreePecoList = ArrayList<TimelineItem>()
  lateinit var onClickListener: OnClickAgreePecoItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): AgreePecoViewHolder =
    AgreePecoViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.agree_peco_join_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: AgreePecoViewHolder,
    position: Int
  ) {
    val item = agreePecoList[position]
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

  override fun getItemCount(): Int = agreePecoList.size

  inner class AgreePecoViewHolder(val binding: AgreePecoJoinItemBinding) : RecyclerView.ViewHolder(
      binding.root
  )

  interface OnClickAgreePecoItemListener {
    fun onClickImageView()
  }
}
