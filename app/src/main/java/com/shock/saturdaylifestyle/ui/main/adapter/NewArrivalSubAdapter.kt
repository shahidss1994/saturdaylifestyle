package com.shock.saturdaylifestyle.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemSublayoutNewArrivalsBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.adapter.NewArrivalSubAdapter.ViewHolder
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.NewArrivalItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.NewArrivalViewState

class NewArrivalSubAdapter(
    val viewModel: MainViewModel,
    val viewState: NewArrivalViewState
) :
    RecyclerView.Adapter<ViewHolder<NewArrivalItemViewState, MainViewModel>>(),
    BindableAdapter<NewArrivalItemViewState> {

    override var items: List<NewArrivalItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<NewArrivalItemViewState, MainViewModel> {
        return ViewHolder.ItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<NewArrivalItemViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.newArrivalItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ItemViewHolder(private val binding: ItemSublayoutNewArrivalsBinding) :
            ViewHolder<NewArrivalItemViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ItemViewHolder(
                    ItemSublayoutNewArrivalsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: NewArrivalItemViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}