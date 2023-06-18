package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.databinding.ItemCardHomeBinding
import id.belitong.bigs.core.domain.model.Biodiversity
import id.belitong.bigs.core.utils.getFirstTwoWords
import id.belitong.bigs.core.utils.loadGeositeImage

class CardHomeAdapter(private val onItemClick: (Biodiversity) -> Unit) :
    ListAdapter<Biodiversity, CardHomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    inner class HomeViewHolder(private val binding: ItemCardHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Biodiversity) {
            binding.apply {
                itemTvTitleHome.text = data.name.getFirstTwoWords()
                itemTvSubtitleHome.text = data.type
                itemImgGeositeHome.loadGeositeImage(data.img)
                root.setOnClickListener { onItemClick(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemCardHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val biodiversity = getItem(position)
        if (biodiversity != null) {
            holder.bind(biodiversity)
        }
    }

    override fun getItemCount(): Int {
        return currentList.size
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