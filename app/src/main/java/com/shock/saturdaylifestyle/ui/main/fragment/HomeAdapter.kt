package com.shock.saturdaylifestyle.ui.main.fragment

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.viewpager2.widget.ViewPager2
import com.shock.saturdaylifestyle.R
import com.shock.saturdaylifestyle.constants.Constants
import com.shock.saturdaylifestyle.databinding.ItemEmptyBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutDetailsMakeUsDifferentBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutExploreOurTopPicksBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutHomeHeaderBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutHomeTryOnBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutNewArrivalsBinding
import com.shock.saturdaylifestyle.databinding.ItemLayoutWhatTheySayBinding
import com.shock.saturdaylifestyle.ui.base.adapter.BindableAdapter
import com.shock.saturdaylifestyle.ui.base.others.diffCallback
import com.shock.saturdaylifestyle.ui.main.adapter.DetailsMakeUsDifferentSubAdapter
import com.shock.saturdaylifestyle.ui.main.adapter.ExploreTopPicksSubAdapter
import com.shock.saturdaylifestyle.ui.main.adapter.NewArrivalSubAdapter
import com.shock.saturdaylifestyle.ui.main.adapter.WhatTheySaySubAdapter
import com.shock.saturdaylifestyle.ui.main.models.HomeViewPagerDM
import com.shock.saturdaylifestyle.ui.main.viewModel.MainViewModel
import com.shock.saturdaylifestyle.ui.main.viewState.DetailsMakeUsDifferentViewState
import com.shock.saturdaylifestyle.ui.main.viewState.ExploreOurTopPicksViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HeaderViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HomeTryOnViewState
import com.shock.saturdaylifestyle.ui.main.viewState.HomeViewState
import com.shock.saturdaylifestyle.ui.main.viewState.NewArrivalViewState
import com.shock.saturdaylifestyle.ui.main.viewState.WhatTheySayViewState
import com.shock.saturdaylifestyle.util.SpacesItemDecoration

