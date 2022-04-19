package com.shock.saturdaylifestyle.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import java.lang.ref.WeakReference


/**
 * @author mogarg
 */
class PrefUtils(val context: Context) {

    companion object {
        var PRIVATE_MODE = 0
        const val REAL_ESTATE_PREF = "REAL_ESTATE_PREF"
        const val DEVICE_TOKEN = "DEVICE_TOKEN"
    }


    private val TAG = "PrefUtils"
    private var sharedPrefs: SharedPreferences =
        context.getSharedPreferences(REAL_ESTATE_PREF, PRIVATE_MODE)

    /**
     * Called to save supplied value in shared preferences against given key.
     *
     * @param context Context of caller activity
     * @param key     Key of value to save against
     * @param value   Value to save
     */
    fun saveToPrefs(key: String?, value: Any) {
        val contextWeakReference: WeakReference<Context> = WeakReference<Context>(context)
        if (contextWeakReference.get() != null) {
            val editor = sharedPrefs.edit()
            if (value is Int) {
                editor.putInt(key, value.toInt())
            } else if (value is String) {
                editor.putString(key, value.toString())
            } else if (value is Boolean) {
                editor.putBoolean(key, value)
            } else if (value is Long) {
                editor.putLong(key, value.toLong())
            } else if (value is Float) {
                editor.putFloat(key, value.toFloat())
            } else if (value is Double) {
                editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
            }
            editor.commit()
        }
    }

    /**
     * Called to retrieve required value from shared preferences, identified by given key.
     * Default value will be returned of no value found or error occurred.
     *
     * @param context      Context of caller activity
     * @param key          Key to find value against
     * @param defaultValue Value to return if no data found against given key
     * @return Return the value found against given key, default if not found or any error occurs
     */
    fun getFromPrefs(key: String?, defaultValue: Any): Any? {
        val contextWeakReference = WeakReference(context)
        if (contextWeakReference.get() != null) {
            try {
                if (defaultValue is String) {
                    return sharedPrefs.getString(key, defaultValue.toString())
                } else if (defaultValue is Int) {
                    return sharedPrefs.getInt(key, defaultValue.toInt())
                } else if (defaultValue is Boolean) {
                    return sharedPrefs.getBoolean(key, defaultValue)
                } else if (defaultValue is Long) {
                    return sharedPrefs.getLong(key, defaultValue.toLong())
                } else if (defaultValue is Float) {
                    return sharedPrefs.getFloat(key, defaultValue.toFloat())
                } else if (defaultValue is Double) {
                    return java.lang.Double.longBitsToDouble(
                        sharedPrefs.getLong(
                            key, java.lang.Double.doubleToLongBits(
                                defaultValue
                            )
                        )
                    )
                }
            } catch (e: Exception) {
                Log.e(TAG, e.message.toString())
                return defaultValue
            }
        }
        return defaultValue
    }

    /**
     * @param context Context of caller activity
     * @param key     Key to delete from SharedPreferences
     */
    fun removeFromPrefs(key: String?) {
        val contextWeakReference = WeakReference(context)
        if (contextWeakReference.get() != null) {
            val editor = sharedPrefs.edit()
            editor.remove(key)
            editor.commit()
        }
    }


    /**
     * @param key Key check in the SharedPreferences
     */
    fun hasKey(key: String?): Boolean {
        val contextWeakReference = WeakReference(context)
        if (contextWeakReference.get() != null) {
            return sharedPrefs.contains(key)
        }
        return false
    }
}