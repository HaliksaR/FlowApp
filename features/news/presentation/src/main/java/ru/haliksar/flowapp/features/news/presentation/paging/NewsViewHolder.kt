package ru.haliksar.flowapp.features.news.presentation.paging

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import kotlinx.android.synthetic.main.news_item.view.*
import ru.haliksar.flowapp.features.news.presentation.R
import ru.haliksar.flowapp.features.news.presentation.uidata.NewsUiData

class ProgressHolder<T : Any?>(
    view: ViewGroup,
) : BaseViewHolder<T>(view, R.layout.item_loading) {

    override fun bindContent() {}
    override fun setListeners() {}
}

class ErrorHolder<T : Any?>(
    view: ViewGroup
) : BaseViewHolder<T>(view, R.layout.item_error) {

    override fun bindContent() {}
    override fun setListeners() {}
}

class FullHolder<T : Any?>(
    view: ViewGroup,
) : BaseViewHolder<T>(view, R.layout.item_full) {

    override fun bindContent() {}
    override fun setListeners() {}
}

class NewsViewHolder<T : Any?>(
    private val view: ViewGroup,
    private val clickListener: ((View, NewsUiData?) -> Unit)? = null
) : BaseViewHolder<T>(view, R.layout.news_item) {

    override fun bindContent() {
        val data = this.data as? NewsUiData
        itemView.title.text = data?.title
        itemView.description.text = data?.description
        itemView.name.text = data?.author?.name
        itemView.surname.text = data?.author?.surname
        itemView.postDate.text = data?.postDate.toString()

        data?.author?.profileUrl?.let {
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
        val data = this.data as? NewsUiData
        itemView.avatar.load(data?.author?.avatarUrl) {
            transformations(CircleCropTransformation())
        }
    }

    private fun setPictures() {
        val data = this.data as? NewsUiData
        data?.pictures?.forEach {
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
        val data = this.data as? NewsUiData
        clickListener?.invoke(itemView, data)
    }
}