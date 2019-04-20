package com.michael.harapeko_android.ui.launch

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.michael.harapeko_android.R
import com.michael.harapeko_android.R.layout

class LaunchActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.launch_activity)

    val fragment = LaunchFragment.newInstance()
    supportFragmentManager.beginTransaction()
        .replace(R.id.containerLayout, fragment)
        .commit()

    supportActionBar?.title = ""
  }
}
