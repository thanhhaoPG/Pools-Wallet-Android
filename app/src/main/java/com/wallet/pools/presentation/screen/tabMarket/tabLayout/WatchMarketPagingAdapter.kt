package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.R
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.disk.DiskCache
import coil.memory.MemoryCache
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.ViewSizeResolver
import com.airo.playground.base.BaseAdapterPaging
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.StreamEncoder
import com.wallet.pools.databinding.ItemWatchMarketBinding
import com.wallet.pools.domain.model.Daum
import java.io.InputStream
import javax.inject.Inject


class WatchMarketPagingAdapter @Inject constructor() :
    BaseAdapterPaging<Daum>(diffCallbackTip) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchMarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWatchMarketBinding.inflate(inflater, parent, false)
        return WatchMarketViewHolder(binding)
    }


    inner class WatchMarketViewHolder(private val binding: ItemWatchMarketBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Daum> {
        override fun bind(item: Daum) {
            // Bind your data to the views in the ViewHolder
            binding.apply {
                Glide.with(cIvIcon.context).load(item.icon).into(cIvIcon)
                tvNameWallet.text = item.name
                tvTypeWallet.text = item.symbol
                tvCountPoint.text = item.quote!!.price!!.toInt().toString()
                tvCountMoney.text =  item.quote.percentChange1h.toString()
//                ivChart.loadUrl(item.miniChart)
            }
            itemView.setOnClickListener {
                onItemClickListener?.let {
                    it(item)
                }
            }


        }
    }


    companion object {
        val diffCallbackTip = object : DiffUtil.ItemCallback<Daum>() {
            override fun areItemsTheSame(oldItem: Daum, newItem: Daum): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Daum, newItem: Daum): Boolean {
                return oldItem == newItem
            }
        }
    }
    fun ImageView.loadUrl(url: String) {

        val imageLoader = ImageLoader.Builder(this.context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()

        val request = ImageRequest.Builder(this.context)
            //.crossfade(true)
            //.crossfade(500)
            .size(ViewSizeResolver(this))

            .memoryCachePolicy(CachePolicy.ENABLED)

            .data(url)
            .target(this)

            .build()

        imageLoader.enqueue(request)



    }


    // Function to apply the color filter
    private fun applyColorFilterToImageView(imageView: ImageView) {
        // Apply your desired color filter here
        val redColorFilter = PorterDuffColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP)
        imageView.colorFilter = redColorFilter
    }


}
