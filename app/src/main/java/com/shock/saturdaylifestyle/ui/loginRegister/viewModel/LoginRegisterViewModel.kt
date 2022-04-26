package com.shock.saturdaylifestyle.ui.loginRegister.viewModel

import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioGroup
import androidx.core.util.PatternsCompat
import androidx.lifecycle.viewModelScope
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.ui.base.viewModel.BaseViewModel
import com.shock.saturdaylifestyle.ui.base.viewState.ColorViewState
import com.shock.saturdaylifestyle.ui.base.viewState.DrawableViewState
import com.shock.saturdaylifestyle.ui.loginRegister.model.RegisterFormModel
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.CountryCodeNumberViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.RegisterFormViewState
import com.shock.saturdaylifestyle.ui.main.network.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginRegisterViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : BaseViewModel(mainRepository) {

    private val eventChannel = Channel<Event>(Channel.BUFFERED)
    val eventFlow = eventChannel.receiveAsFlow()
    val textWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.PHONE_NO)
    val firstNameWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.FIRST_NAME)
    val lastNameWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.LAST_NAME)
    val emailWatcher = LoginRegisterTextWatcher(Constants.TextWatcherType.EMAIL)
    val genderCheckChangeListener = RadioCheckChangeListener()

    val viewState = LoginRegisterViewState()
    val registerFormViewState = RegisterFormViewState()
    val registerFormModel = RegisterFormModel()

    init {
        setViewPagerListData()
        setCountryCodeNumberList()
    }

    fun onLoginCreateAccountClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.LOGIN_OR_CREATE_ACCOUNT))
    }

    fun onContinueClicked() {
        if (viewState.phoneNo.length >= 8) {
            viewState.loginOrCreateAccountVisibility = false
        }
    }

    fun onSendOTPViaWhatsappClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.WHATSAPP_VERIFY_YOUR_NUMBER))
    }

    fun onSendOTPViaSMSClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.CONFIRM_YOUR_NUMBER))
    }

    fun onBackPressed() {
        onEvent(Event.OnBackPressed)
    }

    fun showCountryCodePicker() {
        onEvent(Event.PickerDialog)
    }

    fun onWhatsAppTryAgainClicked() {

    }

    fun onPrivacyPolicyClicked() {

    }

    fun onRegisterFormSaveAndContinueClicked() {
        registerFormViewState.continueBtnDrawableViewState = if (validateForm()) {
            DrawableViewState(R.drawable.bg_button2)
        } else {
            DrawableViewState(R.drawable.bg_button3)
        }
    }

    fun onSkipButtonClicked() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.REGISTER_FORM))
    }

    fun showRegisterForm() {
        onEvent(Event.NavigateTo(Constants.NavigateTo.REGISTER_FORM))
    }

    fun onChooseVerificationBackClicked() {
        viewState.loginOrCreateAccountVisibility = true
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
        viewState.introViewPagerItemViewStateList = arrayList
    }

    private fun setCountryCodeNumberList() {
        val arrayList = arrayListOf<CountryCodeNumberViewState>()
        arrayList.add(CountryCodeNumberViewState("ad", "376", "Andorra"))
        arrayList.add(CountryCodeNumberViewState("ae", "971", "United Arab Emirates (UAE)"))
        arrayList.add(CountryCodeNumberViewState("af", "93", "Afghanistan"))
        arrayList.add(CountryCodeNumberViewState("ag", "1", "Antigua and Barbuda"))
        arrayList.add(CountryCodeNumberViewState("ai", "1", "Anguilla"))
        arrayList.add(CountryCodeNumberViewState("al", "355", "Albania"))
        arrayList.add(CountryCodeNumberViewState("am", "374", "Armenia"))
        arrayList.add(CountryCodeNumberViewState("ao", "244", "Angola"))
        arrayList.add(CountryCodeNumberViewState("aq", "672", "Antarctica"))
        arrayList.add(CountryCodeNumberViewState("ar", "54", "Argentina"))
        arrayList.add(CountryCodeNumberViewState("as", "1", "American Samoa"))
        arrayList.add(CountryCodeNumberViewState("at", "43", "Austria"))
        arrayList.add(CountryCodeNumberViewState("au", "61", "Australia"))
        arrayList.add(CountryCodeNumberViewState("aw", "297", "Aruba"))
        arrayList.add(CountryCodeNumberViewState("ax", "358", "Åland Islands"))
        arrayList.add(CountryCodeNumberViewState("az", "994", "Azerbaijan"))
        arrayList.add(CountryCodeNumberViewState("ba", "387", "Bosnia And Herzegovina"))
        arrayList.add(CountryCodeNumberViewState("bb", "1", "Barbados"))
        arrayList.add(CountryCodeNumberViewState("bd", "880", "Bangladesh"))
        arrayList.add(CountryCodeNumberViewState("be", "32", "Belgium"))
        arrayList.add(CountryCodeNumberViewState("bf", "226", "Burkina Faso"))
        arrayList.add(CountryCodeNumberViewState("bg", "359", "Bulgaria"))
        arrayList.add(CountryCodeNumberViewState("bh", "973", "Bahrain"))
        arrayList.add(CountryCodeNumberViewState("bi", "257", "Burundi"))
        arrayList.add(CountryCodeNumberViewState("bj", "229", "Benin"))
        arrayList.add(CountryCodeNumberViewState("bl", "590", "Saint Barthélemy"))
        arrayList.add(CountryCodeNumberViewState("bm", "1", "Bermuda"))
        arrayList.add(CountryCodeNumberViewState("bn", "673", "Brunei Darussalam"))
        arrayList.add(CountryCodeNumberViewState("bo", "591", "Bolivia, Plurinational State Of"))
        arrayList.add(CountryCodeNumberViewState("br", "55", "Brazil"))
        arrayList.add(CountryCodeNumberViewState("bs", "1", "Bahamas"))
        arrayList.add(CountryCodeNumberViewState("bt", "975", "Bhutan"))
        arrayList.add(CountryCodeNumberViewState("bw", "267", "Botswana"))
        arrayList.add(CountryCodeNumberViewState("by", "375", "Belarus"))
        arrayList.add(CountryCodeNumberViewState("bz", "501", "Belize"))
        arrayList.add(CountryCodeNumberViewState("ca", "1", "Canada"))
        arrayList.add(CountryCodeNumberViewState("cc", "61", "Cocos (keeling) Islands"))
        arrayList.add(CountryCodeNumberViewState("cd", "243", "Congo, The Democratic Republic Of The",))
        arrayList.add(CountryCodeNumberViewState("cf", "236", "Central African Republic"))
        arrayList.add(CountryCodeNumberViewState("cg", "242", "Congo"))
        arrayList.add(CountryCodeNumberViewState("ch", "41", "Switzerland"))
        arrayList.add(CountryCodeNumberViewState("ci", "225", "Côte D'ivoire"))
        arrayList.add(CountryCodeNumberViewState("ck", "682", "Cook Islands"))
        arrayList.add(CountryCodeNumberViewState("cl", "56", "Chile"))
        arrayList.add(CountryCodeNumberViewState("cm", "237", "Cameroon"))
        arrayList.add(CountryCodeNumberViewState("cn", "86", "China"))
        arrayList.add(CountryCodeNumberViewState("co", "57", "Colombia"))
        arrayList.add(CountryCodeNumberViewState("cr", "506", "Costa Rica"))
        arrayList.add(CountryCodeNumberViewState("cu", "53", "Cuba"))
        arrayList.add(CountryCodeNumberViewState("cv", "238", "Cape Verde"))
        arrayList.add(CountryCodeNumberViewState("cw", "599", "Curaçao"))
        arrayList.add(CountryCodeNumberViewState("cx", "61", "Christmas Island"))
        arrayList.add(CountryCodeNumberViewState("cy", "357", "Cyprus"))
        arrayList.add(CountryCodeNumberViewState("cz", "420", "Czech Republic"))
        arrayList.add(CountryCodeNumberViewState("de", "49", "Germany"))
        arrayList.add(CountryCodeNumberViewState("dj", "253", "Djibouti"))
        arrayList.add(CountryCodeNumberViewState("dk", "45", "Denmark"))
        arrayList.add(CountryCodeNumberViewState("dm", "1", "Dominica"))
        arrayList.add(CountryCodeNumberViewState("do", "1", "Dominican Republic"))
        arrayList.add(CountryCodeNumberViewState("dz", "213", "Algeria"))
        arrayList.add(CountryCodeNumberViewState("ec", "593", "Ecuador"))
        arrayList.add(CountryCodeNumberViewState("ee", "372", "Estonia"))
        arrayList.add(CountryCodeNumberViewState("eg", "20", "Egypt"))
        arrayList.add(CountryCodeNumberViewState("er", "291", "Eritrea"))
        arrayList.add(CountryCodeNumberViewState("es", "34", "Spain"))
        arrayList.add(CountryCodeNumberViewState("et", "251", "Ethiopia"))
        arrayList.add(CountryCodeNumberViewState("fi", "358", "Finland"))
        arrayList.add(CountryCodeNumberViewState("fj", "679", "Fiji"))
        arrayList.add(CountryCodeNumberViewState("fk", "500", "Falkland Islands (malvinas)"))
        arrayList.add(CountryCodeNumberViewState("fm", "691", "Micronesia, Federated States Of"))
        arrayList.add(CountryCodeNumberViewState("fo", "298", "Faroe Islands"))
        arrayList.add(CountryCodeNumberViewState("fr", "33", "France"))
        arrayList.add(CountryCodeNumberViewState("ga", "241", "Gabon"))
        arrayList.add(CountryCodeNumberViewState("gb", "44", "United Kingdom"))
        arrayList.add(CountryCodeNumberViewState("gd", "1", "Grenada"))
        arrayList.add(CountryCodeNumberViewState("ge", "995", "Georgia"))
        arrayList.add(CountryCodeNumberViewState("gf", "594", "French Guyana"))
        arrayList.add(CountryCodeNumberViewState("gh", "233", "Ghana"))
        arrayList.add(CountryCodeNumberViewState("gi", "350", "Gibraltar"))
        arrayList.add(CountryCodeNumberViewState("gl", "299", "Greenland"))
        arrayList.add(CountryCodeNumberViewState("gm", "220", "Gambia"))
        arrayList.add(CountryCodeNumberViewState("gn", "224", "Guinea"))
        arrayList.add(CountryCodeNumberViewState("gp", "450", "Guadeloupe"))
        arrayList.add(CountryCodeNumberViewState("gq", "240", "Equatorial Guinea"))
        arrayList.add(CountryCodeNumberViewState("gr", "30", "Greece"))
        arrayList.add(CountryCodeNumberViewState("gt", "502", "Guatemala"))
        arrayList.add(CountryCodeNumberViewState("gu", "1", "Guam"))
        arrayList.add(CountryCodeNumberViewState("gw", "245", "Guinea-bissau"))
        arrayList.add(CountryCodeNumberViewState("gy", "592", "Guyana"))
        arrayList.add(CountryCodeNumberViewState("hk", "852", "Hong Kong"))
        arrayList.add(CountryCodeNumberViewState("hn", "504", "Honduras"))
        arrayList.add(CountryCodeNumberViewState("hr", "385", "Croatia"))
        arrayList.add(CountryCodeNumberViewState("ht", "509", "Haiti"))
        arrayList.add(CountryCodeNumberViewState("hu", "36", "Hungary"))
        arrayList.add(CountryCodeNumberViewState("id", "62", "Indonesia"))
        arrayList.add(CountryCodeNumberViewState("ie", "353", "Ireland"))
        arrayList.add(CountryCodeNumberViewState("il", "972", "Israel"))
        arrayList.add(CountryCodeNumberViewState("im", "44", "Isle Of Man"))
        arrayList.add(CountryCodeNumberViewState("is", "354", "Iceland"))
        arrayList.add(CountryCodeNumberViewState("in", "91", "India"))
        arrayList.add(CountryCodeNumberViewState("io", "246", "British Indian Ocean Territory"))
        arrayList.add(CountryCodeNumberViewState("iq", "964", "Iraq"))
        arrayList.add(CountryCodeNumberViewState("ir", "98", "Iran, Islamic Republic Of"))
        arrayList.add(CountryCodeNumberViewState("it", "39", "Italy"))
        arrayList.add(CountryCodeNumberViewState("je", "44", "Jersey "))
        arrayList.add(CountryCodeNumberViewState("jm", "1", "Jamaica"))
        arrayList.add(CountryCodeNumberViewState("jo", "962", "Jordan"))
        arrayList.add(CountryCodeNumberViewState("jp", "81", "Japan"))
        arrayList.add(CountryCodeNumberViewState("ke", "254", "Kenya"))
        arrayList.add(CountryCodeNumberViewState("kg", "996", "Kyrgyzstan"))
        arrayList.add(CountryCodeNumberViewState("kh", "855", "Cambodia"))
        arrayList.add(CountryCodeNumberViewState("ki", "686", "Kiribati"))
        arrayList.add(CountryCodeNumberViewState("km", "269", "Comoros"))
        arrayList.add(CountryCodeNumberViewState("kn", "1", "Saint Kitts and Nevis"))
        arrayList.add(CountryCodeNumberViewState("kp", "850", "North Korea"))
        arrayList.add(CountryCodeNumberViewState("kr", "82", "South Korea"))
        arrayList.add(CountryCodeNumberViewState("kw", "965", "Kuwait"))
        arrayList.add(CountryCodeNumberViewState("ky", "1", "Cayman Islands"))
        arrayList.add(CountryCodeNumberViewState("kz", "7", "Kazakhstan"))
        arrayList.add(CountryCodeNumberViewState("la", "856", "Lao People's Democratic Republic"))
        arrayList.add(CountryCodeNumberViewState("lb", "961", "Lebanon"))
        arrayList.add(CountryCodeNumberViewState("lc", "1", "Saint Lucia"))
        arrayList.add(CountryCodeNumberViewState("li", "423", "Liechtenstein"))
        arrayList.add(CountryCodeNumberViewState("lk", "94", "Sri Lanka"))
        arrayList.add(CountryCodeNumberViewState("lr", "231", "Liberia"))
        arrayList.add(CountryCodeNumberViewState("ls", "266", "Lesotho"))
        arrayList.add(CountryCodeNumberViewState("lt", "370", "Lithuania"))
        arrayList.add(CountryCodeNumberViewState("lu", "352", "Luxembourg"))
        arrayList.add(CountryCodeNumberViewState("lv", "371", "Latvia"))
        arrayList.add(CountryCodeNumberViewState("ly", "218", "Libya"))
        arrayList.add(CountryCodeNumberViewState("ma", "212", "Morocco"))
        arrayList.add(CountryCodeNumberViewState("mc", "377", "Monaco"))
        arrayList.add(CountryCodeNumberViewState("md", "373", "Moldova, Republic Of"))
        arrayList.add(CountryCodeNumberViewState("me", "382", "Montenegro"))
        arrayList.add(CountryCodeNumberViewState("mf", "590", "Saint Martin"))
        arrayList.add(CountryCodeNumberViewState("mg", "261", "Madagascar"))
        arrayList.add(CountryCodeNumberViewState("mh", "692", "Marshall Islands"))
        arrayList.add(CountryCodeNumberViewState("mk", "389", "Macedonia (FYROM)"))
        arrayList.add(CountryCodeNumberViewState("ml", "223", "Mali"))
        arrayList.add(CountryCodeNumberViewState("mm", "95", "Myanmar"))
        arrayList.add(CountryCodeNumberViewState("mn", "976", "Mongolia"))
        arrayList.add(CountryCodeNumberViewState("mo", "853", "Macau"))
        arrayList.add(CountryCodeNumberViewState("mp", "1", "Northern Mariana Islands"))
        arrayList.add(CountryCodeNumberViewState("mq", "596", "Martinique"))
        arrayList.add(CountryCodeNumberViewState("mr", "222", "Mauritania"))
        arrayList.add(CountryCodeNumberViewState("ms", "1", "Montserrat"))
        arrayList.add(CountryCodeNumberViewState("mt", "356", "Malta"))
        arrayList.add(CountryCodeNumberViewState("mu", "230", "Mauritius"))
        arrayList.add(CountryCodeNumberViewState("mv", "960", "Maldives"))
        arrayList.add(CountryCodeNumberViewState("mw", "265", "Malawi"))
        arrayList.add(CountryCodeNumberViewState("mx", "52", "Mexico"))
        arrayList.add(CountryCodeNumberViewState("my", "60", "Malaysia"))
        arrayList.add(CountryCodeNumberViewState("mz", "258", "Mozambique"))
        arrayList.add(CountryCodeNumberViewState("na", "264", "Namibia"))
        arrayList.add(CountryCodeNumberViewState("nc", "687", "New Caledonia"))
        arrayList.add(CountryCodeNumberViewState("ne", "227", "Niger"))
        arrayList.add(CountryCodeNumberViewState("nf", "672", "Norfolk Islands"))
        arrayList.add(CountryCodeNumberViewState("ng", "234", "Nigeria"))
        arrayList.add(CountryCodeNumberViewState("ni", "505", "Nicaragua"))
        arrayList.add(CountryCodeNumberViewState("nl", "31", "Netherlands"))
        arrayList.add(CountryCodeNumberViewState("no", "47", "Norway"))
        arrayList.add(CountryCodeNumberViewState("np", "977", "Nepal"))
        arrayList.add(CountryCodeNumberViewState("nr", "674", "Nauru"))
        arrayList.add(CountryCodeNumberViewState("nu", "683", "Niue"))
        arrayList.add(CountryCodeNumberViewState("nz", "64", "New Zealand"))
        arrayList.add(CountryCodeNumberViewState("om", "968", "Oman"))
        arrayList.add(CountryCodeNumberViewState("pa", "507", "Panama"))
        arrayList.add(CountryCodeNumberViewState("pe", "51", "Peru"))
        arrayList.add(CountryCodeNumberViewState("pf", "689", "French Polynesia"))
        arrayList.add(CountryCodeNumberViewState("pg", "675", "Papua New Guinea"))
        arrayList.add(CountryCodeNumberViewState("ph", "63", "Philippines"))
        arrayList.add(CountryCodeNumberViewState("pk", "92", "Pakistan"))
        arrayList.add(CountryCodeNumberViewState("pl", "48", "Poland"))
        arrayList.add(CountryCodeNumberViewState("pm", "508", "Saint Pierre And Miquelon"))
        arrayList.add(CountryCodeNumberViewState("pn", "870", "Pitcairn Islands"))
        arrayList.add(CountryCodeNumberViewState("pr", "1", "Puerto Rico"))
        arrayList.add(CountryCodeNumberViewState("ps", "970", "Palestine"))
        arrayList.add(CountryCodeNumberViewState("pt", "351", "Portugal"))
        arrayList.add(CountryCodeNumberViewState("pw", "680", "Palau"))
        arrayList.add(CountryCodeNumberViewState("py", "595", "Paraguay"))
        arrayList.add(CountryCodeNumberViewState("qa", "974", "Qatar"))
        arrayList.add(CountryCodeNumberViewState("re", "262", "Réunion"))
        arrayList.add(CountryCodeNumberViewState("ro", "40", "Romania"))
        arrayList.add(CountryCodeNumberViewState("rs", "381", "Serbia"))
        arrayList.add(CountryCodeNumberViewState("ru", "7", "Russian Federation"))
        arrayList.add(CountryCodeNumberViewState("rw", "250", "Rwanda"))
        arrayList.add(CountryCodeNumberViewState("sa", "966", "Saudi Arabia"))
        arrayList.add(CountryCodeNumberViewState("sb", "677", "Solomon Islands"))
        arrayList.add(CountryCodeNumberViewState("sc", "248", "Seychelles"))
        arrayList.add(CountryCodeNumberViewState("sd", "249", "Sudan"))
        arrayList.add(CountryCodeNumberViewState("se", "46", "Sweden"))
        arrayList.add(CountryCodeNumberViewState("sg", "65", "Singapore"))
        arrayList.add(CountryCodeNumberViewState("sh", "290", "Saint Helena, Ascension And Tristan Da Cunha",))
        arrayList.add(CountryCodeNumberViewState("si", "386", "Slovenia"))
        arrayList.add(CountryCodeNumberViewState("sk", "421", "Slovakia"))
        arrayList.add(CountryCodeNumberViewState("sl", "232", "Sierra Leone"))
        arrayList.add(CountryCodeNumberViewState("sm", "378", "San Marino"))
        arrayList.add(CountryCodeNumberViewState("sn", "221", "Senegal"))
        arrayList.add(CountryCodeNumberViewState("so", "252", "Somalia"))
        arrayList.add(CountryCodeNumberViewState("sr", "597", "Suriname"))
        arrayList.add(CountryCodeNumberViewState("ss", "211", "South Sudan"))
        arrayList.add(CountryCodeNumberViewState("st", "239", "Sao Tome And Principe"))
        arrayList.add(CountryCodeNumberViewState("sv", "503", "El Salvador"))
        arrayList.add(CountryCodeNumberViewState("sx", "1", "Sint Maarten"))
        arrayList.add(CountryCodeNumberViewState("sy", "963", "Syrian Arab Republic"))
        arrayList.add(CountryCodeNumberViewState("sz", "268", "Swaziland"))
        arrayList.add(CountryCodeNumberViewState("tc", "1", "Turks and Caicos Islands"))
        arrayList.add(CountryCodeNumberViewState("td", "235", "Chad"))
        arrayList.add(CountryCodeNumberViewState("tg", "228", "Togo"))
        arrayList.add(CountryCodeNumberViewState("th", "66", "Thailand"))
        arrayList.add(CountryCodeNumberViewState("tj", "992", "Tajikistan"))
        arrayList.add(CountryCodeNumberViewState("tk", "690", "Tokelau"))
        arrayList.add(CountryCodeNumberViewState("tl", "670", "Timor-leste"))
        arrayList.add(CountryCodeNumberViewState("tm", "993", "Turkmenistan"))
        arrayList.add(CountryCodeNumberViewState("tn", "216", "Tunisia"))
        arrayList.add(CountryCodeNumberViewState("to", "676", "Tonga"))
        arrayList.add(CountryCodeNumberViewState("tr", "90", "Turkey"))
        arrayList.add(CountryCodeNumberViewState("tt", "1", "Trinidad &amp; Tobago"))
        arrayList.add(CountryCodeNumberViewState("tv", "688", "Tuvalu"))
        arrayList.add(CountryCodeNumberViewState("tw", "886", "Taiwan"))
        arrayList.add(CountryCodeNumberViewState("tz", "255", "Tanzania, United Republic Of"))
        arrayList.add(CountryCodeNumberViewState("ua", "380", "Ukraine"))
        arrayList.add(CountryCodeNumberViewState("ug", "256", "Uganda"))
        arrayList.add(CountryCodeNumberViewState("us", "1", "United States"))
        arrayList.add(CountryCodeNumberViewState("uy", "598", "Uruguay"))
        arrayList.add(CountryCodeNumberViewState("uz", "998", "Uzbekistan"))
        arrayList.add(CountryCodeNumberViewState("va", "379", "Holy See (vatican City State)"))
        arrayList.add(CountryCodeNumberViewState("vc", "1", "Saint Vincent &amp; The Grenadines"))
        arrayList.add(CountryCodeNumberViewState("ve", "58", "Venezuela, Bolivarian Republic Of"))
        arrayList.add(CountryCodeNumberViewState("vg", "1", "British Virgin Islands"))
        arrayList.add(CountryCodeNumberViewState("vi", "1", "US Virgin Islands"))
        arrayList.add(CountryCodeNumberViewState("vn", "84", "Vietnam"))
        arrayList.add(CountryCodeNumberViewState("vu", "678", "Vanuatu"))
        arrayList.add(CountryCodeNumberViewState("wf", "681", "Wallis And Futuna"))
        arrayList.add(CountryCodeNumberViewState("ws", "685", "Samoa"))
        arrayList.add(CountryCodeNumberViewState("xk", "383", "Kosovo"))
        arrayList.add(CountryCodeNumberViewState("ye", "967", "Yemen"))
        arrayList.add(CountryCodeNumberViewState("yt", "262", "Mayotte"))
        arrayList.add(CountryCodeNumberViewState("za", "27", "South Africa"))
        arrayList.add(CountryCodeNumberViewState("zm", "260", "Zambia"))
        arrayList.add(CountryCodeNumberViewState("zw", "263", "Zimbabwe"))

        viewState.countryCodeNumberViewStateList = arrayList
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
                    if (it.isNotEmpty()) {
                        registerFormViewState.showFirstNameError = false
                    }
                } else if (textWatcherType == Constants.TextWatcherType.LAST_NAME) {
                    registerFormModel.lastName = it
                    if (it.isNotEmpty()) {
                        registerFormViewState.showLastNameError = false
                    }
                } else if (textWatcherType == Constants.TextWatcherType.EMAIL) {
                    registerFormModel.email = it
                    if (it.isNotEmpty()) {
                        registerFormViewState.showEmailError = false
                    }
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

    private fun validateForm(): Boolean {
        var noErrorInForm = true
        registerFormViewState.showFirstNameError = false
        registerFormViewState.showLastNameError = false
        registerFormViewState.showEmailError = false
        registerFormViewState.showBirthdayError = false
        if (registerFormModel.firstName.isEmpty()) {
            registerFormViewState.showFirstNameError = true
            noErrorInForm = false
        }
        if (registerFormModel.lastName.isEmpty()) {
            registerFormViewState.showLastNameError = true
            noErrorInForm = false
        }
        if (registerFormModel.email.isEmpty() || !PatternsCompat.EMAIL_ADDRESS.matcher(registerFormModel.email).matches()) {
            registerFormViewState.showEmailError = true
            noErrorInForm = false
        }
        if (registerFormModel.dob.isEmpty()) {
            registerFormViewState.showBirthdayError = true
            noErrorInForm = false
        }
        return noErrorInForm
    }

}