package ru.haliksar.flowapp.features.quotes.presentation.paging3

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.CircleCropTransformation
import ru.haliksar.flowapp.features.quotes.domain.entity.QuotesEntity
import ru.haliksar.flowapp.features.quotes.presentation.databinding.QuotesItemBinding
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class QuotesAdapter(
    private val clickListener: (QuotesItemBinding, QuotesEntity) -> Unit
) : PagingDataAdapter<QuotesEntity, QuotesAdapter.QuotesViewHolder>(
    object : DiffUtil.ItemCallback<QuotesEntity>() {
        override fun areItemsTheSame(oldItem: QuotesEntity, newItem: QuotesEntity): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: QuotesEntity, newItem: QuotesEntity): Boolean =
            oldItem == newItem
    }
) {

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder {
        val binding = QuotesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuotesViewHolder(binding, clickListener)
    }

    class QuotesViewHolder(
        private val binding: QuotesItemBinding,
        private val clickListener: ((QuotesItemBinding, QuotesEntity) -> Unit)? = null
    ) : BaseViewHolder<QuotesEntity>(binding) {

        override fun bindContent() {
            binding.name.text = data.author.name
            binding.surname.text = data.author.surname
            binding.quote.text = data.quote

            data.author.profileUrl.let {
                val link = SpannableString(it.text)
                link.setSpan(
                    URLSpan(it.link),
                    0,
                    it.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                binding.profileUrl.text = link
            }
            setAvatar()
        }

        private fun setAvatar() {
            binding.avatar.load(data.author.avatarUrl) {
                transformations(CircleCropTransformation())
            }
        }

        override fun setListeners() {
            clickListener?.invoke(binding, data)
        }
    }
}

