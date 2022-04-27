package com.shock.saturdaylifestyle.ui.loginRegister.viewModel

import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioGroup
import androidx.core.util.PatternsCompat
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.network.Resource
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.base.viewState.ColorViewState
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState
import com.shock.saturdaylifestyle.ui.loginRegister.model.RegisterFormModel
import com.shock.saturdaylifestyle.ui.loginRegister.model.SendOtpModel
import com.shock.saturdaylifestyle.ui.loginRegister.network.LoginRegisterRepository
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.CountryCodeNumberViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.RegisterFormViewState
import com.shock.saturdaylifestyle.utility.CommonUtilities
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val repository: LoginRegisterRepository
) : BaseViewModel(repository) {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()

    val phoneNoTextWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.PHONE_NO)
    val firstNameWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.FIRST_NAME)
    val lastNameWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.LAST_NAME)
    val emailWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.EMAIL)
    val formPhoneNoTextWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.EMAIL)

    val countryCodeWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.COUNTRY_CODE)

    val optWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.OTP)

    val genderCheckChangeListener = RadioCheckChangeListener()

    val viewState = LoginRegisterViewState()
    var registerFormViewState = RegisterFormViewState()
    val registerFormModel = RegisterFormModel()

    val mCountryCodeList = arrayListOf<CountryCodeNumberViewState>()
    private val timer = TryAgainTimer()

    init {
        setViewPagerListData()
        setCountryCodeNumberList()
    }

    fun onLoginCreateAccountClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT))

        viewState.loginOrCreateAccountVisibility = true
        viewState.chooseVerificationMethodVisibility = false
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }


    fun onContinueClicked() {
        if (viewState.phoneNo.length >= 8) {
            viewState.loginOrCreateAccountVisibility = false

        }
    }


    fun onSendOTPViaMissedCallClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = false
        viewState.missedCallPopupVisibility = true
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onSendOTPViaMissedCallContinueClicked() {
        onEvent(Event.ToggleLoader(true))
        viewModelScope.launch {
            val rs = repository.sendOtpViaMissedCall(
                Constants.API_KEY,
                viewState.phoneNo,
                viewState.countryCodeNumberViewState.code ?: ""
            )
            onEvent(Event.ToggleLoader(false))
            if (rs is Resource.Success) {
                onEvent(Event.SendOtpViaMissedCallResponse(rs.value))
            }
        }
    }

    fun onSendOTPViaWhatsappClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER))
    }

    fun onSendOTPViaSMSClicked(fromTryAgain: Boolean = false) {
        onEvent(Event.ToggleLoader(true))
        viewModelScope.launch {
            val rs = repository.sendOtpViaSMS(
                Constants.API_KEY,
                viewState.phoneNo,
                viewState.countryCodeNumberViewState.code ?: ""
            )
            CommonUtilities.hideLoader()
            if (rs is Resource.Success) {
                onEvent(Event.SendOtpViaSMSResponse(rs.value))
                if (fromTryAgain) {
                    startTryAgainTimer()
                }
            } else if (rs is Resource.Failure) {
                onEvent(Event.SendOtpViaSMSResponse())
            }
        }
    }

    fun onMissedCallPopupBackClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = true
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onMissedCallPopupOtherMethodClicked() {
        viewState.loginOrCreateAccountVisibility = false
        viewState.chooseVerificationMethodVisibility = true
        viewState.missedCallPopupVisibility = false
        viewState.stillDidntGetOtpPopupVisibility = false
    }

    fun onBackPressed() {
        onEvent(Event.OnBackPressed)
    }

    fun showCountryCodePicker() {
        onEvent(Event.PickerDialog)
    }

    fun onWhatsAppTryAgainClicked() {

    }

    fun onGmailLoginClicked() {

    }

    fun onPrivacyPolicyClicked() {

    }

    fun onRegisterFormSaveAndContinueClicked() {
        validateForm(true)
    }

    fun onSkipButtonClicked() {

    }

    fun showRegisterForm() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.REGISTER_FORM))
    }

    fun showDateDialog() {
        onEvent(Event.DateDialogPicker)
    }

    fun onChooseVerificationBackClicked() {
        viewState.loginOrCreateAccountVisibility = true
    }


    fun onSendOTPTryAgain() {

        if (viewState.sendOtpSmsTryAgainClickCount < 2) {
            viewState.sendOtpSmsTryAgainClickCount = viewState.sendOtpSmsTryAgainClickCount + 1

            viewState.sendOtpSmsTryAgainVisibility = false
            onSendOTPViaSMSClicked(true)

        } else {

            onEvent(Event.NavigateTo(Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT))

            viewState.loginOrCreateAccountVisibility = false
            viewState.chooseVerificationMethodVisibility = false
            viewState.missedCallPopupVisibility = false
            viewState.stillDidntGetOtpPopupVisibility = true

            viewState.phoneNoStillNoOtp = viewState.phoneNo

        }
    }

    fun checkSelectedCountry(countryCodeNumberViewState: CountryCodeNumberViewState) {
        viewState.countryCodeNumberViewState = countryCodeNumberViewState
        onEvent(Event.PickerDialogClose)
    }

    private fun onEvent(event: Event) {
        viewModelScope.launch { eventChannel.send(event) }
    }

    sealed class Event {
        object OnBackPressed : Event()
        object PickerDialog : Event()
        object PickerDialogClose : Event()
        data class NavigateTo(val navigateTo: String) : Event()
        data class SendOtpViaMissedCallResponse(val response: SendOtpModel) : Event()
        data class SendOtpViaSMSResponse(val response: SendOtpModel? = null) : Event()
        data class ToggleLoader(val isToShow: Boolean) : Event()
        object DateDialogPicker : Event()
    }

    private fun setViewPagerListData() {
        val arrayList = arrayListOf<IntroViewPagerItemViewState>()
        val introViewPagerItemViewState1 = IntroViewPagerItemViewState(
            1,
            "ANYWHERE IN THE WORLD!",
            "Enjoy free shipping all across the globe\nonly for you!",
            DrawableViewState(R.mipmap.iv_onboarding1)
        )
        arrayList.add(introViewPagerItemViewState1)

        val introViewPagerItemViewState2 = IntroViewPagerItemViewState(
            2,
            "PRICE INCLUDES\nPRESCRIPTION LENSES",
            "Starting from Rp1.295k, we promise there\nwon’t be any hidden cost! Unless you want\nto upgrade your lenses!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState2)

        val introViewPagerItemViewState3 = IntroViewPagerItemViewState(
            3,
            "HOME TRY ON",
            "Try our entire collection (100+ frames) and\neye exam all for FREE in the comfort of\nyour home!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState3)

        val introViewPagerItemViewState4 = IntroViewPagerItemViewState(
            4,
            "SAVE MORE BUCKS!",
            "Enjoy our exclusive promos as well as our\nloyalty program to save some more!",
            DrawableViewState(R.mipmap.iv_onboarding2)
        )
        arrayList.add(introViewPagerItemViewState4)

        val introViewPagerItemViewState5 = IntroViewPagerItemViewState(
            5,
            "OFFLINE STORES",
            "Still not sure about how it looks on you?\nTry it on at our offline stores! We are\navailable all across Indonesia.",
            DrawableViewState(R.mipmap.iv_onboarding5)
        )
        arrayList.add(introViewPagerItemViewState5)
        // viewState.introViewPagerItemViewStateList = arrayList
    }

    private fun setCountryCodeNumberList() {
        mCountryCodeList.add(CountryCodeNumberViewState("ad", "376", "Andorra"))
        mCountryCodeList.add(CountryCodeNumberViewState("ae", "971", "United Arab Emirates (UAE)"))
        mCountryCodeList.add(CountryCodeNumberViewState("af", "93", "Afghanistan"))
        mCountryCodeList.add(CountryCodeNumberViewState("ag", "1", "Antigua and Barbuda"))
        mCountryCodeList.add(CountryCodeNumberViewState("ai", "1", "Anguilla"))
        mCountryCodeList.add(CountryCodeNumberViewState("al", "355", "Albania"))
        mCountryCodeList.add(CountryCodeNumberViewState("am", "374", "Armenia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ao", "244", "Angola"))
        mCountryCodeList.add(CountryCodeNumberViewState("aq", "672", "Antarctica"))
        mCountryCodeList.add(CountryCodeNumberViewState("ar", "54", "Argentina"))
        mCountryCodeList.add(CountryCodeNumberViewState("as", "1", "American Samoa"))
        mCountryCodeList.add(CountryCodeNumberViewState("at", "43", "Austria"))
        mCountryCodeList.add(CountryCodeNumberViewState("au", "61", "Australia"))
        mCountryCodeList.add(CountryCodeNumberViewState("aw", "297", "Aruba"))
        mCountryCodeList.add(CountryCodeNumberViewState("ax", "358", "Åland Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("az", "994", "Azerbaijan"))
        mCountryCodeList.add(CountryCodeNumberViewState("ba", "387", "Bosnia And Herzegovina"))
        mCountryCodeList.add(CountryCodeNumberViewState("bb", "1", "Barbados"))
        mCountryCodeList.add(CountryCodeNumberViewState("bd", "880", "Bangladesh"))
        mCountryCodeList.add(CountryCodeNumberViewState("be", "32", "Belgium"))
        mCountryCodeList.add(CountryCodeNumberViewState("bf", "226", "Burkina Faso"))
        mCountryCodeList.add(CountryCodeNumberViewState("bg", "359", "Bulgaria"))
        mCountryCodeList.add(CountryCodeNumberViewState("bh", "973", "Bahrain"))
        mCountryCodeList.add(CountryCodeNumberViewState("bi", "257", "Burundi"))
        mCountryCodeList.add(CountryCodeNumberViewState("bj", "229", "Benin"))
        mCountryCodeList.add(CountryCodeNumberViewState("bl", "590", "Saint Barthélemy"))
        mCountryCodeList.add(CountryCodeNumberViewState("bm", "1", "Bermuda"))
        mCountryCodeList.add(CountryCodeNumberViewState("bn", "673", "Brunei Darussalam"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "bo",
                "591",
                "Bolivia, Plurinational State Of"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("br", "55", "Brazil"))
        mCountryCodeList.add(CountryCodeNumberViewState("bs", "1", "Bahamas"))
        mCountryCodeList.add(CountryCodeNumberViewState("bt", "975", "Bhutan"))
        mCountryCodeList.add(CountryCodeNumberViewState("bw", "267", "Botswana"))
        mCountryCodeList.add(CountryCodeNumberViewState("by", "375", "Belarus"))
        mCountryCodeList.add(CountryCodeNumberViewState("bz", "501", "Belize"))
        mCountryCodeList.add(CountryCodeNumberViewState("ca", "1", "Canada"))
        mCountryCodeList.add(CountryCodeNumberViewState("cc", "61", "Cocos (keeling) Islands"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "cd",
                "243",
                "Congo, The Democratic Republic Of The",
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("cf", "236", "Central African Republic"))
        mCountryCodeList.add(CountryCodeNumberViewState("cg", "242", "Congo"))
        mCountryCodeList.add(CountryCodeNumberViewState("ch", "41", "Switzerland"))
        mCountryCodeList.add(CountryCodeNumberViewState("ci", "225", "Côte D'ivoire"))
        mCountryCodeList.add(CountryCodeNumberViewState("ck", "682", "Cook Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("cl", "56", "Chile"))
        mCountryCodeList.add(CountryCodeNumberViewState("cm", "237", "Cameroon"))
        mCountryCodeList.add(CountryCodeNumberViewState("cn", "86", "China"))
        mCountryCodeList.add(CountryCodeNumberViewState("co", "57", "Colombia"))
        mCountryCodeList.add(CountryCodeNumberViewState("cr", "506", "Costa Rica"))
        mCountryCodeList.add(CountryCodeNumberViewState("cu", "53", "Cuba"))
        mCountryCodeList.add(CountryCodeNumberViewState("cv", "238", "Cape Verde"))
        mCountryCodeList.add(CountryCodeNumberViewState("cw", "599", "Curaçao"))
        mCountryCodeList.add(CountryCodeNumberViewState("cx", "61", "Christmas Island"))
        mCountryCodeList.add(CountryCodeNumberViewState("cy", "357", "Cyprus"))
        mCountryCodeList.add(CountryCodeNumberViewState("cz", "420", "Czech Republic"))
        mCountryCodeList.add(CountryCodeNumberViewState("de", "49", "Germany"))
        mCountryCodeList.add(CountryCodeNumberViewState("dj", "253", "Djibouti"))
        mCountryCodeList.add(CountryCodeNumberViewState("dk", "45", "Denmark"))
        mCountryCodeList.add(CountryCodeNumberViewState("dm", "1", "Dominica"))
        mCountryCodeList.add(CountryCodeNumberViewState("do", "1", "Dominican Republic"))
        mCountryCodeList.add(CountryCodeNumberViewState("dz", "213", "Algeria"))
        mCountryCodeList.add(CountryCodeNumberViewState("ec", "593", "Ecuador"))
        mCountryCodeList.add(CountryCodeNumberViewState("ee", "372", "Estonia"))
        mCountryCodeList.add(CountryCodeNumberViewState("eg", "20", "Egypt"))
        mCountryCodeList.add(CountryCodeNumberViewState("er", "291", "Eritrea"))
        mCountryCodeList.add(CountryCodeNumberViewState("es", "34", "Spain"))
        mCountryCodeList.add(CountryCodeNumberViewState("et", "251", "Ethiopia"))
        mCountryCodeList.add(CountryCodeNumberViewState("fi", "358", "Finland"))
        mCountryCodeList.add(CountryCodeNumberViewState("fj", "679", "Fiji"))
        mCountryCodeList.add(CountryCodeNumberViewState("fk", "500", "Falkland Islands (malvinas)"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "fm",
                "691",
                "Micronesia, Federated States Of"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("fo", "298", "Faroe Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("fr", "33", "France"))
        mCountryCodeList.add(CountryCodeNumberViewState("ga", "241", "Gabon"))
        mCountryCodeList.add(CountryCodeNumberViewState("gb", "44", "United Kingdom"))
        mCountryCodeList.add(CountryCodeNumberViewState("gd", "1", "Grenada"))
        mCountryCodeList.add(CountryCodeNumberViewState("ge", "995", "Georgia"))
        mCountryCodeList.add(CountryCodeNumberViewState("gf", "594", "French Guyana"))
        mCountryCodeList.add(CountryCodeNumberViewState("gh", "233", "Ghana"))
        mCountryCodeList.add(CountryCodeNumberViewState("gi", "350", "Gibraltar"))
        mCountryCodeList.add(CountryCodeNumberViewState("gl", "299", "Greenland"))
        mCountryCodeList.add(CountryCodeNumberViewState("gm", "220", "Gambia"))
        mCountryCodeList.add(CountryCodeNumberViewState("gn", "224", "Guinea"))
        mCountryCodeList.add(CountryCodeNumberViewState("gp", "450", "Guadeloupe"))
        mCountryCodeList.add(CountryCodeNumberViewState("gq", "240", "Equatorial Guinea"))
        mCountryCodeList.add(CountryCodeNumberViewState("gr", "30", "Greece"))
        mCountryCodeList.add(CountryCodeNumberViewState("gt", "502", "Guatemala"))
        mCountryCodeList.add(CountryCodeNumberViewState("gu", "1", "Guam"))
        mCountryCodeList.add(CountryCodeNumberViewState("gw", "245", "Guinea-bissau"))
        mCountryCodeList.add(CountryCodeNumberViewState("gy", "592", "Guyana"))
        mCountryCodeList.add(CountryCodeNumberViewState("hk", "852", "Hong Kong"))
        mCountryCodeList.add(CountryCodeNumberViewState("hn", "504", "Honduras"))
        mCountryCodeList.add(CountryCodeNumberViewState("hr", "385", "Croatia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ht", "509", "Haiti"))
        mCountryCodeList.add(CountryCodeNumberViewState("hu", "36", "Hungary"))
        mCountryCodeList.add(CountryCodeNumberViewState("id", "62", "Indonesia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ie", "353", "Ireland"))
        mCountryCodeList.add(CountryCodeNumberViewState("il", "972", "Israel"))
        mCountryCodeList.add(CountryCodeNumberViewState("im", "44", "Isle Of Man"))
        mCountryCodeList.add(CountryCodeNumberViewState("is", "354", "Iceland"))
        mCountryCodeList.add(CountryCodeNumberViewState("in", "91", "India"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "io",
                "246",
                "British Indian Ocean Territory"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("iq", "964", "Iraq"))
        mCountryCodeList.add(CountryCodeNumberViewState("ir", "98", "Iran, Islamic Republic Of"))
        mCountryCodeList.add(CountryCodeNumberViewState("it", "39", "Italy"))
        mCountryCodeList.add(CountryCodeNumberViewState("je", "44", "Jersey "))
        mCountryCodeList.add(CountryCodeNumberViewState("jm", "1", "Jamaica"))
        mCountryCodeList.add(CountryCodeNumberViewState("jo", "962", "Jordan"))
        mCountryCodeList.add(CountryCodeNumberViewState("jp", "81", "Japan"))
        mCountryCodeList.add(CountryCodeNumberViewState("ke", "254", "Kenya"))
        mCountryCodeList.add(CountryCodeNumberViewState("kg", "996", "Kyrgyzstan"))
        mCountryCodeList.add(CountryCodeNumberViewState("kh", "855", "Cambodia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ki", "686", "Kiribati"))
        mCountryCodeList.add(CountryCodeNumberViewState("km", "269", "Comoros"))
        mCountryCodeList.add(CountryCodeNumberViewState("kn", "1", "Saint Kitts and Nevis"))
        mCountryCodeList.add(CountryCodeNumberViewState("kp", "850", "North Korea"))
        mCountryCodeList.add(CountryCodeNumberViewState("kr", "82", "South Korea"))
        mCountryCodeList.add(CountryCodeNumberViewState("kw", "965", "Kuwait"))
        mCountryCodeList.add(CountryCodeNumberViewState("ky", "1", "Cayman Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("kz", "7", "Kazakhstan"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "la",
                "856",
                "Lao People's Democratic Republic"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("lb", "961", "Lebanon"))
        mCountryCodeList.add(CountryCodeNumberViewState("lc", "1", "Saint Lucia"))
        mCountryCodeList.add(CountryCodeNumberViewState("li", "423", "Liechtenstein"))
        mCountryCodeList.add(CountryCodeNumberViewState("lk", "94", "Sri Lanka"))
        mCountryCodeList.add(CountryCodeNumberViewState("lr", "231", "Liberia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ls", "266", "Lesotho"))
        mCountryCodeList.add(CountryCodeNumberViewState("lt", "370", "Lithuania"))
        mCountryCodeList.add(CountryCodeNumberViewState("lu", "352", "Luxembourg"))
        mCountryCodeList.add(CountryCodeNumberViewState("lv", "371", "Latvia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ly", "218", "Libya"))
        mCountryCodeList.add(CountryCodeNumberViewState("ma", "212", "Morocco"))
        mCountryCodeList.add(CountryCodeNumberViewState("mc", "377", "Monaco"))
        mCountryCodeList.add(CountryCodeNumberViewState("md", "373", "Moldova, Republic Of"))
        mCountryCodeList.add(CountryCodeNumberViewState("me", "382", "Montenegro"))
        mCountryCodeList.add(CountryCodeNumberViewState("mf", "590", "Saint Martin"))
        mCountryCodeList.add(CountryCodeNumberViewState("mg", "261", "Madagascar"))
        mCountryCodeList.add(CountryCodeNumberViewState("mh", "692", "Marshall Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("mk", "389", "Macedonia (FYROM)"))
        mCountryCodeList.add(CountryCodeNumberViewState("ml", "223", "Mali"))
        mCountryCodeList.add(CountryCodeNumberViewState("mm", "95", "Myanmar"))
        mCountryCodeList.add(CountryCodeNumberViewState("mn", "976", "Mongolia"))
        mCountryCodeList.add(CountryCodeNumberViewState("mo", "853", "Macau"))
        mCountryCodeList.add(CountryCodeNumberViewState("mp", "1", "Northern Mariana Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("mq", "596", "Martinique"))
        mCountryCodeList.add(CountryCodeNumberViewState("mr", "222", "Mauritania"))
        mCountryCodeList.add(CountryCodeNumberViewState("ms", "1", "Montserrat"))
        mCountryCodeList.add(CountryCodeNumberViewState("mt", "356", "Malta"))
        mCountryCodeList.add(CountryCodeNumberViewState("mu", "230", "Mauritius"))
        mCountryCodeList.add(CountryCodeNumberViewState("mv", "960", "Maldives"))
        mCountryCodeList.add(CountryCodeNumberViewState("mw", "265", "Malawi"))
        mCountryCodeList.add(CountryCodeNumberViewState("mx", "52", "Mexico"))
        mCountryCodeList.add(CountryCodeNumberViewState("my", "60", "Malaysia"))
        mCountryCodeList.add(CountryCodeNumberViewState("mz", "258", "Mozambique"))
        mCountryCodeList.add(CountryCodeNumberViewState("na", "264", "Namibia"))
        mCountryCodeList.add(CountryCodeNumberViewState("nc", "687", "New Caledonia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ne", "227", "Niger"))
        mCountryCodeList.add(CountryCodeNumberViewState("nf", "672", "Norfolk Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("ng", "234", "Nigeria"))
        mCountryCodeList.add(CountryCodeNumberViewState("ni", "505", "Nicaragua"))
        mCountryCodeList.add(CountryCodeNumberViewState("nl", "31", "Netherlands"))
        mCountryCodeList.add(CountryCodeNumberViewState("no", "47", "Norway"))
        mCountryCodeList.add(CountryCodeNumberViewState("np", "977", "Nepal"))
        mCountryCodeList.add(CountryCodeNumberViewState("nr", "674", "Nauru"))
        mCountryCodeList.add(CountryCodeNumberViewState("nu", "683", "Niue"))
        mCountryCodeList.add(CountryCodeNumberViewState("nz", "64", "New Zealand"))
        mCountryCodeList.add(CountryCodeNumberViewState("om", "968", "Oman"))
        mCountryCodeList.add(CountryCodeNumberViewState("pa", "507", "Panama"))
        mCountryCodeList.add(CountryCodeNumberViewState("pe", "51", "Peru"))
        mCountryCodeList.add(CountryCodeNumberViewState("pf", "689", "French Polynesia"))
        mCountryCodeList.add(CountryCodeNumberViewState("pg", "675", "Papua New Guinea"))
        mCountryCodeList.add(CountryCodeNumberViewState("ph", "63", "Philippines"))
        mCountryCodeList.add(CountryCodeNumberViewState("pk", "92", "Pakistan"))
        mCountryCodeList.add(CountryCodeNumberViewState("pl", "48", "Poland"))
        mCountryCodeList.add(CountryCodeNumberViewState("pm", "508", "Saint Pierre And Miquelon"))
        mCountryCodeList.add(CountryCodeNumberViewState("pn", "870", "Pitcairn Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("pr", "1", "Puerto Rico"))
        mCountryCodeList.add(CountryCodeNumberViewState("ps", "970", "Palestine"))
        mCountryCodeList.add(CountryCodeNumberViewState("pt", "351", "Portugal"))
        mCountryCodeList.add(CountryCodeNumberViewState("pw", "680", "Palau"))
        mCountryCodeList.add(CountryCodeNumberViewState("py", "595", "Paraguay"))
        mCountryCodeList.add(CountryCodeNumberViewState("qa", "974", "Qatar"))
        mCountryCodeList.add(CountryCodeNumberViewState("re", "262", "Réunion"))
        mCountryCodeList.add(CountryCodeNumberViewState("ro", "40", "Romania"))
        mCountryCodeList.add(CountryCodeNumberViewState("rs", "381", "Serbia"))
        mCountryCodeList.add(CountryCodeNumberViewState("ru", "7", "Russian Federation"))
        mCountryCodeList.add(CountryCodeNumberViewState("rw", "250", "Rwanda"))
        mCountryCodeList.add(CountryCodeNumberViewState("sa", "966", "Saudi Arabia"))
        mCountryCodeList.add(CountryCodeNumberViewState("sb", "677", "Solomon Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("sc", "248", "Seychelles"))
        mCountryCodeList.add(CountryCodeNumberViewState("sd", "249", "Sudan"))
        mCountryCodeList.add(CountryCodeNumberViewState("se", "46", "Sweden"))
        mCountryCodeList.add(CountryCodeNumberViewState("sg", "65", "Singapore"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "sh",
                "290",
                "Saint Helena, Ascension And Tristan Da Cunha",
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("si", "386", "Slovenia"))
        mCountryCodeList.add(CountryCodeNumberViewState("sk", "421", "Slovakia"))
        mCountryCodeList.add(CountryCodeNumberViewState("sl", "232", "Sierra Leone"))
        mCountryCodeList.add(CountryCodeNumberViewState("sm", "378", "San Marino"))
        mCountryCodeList.add(CountryCodeNumberViewState("sn", "221", "Senegal"))
        mCountryCodeList.add(CountryCodeNumberViewState("so", "252", "Somalia"))
        mCountryCodeList.add(CountryCodeNumberViewState("sr", "597", "Suriname"))
        mCountryCodeList.add(CountryCodeNumberViewState("ss", "211", "South Sudan"))
        mCountryCodeList.add(CountryCodeNumberViewState("st", "239", "Sao Tome And Principe"))
        mCountryCodeList.add(CountryCodeNumberViewState("sv", "503", "El Salvador"))
        mCountryCodeList.add(CountryCodeNumberViewState("sx", "1", "Sint Maarten"))
        mCountryCodeList.add(CountryCodeNumberViewState("sy", "963", "Syrian Arab Republic"))
        mCountryCodeList.add(CountryCodeNumberViewState("sz", "268", "Swaziland"))
        mCountryCodeList.add(CountryCodeNumberViewState("tc", "1", "Turks and Caicos Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("td", "235", "Chad"))
        mCountryCodeList.add(CountryCodeNumberViewState("tg", "228", "Togo"))
        mCountryCodeList.add(CountryCodeNumberViewState("th", "66", "Thailand"))
        mCountryCodeList.add(CountryCodeNumberViewState("tj", "992", "Tajikistan"))
        mCountryCodeList.add(CountryCodeNumberViewState("tk", "690", "Tokelau"))
        mCountryCodeList.add(CountryCodeNumberViewState("tl", "670", "Timor-leste"))
        mCountryCodeList.add(CountryCodeNumberViewState("tm", "993", "Turkmenistan"))
        mCountryCodeList.add(CountryCodeNumberViewState("tn", "216", "Tunisia"))
        mCountryCodeList.add(CountryCodeNumberViewState("to", "676", "Tonga"))
        mCountryCodeList.add(CountryCodeNumberViewState("tr", "90", "Turkey"))
        mCountryCodeList.add(CountryCodeNumberViewState("tt", "1", "Trinidad &amp; Tobago"))
        mCountryCodeList.add(CountryCodeNumberViewState("tv", "688", "Tuvalu"))
        mCountryCodeList.add(CountryCodeNumberViewState("tw", "886", "Taiwan"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "tz",
                "255",
                "Tanzania, United Republic Of"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("ua", "380", "Ukraine"))
        mCountryCodeList.add(CountryCodeNumberViewState("ug", "256", "Uganda"))
        mCountryCodeList.add(CountryCodeNumberViewState("us", "1", "United States"))
        mCountryCodeList.add(CountryCodeNumberViewState("uy", "598", "Uruguay"))
        mCountryCodeList.add(CountryCodeNumberViewState("uz", "998", "Uzbekistan"))
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "va",
                "379",
                "Holy See (vatican City State)"
            )
        )
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "vc",
                "1",
                "Saint Vincent &amp; The Grenadines"
            )
        )
        mCountryCodeList.add(
            CountryCodeNumberViewState(
                "ve",
                "58",
                "Venezuela, Bolivarian Republic Of"
            )
        )
        mCountryCodeList.add(CountryCodeNumberViewState("vg", "1", "British Virgin Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("vi", "1", "US Virgin Islands"))
        mCountryCodeList.add(CountryCodeNumberViewState("vn", "84", "Vietnam"))
        mCountryCodeList.add(CountryCodeNumberViewState("vu", "678", "Vanuatu"))
        mCountryCodeList.add(CountryCodeNumberViewState("wf", "681", "Wallis And Futuna"))
        mCountryCodeList.add(CountryCodeNumberViewState("ws", "685", "Samoa"))
        mCountryCodeList.add(CountryCodeNumberViewState("xk", "383", "Kosovo"))
        mCountryCodeList.add(CountryCodeNumberViewState("ye", "967", "Yemen"))
        mCountryCodeList.add(CountryCodeNumberViewState("yt", "262", "Mayotte"))
        mCountryCodeList.add(CountryCodeNumberViewState("za", "27", "South Africa"))
        mCountryCodeList.add(CountryCodeNumberViewState("zm", "260", "Zambia"))
        mCountryCodeList.add(CountryCodeNumberViewState("zw", "263", "Zimbabwe"))

        viewState.countryCodeNumberViewStateList = mCountryCodeList
    }

    inner class LoginRegisterTextWatcher(private val textWatcherType: String) : TextWatcher {

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            s?.toString()?.let {
                if (textWatcherType == Constants.TextWatcherType.PHONE_NO) {
                    viewState.phoneNo = it
                    if (it.isNotEmpty()) {
                        if (it.length < 8) {
                            viewState.phoneNoMessageColorViewState =
                                ColorViewState(R.color.warning_orange)
                            viewState.continueBtnDrawableViewState =
                                DrawableViewState(R.drawable.bg_button3)
                        } else {
                            viewState.phoneNoMessageColorViewState =
                                ColorViewState(R.color.success_green)
                            viewState.continueBtnDrawableViewState =
                                DrawableViewState(R.drawable.bg_button2)
                        }
                        viewState.showPhoneNoMessage = true
                    } else {
                        viewState.showPhoneNoMessage = false
                        viewState.continueBtnDrawableViewState =
                            DrawableViewState(R.drawable.bg_button3)
                    }
                } else if (textWatcherType == Constants.TextWatcherType.FIRST_NAME) {
                    registerFormModel.firstName = it
                    validateForm(false)
                    if (it.isNotEmpty()) {
                        registerFormViewState.showFirstNameError = false
                    }
                } else if (textWatcherType == Constants.TextWatcherType.LAST_NAME) {
                    registerFormModel.lastName = it
                    validateForm(false)
                    if (it.isNotEmpty()) {
                        registerFormViewState.showLastNameError = false
                    }
                } else if (textWatcherType == Constants.TextWatcherType.EMAIL) {
                    registerFormModel.email = it
                    validateForm(false)
                    if (it.isNotEmpty()) {
                        registerFormViewState.showEmailError = false
                    }
                } else if (textWatcherType == Constants.TextWatcherType.COUNTRY_CODE) {
                    if (it.isEmpty()) {
                        viewState.countryCodeNumberViewStateList = mCountryCodeList
                    } else {
                        val arrayList = mCountryCodeList.filter { filter ->
                            filter.id?.contains(it, true) == true || filter.code?.contains(
                                it,
                                true
                            ) == true || filter.name?.contains(it, true) == true
                        }
                        viewState.countryCodeNumberViewStateList = arrayList
                    }
                } else if (textWatcherType == Constants.TextWatcherType.OTP) {
                    if(it.length == 6){
                        showRegisterForm()
                    }
                } else if (textWatcherType == Constants.TextWatcherType.FORM_PHONE_NO) {
                    validateForm(false)
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    inner class RadioCheckChangeListener : RadioGroup.OnCheckedChangeListener {

        override fun onCheckedChanged(group: RadioGroup?, checkedId: Int) {
            registerFormModel.gender = if (checkedId == R.id.rbMale) {
                Constants.Gender.MALE
            } else {
                Constants.Gender.FEMALE
            }
        }

    }

    private fun validateForm(fromContinueBtn:Boolean = false): Boolean {
        var noErrorInForm = true
        if(fromContinueBtn) {
            registerFormViewState.showFirstNameError = false
            registerFormViewState.showLastNameError = false
            registerFormViewState.showEmailError = false
            registerFormViewState.showBirthdayError = false
        }
        if (registerFormModel.firstName.isEmpty()) {
            if(fromContinueBtn) {
                registerFormViewState.showFirstNameError = true
            }
            noErrorInForm = false
        }
        if (registerFormModel.lastName.isEmpty()) {
            if(fromContinueBtn) {
                registerFormViewState.showLastNameError = true
            }
            noErrorInForm = false
        }
        if (registerFormModel.email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(
                registerFormModel.email
            ).matches()
        ) {
            if(fromContinueBtn) {
                registerFormViewState.showEmailError = true
            }
            noErrorInForm = false
        }
        if (registerFormModel.dob.isEmpty()) {
            if(fromContinueBtn) {
                registerFormViewState.showBirthdayError = true
            }
            noErrorInForm = false
        }
        registerFormViewState.continueBtnDrawableViewState = if (noErrorInForm) {
            DrawableViewState(R.drawable.bg_button2)
        } else {
            DrawableViewState(R.drawable.bg_button3)
        }
        return noErrorInForm
    }

    fun initRegisterViewState() {
        registerFormViewState = RegisterFormViewState()
        if (viewState.phoneNo.isNotEmpty()) {
            registerFormViewState.phoneNumberViewState = viewState.countryCodeNumberViewState
            registerFormViewState.phoneNumberViewState.number = viewState.phoneNo
            registerFormViewState.freezePhoneNo = true
            registerFormViewState.freezeEmail = false
        } else {
            registerFormViewState.freezePhoneNo = false
            registerFormViewState.freezeEmail = true
        }
    }

    fun destroyRegisterViewState() {
        viewState.loginOrCreateAccountVisibility = true
        registerFormModel.clear()
    }

    fun initConfirmYourNumberViewState() {
        startTryAgainTimer()
    }

    fun destroyConfirmYourNumberViewState() {
        timer.cancel()
    }

    private fun startTryAgainTimer() {
        viewState.sendOtpSmsTryAgainVisibility = true
        timer.cancel()
        timer.start()
    }

    inner class TryAgainTimer : CountDownTimer(31000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            if (millisUntilFinished > 0) {
                viewState.smsTryAgainTimerText = "${millisUntilFinished / 1000}"
            } else {
                viewState.sendOtpSmsTryAgainVisibility = false
            }
        }

        override fun onFinish() {
            viewState.sendOtpSmsTryAgainVisibility = false
        }
    }

    fun onDateSelected(year: Int, monthOfYear: Int, dayOfMonth: Int) {
        onEvent(Event.DateDialogPicker)
        registerFormModel.dob = "$dayOfMonth-$monthOfYear-$year"
        registerFormViewState.date = "$dayOfMonth-$monthOfYear-$year"
        validateForm(false)
    }

}