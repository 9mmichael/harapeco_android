package com.qmmichael.harapeco_android.ui.mypage

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.MypageListItemBinding

data class Peco(
  val imageUrl: String?,
  val userName: String,
  val postTime: String,
  val participant: Int
)

class MypageAdapter : RecyclerView.Adapter<MypageAdapter.MypageViewHolder>() {
  val pecoList = ArrayList<Peco>()
  lateinit var onClickListener: OnClickMypageListItemListener

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): MypageViewHolder =
    MypageViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.mypage_list_item,
            parent,
            false
        )
    )

  override fun onBindViewHolder(
    holder: MypageViewHolder,
    position: Int
  ) {
    val item = pecoList[position]
    holder.binding.peco = item

    holder.binding.root.setOnClickListener {
      onClickListener.onClickRoot()
    }

    val image = item.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(holder.binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.itemImageView)
  }

  override fun getItemCount(): Int = pecoList.size

  inner class MypageViewHolder(val binding: MypageListItemBinding) : RecyclerView.ViewHolder(
      binding.root
  )
  interface OnClickMypageListItemListener {
    fun onClickRoot()
  }
}
