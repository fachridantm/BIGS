package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.databinding.ItemCardGeositeBinding
import id.belitong.bigs.core.domain.model.Geosite

class CardGeositeAdapter(private val onItemClick: (Geosite) -> Unit) :
    ListAdapter<Geosite, CardGeositeAdapter.GeositeViewHolder>(DIFF_CALLBACK) {

    inner class GeositeViewHolder(private val binding: ItemCardGeositeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Geosite) {
            binding.apply {

                root.setOnClickListener { onItemClick(data) }
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardGeositeAdapter.GeositeViewHolder {
        val binding =
            ItemCardGeositeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GeositeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardGeositeAdapter.GeositeViewHolder, position: Int) {
        val geosite = getItem(position)
        if (geosite != null) {
            holder.bind(geosite)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Geosite>() {
            override fun areItemsTheSame(oldItem: Geosite, newItem: Geosite): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Geosite, newItem: Geosite): Boolean =
                oldItem.id == newItem.id
        }
    }
}