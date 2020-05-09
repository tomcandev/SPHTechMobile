package com.tomcandev.mobiledatausage.presentation.yearlydatausage

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.viewholder.YearlyViewHolder

class YearlyListAdapter(var imageClickCallback: ((YearlyItemModel) -> Unit)? = null) :
    PagedListAdapter<YearlyListViewType, RecyclerView.ViewHolder>(itemDiffCallback) {

    companion object {
        val itemDiffCallback = object : DiffUtil.ItemCallback<YearlyListViewType>() {
            override fun areItemsTheSame(
                oldItem: YearlyListViewType,
                newItem: YearlyListViewType
            ): Boolean {
                if (oldItem is YearlyListViewType.YearlyItem && newItem is YearlyListViewType.YearlyItem) {
                    return oldItem.yearlyItemModel.year == newItem.yearlyItemModel.year
                }
                return oldItem == newItem
            }
            override fun areContentsTheSame(
                oldItem: YearlyListViewType,
                newItem: YearlyListViewType
            ): Boolean {
                return oldItem == newItem
            }
        }
        const val TYPE_YEARLY = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            TYPE_YEARLY -> YearlyViewHolder.create(parent)
            else -> YearlyViewHolder.create(parent)
        }

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is YearlyListViewType.YearlyItem -> TYPE_YEARLY
        else -> TYPE_YEARLY
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val yearlyListViewType = getItem(position)
        when (holder) {
            is YearlyViewHolder -> if (yearlyListViewType is YearlyListViewType.YearlyItem) holder.bindView(
                imageClickCallback,
                yearlyListViewType.yearlyItemModel
            )

        }
    }
}