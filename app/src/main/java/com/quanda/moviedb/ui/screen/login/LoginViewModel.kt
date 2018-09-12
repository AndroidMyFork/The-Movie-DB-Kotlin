package com.quanda.moviedb.ui.screen.login

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.core.util.PatternsCompat
import android.text.TextUtils
import com.quanda.moviedb.data.constants.Constants
import com.quanda.moviedb.ui.base.BaseViewModel
import javax.inject.Inject

class LoginViewModel @Inject constructor(

) : BaseViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val formValid = MediatorLiveData<Boolean>().apply {
        value = false
        addSource(email) { value = validateForm(email.value, password.value) }
        addSource(password) { value = validateForm(email.value, password.value) }
    }

    private fun validateForm(email: String?, password: String?): Boolean = validateEmail(
            email) && validatePassword(password)

    private fun validateEmail(email: String?): Boolean = email != null
            && !TextUtils.isEmpty(email.trim())
            && PatternsCompat.EMAIL_ADDRESS.matcher(email.trim()).matches()

    private fun validatePassword(password: String?): Boolean = password != null
            && !TextUtils.isEmpty(password.trim())
            && password.trim().length >= Constants.MIN_PASSWORD_LENGTH

    fun clickLogin() {

    }
}