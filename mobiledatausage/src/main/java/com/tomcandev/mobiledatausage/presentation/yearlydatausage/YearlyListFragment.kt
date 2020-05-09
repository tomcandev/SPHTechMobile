package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomcandev.core.base.fragment.BaseFragment

import com.tomcandev.mobiledatausage.R
import com.tomcandev.mobiledatausage.databinding.MobiledatausageFragmentYearlyListBinding
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.di.YearlyDataUsageComponent
import javax.inject.Inject

class YearlyListFragment : BaseFragment() {
    @Inject
    lateinit var yearlyListAdapter: YearlyListAdapter

    @Inject
    lateinit var yearlyListViewModelFactory: YearlyListViewModelFactory
    lateinit var yearlyListViewModel: YearlyListViewModel

    private lateinit var binding: MobiledatausageFragmentYearlyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MobiledatausageFragmentYearlyListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun componentInject() {
        YearlyDataUsageComponent.init().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        yearlyListViewModel =
            ViewModelProvider(this, yearlyListViewModelFactory).get(YearlyListViewModel::class.java)

        setupToolbar()
        setupRecyclerView()
        setupLoading()
    }

    private fun setupLoading() {
        binding.vSwipeRefresh.setOnRefreshListener {
            yearlyListViewModel.refresh()
            binding.vSwipeRefresh.isRefreshing = false
        }
        binding.btnTry.setOnClickListener {
            yearlyListViewModel.refresh()
            binding.vSwipeRefresh.isRefreshing = false
        }
    }

    private fun setupRecyclerView() {
        yearlyListAdapter.imageClickCallback = { yearlyItemModel ->
        }
        binding.rvRecordList.adapter = yearlyListAdapter
        binding.rvRecordList.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        yearlyListViewModel.pagedList.observe(viewLifecycleOwner, Observer {
            yearlyListAdapter.submitList(it)
        })
    }

    private fun setupToolbar() {
        (activity as AppCompatActivity?)?.setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.title_yearly_list)
    }
}
