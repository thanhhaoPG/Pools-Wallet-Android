package com.wallet.pools.presentation.screen.tabMarket.tabLayout

import android.content.res.ColorStateList
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.airo.playground.base.BaseAdapterPaging
import com.bumptech.glide.Glide
import com.wallet.pools.R
import com.wallet.pools.databinding.ItemWatchMaketBinding
import com.wallet.pools.domain.model.Daum
import com.wallet.pools.lib.loadSVG.GlideToVectorYou
import com.wallet.pools.lib.loadSVG.GlideToVectorYouListener
import timber.log.Timber
import javax.inject.Inject


class WatchMarketPagingAdapter @Inject constructor() :
    BaseAdapterPaging<Daum>(diffCallbackTip) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WatchMarketViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemWatchMaketBinding.inflate(inflater, parent, false)
        return WatchMarketViewHolder(binding)
    }


    inner class WatchMarketViewHolder(private val binding: ItemWatchMaketBinding) :
        RecyclerView.ViewHolder(binding.root), Binder<Daum> {
        override fun bind(item: Daum) {
            // Bind your data to the views in the ViewHolder
            binding.apply {
                Glide.with(cIvIcon.context).load(item.icon).into(cIvIcon)
                tvNameWallet.text = item.name
                tvTypeWallet.text = item.symbol
                tvCountPoint.text = item.quote.price.toInt().toString()
                tvCountMoney.text = item.quote.percentChange1h.toString()

                GlideToVectorYou
                    .init()
                    .with(root.context)
                    .withListener(object : GlideToVectorYouListener {
                        override fun onLoadFailed() {
                            Timber.i("HAOHAO  GlideToVectorYouListener onLoadFailed $bindingAdapterPosition")
                        }

                        override fun onResourceReady() {

                            Timber.i("HAOHAO  GlideToVectorYouListener onResourceReady $bindingAdapterPosition")

                            binding.ivChart.post {

                                val paint = Paint()

                                if(item.quote.percentChange7d<0){ //chart red
                                    paint.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(root.context, R.color.color_chart_red), PorterDuff.Mode.SRC_ATOP)
                                    ivChart.setLayerPaint(paint)
                                }else{// chart green
                                    paint.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(root.context, R.color.color_chart_green), PorterDuff.Mode.SRC_ATOP)
                                    ivChart.setLayerPaint(paint)
                                }



                            }

                        }
                    })
                    .load(Uri.parse(item.miniChart), ivChart)

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






}
