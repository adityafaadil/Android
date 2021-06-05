package com.dicoding.capspro.di

import com.dicoding.capspro.data.Repository
import com.dicoding.capspro.data.remote.ApiService
import com.dicoding.capspro.data.remote.RemoteSource
import com.dicoding.capspro.ui.cluster.ClusterViewModel
import com.dicoding.capspro.ui.forum.ForumViewModel
import com.dicoding.capspro.ui.forum.ThreadDetailsViewModel
import com.dicoding.capspro.ui.report.ReportViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://capstone-b21-cap0156.et.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { RemoteSource(get()) }
    single { Repository(get()) }
}

@InternalCoroutinesApi
val viewmodelModule = module {
    viewModel { ForumViewModel(get()) }
    viewModel { ThreadDetailsViewModel(get()) }
    viewModel { ClusterViewModel(get()) }
    viewModel { ReportViewModel(get()) }
}