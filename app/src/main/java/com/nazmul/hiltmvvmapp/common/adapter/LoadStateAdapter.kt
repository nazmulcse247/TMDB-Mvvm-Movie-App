package com.nazmul.hiltmvvmapp.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nazmul.hiltmvvmapp.databinding.LoadStateViewBinding
import okio.IOException
import timber.log.Timber

class LoadStateAdapter(private val retry: () -> Unit) : LoadStateAdapter<com.nazmul.hiltmvvmapp.common.adapter.LoadStateAdapter.LoadStateAdapterViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateAdapterViewHolder, loadState: LoadState) {
        holder.bind(loadState, retry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateAdapterViewHolder {
        return LoadStateAdapterViewHolder(
            LoadStateViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    class LoadStateAdapterViewHolder(private val binding: LoadStateViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState, retry: () -> Unit) {
            with(binding) {
                loadStateProgress.isVisible = loadState is LoadState.Loading

                if (loadState is LoadState.Error) {
                   Timber.d("Error: ${loadState.error.localizedMessage}")
                }

            }
        }
    }
}