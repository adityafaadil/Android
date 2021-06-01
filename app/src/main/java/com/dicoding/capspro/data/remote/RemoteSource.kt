package com.dicoding.capspro.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capspro.data.remote.forum.Thread
import com.dicoding.capspro.data.remote.forum.ThreadList
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteSource(private val apiService: ApiService) {
    fun getThread(): LiveData<ArrayList<Thread>> {
        val thread = MutableLiveData<ArrayList<Thread>>()
        val client = apiService.getThread()
        client.enqueue(object : Callback<ThreadList> {
            override fun onResponse(call: Call<ThreadList>, response: Response<ThreadList>) {
                val obj = response.body() as ThreadList
                thread.postValue(obj.data)
            }

            override fun onFailure(call: Call<ThreadList>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }
        })
        return thread
    }
}