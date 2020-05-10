package com.tomcandev.mobiledatausage.presentation.yearlydatausage.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tomcandev.mobiledatausage.R
import com.tomcandev.mobiledatausage.databinding.MobiledatausageItemYearlyBinding
import com.tomcandev.mobiledatausage.domain.model.QuarterRecordDomainModel
import com.tomcandev.mobiledatausage.presentation.yearlydatausage.model.YearlyItemModel

class YearlyViewHolder(private val binding: MobiledatausageItemYearlyBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindView(
        imageClickCallback: ((YearlyItemModel) -> Unit)?,
        yearlyItemModel: YearlyItemModel
    ) {
        with(binding) {
            tvVolume.text =
                root.context.getString(R.string.text_volume_format, yearlyItemModel.volume)
            tvYear.text = root.context.getString(R.string.text_year_format, yearlyItemModel.year)
            ivDown.setOnClickListener {
                imageClickCallback?.invoke(yearlyItemModel)
            }
            ivDown.visibility = if (yearlyItemModel.isAnyDownVolume) View.VISIBLE else View.GONE
        }
    }

    companion object {
        fun create(parent: ViewGroup): YearlyViewHolder {
            return YearlyViewHolder(
                MobiledatausageItemYearlyBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }
}