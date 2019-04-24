package com.qmmichael.harapeco_android.ui.timeline

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.TimelineActivityBinding
import com.qmmichael.harapeco_android.ui.mypage.MypageFragment

class TimelineActivity : AppCompatActivity() {

  private lateinit var binding: TimelineActivityBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.timeline_activity)
    binding = DataBindingUtil.setContentView(this, R.layout.timeline_activity)

    binding.bottomNavigationView.selectedItemId = R.id.tab_timeline
    binding.bottomNavigationView.setOnNavigationItemSelectedListener {
      when (it.itemId) {
        R.id.tab_timeline -> {
          val fragment = TimelineFragment.newInstance()
          supportFragmentManager.beginTransaction()
              .replace(R.id.containerLayout, fragment)
              .commit()
          true
        }
        R.id.tab_mypage -> {
          val fragment = MypageFragment.newInstance()
          supportFragmentManager.beginTransaction()
              .replace(R.id.containerLayout, fragment)
              .commit()
          true
        }
        else -> {
          false
        }
      }
    }

    val fragment = TimelineFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(R.id.containerLayout, fragment)
        .commit()

    supportActionBar?.title = ""
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.action_bar_timeline, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      R.id.tab_timeline -> {
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
