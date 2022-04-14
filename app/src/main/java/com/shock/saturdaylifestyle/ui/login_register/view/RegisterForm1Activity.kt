package com.shock.saturdaylifestyle.ui.login_register.view
import android.app.DatePickerDialog
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.DatePicker
import android.widget.TextView
import androidx.annotation.RequiresApi

import com.saturdays.login_register.callbacks.RegisterForm1ActivityViewCallBacks
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.RegisterForm1ActivityDataBinding
import com.shock.saturdaylifestyle.ui.common.BaseActivity
import java.text.SimpleDateFormat
import java.util.*


class RegisterForm1Activity : BaseActivity<RegisterForm1ActivityDataBinding>(),
    RegisterForm1ActivityViewCallBacks , DatePickerDialog.OnDateSetListener {


    private val TAG = RegisterForm1Activity::class.java.simpleName

    private lateinit var binding : RegisterForm1ActivityDataBinding

    private lateinit var tv_dob : TextView

    var isGenderSelected= false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = binding()
        tv_dob = findViewById(R.id.tv_dob)
        tv_dob.setOnClickListener {
            pickerClick()
        }

        initTextChangeListeners()
    }


    private fun initTextChangeListeners() {

        binding.edFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!binding.edFirstName.text.toString().isNotEmpty())
                    {
                        binding.clFirstName.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        binding.tvAlertFirstName.visibility= View.VISIBLE
                    }


                    initFieldsObserver()
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

        binding.edLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!binding.edLastName.text.toString().isNotEmpty())
                    {
                        binding.clLastName.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        binding.tvAlertLastName.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

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

        binding.edEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!binding.edEmail.text.toString().isNotEmpty())
                    {
                        binding.clEmail.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        binding.tvAlertEmail.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

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

        binding.tvDob.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null) {

                    if (!binding.tvDob.text.toString().isNotEmpty())
                    {
                        binding.clDob.background = resources.getDrawable(R.drawable.bg_edittext_error)
                        binding.tvAlertDob.visibility= View.VISIBLE
                    }

                    initFieldsObserver()

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

    private fun initFieldsObserver() {

        if (binding.edFirstName.text.toString().isNotEmpty())
        {
            binding.clFirstName.background = resources.getDrawable(R.drawable.bg_edittext)
            binding.tvAlertFirstName.visibility= View.INVISIBLE
        }


        if (binding.edLastName.text.toString().isNotEmpty())
        {
            binding.clLastName.background = resources.getDrawable(R.drawable.bg_edittext)
            binding.tvAlertLastName.visibility= View.INVISIBLE
        }


        if (binding.edEmail.text.toString().isNotEmpty())
        {
            binding.clEmail.background = resources.getDrawable(R.drawable.bg_edittext)
            binding.tvAlertEmail.visibility= View.INVISIBLE
        }


        if (binding.tvDob.text.toString().isNotEmpty())
        {
            binding.clDob.background = resources.getDrawable(R.drawable.bg_edittext)
            binding.tvAlertDob.visibility= View.INVISIBLE
        }




        if (binding.edFirstName.text.toString().isNotEmpty() &&
            binding.edLastName.text.toString().isNotEmpty()  &&
            binding.tvDob.text.toString().isNotEmpty()  &&
            Patterns.EMAIL_ADDRESS.matcher(binding.edEmail!!.text.toString()).matches()
        ) {

            binding.btnContinue.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#133B64"));
            binding.btnContinue.setTextColor(Color.parseColor("#FFFFFF"))
        }else
        {
            binding.btnContinue.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#D8D8D8"))
            binding.btnContinue.setTextColor(Color.parseColor("#979797"))


        }


    }

    override fun continueClick() {


        if (binding.edFirstName.text.isEmpty()) {
            binding.clFirstName.background = resources.getDrawable(R.drawable.bg_edittext_error)
            binding.tvAlertFirstName.visibility= View.VISIBLE

        } else if (binding.edLastName.text.isEmpty()) {
            binding.clLastName.background = resources.getDrawable(R.drawable.bg_edittext_error)
            binding.tvAlertLastName.visibility= View.VISIBLE
        }  else if (binding.edEmail.text.isEmpty()) {
            binding.clEmail.background = resources.getDrawable(R.drawable.bg_edittext_error)
            binding.tvAlertEmail.visibility= View.VISIBLE
        } else if (!(Patterns.EMAIL_ADDRESS.matcher(binding.edEmail.text.toString())
                .matches()))
        {
            binding.clEmail.background = resources.getDrawable(R.drawable.bg_edittext_error)
            binding.tvAlertEmail.visibility= View.VISIBLE
        } else if (binding.tvDob.text.isEmpty()) {

            binding.clDob.background = resources.getDrawable(R.drawable.bg_edittext_error)
            binding.tvAlertDob.visibility= View.VISIBLE

        } else{

        }


    }

    override fun pickerClick() {

    val calendar = Calendar.getInstance()
    val mYear = calendar.get(Calendar.YEAR)
    val mMonth = calendar.get(Calendar.MONTH)
    val mDay = calendar.get(Calendar.DAY_OF_MONTH)
    var datePickerDialog = DatePickerDialog(this, R.style.datepicker, this, mYear, mMonth, mDay)
    datePickerDialog.datePicker.maxDate = calendar.timeInMillis
    datePickerDialog.show()

    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar2: Calendar = Calendar.getInstance()
        calendar2.set(year, month, dayOfMonth, 0, 0, 0)
        val selectedDate = calendar2.time
        val dateFormat = SimpleDateFormat("dd-MMM-yyyy")
        var date= dateFormat.format(selectedDate)

        tv_dob.text = date.toString()

    }

    override fun getLayoutId(): Int {
        return R.layout.activity_register_form1
    }

    override fun listenChannel() {
    }


}