package com.qmmichael.harapeco_android.ui.mypage

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.MypageTabFragmentBinding
import com.qmmichael.harapeco_android.ui.agree_peco.AgreePecoActivity
import com.qmmichael.harapeco_android.ui.my_peco.MyPecoActivity

class MypageTabFragment : Fragment() {

  enum class MypageListType {
    MyPeco,
    AgreePeco
  }

  private lateinit var binding: MypageTabFragmentBinding
  private lateinit var adapter: MypageAdapter

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.mypage_tab_fragment, container, false)

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    val mypageListType = arguments?.getSerializable(KEY_MYPAGE_LIST_TYPE) as MypageListType
    adapter = MypageAdapter()

    adapter.onClickListener = object : MypageAdapter.OnClickMypageListItemListener {
      override fun onClickRoot() {
        when (mypageListType) {
          MypageListType.MyPeco -> {
            startActivity(Intent(activity, MyPecoActivity::class.java))
          }
          MypageListType.AgreePeco -> {
            startActivity(Intent(activity, AgreePecoActivity::class.java))
          }
        }
      }
    }
    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.recyclerView.adapter = adapter

    val myPecoList = listOf(
        Peco(
            null, "あああああ", "11:11", 1
        ),
        Peco(
            null, "いいいいい", "22:22", 0
        ),
        Peco(
            null, "ううううう", "33:33", 100
        )
    )
    val agreePecoList = listOf(
        Peco(
            null, "かかかかか", "11:11", 1
        ),
        Peco(
            null, "ききききき", "22:22", 0
        ),
        Peco(
            null, "くくくくく", "33:33", 100
        )
    )
    when (mypageListType) {
      MypageListType.MyPeco -> {
        adapter.pecoList.addAll(myPecoList.map {
          Peco(
              it.imageUrl,
              it.userName,
              it.postTime,
              it.participant
          )
        })
      }
      MypageListType.AgreePeco -> {
        adapter.pecoList.addAll(agreePecoList.map {
          Peco(
              it.imageUrl,
              it.userName,
              it.postTime,
              it.participant
          )
        })
      }
    }



  }

  companion object {
    private const val KEY_MYPAGE_LIST_TYPE = "KEY_MYPAGE_LIST_TYPE"
    fun newInstance(mypageListType: MypageListType) = MypageTabFragment().apply {
      arguments = Bundle().apply {
        putSerializable(KEY_MYPAGE_LIST_TYPE, mypageListType)
      }
    }
  }
}
