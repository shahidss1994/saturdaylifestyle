package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.DialogCountryPickerBinding
import com.shock.saturdaylifestyle.ui.base.fragment.DialogBaseFragment
import com.shock.saturdaylifestyle.ui.loginRegister.adapter.CountryCodeNumberAdapter
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryCodeNumberFragment :
    DialogBaseFragment<DialogCountryPickerBinding>(R.layout.dialog_country_picker) {

    companion object {
        const val TAG = "CountryCodeFragment"
    }

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewState = mViewModel.viewState
            with(rvCountry) {
                adapter = CountryCodeNumberAdapter(mViewModel, mViewModel.viewState)
            }
        }
    }

    override fun listenChannel() {

    }

}