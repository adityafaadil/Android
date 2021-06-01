package com.dicoding.capspro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.capspro.data.remote.ApiConfig
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
class MainViewModel : ViewModel() {

    private val accessToken = "pk.eyJ1IjoiYWRpdHlhZmFhZGlsIiwiYSI6ImNrcGFzcXpvYTBzOGcyb3Blc3hqYzYwbDUifQ.1Cm8yBrwZVXM1GF3ArOt9A"
    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    val searchResult = queryChannel.asFlow()
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            ApiConfig.provideApiService().getCountry(it, accessToken).features
        }
        .asLiveData()
}