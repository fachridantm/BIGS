package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.databinding.ItemCardSearchBinding
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.utils.loadGeoparkImage

class CardSearchAdapter(private val onItemClick: (Biodiversity) -> Unit) :
    ListAdapter<Biodiversity, CardSearchAdapter.SearchViewHolder>(DIFF_CALLBACK) {

    inner class SearchViewHolder(private val binding: ItemCardSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Biodiversity) {
            binding.apply {
                itemTvSearchTitle.text = data.name
                itemTvSearchSubtitle.text = data.location
                itemIvSearch.loadGeoparkImage(data.img)
                root.setOnClickListener { onItemClick(data) }
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val binding =
            ItemCardSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val biodiversity = getItem(position)
        if (biodiversity != null) {
            holder.bind(biodiversity)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Biodiversity>() {
            override fun areItemsTheSame(oldItem: Biodiversity, newItem: Biodiversity): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Biodiversity, newItem: Biodiversity): Boolean =
                oldItem.id == newItem.id
        }
    }
}