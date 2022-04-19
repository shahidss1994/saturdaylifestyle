package com.shock.saturdaylifestyle.ui.login_register.viewmodel

import androidx.lifecycle.ViewModel
import com.shock.saturdaylifestyle.network.LoginRegisterData
import com.shock.saturdaylifestyle.errorProvider.ErrorProvider
import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel  @Inject constructor(val data: LoginRegisterData,
                                          private val errorProvider: ErrorProvider
) : ViewModel() {

  /*  private val _getReferralLink = MutableLiveData<Result<ResponseReferralLinkDM>>()
    val getReferralLink: LiveData<Result<ResponseReferralLinkDM>>
        get() = _getReferralLink


    fun sen(token:String){
        viewModelScope.launch {
            try {
                _getReferralLink.postValue(Result.loading())
                val response= data.getReferralLink(token)
                _getReferralLink.postValue(Result.success(response))
            }
            catch (e: Exception) {
                _getReferralLink.postValue(Result.error(e.message.toString()))
            }
        }
    }*/

}