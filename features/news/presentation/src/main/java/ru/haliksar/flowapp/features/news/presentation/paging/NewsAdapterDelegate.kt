package ru.haliksar.flowapp.features.news.presentation.paging

import android.text.SpannableString
import android.text.Spanned
import android.text.style.URLSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import ru.haliksar.flowapp.features.news.domain.entity.NewsEntity
import ru.haliksar.flowapp.features.news.presentation.databinding.NewsItemBinding
import ru.haliksar.flowapp.libraries.core.presentation.base.BaseViewHolder

class NewsAdapterDelegate(
    private val clickListener: (NewsItemBinding, NewsEntity) -> Unit
) : AdapterDelegate<MutableList<Any>>() {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, clickListener)
    }

    override fun isForViewType(items: MutableList<Any>, position: Int): Boolean = true

    override fun onBindViewHolder(
        items: MutableList<Any>,
        position: Int,
        holder: RecyclerView.ViewHolder,
        payloads: MutableList<Any>
    ) = (holder as NewsViewHolder).bind(items[position] as NewsEntity)

    class NewsViewHolder(
        private val binding: NewsItemBinding,
        private val clickListener: ((NewsItemBinding, NewsEntity) -> Unit)? = null
    ) : BaseViewHolder<NewsEntity>(binding) {

        override fun bindContent() {
            binding.title.text = data.title
            binding.description.text = data.description
            binding.name.text = data.author.name
            binding.surname.text = data.author.surname
            binding.postDate.text = data.postDate.toString()

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
            setPictures()
        }

        private fun setAvatar() {
            binding.avatar.load(data.author.avatarUrl) {
                transformations(CircleCropTransformation())
            }
        }

        private fun setPictures() {
            data.pictures?.forEach {
                binding.pictures.addView(
                    ImageView(binding.pictures.context).apply {
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
            clickListener?.invoke(binding, data)
        }
    }
}

