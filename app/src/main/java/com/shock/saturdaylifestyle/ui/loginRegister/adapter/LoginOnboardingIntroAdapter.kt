package com.shock.saturdaylifestyle.ui.loginRegister.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.OnboardingPagerItemBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.IntroViewPagerItemViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState

class LoginOnboardingIntroAdapter(
    val viewModel: LoginRegisterViewModel,
    val viewState: LoginRegisterViewState
) :
    RecyclerView.Adapter<LoginOnboardingIntroAdapter.ViewHolder<IntroViewPagerItemViewState, LoginRegisterViewModel>>(),
    BindableAdapter<IntroViewPagerItemViewState> {

    override var items: List<IntroViewPagerItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<IntroViewPagerItemViewState, LoginRegisterViewModel> {
        return ViewHolder.ContentItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<IntroViewPagerItemViewState, LoginRegisterViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.introViewPagerItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ContentItemViewHolder(private val binding: OnboardingPagerItemBinding) :
            ViewHolder<IntroViewPagerItemViewState, LoginRegisterViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ContentItemViewHolder(
                    OnboardingPagerItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: IntroViewPagerItemViewState,
                viewModel: LoginRegisterViewModel
            ) {
                binding.viewState = viewState
            }

        }

    }

}