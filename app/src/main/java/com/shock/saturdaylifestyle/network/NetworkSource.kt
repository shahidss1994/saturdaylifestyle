package com.shock.saturdaylifestyle.network


import com.google.gson.JsonObject
import com.shock.saturdaylifestyle.network.ApiCalls
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRegisterData @Inject constructor(private val api: ApiCalls) {

    suspend fun LoginRegisterProcess(obj: JsonObject) = withContext(Dispatchers.IO) {
        val data = api.loginRegister(obj)
        data
    }
}








