package com.shock.saturdaylifestyle.ui.login_register.view
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.saturdays.login_register.adapters.CountryAdapter
import com.saturdays.login_register.callbacks.LoginRegisterActivityViewCallBacks
import com.saturdays.login_register.models.CountryDM
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.SignInActivityDataBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import com.shock.saturdaylifestyle.utility.CommonUtilities
import com.shock.saturdaylifestyle.utility.MyBottomSheetDialog


class LoginRegisterActivity : BaseActivity<SignInActivityDataBinding>(),
    LoginRegisterActivityViewCallBacks {


    private lateinit var binding : SignInActivityDataBinding
    private val TAG = LoginRegisterActivity::class.java.simpleName
    private var countriesList: ArrayList<CountryDM> = ArrayList()

    var countryAdapter : CountryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding()
        initCountryList()
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


        /*if (binding.edPhoneNo.text.toString().isEmpty()) {
            binding.edPhoneNo.error = getString(R.string.please_enter_your_phone_no)

        }
        else if (binding.edPhoneNo.text.toString().length>=10) {
            binding.edPhoneNo.error = getString(R.string.please_enter_valid_phone_no)

        }
        else {

            showBottomSheetWhatsapp()

        }*/


    }

    override fun pickerClick() {
        dialogCountryPicker(this)
    }

    fun setSelectedCountry(code:String)
    {
        binding.tvPhoneCode.text = code
    }


    private fun showBottomSheetWhatsapp() {


        var bottomSheetDialog = MyBottomSheetDialog(this, false)



        @SuppressLint("InflateParams") val view: View =
            layoutInflater.inflate(R.layout.dialog_send_otp_via_whatsapp, null)



        (view.findViewById(R.id.btn_continue) as View).setOnClickListener {

            bottomSheetDialog.dismiss()

            CommonUtilities.fireActivityIntent(
                this,
                Intent(this, RegisterForm1Activity::class.java),
                isFinish = false,
                isForward = true
            )

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


        (view.findViewById(R.id.rl_misscall) as View).setOnClickListener {

            bottomSheetDialog.dismiss()
        }



        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }



    fun dialogCountryPicker(activity: Activity) {


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


    fun performCountrySearch(s: String) {

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

    override fun getLayoutId(): Int {
        return R.layout.activity_login_register
    }

    override fun listenChannel() {

    }


}