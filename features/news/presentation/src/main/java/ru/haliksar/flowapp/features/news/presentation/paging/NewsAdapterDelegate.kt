package ru.haliksar.flowapp.features.news.presentation.paging

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import kotlinx.android.synthetic.main.news_item.view.*
import ru.haliksar.flowapp.features.news.presentation.R
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class NewsAdapterDelegate(
    private val clickListener: (View, NewsUiData) -> Unit
) : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        NewsViewHolder(parent, clickListener)

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as NewsViewHolder).bind(items[position] as NewsUiData)

    private inner class NewsViewHolder(
        private val view: ViewGroup,
        private val clickListener: ((View, NewsUiData) -> Unit)? = null
    ) : BaseViewHolder<NewsUiData>(view, R.layout.news_item) {

        override fun bindContent() {
            itemView.title.text = data.title
            itemView.description.text = data.description
            itemView.name.text = data.author.name
            itemView.surname.text = data.author.surname
            itemView.postDate.text = data.postDate.toString()

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
            setPictures()
        }

        private fun setAvatar() {
            itemView.avatar.load(data.author.avatarUrl) {
                transformations(CircleCropTransformation())
            }
        }

        private fun setPictures() {
            data.pictures?.forEach {
                itemView.pictures.addView(
                    ImageView(view.context).apply {
                        scaleType = ImageView.ScaleType.FIT_CENTER
                        adjustViewBounds = true
                        load(it.link) {
                            scale(Scale.FILL)
                        }
                    }
                )
            }
        }

        override fun setListeners() {
            clickListener?.invoke(itemView, data)
        }
    }
}

