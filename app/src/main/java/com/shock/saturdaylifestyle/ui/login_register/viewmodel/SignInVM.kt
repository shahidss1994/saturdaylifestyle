package com.saturdays.login_register.viewmodel

/*
class SignInVM @Inject constructor(private  val signInData: SignInData,
                                   private val errorProvider: ErrorProvider
):ViewModel() {

    private var _signInResult = MutableLiveData<Result<ResponseAuthDM>>()

    val signInResult: LiveData<Result<ResponseAuthDM>>
        get() = _signInResult

    fun signInUser(user_email:String,user_pwd:String,device_token:String){
        viewModelScope.launch {
            try {
                _signInResult.postValue(Result.loading())
                val response = signInData.SignInProcess("application/x-www-form-urlencoded",2,
                    device_token,user_pwd,user_email)
                _signInResult.postValue(Result.success(response))
            } catch (exception: Exception) {
                _signInResult.postValue(Result.error(errorProvider.getErrorMessage(exception)))
            }
        }
    }


}*/


