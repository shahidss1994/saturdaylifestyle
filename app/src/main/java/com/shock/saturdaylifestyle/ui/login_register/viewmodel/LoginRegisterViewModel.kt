package com.shock.saturdaylifestyle.ui.login_register.viewmodel

/*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.errorProvider.ErrorProvider
import com.shock.saturdaylifestyle.network.LoginRegisterData
import com.shock.saturdaylifestyle.ui.login_register.models.ResponseSendOTP
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




    private var _verifyOTP = MutableLiveData<com.shock.saturdaylifestyle.network.Result<ResponseSendOTP>>()
    val verifyOTP: LiveData<com.shock.saturdaylifestyle.network.Result<ResponseSendOTP>>
        get() = _verifyOTP

    fun verifyOTP(key: String, otp: String){
        viewModelScope.launch {

            try {
                _verifyOTP.postValue(com.shock.saturdaylifestyle.network.Result.loading())
                val response = repository.VerifyOtpProcess(key,"application/x-www-form-urlencoded",otp)
                _verifyOTP.postValue(com.shock.saturdaylifestyle.network.Result.success(response))
            } catch (exception: Exception) {
                _verifyOTP.postValue(com.shock.saturdaylifestyle.network.Result.error(errorProvider.getErrorMessage(exception)))
            }

        }
    }


}*/
