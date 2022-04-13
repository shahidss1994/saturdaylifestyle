package com.shock.saturdaylifestyle.utility

import android.app.Activity
import android.app.Dialog
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.provider.Settings
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.util.Base64
import android.util.Log
import android.view.*
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class CommonUtilities {
    companion object {
        var CATEGORY_VALUE = ""
        var INSURANCE_VALUE = ""
        var handler_value = ""

        @JvmStatic
        fun putInt(activity: Context, name: String?, value: Int) {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            preferences.edit().putInt(name, value).apply()
        }

        fun getInt(activity: Context, name: String?): Int {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            return preferences.getInt(name, 0)
        }


        fun putString(activity: Context, name: String?, value: String?) {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            preferences.edit().putString(name, value).apply()
        }

        fun getString(activity: Context, name: String?): String? {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            return preferences.getString(name, "")
        }


        fun putBoolean(activity: Context, name: String?, value: Boolean) {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            preferences.edit().putBoolean(name, value).apply()
        }

        fun getBoolean(activity: Context, name: String?): Boolean {
            val preferences: SharedPreferences =
                activity.getSharedPreferences(Constants.Saturdays, Context.MODE_PRIVATE)
            return preferences.getBoolean(name, false)
        }

        fun clearPrefrences(activity: Context) {
            val preferences: SharedPreferences = activity.getSharedPreferences(
                Constants.Saturdays, Context.MODE_PRIVATE
            )
            preferences.edit().clear().apply()
        }

        //get the current version number and name
        fun getVersionInfo(activity: Activity): String? {
            var versionName = ""
            var versionCode = -1
            try {
                val packageInfo =
                    activity.packageManager.getPackageInfo(activity.packageName, 0)
                versionName = packageInfo.versionName
                versionCode = packageInfo.versionCode
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            Log.e("versionName", "" + versionName)
            Log.e("versionCode", "" + versionCode)
            return versionName
        }


        fun printHashKey(pContext: Context) {
            try {
                val info = pContext.packageManager
                    .getPackageInfo(pContext.packageName, PackageManager.GET_SIGNATURES)
                for (signature in info.signatures) {
                    val md = MessageDigest.getInstance("SHA")
                    md.update(signature.toByteArray())
                    val hashKey = String(Base64.encode(md.digest(), 0))
                    Log.e("Login", "printHashKey() Hash Key: $hashKey")
                }
            } catch (e: NoSuchAlgorithmException) {
                Log.e("Login", "printHashKey()", e)
            } catch (e: Exception) {
                Log.e("Login", "printHashKey()", e)
            }
        }

        fun showToast(context: Context?, msg: String?) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
        }
        fun showToast(context: Context?, msg: Int?) {
            Toast.makeText(context, msg!!, Toast.LENGTH_SHORT).show()
        }


      lateinit  var dialog: Dialog
        fun showLoader(context: Context) {
            dialog = Dialogs.getLoadingDialog(context)
            dialog?.show()
        }

        fun hideLoader() {

            try {
                if (dialog.isShowing) {
                    dialog.cancel()
                    dialog.dismiss()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
        fun addZero(num: Int): String? {
            return if (num < 10) {
                "0$num"
            } else {
                "" + num
            }
        }


        fun changeDateFormat(
            dateStr: String?,
            oldFormat: String,
            newFormat: String
        ): String? {
            val inputFormat = SimpleDateFormat(oldFormat)
            val outputFormat = SimpleDateFormat(newFormat)
            var date: Date? = null
            var str: String? = null
            try {
                date = inputFormat.parse(dateStr)
                str = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()

            }
            return str
        }


        fun getDeviceToken(context: Context): String? {
            val android_id = Settings.Secure.getString(
                context.contentResolver,
                Settings.Secure.ANDROID_ID
            )
            Log.e("android_id", "" + android_id)
            return android_id
        }

        fun getFilePath(
            selectedImage: Uri?,
            context: Context
        ): String? {
            val filePathColumn =
                arrayOf(MediaStore.Images.Media.DATA)
            val cursor =
                context.contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
            cursor!!.moveToFirst()
            val columnIndex = cursor.getColumnIndex(filePathColumn[0])
            val filePath = cursor.getString(columnIndex)
            cursor.close()
            println("File path $filePath")
            return filePath
        }

        fun isConnectingToInternet(context: Context?): Boolean? {
            val connectivity =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (connectivity != null) {
                val info = connectivity.allNetworkInfo
                if (info != null) for (i in info.indices) {
                    if (info[i].state == NetworkInfo.State.CONNECTED) {
                        return true
                    }
                }
            }
            return false
        }


        fun changeStatusBarColor(activity: Activity, color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val window = activity.window
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                window.statusBarColor = color
            }
        }


        fun convertUTCtoLocal(createdAt: String?): String {
            var formatLong: SimpleDateFormat? = null
            var formatShort: SimpleDateFormat? = null
            var timeLong = ""
            try {
                timeLong = createdAt!!
                formatLong = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
                formatShort = SimpleDateFormat("hh:mm aa", Locale.getDefault())
                Log.e("out", "time final: " + formatShort.format(formatLong.parse(timeLong)))


            } catch (e: ParseException) {
                e.printStackTrace()
            }

            return formatShort!!.format(formatLong!!.parse(timeLong))
        }

        fun animleft(activity: Activity?): Animation? {
            return AnimationUtils.loadAnimation(
                activity,
                R.anim.enter_from_leftslow
            )
        }

        fun animRight(activity: Activity?): Animation? {
            return AnimationUtils.loadAnimation(activity, R.anim.enter_from_rightslow)
        }

        fun animShake(activity: Activity?): Animation? {
            return AnimationUtils.loadAnimation(activity, R.anim.shake_animation)
        }

        fun animTopToCenter(activity: Activity?): Animation? {
            return AnimationUtils.loadAnimation(activity, R.anim.grow_from_top)
        }

        fun fadeIn(activity: Activity?): Animation? {
            return AnimationUtils.loadAnimation(activity, R.anim.fade_in_anim)
        }


        fun fireActivityIntent(
            sourceActivity: Activity,
            mIntent: Intent,
            isFinish: Boolean,
            isForward: Boolean
        ) {
            sourceActivity.startActivity(mIntent)
            if (isFinish) {
                sourceActivity.finish()
            }
            if (isForward) {
                sourceActivity.overridePendingTransition(
                    R.anim.slide_in_left,
                    R.anim.slide_out_left
                )
            } else {
                sourceActivity.overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_right
                )
            }
        }


        fun makeTextViewResizable(
            tv: TextView,
            maxLine: Int,
            expandText: String,
            viewMore: Boolean
        ) {
            if (tv.getTag() == null) {
                tv.setTag(tv.getText())
            }
            val vto: ViewTreeObserver = tv.getViewTreeObserver()
            vto.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    val obs: ViewTreeObserver = tv.getViewTreeObserver()
                    obs.removeGlobalOnLayoutListener(this)
                    if (maxLine == 0) {
                        val lineEndIndex: Int = tv.getLayout().getLineEnd(0)
                        val text: String =
                            tv.getText().subSequence(0, lineEndIndex - expandText.length + 1)
                                .toString() + " " + expandText
                        tv.setText(text)
                        tv.setMovementMethod(LinkMovementMethod.getInstance())
                        tv.setText(
                            addClickablePartTextViewResizable(
                                Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                viewMore
                            ), TextView.BufferType.SPANNABLE
                        )
                    } else if (maxLine > 0 && tv.getLineCount() >= maxLine) {
                        val lineEndIndex: Int = tv.getLayout().getLineEnd(maxLine - 1)
                        val text: String =
                            tv.getText().subSequence(0, lineEndIndex - expandText.length + 1)
                                .toString() + " " + expandText
                        tv.setText(text)
                        tv.setMovementMethod(LinkMovementMethod.getInstance())
                        tv.setText(
                            addClickablePartTextViewResizable(
                                Html.fromHtml(tv.getText().toString()), tv, maxLine, expandText,
                                viewMore
                            ), TextView.BufferType.SPANNABLE
                        )
                    } else {
                        val lineEndIndex: Int =
                            tv.getLayout().getLineEnd(tv.getLayout().getLineCount() - 1)
                        val text: String =
                            tv.getText().subSequence(0, lineEndIndex).toString() + " " + expandText
                        tv.setText(text)
                        tv.setMovementMethod(LinkMovementMethod.getInstance())
                        tv.setText(
                            addClickablePartTextViewResizable(
                                Html.fromHtml(tv.getText().toString()),
                                tv,
                                lineEndIndex,
                                expandText,
                                viewMore
                            ), TextView.BufferType.SPANNABLE
                        )
                    }
                }
            })
        }

        fun addClickablePartTextViewResizable(
            strSpanned: Spanned, tv: TextView,
            maxLine: Int, spanableText: String, viewMore: Boolean
        ): SpannableStringBuilder? {
            val str: String = strSpanned.toString()
            val ssb = SpannableStringBuilder(strSpanned)
            if (str.contains(spanableText)) {
                ssb.setSpan(object : MySpannable(false) {
                    override fun onClick(p0: View) {
                        if (viewMore) {
                            tv.setLayoutParams(tv.getLayoutParams())
                            tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE)
                            tv.invalidate()
                            makeTextViewResizable(tv, -1, "less", false)
                        } else {
                            tv.setLayoutParams(tv.getLayoutParams())
                            tv.setText(tv.getTag().toString(), TextView.BufferType.SPANNABLE)
                            tv.invalidate()
                            makeTextViewResizable(
                                tv, 3, "...more", true

                            )
                        }
                    }
                }, str.indexOf(spanableText), str.indexOf(spanableText) + spanableText.length, 0)
            }
            return ssb
        }






        /*      fun getFirebaseToken(activity: Activity?) {
                  FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                      if (!task.isSuccessful) {
                          Log.w(
                              "FCM_TAG",
                              "Fetching FCM registration token failed",
                              task.exception
                          )
                          return@OnCompleteListener
                      }

                      // Get new FCM registration token
                      val token = task.result
                      putString(
                          activity!!,
                          Constants.DEVICE_TOKEN,
                          token
                      )
                      Log.d("FCM_TAG", token.toString())
                  })
              }
      */

        fun isMarshmallow(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
        }

        fun isLollipop(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
        }

        fun isOreo(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
        }

        fun makeShortTimeString(context: Context, secs: Long): String {
            var secs = secs
            val hours: Long
            val mins: Long
            hours = secs / 3600
            secs %= 3600
            mins = secs / 60
            secs %= 60
            val durationFormat = context.resources.getString(
                if (hours == 0L) R.string.durationformatshort else R.string.durationformatlong
            )
            return String.format(durationFormat, hours, mins, secs)
        }


        enum class IdType(val mId: Int) {
            NA(0), Artist(1), Album(2), Playlist(3);

            companion object {
                fun getTypeById(id: Int): IdType {
                    for (type in IdType.values()) {
                        if (type.mId == id) {
                            return type
                        }
                    }
                    throw IllegalArgumentException("Unrecognized id: $id")
                }
            }
        }

        fun isJellyBeanMR2(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2
        }

        fun isJellyBean(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
        }

        fun isJellyBeanMR1(): Boolean {
            return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1
        }

        fun milliSecondsToTimer(milliseconds: Long): String? {
            var finalTimerString = ""
            var secondsString = ""

            // Convert total duration into time
            val hours = (milliseconds / (1000 * 60 * 60)).toInt()
            val minutes = (milliseconds % (1000 * 60 * 60)).toInt() / (1000 * 60)
            val seconds = (milliseconds % (1000 * 60 * 60) % (1000 * 60) / 1000).toInt()
            // Add hours if there
            if (hours > 0) {
                finalTimerString = "$hours:"
            }

            // Prepending 0 to seconds if it is one digit
            secondsString = if (seconds < 10) {
                "0$seconds"
            } else {
                "" + seconds
            }
            finalTimerString = "$finalTimerString$minutes:$secondsString"

            // return timer string
            return finalTimerString
        }
        fun progressToTimer(progress: Int, totalDuration: Int): Int {
            var totalDuration = totalDuration
            var currentDuration = 0
            totalDuration = (totalDuration / 1000)
            currentDuration = (progress.toDouble() / 100 * totalDuration).toInt()

            // return current duration in milliseconds
            return currentDuration * 1000
        }



        fun getAlbumArtUri(albumId: Long): Uri? {
            return ContentUris.withAppendedId(
                Uri.parse("content://media/external/audio/albumart"),
                albumId
            )
        }






        fun customDialog(context: Context, dialogView: Int, heightVal: Float = 0.80f): Dialog {
            val dialog = Dialog(context)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(false)
            dialog.setContentView(dialogView)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.window!!.setGravity(Gravity.CENTER_VERTICAL)


            val width = (context.resources.displayMetrics.widthPixels * 0.80).toInt()
            val height = (context.resources.displayMetrics.heightPixels * 0.75).toInt()
            //val height = (context.resources.displayMetrics.heightPixels * heightVal).toInt()

            dialog.getWindow()?.setLayout(width, height)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));



            dialog.show()
            return dialog
        }


    }







}



