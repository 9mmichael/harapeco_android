package com.qmmichael.harapeco_android.ui.launch

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.qmmichael.harapeco_android.R
import com.qmmichael.harapeco_android.databinding.LaunchFragmentBinding
import com.qmmichael.harapeco_android.ui.timeline.TimelineActivity

class LaunchFragment : Fragment() {
  private lateinit var binding: LaunchFragmentBinding
  private lateinit var auth: FirebaseAuth

  private lateinit var callbackManager: CallbackManager

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    auth = FirebaseAuth.getInstance()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    binding = DataBindingUtil.inflate(inflater, R.layout.launch_fragment, container, false)
    callbackManager = CallbackManager.Factory.create()

    return binding.root
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)

    binding.facebookLoginButton.setFragment(this)
    binding.facebookLoginButton.setReadPermissions("email", "public_profile")
    binding.facebookLoginButton.registerCallback(
        callbackManager, object : FacebookCallback<LoginResult> {
      override fun onSuccess(result: LoginResult) {
        handleFacebookAccessToken(result.accessToken)
      }

      override fun onCancel() {
        Toast.makeText(context, "Cancel, facebook login", Toast.LENGTH_SHORT)
            .show()
      }

      override fun onError(error: FacebookException?) {
        if (error != null) {
          Toast.makeText(context, error.message, Toast.LENGTH_LONG)
              .show()
        }
      }
    })

    binding.mailAddressCreateAccountButton.setOnClickListener {
      val email: String? = binding.mailAddressEditText.text.toString()
      val password: String? = binding.passwordEditText.text.toString()
      createAccountForEmail(email, password)
    }
    binding.mailAddressLoginButton.setOnClickListener {
      val email: String? = binding.mailAddressEditText.text.toString()
      val password: String? = binding.passwordEditText.text.toString()
      loginForEmail(email, password)
    }

    binding.facebookSignoutButton.setOnClickListener {
      auth.signOut()
      LoginManager.getInstance()
          .logOut()
    }
  }

  override fun onStart() {
    super.onStart()
    val currentUser = auth.currentUser
    currentUser?.let {
      startActivity(Intent(activity, TimelineActivity::class.java))
    }
  }

  override fun onActivityResult(
    requestCode: Int,
    resultCode: Int,
    data: Intent?
  ) {
    super.onActivityResult(requestCode, resultCode, data)
    callbackManager.onActivityResult(requestCode, resultCode, data)
  }

  private fun createAccountForEmail(
    email: String?,
    password: String?
  ) {
    if (email == null || password == null) {
      Toast.makeText(context, "なんか入力してください", Toast.LENGTH_SHORT)
          .show()
      return
    } else {
      auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              Toast.makeText(context, "新規作成成功！", Toast.LENGTH_LONG)
                  .show()

              startActivity(Intent(activity, TimelineActivity::class.java))
            } else {
              Toast.makeText(context, "新規作成失敗: " + task.exception?.message, Toast.LENGTH_LONG)
                  .show()
              Log.d("hoge", task.exception?.message)
            }
          }
    }
  }

  private fun loginForEmail(
    email: String?,
    password: String?
  ) {
    if (email == null || password == null) {
      return
    } else {
      auth.createUserWithEmailAndPassword(email, password)
          .addOnCompleteListener { task ->
            if (task.isSuccessful) {
              Toast.makeText(context, "再認証成功！", Toast.LENGTH_LONG)
                  .show()
            } else {
              Toast.makeText(context, "再認証失敗: " + task.exception?.message, Toast.LENGTH_LONG)
                  .show()
              Log.d("hoge", task.exception?.message)
            }
          }
    }
  }

  private fun handleFacebookAccessToken(accessToken: AccessToken) {
    val credential = FacebookAuthProvider.getCredential(accessToken.token)
    auth.signInWithCredential(credential)
        .addOnCompleteListener() { task ->
          if (task.isSuccessful) {
            val user = auth.currentUser

            startActivity(Intent(activity, TimelineActivity::class.java))
          } else {
            auth.signOut()
            LoginManager.getInstance()
                .logOut()
            Toast.makeText(context, "ログイン失敗", Toast.LENGTH_SHORT)
                .show()
            Log.w("log", "launch, signInWithCredential", task.exception)
          }
        }
  }

  companion object {
    fun newInstance() = LaunchFragment()
  }

}