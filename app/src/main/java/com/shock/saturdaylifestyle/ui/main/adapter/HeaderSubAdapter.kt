package com.shock.saturdaylifestyle.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemSublayoutHomeHeaderBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.adapter.HeaderSubAdapter.ViewHolder
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.HeaderItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HeaderViewState

class HeaderSubAdapter(
    val viewModel: MainViewModel,
    val viewState: HeaderViewState
) :
    RecyclerView.Adapter<ViewHolder<HeaderItemViewState, MainViewModel>>(),
    BindableAdapter<HeaderItemViewState> {

    override var items: List<HeaderItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<HeaderItemViewState, MainViewModel> {
        return ViewHolder.ItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<HeaderItemViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.initHeaderItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ItemViewHolder(private val binding: ItemSublayoutHomeHeaderBinding) :
            ViewHolder<HeaderItemViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ItemViewHolder(
                    ItemSublayoutHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: HeaderItemViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}