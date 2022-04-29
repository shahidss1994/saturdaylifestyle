package com.shock.saturdaylifestyle.util

import com.google.gson.Gson
import com.google.gson.JsonParseException
import com.google.gson.reflect.TypeToken

class DataParser {
    companion object {
        inline fun <reified T> fromJson(json: String): T? {
            try {
                return Gson().fromJson(json, object : TypeToken<T>() {}.type)
            } catch (e: JsonParseException) {
                e.printStackTrace()
            }
            return null
        }

        fun <T> getClassData(body: T): String {
            return Gson().toJson(body, object : TypeToken<T>(){}.type)
        }
    }
}