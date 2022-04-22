package com.shock.saturdaylifestyle.ui.login_register.view

/*
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
import com.saturdays.login_register.adapters.CountryAdapter
import com.shock.saturdaylifestyle.ui.login_register.callbacks.LoginRegisterActivityViewCallBacks
import com.shock.saturdaylifestyle.ui.login_register.models.CountryDM
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.SignInActivityDataBinding
import com.shock.saturdaylifestyle.di.DaggerProvider
import com.shock.saturdaylifestyle.di.modules.ViewModules.ViewModelFactory
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



    */
/**
     *  below code for google signin
     *//*

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
        countriesList.add(CountryDM(1,"+91","India",false))
        countriesList.add(CountryDM(2,"+92","Pakistan",false))
        countriesList.add(CountryDM(3,"+1","United States",false))
        countriesList.add(CountryDM(4,"+11","United Kingdom",false))
        countriesList.add(CountryDM(5,"+42","Australia",false))

        countriesList.add(CountryDM(6,"+91","India",false))
        countriesList.add(CountryDM(7,"+92","Pakistan",false))
        countriesList.add(CountryDM(8,"+1","United States",false))
        countriesList.add(CountryDM(9,"+11","United Kingdom",false))
        countriesList.add(CountryDM(10,"+42","Australia",false))

        countriesList.add(CountryDM(11,"+91","India",false))
        countriesList.add(CountryDM(12,"+92","Pakistan",false))
        countriesList.add(CountryDM(13,"+1","United States",false))
        countriesList.add(CountryDM(14,"+11","United Kingdom",false))
        countriesList.add(CountryDM(15,"+42","Australia",false))

        countriesList.add(CountryDM(16,"+91","India",false))
        countriesList.add(CountryDM(17,"+92","Pakistan",false))
        countriesList.add(CountryDM(18,"+1","United States",false))
        countriesList.add(CountryDM(19,"+11","United Kingdom",false))
        countriesList.add(CountryDM(20,"+42","Australia",false))

    }

    override fun continueClick() {

        showBottomSheetWhatsapp()

    }

    override fun pickerClick() {
        dialogCountryPicker(this)
    }

    fun setSelectedCountry(code:String)
    {
        binding.tvPhoneCode.text = code
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

            vm.sendOTP(Constants.API_KEY,binding.edPhoneNo.text.toString().trim(), country_code)
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

            CommonUtilities.fireActivityIntent(
                this,
                Intent(this, OTPActivity::class.java).putExtra("number", country_code+" "+ed_phone_no.text.toString().trim()),
                isFinish = false,
                isForward = true
            )

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

   */
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
        })*//*



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






    */
/**
     *  below code for google signin
     *//*

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



                    CommonUtilities.fireActivityIntent(
                        this,
                        Intent(this, RegisterForm1Activity::class.java),
                        isFinish = false,
                        isForward = true
                    )



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
                            Intent(this, OTPActivity::class.java).putExtra("number", country_code+" "+ed_phone_no.text.toString().trim()),
                            isFinish = true,
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




}*/
