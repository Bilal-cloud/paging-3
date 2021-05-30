package com.example.paging3withmvvm.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.paging3withmvvm.data.Pics
import com.example.paging3withmvvm.databinding.EachRowBinding
import javax.inject.Inject

class PicsAdapter
@Inject
constructor():PagingDataAdapter<Pics,PicsAdapter.PicsViewHolder>(Diff) {



    class PicsViewHolder(private val binding: EachRowBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(pics:Pics){
            binding.apply {
                image.load(pics.download_url)
            }
        }
    }

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
       val pics=getItem(position)
        if(pics!=null){
            holder.bind(pics)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicsViewHolder {
        return PicsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    object Diff:DiffUtil.ItemCallback<Pics>() {
        override fun areItemsTheSame(oldItem: Pics, newItem: Pics): Boolean=
            oldItem.download_url == newItem.download_url


        override fun areContentsTheSame(oldItem: Pics, newItem: Pics): Boolean=
            oldItem == newItem

    }

}