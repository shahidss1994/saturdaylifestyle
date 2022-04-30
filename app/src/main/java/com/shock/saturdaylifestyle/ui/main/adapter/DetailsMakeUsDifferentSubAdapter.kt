package com.shock.saturdaylifestyle.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemSublayoutDetailsMakeUsDifferentBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.adapter.DetailsMakeUsDifferentSubAdapter.ViewHolder
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.DetailsMakeUsDifferentItemViewState
import com.shock.saturdaylifestyle.ui.main.viewState.DetailsMakeUsDifferentViewState

class DetailsMakeUsDifferentSubAdapter(
    val viewModel: MainViewModel,
    val viewState: DetailsMakeUsDifferentViewState
) :
    RecyclerView.Adapter<ViewHolder<DetailsMakeUsDifferentItemViewState, MainViewModel>>(),
    BindableAdapter<DetailsMakeUsDifferentItemViewState> {

    override var items: List<DetailsMakeUsDifferentItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<DetailsMakeUsDifferentItemViewState, MainViewModel> {
        return ViewHolder.ItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<DetailsMakeUsDifferentItemViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.detailsMakesUsDifferentItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ItemViewHolder(private val binding: ItemSublayoutDetailsMakeUsDifferentBinding) :
            ViewHolder<DetailsMakeUsDifferentItemViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ItemViewHolder(
                    ItemSublayoutDetailsMakeUsDifferentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: DetailsMakeUsDifferentItemViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}