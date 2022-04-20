package com.shock.saturdaylifestyle.ui.login_register.view
import android.annotation.SuppressLint
import android.app.Activity
import android.content.ClipData
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.provider.SyncStateContract
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chuckerteam.chucker.BuildConfig
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.Gson
import com.saturdays.login_register.adapters.CountryAdapter
import com.saturdays.login_register.callbacks.LoginRegisterActivityViewCallBacks
import com.shock.saturdaylifestyle.ui.login_register.models.CountryDM
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.SignInActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelFactory
import com.shock.saturdaylifestyle.network.Status
import com.shock.saturdaylifestyle.ui.base.activity.BaseActivity
import com.shock.saturdaylifestyle.ui.base.activity.BaseDataBindingActivity
import com.shock.saturdaylifestyle.ui.login_register.viewmodel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.utility.CommonUtilities
import com.shock.saturdaylifestyle.utility.MyBottomSheetDialog
import javax.inject.Inject

class LoginRegisterActivity : BaseDataBindingActivity<SignInActivityDataBinding>(R.layout.activity_login_register),
    LoginRegisterActivityViewCallBacks {


//    private val mViewModel: LoginRegisterViewModel by viewModels()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var vm: LoginRegisterViewModel

    private val TAG = LoginRegisterActivity::class.java.simpleName
    private var countriesList: ArrayList<CountryDM> = ArrayList()

    var countryAdapter : CountryAdapter? = null



    /**
     *  below code for google signin
     */
    private var mfirebaseAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var RC_SIGN_IN_GOOGLE_SIGNIN = 200


    private lateinit var tv_phone_code : TextView
    private lateinit var btn_contibute : TextView
    private lateinit var ed_phone_no : EditText

    companion object{
        var country_code : String = ""
        var phone_number : String = ""
    }


    override fun onDataBindingCreated() {
        binding.callback = this
        binding.lifecycleOwner = this
        supportActionBar!!.hide()

        initCountryList()

        btn_contibute = findViewById(R.id.btn_contibute)
        tv_phone_code = findViewById(R.id.tv_phone_code)
        ed_phone_no = findViewById(R.id.ed_phone_no)
        tv_phone_code.setOnClickListener {
            pickerClick()
        }
        btn_contibute.setOnClickListener {
            continueClick()
        }
        binding.rlGoogle.setOnClickListener {
            initGoogleIntegration()
        }

        setObservers()

    }
    override fun injectDaggerComponent() {
        DaggerProvider.getAppComponent()?.inject(this)
        vm = ViewModelProvider(this, viewModelFactory).get(LoginRegisterViewModel::class.java)
    }

    private fun initCountryList() {
        countriesList.clear()
        countriesList.add(CountryDM("ad", "376", "Andorra",false))
        countriesList.add(CountryDM("ae", "971", "United Arab Emirates (UAE)",false))
        countriesList.add(CountryDM("af", "93", "Afghanistan",false))
        countriesList.add(CountryDM("ag", "1", "Antigua and Barbuda",false))
        countriesList.add(CountryDM("ai", "1", "Anguilla",false))
        countriesList.add(CountryDM("al", "355", "Albania",false))
        countriesList.add(CountryDM("am", "374", "Armenia",false))
        countriesList.add(CountryDM("ao", "244", "Angola",false))
        countriesList.add(CountryDM("aq", "672", "Antarctica",false))
        countriesList.add(CountryDM("ar", "54", "Argentina",false))
        countriesList.add(CountryDM("as", "1", "American Samoa",false))
        countriesList.add(CountryDM("at", "43", "Austria",false))
        countriesList.add(CountryDM("au", "61", "Australia",false))
        countriesList.add(CountryDM("aw", "297", "Aruba",false))
        countriesList.add(CountryDM("ax", "358", "Åland Islands",false))
        countriesList.add(CountryDM("az", "994", "Azerbaijan",false))
        countriesList.add(CountryDM("ba", "387", "Bosnia And Herzegovina",false))
        countriesList.add(CountryDM("bb", "1", "Barbados",false))
        countriesList.add(CountryDM("bd", "880", "Bangladesh",false))
        countriesList.add(CountryDM("be", "32", "Belgium",false))
        countriesList.add(CountryDM("bf", "226", "Burkina Faso",false))
        countriesList.add(CountryDM("bg", "359", "Bulgaria",false))
        countriesList.add(CountryDM("bh", "973", "Bahrain",false))
        countriesList.add(CountryDM("bi", "257", "Burundi",false))
        countriesList.add(CountryDM("bj", "229", "Benin",false))
        countriesList.add(CountryDM("bl", "590", "Saint Barthélemy",false))
        countriesList.add(CountryDM("bm", "1", "Bermuda",false))
        countriesList.add(CountryDM("bn", "673", "Brunei Darussalam",false))
        countriesList.add(CountryDM("bo", "591", "Bolivia, Plurinational State Of",false))
        countriesList.add(CountryDM("br", "55", "Brazil",false))
        countriesList.add(CountryDM("bs", "1", "Bahamas",false))
        countriesList.add(CountryDM("bt", "975", "Bhutan",false))
        countriesList.add(CountryDM("bw", "267", "Botswana",false))
        countriesList.add(CountryDM("by", "375", "Belarus",false))
        countriesList.add(CountryDM("bz", "501", "Belize",false))
        countriesList.add(CountryDM("ca", "1", "Canada",false))
        countriesList.add(CountryDM("cc", "61", "Cocos (keeling) Islands",false))
        countriesList.add(CountryDM("cd", "243", "Congo, The Democratic Republic Of The",))
        countriesList.add(CountryDM("cf", "236", "Central African Republic",false))
        countriesList.add(CountryDM("cg", "242", "Congo",false))
        countriesList.add(CountryDM("ch", "41", "Switzerland",false))
        countriesList.add(CountryDM("ci", "225", "Côte D'ivoire",false))
        countriesList.add(CountryDM("ck", "682", "Cook Islands",false))
        countriesList.add(CountryDM("cl", "56", "Chile",false))
        countriesList.add(CountryDM("cm", "237", "Cameroon",false))
        countriesList.add(CountryDM("cn", "86", "China",false))
        countriesList.add(CountryDM("co", "57", "Colombia",false))
        countriesList.add(CountryDM("cr", "506", "Costa Rica",false))
        countriesList.add(CountryDM("cu", "53", "Cuba",false))
        countriesList.add(CountryDM("cv", "238", "Cape Verde",false))
        countriesList.add(CountryDM("cw", "599", "Curaçao",false))
        countriesList.add(CountryDM("cx", "61", "Christmas Island",false))
        countriesList.add(CountryDM("cy", "357", "Cyprus",false))
        countriesList.add(CountryDM("cz", "420", "Czech Republic",false))
        countriesList.add(CountryDM("de", "49", "Germany",false))
        countriesList.add(CountryDM("dj", "253", "Djibouti",false))
        countriesList.add(CountryDM("dk", "45", "Denmark",false))
        countriesList.add(CountryDM("dm", "1", "Dominica",false))
        countriesList.add(CountryDM("do", "1", "Dominican Republic",false))
        countriesList.add(CountryDM("dz", "213", "Algeria",false))
        countriesList.add(CountryDM("ec", "593", "Ecuador",false))
        countriesList.add(CountryDM("ee", "372", "Estonia",false))
        countriesList.add(CountryDM("eg", "20", "Egypt",false))
        countriesList.add(CountryDM("er", "291", "Eritrea",false))
        countriesList.add(CountryDM("es", "34", "Spain",false))
        countriesList.add(CountryDM("et", "251", "Ethiopia",false))
        countriesList.add(CountryDM("fi", "358", "Finland",false))
        countriesList.add(CountryDM("fj", "679", "Fiji",false))
        countriesList.add(CountryDM("fk", "500", "Falkland Islands (malvinas)",false))
        countriesList.add(CountryDM("fm", "691", "Micronesia, Federated States Of",false))
        countriesList.add(CountryDM("fo", "298", "Faroe Islands",false))
        countriesList.add(CountryDM("fr", "33", "France",false))
        countriesList.add(CountryDM("ga", "241", "Gabon",false))
        countriesList.add(CountryDM("gb", "44", "United Kingdom",false))
        countriesList.add(CountryDM("gd", "1", "Grenada",false))
        countriesList.add(CountryDM("ge", "995", "Georgia",false))
        countriesList.add(CountryDM("gf", "594", "French Guyana",false))
        countriesList.add(CountryDM("gh", "233", "Ghana",false))
        countriesList.add(CountryDM("gi", "350", "Gibraltar",false))
        countriesList.add(CountryDM("gl", "299", "Greenland",false))
        countriesList.add(CountryDM("gm", "220", "Gambia",false))
        countriesList.add(CountryDM("gn", "224", "Guinea",false))
        countriesList.add(CountryDM("gp", "450", "Guadeloupe",false))
        countriesList.add(CountryDM("gq", "240", "Equatorial Guinea",false))
        countriesList.add(CountryDM("gr", "30", "Greece",false))
        countriesList.add(CountryDM("gt", "502", "Guatemala",false))
        countriesList.add(CountryDM("gu", "1", "Guam",false))
        countriesList.add(CountryDM("gw", "245", "Guinea-bissau",false))
        countriesList.add(CountryDM("gy", "592", "Guyana",false))
        countriesList.add(CountryDM("hk", "852", "Hong Kong",false))
        countriesList.add(CountryDM("hn", "504", "Honduras",false))
        countriesList.add(CountryDM("hr", "385", "Croatia",false))
        countriesList.add(CountryDM("ht", "509", "Haiti",false))
        countriesList.add(CountryDM("hu", "36", "Hungary",false))
        countriesList.add(CountryDM("id", "62", "Indonesia",false))
        countriesList.add(CountryDM("ie", "353", "Ireland",false))
        countriesList.add(CountryDM("il", "972", "Israel",false))
        countriesList.add(CountryDM("im", "44", "Isle Of Man",false))
        countriesList.add(CountryDM("is", "354", "Iceland",false))
        countriesList.add(CountryDM("in", "91", "India",false))
        countriesList.add(CountryDM("io", "246", "British Indian Ocean Territory",false))
        countriesList.add(CountryDM("iq", "964", "Iraq",false))
        countriesList.add(CountryDM("ir", "98", "Iran, Islamic Republic Of",false))
        countriesList.add(CountryDM("it", "39", "Italy",false))
        countriesList.add(CountryDM("je", "44", "Jersey ",false))
        countriesList.add(CountryDM("jm", "1", "Jamaica",false))
        countriesList.add(CountryDM("jo", "962", "Jordan",false))
        countriesList.add(CountryDM("jp", "81", "Japan",false))
        countriesList.add(CountryDM("ke", "254", "Kenya",false))
        countriesList.add(CountryDM("kg", "996", "Kyrgyzstan",false))
        countriesList.add(CountryDM("kh", "855", "Cambodia",false))
        countriesList.add(CountryDM("ki", "686", "Kiribati",false))
        countriesList.add(CountryDM("km", "269", "Comoros",false))
        countriesList.add(CountryDM("kn", "1", "Saint Kitts and Nevis",false))
        countriesList.add(CountryDM("kp", "850", "North Korea",false))
        countriesList.add(CountryDM("kr", "82", "South Korea",false))
        countriesList.add(CountryDM("kw", "965", "Kuwait",false))
        countriesList.add(CountryDM("ky", "1", "Cayman Islands",false))
        countriesList.add(CountryDM("kz", "7", "Kazakhstan",false))
        countriesList.add(CountryDM("la", "856", "Lao People's Democratic Republic",false))
        countriesList.add(CountryDM("lb", "961", "Lebanon",false))
        countriesList.add(CountryDM("lc", "1", "Saint Lucia",false))
        countriesList.add(CountryDM("li", "423", "Liechtenstein",false))
        countriesList.add(CountryDM("lk", "94", "Sri Lanka",false))
        countriesList.add(CountryDM("lr", "231", "Liberia",false))
        countriesList.add(CountryDM("ls", "266", "Lesotho",false))
        countriesList.add(CountryDM("lt", "370", "Lithuania",false))
        countriesList.add(CountryDM("lu", "352", "Luxembourg",false))
        countriesList.add(CountryDM("lv", "371", "Latvia",false))
        countriesList.add(CountryDM("ly", "218", "Libya",false))
        countriesList.add(CountryDM("ma", "212", "Morocco",false))
        countriesList.add(CountryDM("mc", "377", "Monaco",false))
        countriesList.add(CountryDM("md", "373", "Moldova, Republic Of",false))
        countriesList.add(CountryDM("me", "382", "Montenegro",false))
        countriesList.add(CountryDM("mf", "590", "Saint Martin",false))
        countriesList.add(CountryDM("mg", "261", "Madagascar",false))
        countriesList.add(CountryDM("mh", "692", "Marshall Islands",false))
        countriesList.add(CountryDM("mk", "389", "Macedonia (FYROM)",false))
        countriesList.add(CountryDM("ml", "223", "Mali",false))
        countriesList.add(CountryDM("mm", "95", "Myanmar",false))
        countriesList.add(CountryDM("mn", "976", "Mongolia",false))
        countriesList.add(CountryDM("mo", "853", "Macau",false))
        countriesList.add(CountryDM("mp", "1", "Northern Mariana Islands",false))
        countriesList.add(CountryDM("mq", "596", "Martinique",false))
        countriesList.add(CountryDM("mr", "222", "Mauritania",false))
        countriesList.add(CountryDM("ms", "1", "Montserrat",false))
        countriesList.add(CountryDM("mt", "356", "Malta",false))
        countriesList.add(CountryDM("mu", "230", "Mauritius",false))
        countriesList.add(CountryDM("mv", "960", "Maldives",false))
        countriesList.add(CountryDM("mw", "265", "Malawi",false))
        countriesList.add(CountryDM("mx", "52", "Mexico",false))
        countriesList.add(CountryDM("my", "60", "Malaysia",false))
        countriesList.add(CountryDM("mz", "258", "Mozambique",false))
        countriesList.add(CountryDM("na", "264", "Namibia",false))
        countriesList.add(CountryDM("nc", "687", "New Caledonia",false))
        countriesList.add(CountryDM("ne", "227", "Niger",false))
        countriesList.add(CountryDM("nf", "672", "Norfolk Islands",false))
        countriesList.add(CountryDM("ng", "234", "Nigeria",false))
        countriesList.add(CountryDM("ni", "505", "Nicaragua",false))
        countriesList.add(CountryDM("nl", "31", "Netherlands",false))
        countriesList.add(CountryDM("no", "47", "Norway",false))
        countriesList.add(CountryDM("np", "977", "Nepal",false))
        countriesList.add(CountryDM("nr", "674", "Nauru",false))
        countriesList.add(CountryDM("nu", "683", "Niue",false))
        countriesList.add(CountryDM("nz", "64", "New Zealand",false))
        countriesList.add(CountryDM("om", "968", "Oman",false))
        countriesList.add(CountryDM("pa", "507", "Panama",false))
        countriesList.add(CountryDM("pe", "51", "Peru",false))
        countriesList.add(CountryDM("pf", "689", "French Polynesia",false))
        countriesList.add(CountryDM("pg", "675", "Papua New Guinea",false))
        countriesList.add(CountryDM("ph", "63", "Philippines",false))
        countriesList.add(CountryDM("pk", "92", "Pakistan",false))
        countriesList.add(CountryDM("pl", "48", "Poland",false))
        countriesList.add(CountryDM("pm", "508", "Saint Pierre And Miquelon",false))
        countriesList.add(CountryDM("pn", "870", "Pitcairn Islands",false))
        countriesList.add(CountryDM("pr", "1", "Puerto Rico",false))
        countriesList.add(CountryDM("ps", "970", "Palestine",false))
        countriesList.add(CountryDM("pt", "351", "Portugal",false))
        countriesList.add(CountryDM("pw", "680", "Palau",false))
        countriesList.add(CountryDM("py", "595", "Paraguay",false))
        countriesList.add(CountryDM("qa", "974", "Qatar",false))
        countriesList.add(CountryDM("re", "262", "Réunion",false))
        countriesList.add(CountryDM("ro", "40", "Romania",false))
        countriesList.add(CountryDM("rs", "381", "Serbia",false))
        countriesList.add(CountryDM("ru", "7", "Russian Federation",false))
        countriesList.add(CountryDM("rw", "250", "Rwanda",false))
        countriesList.add(CountryDM("sa", "966", "Saudi Arabia",false))
        countriesList.add(CountryDM("sb", "677", "Solomon Islands",false))
        countriesList.add(CountryDM("sc", "248", "Seychelles",false))
        countriesList.add(CountryDM("sd", "249", "Sudan",false))
        countriesList.add(CountryDM("se", "46", "Sweden",false))
        countriesList.add(CountryDM("sg", "65", "Singapore",false))
        countriesList.add(CountryDM("sh", "290", "Saint Helena, Ascension And Tristan Da Cunha",))
        countriesList.add(CountryDM("si", "386", "Slovenia",false))
        countriesList.add(CountryDM("sk", "421", "Slovakia",false))
        countriesList.add(CountryDM("sl", "232", "Sierra Leone",false))
        countriesList.add(CountryDM("sm", "378", "San Marino",false))
        countriesList.add(CountryDM("sn", "221", "Senegal",false))
        countriesList.add(CountryDM("so", "252", "Somalia",false))
        countriesList.add(CountryDM("sr", "597", "Suriname",false))
        countriesList.add(CountryDM("ss", "211", "South Sudan",false))
        countriesList.add(CountryDM("st", "239", "Sao Tome And Principe",false))
        countriesList.add(CountryDM("sv", "503", "El Salvador",false))
        countriesList.add(CountryDM("sx", "1", "Sint Maarten",false))
        countriesList.add(CountryDM("sy", "963", "Syrian Arab Republic",false))
        countriesList.add(CountryDM("sz", "268", "Swaziland",false))
        countriesList.add(CountryDM("tc", "1", "Turks and Caicos Islands",false))
        countriesList.add(CountryDM("td", "235", "Chad",false))
        countriesList.add(CountryDM("tg", "228", "Togo",false))
        countriesList.add(CountryDM("th", "66", "Thailand",false))
        countriesList.add(CountryDM("tj", "992", "Tajikistan",false))
        countriesList.add(CountryDM("tk", "690", "Tokelau",false))
        countriesList.add(CountryDM("tl", "670", "Timor-leste",false))
        countriesList.add(CountryDM("tm", "993", "Turkmenistan",false))
        countriesList.add(CountryDM("tn", "216", "Tunisia",false))
        countriesList.add(CountryDM("to", "676", "Tonga",false))
        countriesList.add(CountryDM("tr", "90", "Turkey",false))
        countriesList.add(CountryDM("tt", "1", "Trinidad &amp; Tobago",false))
        countriesList.add(CountryDM("tv", "688", "Tuvalu",false))
        countriesList.add(CountryDM("tw", "886", "Taiwan",false))
        countriesList.add(CountryDM("tz", "255", "Tanzania, United Republic Of",false))
        countriesList.add(CountryDM("ua", "380", "Ukraine",false))
        countriesList.add(CountryDM("ug", "256", "Uganda",false))
        countriesList.add(CountryDM("us", "1", "United States",false))
        countriesList.add(CountryDM("uy", "598", "Uruguay",false))
        countriesList.add(CountryDM("uz", "998", "Uzbekistan",false))
        countriesList.add(CountryDM("va", "379", "Holy See (vatican City State)",false))
        countriesList.add(CountryDM("vc", "1", "Saint Vincent &amp; The Grenadines",false))
        countriesList.add(CountryDM("ve", "58", "Venezuela, Bolivarian Republic Of",false))
        countriesList.add(CountryDM("vg", "1", "British Virgin Islands",false))
        countriesList.add(CountryDM("vi", "1", "US Virgin Islands",false))
        countriesList.add(CountryDM("vn", "84", "Vietnam",false))
        countriesList.add(CountryDM("vu", "678", "Vanuatu",false))
        countriesList.add(CountryDM("wf", "681", "Wallis And Futuna",false))
        countriesList.add(CountryDM("ws", "685", "Samoa",false))
        countriesList.add(CountryDM("xk", "383", "Kosovo",false))
        countriesList.add(CountryDM("ye", "967", "Yemen",false))
        countriesList.add(CountryDM("yt", "262", "Mayotte",false))
        countriesList.add(CountryDM("za", "27", "South Africa",false))
        countriesList.add(CountryDM("zm", "260", "Zambia",false))
        countriesList.add(CountryDM("zw", "263", "Zimbabwe",false))


    }

    override fun continueClick() {

        showBottomSheetWhatsapp()

    }

    override fun pickerClick() {
        dialogCountryPicker(this)
    }

    fun setSelectedCountry(code:String)
    {
        binding.tvPhoneCode.text = "+"+code
    }


    private fun showBottomSheetWhatsapp() {
        country_code = binding.tvPhoneCode.text.toString()
        phone_number = ed_phone_no.text.toString()

        Log.d("*** LoginRegister >>>", ""+ phone_number)

        var bottomSheetDialog = MyBottomSheetDialog(this, false)



        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_send_otp_via_whatsapp, null)



        (view.findViewById(R.id.btn_continue) as View).setOnClickListener {

            bottomSheetDialog.dismiss()

            vm.sendOTP(Constants.API_KEY, phone_number, country_code)
            CommonUtilities.showLoader(this)



        }


        (view.findViewById(R.id.iv_back) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }


        (view.findViewById(R.id.tv_other_method) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
            showBottomSheetOtherMethod()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }


    private fun showBottomSheetOtherMethod() {


        var bottomSheetDialog = MyBottomSheetDialog(this, false)


        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_choose_verification_method, null)


        (view.findViewById(R.id.iv_back) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
            showBottomSheetWhatsapp()
        }



        (view.findViewById(R.id.rl_whatsapp) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }


        (view.findViewById(R.id.rl_otp) as View).setOnClickListener {

            bottomSheetDialog.dismiss()

         /*   CommonUtilities.fireActivityIntent(
                this,
                Intent(this, OTPActivity::class.java)
                    .putExtra("number", country_code+" "+ed_phone_no.text.toString().trim())
                    .putExtra("isUserExist", false),
                isFinish = false,
                isForward = true
            )*/

        }

        (view.findViewById(R.id.rl_misscall) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }



    private fun dialogCountryPicker(activity: Activity) {


        var dialogGet = CommonUtilities.customDialog(
            context = activity!!,
            dialogView = R.layout.dialog_country_picker
        )


        var rvCountry = dialogGet.findViewById<RecyclerView>(R.id.rvCountry)
        var edSearch = dialogGet.findViewById<EditText>(R.id.ed_search)


         countryAdapter =
            CountryAdapter(this,countriesList,dialogGet)
        rvCountry.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = countryAdapter
        }

        initCountrySearchListener(edSearch)


        dialogGet.show()
        dialogGet.setCancelable(true)



    }

    private fun initCountrySearchListener(edSearch: EditText) {

   /*     edSearch?.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(
                v: TextView?,
                actionId: Int,
                event: KeyEvent?
            ): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    //   binding!!.edSearch.clearFocus()
                    val `in`: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    `in`.hideSoftInputFromWindow(edSearch.windowToken, 0)

                    performCountrySearch(edSearch.text.toString().toLowerCase())



                    return true
                }
                return false
            }
        })*/


        edSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (s.isNotEmpty()) {

                        performCountrySearch(edSearch.text.toString().toLowerCase())

                    } else {

                        //    binding!!.edSearch.clearFocus()
                        val `in`: InputMethodManager =
                            getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                        `in`.hideSoftInputFromWindow(edSearch.windowToken, 0)

                        countryAdapter!!.updateList(countriesList)


                    }

                }
            }

            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })



    }


    fun performCountrySearch(s: String)
    {

         var countriesListResult: ArrayList<CountryDM> = ArrayList()


        for (i in countriesList)
        {
            if (i.code.toString().toLowerCase().contains(s) ||  i.countryName.toString().toLowerCase().contains(s) )
            {
                countriesListResult.add(i)
            }
        }

        countryAdapter!!.updateList(countriesListResult)

    }






    /**
     *  below code for google signin
     */
    private fun initGoogleIntegration() {
        mfirebaseAuth = FirebaseAuth.getInstance()

        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id))
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        val intent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(intent, RC_SIGN_IN_GOOGLE_SIGNIN)


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN_GOOGLE_SIGNIN) {
            val result = data?.let { Auth.GoogleSignInApi.getSignInResultFromIntent(it) }
            handleGoogleSignInResult(result!!)
        }
    }


    private fun handleGoogleSignInResult(result: GoogleSignInResult) {
        if (result.isSuccess) {
            val account = result.signInAccount
            val credential = GoogleAuthProvider.getCredential(account!!.idToken, null)
            firebaseAuthWithGoogle(credential)
        } else {
            // Google Sign In failed, update UI appropriately
            Toast.makeText(this, "Google Login Unsuccessful" + result.status.statusCode, Toast.LENGTH_LONG).show();
        }
    }


    private fun firebaseAuthWithGoogle(credential: AuthCredential) {
        mfirebaseAuth?.signInWithCredential(credential)?.addOnCompleteListener(
            this,
            OnCompleteListener<AuthResult?> { task ->
                Log.d("TAG", "signInWithCredential:onComplete:" + task.isSuccessful)
                if (task.isSuccessful) {
                  //  Toast.makeText(this, "google signin successful", Toast.LENGTH_LONG).show();
                  //  mGoogleSignInClient!!.signOut()


                } else {
                    Log.w("TAG", "signInWithCredential" + task.exception!!.message)
                    task.exception!!.printStackTrace()
                    Toast.makeText(this, " Google Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            })
    }


    private fun setObservers() {
        vm.sendOTP.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    //  CommonUtilities.showLoader(this)
                }
                Status.SUCCESS -> {
                    CommonUtilities.hideLoader()
                    val data = it.data!!.data

                    CommonUtilities.showToast(this,it.data.message?.en.toString())

                    if (it.data.status == true)
                    {



                        CommonUtilities.fireActivityIntent(
                            this,
                            Intent(this, OTPActivity::class.java)
                                .putExtra("number", country_code+" "+ed_phone_no.text.toString().trim())
                                .putExtra("country_code", country_code)
                                .putExtra("phone_wihout_code", phone_number)
                                .putExtra("isUserExist", data?.isUserExist),
                            isFinish = false,
                            isForward = true
                        )


                    }
                }
                Status.ERROR -> {
                    CommonUtilities.hideLoader()
                    CommonUtilities.showToast(this, it.message)
                }
            }
        });
    }





}