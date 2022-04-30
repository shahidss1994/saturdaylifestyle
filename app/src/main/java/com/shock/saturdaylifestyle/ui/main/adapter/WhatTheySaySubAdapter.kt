package com.shock.saturdaylifestyle.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemSublayoutWhatTheySayBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.WhatTheySayItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.WhatTheySayViewState

class WhatTheySaySubAdapter(
    val viewModel: MainViewModel,
    val viewState: WhatTheySayViewState
) :
    RecyclerView.Adapter<WhatTheySaySubAdapter.ViewHolder<WhatTheySayItemViewState, MainViewModel>>(),
    BindableAdapter<WhatTheySayItemViewState> {

    override var items: List<WhatTheySayItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<WhatTheySayItemViewState, MainViewModel> {
        return ViewHolder.ItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<WhatTheySayItemViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.whatTheySayItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ItemViewHolder(private val binding: ItemSublayoutWhatTheySayBinding) :
            ViewHolder<WhatTheySayItemViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ItemViewHolder(
                    ItemSublayoutWhatTheySayBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: WhatTheySayItemViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}