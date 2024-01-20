package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.R
import id.belitong.bigs.core.databinding.ItemCardTabHistoryBinding
import id.belitong.bigs.core.domain.model.HistoryListItem
import id.belitong.bigs.core.domain.model.HistoryListItem.OrderItem
import id.belitong.bigs.core.domain.model.HistoryListItem.ReportItem
import id.belitong.bigs.core.domain.model.Order
import id.belitong.bigs.core.domain.model.Report
import id.belitong.bigs.core.utils.HistoryStatus
import id.belitong.bigs.core.utils.loadGeoparkImage
import id.belitong.bigs.core.utils.statusBackgroundFilter

class HistoryTabAdapter(
    private val reportCancelClicked: (Report) -> Unit,
    private val orderCancelClicked: (Order) -> Unit
) :
    ListAdapter<HistoryListItem, HistoryTabAdapter.HistoryItemViewHolder>(DIFF_CALLBACK) {

    inner class HistoryItemViewHolder(private val binding: ItemCardTabHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HistoryListItem) {
            when (item) {
                is OrderItem -> bindOrder(item.order)
                is ReportItem -> bindReport(item.report)
            }
        }

        private fun bindOrder(data: Order) {
            binding.apply {
                itemTvTitleGeositeOrder.text = data.geositeName
                itemTvProgram.text = data.program
                itemTvInstance.text = data.instance
                itemTvPhoneNumber.text = data.phoneNumber
                itemTvTourDate.text = data.tourDate
                itemTvTourGuideName.text = data.tourGuideName
                itemTvBookingDate.text = data.bookingDate
                itemTvBookingTime.text = data.bookingTime
                itemIvOrderGeosite.loadGeoparkImage(data.geositeImage)

                itemTvStatus.statusBackgroundFilter(data.status)
                if (data.status == HistoryStatus.COMPLETED.value || data.status == HistoryStatus.CANCELLED.value) {
                    btnCancel.visibility = ViewGroup.GONE
                } else {
                    btnCancel.visibility = ViewGroup.VISIBLE
                    btnCancel.setOnClickListener { orderCancelClicked(data) }
                }
            }
        }

        private fun bindReport(data: Report) {
            binding.apply {
                textView5.visibility = View.GONE
                itemTvBookingDate.visibility = View.GONE
                itemTvBookingTime.visibility = View.GONE
                itemTvTourGuideName.visibility = View.GONE
                itemTvTitleGeositeOrder.visibility = View.GONE

                textView1.text = itemView.context.getString(R.string.category)
                itemTvProgram.text = data.category

                textView2.text = itemView.context.getString(R.string.name)
                itemTvInstance.text = data.name

                textView3.text = itemView.context.getString(R.string.short_desc)
                itemTvPhoneNumber.text = data.shortDesc

                textView4.text = itemView.context.getString(R.string.place)
                itemTvTourDate.text = data.place

                itemIvOrderGeosite.loadGeoparkImage(data.photo)

                itemTvStatus.statusBackgroundFilter(data.status)
                if (data.status == HistoryStatus.COMPLETED.value || data.status == HistoryStatus.CANCELLED.value) {
                    btnCancel.visibility = ViewGroup.GONE
                } else {
                    btnCancel.visibility = ViewGroup.VISIBLE
                    btnCancel.setOnClickListener { reportCancelClicked(data) }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryItemViewHolder {
        val binding =
            ItemCardTabHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK by lazy {
            object : DiffUtil.ItemCallback<HistoryListItem>() {
                override fun areItemsTheSame(
                    oldItem: HistoryListItem,
                    newItem: HistoryListItem
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(
                    oldItem: HistoryListItem,
                    newItem: HistoryListItem
                ): Boolean {
                    return when {
                        oldItem is OrderItem && newItem is OrderItem -> oldItem.order.id == newItem.order.id
                        oldItem is ReportItem && newItem is ReportItem -> oldItem.report.id == newItem.report.id
                        else -> false
                    }
                }
            }
        }
    }
}