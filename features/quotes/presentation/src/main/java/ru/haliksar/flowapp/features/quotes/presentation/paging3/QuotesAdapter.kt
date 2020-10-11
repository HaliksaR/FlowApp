package ru.haliksar.flowapp.features.quotes.presentation.paging3

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import coil.load
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.quotes_item.view.*
import ru.haliksar.flowapp.features.quotes.presentation.R
import ru.haliksar.flowapp.features.quotes.presentation.uidata.QuotesUiData
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class QuotesAdapter(
    private val clickListener: (View, QuotesUiData) -> Unit
) : PagingDataAdapter<QuotesUiData, QuotesAdapter.QuotesViewHolder>(
    object : DiffUtil.ItemCallback<QuotesUiData>() {
        override fun areItemsTheSame(oldItem: QuotesUiData, newItem: QuotesUiData): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: QuotesUiData, newItem: QuotesUiData): Boolean =
            oldItem == newItem
    }
) {

    inner class QuotesViewHolder(
        private val view: ViewGroup,
        private val clickListener: ((View, QuotesUiData) -> Unit)? = null
    ) : BaseViewHolder<QuotesUiData>(view, R.layout.quotes_item) {

        override fun bindContent() {
            itemView.name.text = data.author.name
            itemView.surname.text = data.author.surname
            itemView.quote.text = data.quote

            data.author.profileUrl.let {
                val link = SpannableString(it.text)
                link.setSpan(
                    URLSpan(it.link),
                    0,
                    it.text.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                itemView.profileUrl.text = link
            }
            setAvatar()
        }

        private fun setAvatar() {
            itemView.avatar.load(data.author.avatarUrl) {
                transformations(CircleCropTransformation())
            }
        }

        override fun setListeners() {
            clickListener?.invoke(itemView, data)
        }
    }

    override fun onBindViewHolder(holder: QuotesViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuotesViewHolder =
        QuotesViewHolder(parent, clickListener)
}