class HomeAdapter(val viewModel: MainViewModel) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder<HomeViewState, MainViewModel>>(),
    BindableAdapter<HomeViewState> {

    private val EMPTY_VIEW_TYPE = 1
    private val HEADER_VIEW_TYPE = 2
    private val DETAILS_MAKE_US_DIFFERENT_VIEW_TYPE = 3
    private val TOP_PICKS_VIEW_TYPE = 4
    private val NEW_ARRIVALS_VIEW_TYPE = 5
    private val HOME_TRY_ON_VIEW_TYPE = 6
    private val WHAT_THEY_SAY_VIEW_TYPE = 7

    override var items: List<HomeViewState> by diffCallback(emptyList()) { o, n ->
        o.id == n.id
    }

    sealed class ViewHolder<VIEW_STATE, in VIEW_MODEL : ViewModel>(view: View) :
        RecyclerView.ViewHolder(view) {

        abstract fun bind(
            viewState: VIEW_STATE,
            viewModel: VIEW_MODEL
        )

        class HeaderViewHolder(private val binding: ItemLayoutHomeHeaderBinding) :
            ViewHolder<HomeViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = HeaderViewHolder(
                    ItemLayoutHomeHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: HomeViewState, viewModel: MainViewModel
            ) {
                val homeViewState = viewState.viewState as HeaderViewState
                binding.viewState = homeViewState
                val viewpagerDataList = ArrayList<HomeViewPagerDM>()
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))
                viewpagerDataList.add(HomeViewPagerDM(R.mipmap.iv_home_viewpager_item))

                val adapter = HomeViewPagerAdapter(viewpagerDataList)
                binding.viewPager.adapter = adapter
                binding.dotsIndicator.setViewPager2(binding.viewPager)
                binding.viewPager.currentItem = homeViewState.previousPosition
                homeViewState.runnable?.let { runnable ->
                    homeViewState.handler?.let { handler ->
                        handler.removeCallbacks(runnable)
                    }
                }
                homeViewState.runnable = null
                if (homeViewState.handler == null) {
                    homeViewState.handler = Handler(Looper.getMainLooper())
                }
                homeViewState.runnable = Runnable {
                    homeViewState.previousPosition =
                        (homeViewState.previousPosition + 1) % viewpagerDataList.size
                    binding.viewPager.currentItem = homeViewState.previousPosition
                }
                binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                        homeViewState.runnable?.let { runnable ->
                            homeViewState.handler?.let { handler ->
                                handler.postDelayed(runnable,2000L)
                            }
                        }
                    }
                })
            }

        }

        class DetailsMakeUsDifferentViewHolder(private val binding: ItemLayoutDetailsMakeUsDifferentBinding) :
            ViewHolder<HomeViewState, MainViewModel>(binding.root) {

            companion object {
                operator fun invoke(parent: ViewGroup) = DetailsMakeUsDifferentViewHolder(
                    ItemLayoutDetailsMakeUsDifferentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            override fun bind(
                viewState: HomeViewState, viewModel: MainViewModel
            ) {
                binding.viewState = viewState.viewState as DetailsMakeUsDifferentViewState

                val llmanager = LinearLayoutManager(
                    binding.root.context,
                    LinearLayoutManager.HORIZONTAL,
                    false
                )

                val mainSubAdapter = DetailsMakeUsDifferentSubAdapter(
                    viewModel,
                    viewState.viewState as DetailsMakeUsDifferentViewState
                )

                binding.rvDetailsMakeUsDifferentSublist.apply {
                    layoutManager = llmanager
                    addItemDecoration(SpacesItemDecoration(10))
                    (itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                        false
                    adapter = mainSubAdapter
                    setHasFixedSize(true)
                }
            }
        }
    }

    class NewArrivalsViewHolder(private val binding: ItemLayoutNewArrivalsBinding) :
        ViewHolder<HomeViewState, MainViewModel>(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup) = NewArrivalsViewHolder(
                ItemLayoutNewArrivalsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun bind(
            viewState: HomeViewState, viewModel: MainViewModel
        ) {
            binding.viewState = viewState.viewState as NewArrivalViewState

            val llmanager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            val mainSubAdapter = NewArrivalSubAdapter(viewModel, viewState.viewState as NewArrivalViewState)

            binding.rvNewArrivalSublist.apply {
                layoutManager = llmanager
                addItemDecoration(SpacesItemDecoration(10))
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                    false
                adapter = mainSubAdapter
                setHasFixedSize(true)
            }
        }

    }

    class ExploreOurTopPicksViewHolder(private val binding: ItemLayoutExploreOurTopPicksBinding) :
        ViewHolder<HomeViewState, MainViewModel>(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup) = ExploreOurTopPicksViewHolder(
                ItemLayoutExploreOurTopPicksBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun bind(
            viewState: HomeViewState, viewModel: MainViewModel
        ) {
            binding.viewState = viewState.viewState as ExploreOurTopPicksViewState

            val llmanager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            //set sublist adapter
            val mainSubAdapter = ExploreTopPicksSubAdapter(
                viewModel,
                viewState.viewState as ExploreOurTopPicksViewState
            )

            binding.rvExploreTopPickSublist.apply {
                layoutManager = llmanager
                addItemDecoration(SpacesItemDecoration(10))
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                    false
                adapter = mainSubAdapter
                setHasFixedSize(true)
            }
        }

    }

    class HomeTryOnViewHolder(private val binding: ItemLayoutHomeTryOnBinding) :
        ViewHolder<HomeViewState, MainViewModel>(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup) = HomeTryOnViewHolder(
                ItemLayoutHomeTryOnBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun bind(
            viewState: HomeViewState, viewModel: MainViewModel
        ) {
            binding.viewState = viewState.viewState as HomeTryOnViewState
        }

    }

    class WhatTheySayViewHolder(private val binding: ItemLayoutWhatTheySayBinding) :
        ViewHolder<HomeViewState, MainViewModel>(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup) = WhatTheySayViewHolder(
                ItemLayoutWhatTheySayBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun bind(
            viewState: HomeViewState, viewModel: MainViewModel
        ) {
            binding.viewState = viewState.viewState as WhatTheySayViewState

            val llmanager = LinearLayoutManager(
                binding.root.context,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            //set sublist adapter
            val mainSubAdapter =
                WhatTheySaySubAdapter(viewModel, viewState.viewState as WhatTheySayViewState)

            binding.rvWhatTheySaySublist.apply {
                layoutManager = llmanager
                addItemDecoration(SpacesItemDecoration(10))
                (itemAnimator as SimpleItemAnimator).supportsChangeAnimations =
                    false
                adapter = mainSubAdapter
                setHasFixedSize(true)
            }
        }
    }


    class EmptyViewHolder(private val binding: ItemEmptyBinding) :
        ViewHolder<HomeViewState, MainViewModel>(binding.root) {

        companion object {
            operator fun invoke(parent: ViewGroup) = EmptyViewHolder(
                ItemEmptyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun bind(
            viewState: HomeViewState, viewModel: MainViewModel
        ) {

        }

    }


    override fun getItemViewType(position: Int): Int = when (items[position].sectionName) {
        Constants.SectionName.Home.HEADER -> HEADER_VIEW_TYPE
        Constants.SectionName.Home.DETAILS_MAKE_US_DIFFERENT -> DETAILS_MAKE_US_DIFFERENT_VIEW_TYPE
        Constants.SectionName.Home.NEW_ARRIVALS -> NEW_ARRIVALS_VIEW_TYPE
        Constants.SectionName.Home.TOP_PICKS -> TOP_PICKS_VIEW_TYPE
        Constants.SectionName.Home.HOME_TRY_ON -> HOME_TRY_ON_VIEW_TYPE
        Constants.SectionName.Home.WHAT_THEY_SAY -> WHAT_THEY_SAY_VIEW_TYPE
        else -> EMPTY_VIEW_TYPE
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): ViewHolder<HomeViewState, MainViewModel> {
        return when (viewType) {
            HEADER_VIEW_TYPE -> ViewHolder.HeaderViewHolder(parent)
            DETAILS_MAKE_US_DIFFERENT_VIEW_TYPE -> ViewHolder.DetailsMakeUsDifferentViewHolder(
                parent
            )
            TOP_PICKS_VIEW_TYPE -> ExploreOurTopPicksViewHolder(parent)
            NEW_ARRIVALS_VIEW_TYPE -> NewArrivalsViewHolder(parent)
            HOME_TRY_ON_VIEW_TYPE -> HomeTryOnViewHolder(parent)
            WHAT_THEY_SAY_VIEW_TYPE -> WhatTheySayViewHolder(parent)
            else -> EmptyViewHolder(parent)
        }
    }

    override fun onBindViewHolder(
        holder: ViewHolder<HomeViewState, MainViewModel>,
        position: Int,
    ) {
        holder.bind(items[position], viewModel)
    }

    override fun getItemCount() = items.size

}