package com.shock.saturdaylifestyle.network


import com.shock.saturdaylifestyle.constants.Constants
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkConfigurationImpl @Inject constructor() : NetworkConfiguration {

    companion object {
        private  var BASE_URL = Constants.BASE_URL
    }

    override fun getBaseUrl(): String {
        return BASE_URL
    }


}