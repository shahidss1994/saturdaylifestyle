package com.shock.saturdaylifestyle.ui.main.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.databinding.FragmentHomeBinding
import com.shock.saturdaylifestyle.ui.base.fragment.BaseFragment
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val mViewModel: MainViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().apply {
            mainViewState = mViewModel.mainViewState
            rcv.adapter = HomeAdapter(mViewModel, activity)
        }
    }

    override fun listenChannel() {

    }

}