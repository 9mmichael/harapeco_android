package com.michael.harapeko_android.ui.agree_peko

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.michael.harapeko_android.R

class AgreePekoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.agree_peko_activity)

    val fragment = AgreePekoFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(R.id.containerLayout, fragment)
        .commit()

    supportActionBar?.title = "マッチペコ"
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
