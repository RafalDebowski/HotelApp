package com.example.pinapp.ui.pins

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pinapp.databinding.ItemPinBinding
import com.example.pinapp.model.domain.PinDomain

class PinAdapter : RecyclerView.Adapter<PinAdapter.ViewHolder>() {

    var listItem = mutableListOf<PinDomain>()

    fun setNewData(newList: List<PinDomain>) {
        val diffCallback = PinDiffCallback(
            oldElements = listItem,
            newElements = newList
        )
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        listItem = newList.map { it }.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPinBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            listItem[position]
        )
    }

    class ViewHolder(
        private val binding: ItemPinBinding
    ) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(
            item: PinDomain
        ) {
            binding.code.text = item.code.toString()
            binding.name.text = item.name
        }

    }

    class PinDiffCallback(
        private val oldElements: List<PinDomain>,
        private val newElements: List<PinDomain>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldElements.size

        override fun getNewListSize(): Int = newElements.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldElements[oldItemPosition].id == newElements[newItemPosition].id &&
                    oldElements[oldItemPosition].name == newElements[newItemPosition].name &&
                    oldElements[oldItemPosition].code == newElements[newItemPosition].code

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldElements[oldItemPosition].name == newElements[newItemPosition].name &&
                    oldElements[oldItemPosition].code == newElements[newItemPosition].code
    }

}