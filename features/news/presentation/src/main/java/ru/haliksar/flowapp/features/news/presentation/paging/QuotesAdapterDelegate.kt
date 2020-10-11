package ru.haliksar.flowapp.features.news.presentation.paging

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.quotes_item.view.*
import ru.haliksar.flowapp.features.news.presentation.R
import ru.haliksar.flowapp.features.news.presentation.uidata.QuotesUiData
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class QuotesAdapterDelegate(
    private val clickListener: (View, QuotesUiData) -> Unit
) : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        QuotesViewHolder(parent, clickListener)

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as QuotesViewHolder).bind(items[position] as QuotesUiData)

    private inner class QuotesViewHolder(
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
}

