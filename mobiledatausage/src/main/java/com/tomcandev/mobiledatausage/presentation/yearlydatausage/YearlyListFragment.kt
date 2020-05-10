package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tomcandev.core.base.fragment.BaseFragment
import com.tomcandev.mobiledatausage.R
import com.tomcandev.mobiledatausage.databinding.MobiledatausageFragmentYearlyListBinding
import com.tomcandev.mobiledatausage.presentation.utils.NetworkState
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.di.YearlyDataUsageComponent
import java.lang.StringBuilder
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
        yearlyListViewModel.initialLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                is NetworkState.Loading -> {
                    binding.vSwipeRefresh.isRefreshing = true
                    binding.rvRecordList.visibility = View.GONE
                    binding.clError.visibility = View.GONE
                }
                is NetworkState.Error -> {
                    binding.vSwipeRefresh.isRefreshing = false
                    binding.rvRecordList.visibility = View.GONE
                    binding.clError.visibility = View.VISIBLE
                }
                else -> {
                    binding.vSwipeRefresh.isRefreshing = false
                    binding.rvRecordList.visibility = View.VISIBLE
                    binding.clError.visibility = View.GONE
                }
            }
        })
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
            context?.let {
                val message = StringBuilder()
                for ((index, quarter) in yearlyItemModel.quarters.withIndex()) {
                    val stringId = when {
                        index == 0 -> R.string.text_quarter_format
                        quarter.volume < yearlyItemModel.quarters[index - 1].volume -> R.string.text_quarter_down_format
                        else -> R.string.text_quarter_up_format
                    }
                    message.append(getString(stringId, quarter.quarter, quarter.volume))
                }
                AlertDialog.Builder(it)
                    .setTitle(
                        getString(
                            R.string.text_year_volume_format,
                            yearlyItemModel.year,
                            yearlyItemModel.volume
                        )
                    )
                    .setMessage(message.toString())
                    .setCancelable(true)
                    .setPositiveButton(R.string.text_ok, null).show()
            }

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
