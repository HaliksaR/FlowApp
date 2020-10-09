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
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class NewsViewHolder(
    private val view: ViewGroup,
    private val clickListener: (View, NewsUiData) -> Unit
) : BaseViewHolder<NewsUiData>(view, R.layout.news_item) {

    override fun bindContent() {
        itemView.title.text = data.title
        itemView.description.text = data.description
        itemView.name.text = data.author.name
        itemView.surname.text = data.author.surname
        itemView.postDate.text = data.postDate.toString()

        val link = SpannableString(data.author.profileUrl.text)
        link.setSpan(
            URLSpan(data.author.profileUrl.link),
            0,
            data.author.profileUrl.text.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        itemView.profileUrl.text = link
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
        clickListener(itemView, data)
    }
}