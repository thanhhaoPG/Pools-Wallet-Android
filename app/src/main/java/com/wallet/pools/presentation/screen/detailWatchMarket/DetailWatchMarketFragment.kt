package com.wallet.pools.presentation.screen.detailWatchMarket
import android.graphics.Color
import android.graphics.ColorSpace.Rgb
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide


import com.tradingview.lightweightcharts.api.chart.models.color.IntColor

import com.tradingview.lightweightcharts.api.interfaces.SeriesApi

import com.tradingview.lightweightcharts.api.options.models.BaselineSeriesOptions

import com.tradingview.lightweightcharts.api.series.models.BaseValuePrice
import com.tradingview.lightweightcharts.api.series.models.BaseValuePriceType
import com.tradingview.lightweightcharts.api.series.models.LineData

import com.tradingview.lightweightcharts.api.series.models.Time
import com.wallet.pools.R

import com.wallet.pools.base.BaseFragment
import com.wallet.pools.base.BaseViewModel
import com.wallet.pools.databinding.FragmentDetailWatchMarketBinding

import com.wallet.pools.domain.model.DataChart
import com.wallet.pools.presentation.screen.main.MainActivity

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import kotlin.random.Random


@AndroidEntryPoint
class DetailWatchMarketFragment : BaseFragment<FragmentDetailWatchMarketBinding, BaseViewModel>() {


    override val viewModel: DetailWatchMarketViewModel by viewModels()
    private val args : DetailWatchMarketFragmentArgs by navArgs()
    private var curSeries: SeriesApi? = null
    override fun getViewBinding(): FragmentDetailWatchMarketBinding =
    FragmentDetailWatchMarketBinding.inflate(layoutInflater)

    override fun onBackFragment() {

        findNavController().navigateUp()

    }

    override fun onStart() {
        (requireActivity()as MainActivity).hideBottomView()
        super.onStart()

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewStateChange()
    }


    private fun initViewStateChange() {
        viewModel.getDataChartState.mapNotNull { it }.onEach(this::onViewStateTipFavoriteChange)
            .launchIn(lifecycleScope)
        args.data.id?.let { viewModel.getDataChart(it) }
    }

    private fun initView() {
        binding.apply {
           Glide.with(requireContext()).load(args.data.icon).into(ivIcon)
            tvNameCoin.text = args.data.name
            tvSymbolCoin.text = args.data.symbol
            tvPrice.text = args.data.quote!!.price.toString()
//            tv1Hours.text = args.data.quote!!.percentChange1h.toString()
//            tv24Hours.text = args.data.quote!!.percentChange24h.toString()
//            tv7days.text = args.data.quote!!.percentChange7d.toString()
//            tv30days.text = args.data.quote!!.percentChange30d.toString()
//            tv60days.text = args.data.quote!!.percentChange60d.toString()
//            tv90days.text = args.data.quote!!.percentChange90d.toString()
//            tvVolumeChange24.text = args.data.quote!!.volumeChange24h!!.toInt().toString()
//            tv24hVol.text = args.data.quote!!.volume24h!!.toInt().toString()
//            tvMarketCap.text = args.data.quote!!.marketCap!!.toInt().toString()
            checkColorText(args.data.quote!!.percentChange1h!!,tv1Hours)
            checkColorText(args.data.quote!!.percentChange24h!!,tv24Hours)
            checkColorText(args.data.quote!!.percentChange7d!!,tv7days)
            checkColorText(args.data.quote!!.percentChange30d!!,tv30days)
            checkColorText(args.data.quote!!.percentChange60d!!,tv60days)
            checkColorText(args.data.quote!!.percentChange90d!!,tv90days)
            checkColorText(args.data.quote!!.volumeChange24h!!,tvVolumeChange24)
            checkColorText(args.data.quote!!.volume24h!!,tv24hVol)
            checkColorText(args.data.quote!!.marketCap!!,tvMarketCap)
            checkColorText(args.data.quote!!.percentChange7d!!,tv1WeekTitle)
             frmBack.setOnClickListener {
                 onBackFragment()
             }
            val textBuy =  "Buy ${args.data.name}"
            tvBuy.text = textBuy
            llBuy.setOnClickListener {
                val bundle = bundleOf(
                    "nameTitle" to "",
                    "linkURL" to "https://www.binance.com/en/trade/${args.data.symbol}_USDT?theme=dark&type=spot")
                findNavController().navigate(R.id.webViewFragment,bundle)
            }
        }
    }
    private fun onViewStateTipFavoriteChange(event: GetDataChartState) {
        when (event) {
            is GetDataChartState.Error -> {
                handleLoading(false)
            }
            is GetDataChartState.Loading -> {
                handleLoading(event.isLoading)
            }
            is GetDataChartState.Success -> {
                handleLoading(false)
                Timber.i("TTT : Success : ${event.data.quotes.size}")
                optionColor(event.data.quotes)
            }
        }
    }

    private fun checkColorText(value : Double , textView: TextView){
        textView.text = value.toString()
        if(value >= 0){
            textView.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_06C270_unchanged))
        }else {
            textView.setTextColor(ContextCompat.getColor(requireContext(),R.color.color_ee5151))
        }
    }
    private fun optionColor(data: List<DataChart>){
        val dataMap = data.map {
            LineData(Time.StringTime(it.time!!),it.value!!.toFloat())
        }
         binding.chartsView.api.addBaselineSeries(
             options = BaselineSeriesOptions(
            baseValue = BaseValuePrice(price = (data[data.count() - 30].value)!!.toInt() , type = BaseValuePriceType.PRICE),
            topFillColor1 = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_top_fill_color_1)),
            topFillColor2 = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_top_fill_color_2)),
            topLineColor = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_top_line_color)),
            bottomFillColor1 = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_bottom_fill_color_1)),
            bottomFillColor2 = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_bottom_fill_color_2)),
            bottomLineColor = IntColor(ContextCompat.getColor(requireContext(), R.color.color_chart_bottom_line_color))
        ),
             onSeriesCreated = { series ->
                 curSeries = series
                 series.setData(dataMap)
             } )
    }



}