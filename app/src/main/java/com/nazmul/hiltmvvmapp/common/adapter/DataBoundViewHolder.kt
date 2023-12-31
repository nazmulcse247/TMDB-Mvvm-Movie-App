package com.nazmul.hiltmvvmapp.common.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/**
 * A generic ViewHolder that works with a [ViewBinding].
 * @param <T> The type of the ViewDataBinding.
</T> */
class DataBoundViewHolder<out T : ViewBinding>(val binding: T) : RecyclerView.ViewHolder(binding.root)