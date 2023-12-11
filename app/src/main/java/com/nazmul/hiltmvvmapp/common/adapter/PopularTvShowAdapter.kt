package com.nazmul.hiltmvvmapp.common.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.nazmul.hiltmvvmapp.common.AppConstraits.IMAGE_BASE_URL
import com.nazmul.hiltmvvmapp.common.utils.ImageTypeEnum
import com.nazmul.hiltmvvmapp.common.utils.loadImage
import com.nazmul.hiltmvvmapp.data.Result
import com.nazmul.hiltmvvmapp.databinding.PopularTvShowItemBinding
import timber.log.Timber

class PopularTvShowAdapter(
    private val onClick : (View.OnClickListener)
) : DataBoundListAdapter<Result,PopularTvShowItemBinding>(
    diffCallback = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id
        }
    }
){
    override fun createBinding(parent: ViewGroup): PopularTvShowItemBinding = PopularTvShowItemBinding.inflate(
        LayoutInflater.from(parent.context),parent,false)

    override fun bind(binding: PopularTvShowItemBinding, item: Result, position: Int) {
        binding.root.setOnClickListener(onClick)
        Timber.d("bind: %s", IMAGE_BASE_URL+item.poster_path)
        binding.imageView.loadImage(IMAGE_BASE_URL+item.poster_path,ImageTypeEnum.REGULAR)
    }
}