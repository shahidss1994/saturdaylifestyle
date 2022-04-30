package com.shock.saturdaylifestyle.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.shock.saturdaylifestyle.databinding.ItemSublayoutHomeExploreTopPicksBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.adapter.ExploreTopPicksSubAdapter.ViewHolder
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreOurTopPicksViewState
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreTopPicksItemViewState

class ExploreTopPicksSubAdapter(
    val viewModel: MainViewModel,
    val viewState: ExploreOurTopPicksViewState
) :
    RecyclerView.Adapter<ViewHolder<ExploreTopPicksItemViewState, MainViewModel>>(),
    BindableAdapter<ExploreTopPicksItemViewState> {

    override var items: List<ExploreTopPicksItemViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder<ExploreTopPicksItemViewState, MainViewModel> {
        return ViewHolder.ItemViewHolder(parent)
    }

    override fun onBindViewHolder(
        holder: ViewHolder<ExploreTopPicksItemViewState, MainViewModel>,
        position: Int
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = viewState.initExploreTopPicksItemViewStateList.size

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(viewState: VIEW_STATE, viewModel: VIEW_MODEL)

        class ItemViewHolder(private val binding: ItemSublayoutHomeExploreTopPicksBinding) :
            ViewHolder<ExploreTopPicksItemViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = ItemViewHolder(
                    ItemSublayoutHomeExploreTopPicksBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: ExploreTopPicksItemViewState,
                viewModel: MainViewModel
            ) {
                binding.viewModel = viewModel
                binding.viewState = viewState
            }

        }

    }

}