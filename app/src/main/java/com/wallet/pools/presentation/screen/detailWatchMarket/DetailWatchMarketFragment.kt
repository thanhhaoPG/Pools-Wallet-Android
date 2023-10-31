package com.wallet.pools.presentation.screen.detailWatchMarket
import android.annotation.SuppressLint
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
import com.wallet.pools.extenstion.formatDoubleWithCommas
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
        initViewStateChange()
        initView()
    }


    private fun initViewStateChange() {
        viewModel.getDataChartState.mapNotNull { it }.onEach(this::onViewStateTipFavoriteChange)
            .launchIn(lifecycleScope)
        args.data.id.let { viewModel.getDataChart(it) }
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.apply {
            val dataArgs=args.data
           Glide.with(requireContext()).load(dataArgs.icon).into(ivIcon)
            tvNameCoin.text = dataArgs.name
            tvSymbolCoin.text = dataArgs.symbol
            tvPrice.text =formatDoubleWithCommas(dataArgs.quote.price)

            checkColorText(dataArgs.quote.percentChange7d,tv1WeekTitle)
            checkColorText(dataArgs.quote.percentChange1h,tv1Hours)
            checkColorText(dataArgs.quote.percentChange24h,tv24Hours)
            checkColorText(dataArgs.quote.percentChange7d,tv7days)
            checkColorText(dataArgs.quote.percentChange30d,tv30days)
            checkColorText(dataArgs.quote.percentChange60d,tv60days)
            checkColorText(dataArgs.quote.percentChange90d,tv90days)

            tvVolumeChange24.text="${formatDoubleWithCommas(dataArgs.quote.volumeChange24h)}$"
            tv24hVol.text="${formatDoubleWithCommas(dataArgs.quote.volume24h)}$"
            tvMarketCap.text="${formatDoubleWithCommas(dataArgs.quote.marketCap)}$"



             frmBack.setOnClickListener {
                 onBackFragment()
             }
            val textBuy =  "Buy ${dataArgs.symbol}"
            tvBuy.text = textBuy
            llBuy.setOnClickListener {
                val bundle = bundleOf(
                    "nameTitle" to "",
                    "linkURL" to "https://www.binance.com/en/trade/${dataArgs.symbol}_USDT?theme=dark&type=spot")
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

    @SuppressLint("SetTextI18n")
    private fun checkColorText(value : Double, textView: TextView){
        textView.text = "$value%"
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