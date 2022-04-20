package com.shock.saturdaylifestyle.ui.login_register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.errorProvider.ErrorProvider
import com.shock.saturdaylifestyle.network.LoginRegisterData
import com.shock.saturdaylifestyle.network.Result
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseRegisterUser
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseSendOTP
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseVerifyOTP
//import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

//@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val repository: LoginRegisterData,
    private val errorProvider: ErrorProvider,
) : ViewModel()  {



    private var _sendOTP = MutableLiveData<Result<ResponseSendOTP>>()
    val sendOTP: LiveData<Result<ResponseSendOTP>>
        get() = _sendOTP

    fun sendOTP(key: String, phoneNumber: String,
                countryCode: String) {
        viewModelScope.launch {

            try {
                _sendOTP.postValue(com.shock.saturdaylifestyle.network.Result.loading())
                val response = repository.SendOtpProcess(key,"application/x-www-form-urlencoded",phoneNumber,countryCode)
                _sendOTP.postValue(com.shock.saturdaylifestyle.network.Result.success(response))
            } catch (exception: Exception) {
                _sendOTP.postValue(com.shock.saturdaylifestyle.network.Result.error(errorProvider.getErrorMessage(exception)))
            }

        }
    }




    private var _verifyOTP = MutableLiveData<Result<ResponseVerifyOTP>>()
    val verifyOTP: LiveData<Result<ResponseVerifyOTP>>
        get() = _verifyOTP

    fun verifyOTP(key: String, otp: String){
        viewModelScope.launch {
            try {
                _verifyOTP.postValue(Result.loading())
                val response = repository.VerifyOtpProcess(key,"application/x-www-form-urlencoded",otp)
                _verifyOTP.postValue(Result.success(response))
            } catch (exception: Exception) {
                _verifyOTP.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }

        }
    }

    private var _loginUser = MutableLiveData<Result<ResponseVerifyOTP>>()
    val loginUser: LiveData<Result<ResponseVerifyOTP>>
        get() = _loginUser

    fun loginUser(
                  mobile: String,
                  otp: String,
                  country_code: String,
                  device_token: String){
        viewModelScope.launch {
            try {
                _loginUser.postValue(Result.loading())
                val response = repository.LoginProcess("application/x-www-form-urlencoded",mobile, otp, country_code, device_token, "android")
                _loginUser.postValue(Result.success(response))
            } catch (exception: Exception) {
                _loginUser.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }

        }
    }


    private var _registerUser = MutableLiveData<Result<ResponseRegisterUser>>()
    val registerUser: LiveData<Result<ResponseRegisterUser>>
        get() = _registerUser

    fun registerUser(
        name: String,
        mobile: String,
        country_code: String,
        genderType: Int,
        email: String){
        viewModelScope.launch {
            try {
                _registerUser.postValue(Result.loading())
                val response = repository.RegisterProcess("application/x-www-form-urlencoded",name, mobile, country_code, genderType, email)
                _registerUser.postValue(Result.success(response))
            } catch (exception: Exception) {
                _registerUser.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }

        }
    }


}