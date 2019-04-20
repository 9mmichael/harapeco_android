package com.michael.harapeko_android.ui.mypage

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.MypageListItemBinding

data class Peko(
  val imageUrl: String?,
  val userName: String,
  val postTime: String,
  val participant: Int
)

class MypageAdapter : RecyclerView.Adapter<MypageAdapter.MypageViewHolder>() {
  val pekoList = ArrayList<Peko>()
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
    val item = pekoList[position]
    holder.binding.peko = item

    holder.binding.root.setOnClickListener {
      onClickListener.onClickRoot()
    }

    val image = item.imageUrl ?: R.mipmap.ic_launcher
    Glide.with(holder.binding.root)
        .load(image)
        .apply(RequestOptions.circleCropTransform())
        .into(holder.binding.itemImageView)
  }

  override fun getItemCount(): Int = pekoList.size

  inner class MypageViewHolder(val binding: MypageListItemBinding) : RecyclerView.ViewHolder(
      binding.root
  )
  interface OnClickMypageListItemListener {
    fun onClickRoot()
  }
}
