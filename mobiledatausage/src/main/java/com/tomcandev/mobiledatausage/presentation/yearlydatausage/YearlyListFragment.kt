package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tomcandev.mobiledatausage.R

class YearlyListFragment : Fragment() {

    companion object {
        fun newInstance() = YearlyListFragment()
    }

    private lateinit var viewModel: YearlyListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_yearly_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}
