package com.michael.harapeko_android.ui.mypage

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.michael.harapeko_android.R
import com.michael.harapeko_android.databinding.MypageTabFragmentBinding
import com.michael.harapeko_android.ui.my_peko.MyPekoActivity

class MypageTabFragment : Fragment() {

  enum class MypageListType {
    MyPeko,
    AgreePeko
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
          MypageListType.MyPeko -> {
            startActivity(Intent(activity, MyPekoActivity::class.java))
          }
          MypageListType.AgreePeko -> {
            Toast.makeText(context, "tap, item agree peko", Toast.LENGTH_SHORT).show()
          }
        }
      }
    }
    binding.recyclerView.layoutManager = LinearLayoutManager(activity)
    binding.recyclerView.adapter = adapter

    val myPekoList = listOf(
        Peko(
            null, "あああああ", "11:11", 1
        ),
        Peko(
            null, "いいいいい", "22:22", 0
        ),
        Peko(
            null, "ううううう", "33:33", 100
        )
    )
    val agreePekoList = listOf(
        Peko(
            null, "かかかかか", "11:11", 1
        ),
        Peko(
            null, "ききききき", "22:22", 0
        ),
        Peko(
            null, "くくくくく", "33:33", 100
        )
    )
    when (mypageListType) {
      MypageListType.MyPeko -> {
        adapter.pekoList.addAll(myPekoList.map {
          Peko(
              it.imageUrl,
              it.userName,
              it.postTime,
              it.participant
          )
        })
      }
      MypageListType.AgreePeko -> {
        adapter.pekoList.addAll(agreePekoList.map {
          Peko(
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
