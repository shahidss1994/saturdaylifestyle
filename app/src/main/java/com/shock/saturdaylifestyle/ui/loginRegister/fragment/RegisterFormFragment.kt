package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.FragmentRegisterFormBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BottomSheetBaseFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class RegisterFormFragment :
    BottomSheetBaseFragment<FragmentRegisterFormBinding>(R.layout.fragment_register_form) {

    companion object {
        const val TAG = "LoginOrCreateAccountFragment"
    }

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            mViewModel.initRegisterViewState()
            viewModel = mViewModel
            viewState = mViewModel.registerFormViewState
        }
    }

    override fun listenChannel() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.destroyRegisterViewState()
    }


}