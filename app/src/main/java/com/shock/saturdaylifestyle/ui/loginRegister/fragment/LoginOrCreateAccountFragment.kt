package com.shock.saturdaylifestyle.ui.loginRegister.fragment

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.FragmentLoginOrCreateAccountBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BottomSheetBaseFragment
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOrCreateAccountFragment: BottomSheetBaseFragment<FragmentLoginOrCreateAccountBinding>(R.layout.fragment_login_or_create_account) {

    companion object {
        const val TAG = "LoginOrCreateAccountFragment"
    }

    private val mViewModel: LoginRegisterViewModel by activityViewModels()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return (super.onCreateDialog(savedInstanceState) as BottomSheetDialog).apply {
            setOnShowListener {
                behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            viewModel = mViewModel
            viewState = mViewModel.viewState
        }
    }

    override fun listenChannel() {

    }

    override fun onDestroy() {
        super.onDestroy()
        mViewModel.viewState.loginOrCreateAccountVisibility = true
    }

}