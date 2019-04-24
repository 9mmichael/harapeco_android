package com.qmmichael.harapeco_android.ui.my_peco

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.qmmichael.harapeco_android.R

class MyPecoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.my_peco_activity)

    val fragment = MyPecoFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(R.id.containerLayout, fragment)
        .commit()

    supportActionBar?.title = "マイペコ"
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    return when (item?.itemId) {
      android.R.id.home -> {
        finish()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }
  }
}
