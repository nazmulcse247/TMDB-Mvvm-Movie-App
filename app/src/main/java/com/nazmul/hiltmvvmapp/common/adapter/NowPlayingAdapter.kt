package com.nazmul.hiltmvvmapp.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nazmul.hiltmvvmapp.common.AppConstraits
import com.nazmul.hiltmvvmapp.common.utils.ImageTypeEnum
import com.nazmul.hiltmvvmapp.common.utils.format
import com.nazmul.hiltmvvmapp.common.utils.loadImage
import com.nazmul.hiltmvvmapp.data.Result
import com.nazmul.hiltmvvmapp.databinding.PopularTvShowItemBinding
import com.nazmul.hiltmvvmapp.ui.base.BasePagingAdapter

class NowPlayingAdapter(
    private val onClickMovie: ((movieId: Int) -> Unit)?
) : BasePagingAdapter<Result>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {

    class MovieViewHolder(
        private val binding: PopularTvShowItemBinding,
        private val onClickMovie: ((movieId: Int) -> Unit)?
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Result) = binding.apply {

            imageView.loadImage(AppConstraits.IMAGE_BASE_URL +item.poster_path,ImageTypeEnum.REGULAR)
            voteAverageTV.text = item.vote_average.format(1)

            root.setOnClickListener {
                onClickMovie?.invoke(item.id)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder =
        MovieViewHolder(
            PopularTvShowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), onClickMovie
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MovieViewHolder -> {
                getItem(position)?.let { movie -> holder.bind(movie) }
            }
        }
    }


}