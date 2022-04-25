package com.shock.saturdaylifestyle.ui.loginRegister.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemCountryBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.loginRegister.viewModel.LoginRegisterViewModel
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.CountryCodeNumberViewState
import com.shock.saturdaylifestyle.ui.loginRegister.viewState.LoginRegisterViewState

class CountryCodeNumberAdapter(
    val viewModel: LoginRegisterViewModel,
    val viewState: LoginRegisterViewState
) :
    RecyclerView.Adapter<CountryCodeNumberAdapter.ViewHolder<CountryCodeNumberViewState, LoginRegisterViewModel>>(),
    BindableAdapter<CountryCodeNumberViewState> {

    override var items: List<CountryCodeNumberViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<CountryCodeNumberViewState, LoginRegisterViewModel> {
        return ViewHolder.ContentItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<CountryCodeNumberViewState, LoginRegisterViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.countryCodeNumberViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ContentItemViewHolder(private val binding: ItemCountryBinding) :
            ViewHolder<CountryCodeNumberViewState, LoginRegisterViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ContentItemViewHolder(
                    ItemCountryBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: CountryCodeNumberViewState,
                viewModel: LoginRegisterViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}