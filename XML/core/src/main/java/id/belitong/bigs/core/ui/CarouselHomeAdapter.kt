package id.belitong.bigs.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.belitong.bigs.core.databinding.ItemCarouselHomeBinding
import id.belitong.bigs.core.domain.model.Geosite
import id.belitong.bigs.core.utils.loadGeoparkImage

class CarouselHomeAdapter(private val onItemClick: (Geosite) -> Unit) :
    ListAdapter<Geosite, CarouselHomeAdapter.CarouselViewHolder>(DIFF_CALLBACK) {

    inner class CarouselViewHolder(private val binding: ItemCarouselHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: Geosite) {
            binding.apply {
                itemTvGeositeHome.text = data.name
                itemImgGeositeHome.loadGeoparkImage(data.img)
                root.setOnClickListener { onItemClick(data) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding =
            ItemCarouselHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        val shuffledList = currentList.shuffled()
        val geosite = shuffledList[position]
        if (geosite != null) {
            holder.bind(geosite)
        }
    }

    override fun getItemCount(): Int = if (currentList.size > 5) 5 else currentList.size

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Geosite>() {
            override fun areItemsTheSame(oldItem: Geosite, newItem: Geosite): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: Geosite, newItem: Geosite): Boolean =
                oldItem.id == newItem.id
        }
    }
}