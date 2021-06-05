package com.dicoding.capspro.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.capspro.data.remote.forum.cluster.Cluster
import com.dicoding.capspro.data.remote.forum.cluster.ClusterResponse
import com.dicoding.capspro.data.remote.forum.comment.Comment
import com.dicoding.capspro.data.remote.forum.comment.CommentList
import com.dicoding.capspro.data.remote.forum.comment.CommentResponse
import com.dicoding.capspro.data.remote.forum.thread.Thread
import com.dicoding.capspro.data.remote.forum.thread.ThreadList
import com.dicoding.capspro.data.remote.forum.thread.ThreadResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteSource(private val apiService: ApiService) {
    fun getThread(): LiveData<ArrayList<ThreadList>> {
        val thread = MutableLiveData<ArrayList<ThreadList>>()
        val client = apiService.getThread()
        client.enqueue(object : Callback<ThreadResponse> {
            override fun onResponse(
                call: Call<ThreadResponse>,
                response: Response<ThreadResponse>
            ) {
                val obj = response.body() as ThreadResponse
                thread.postValue(obj.data)
            }

            override fun onFailure(call: Call<ThreadResponse>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }
        })
        return thread
    }

    fun upvoteThread(threadId: String) {
        val client = apiService.upvoteThread(threadId)
        client.enqueue(object : Callback<Thread> {
            override fun onResponse(call: Call<Thread>, response: Response<Thread>) {

            }

            override fun onFailure(call: Call<Thread>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }

        })
    }

    fun addComment(threadId: String, email: String, comment: String) {
        val client = apiService.addComment(threadId, email, comment)
        client.enqueue(object : Callback<Comment> {
            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {

            }

            override fun onFailure(call: Call<Comment>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }

        })
    }

    fun getComment(threadId: String): LiveData<ArrayList<CommentList>> {
        val comment = MutableLiveData<ArrayList<CommentList>>()
        val client = apiService.getComment(threadId)
        client.enqueue(object : Callback<CommentResponse> {
            override fun onResponse(
                call: Call<CommentResponse>,
                response: Response<CommentResponse>
            ) {
                val obj = response.body() as CommentResponse
                comment.postValue(obj.data)
            }

            override fun onFailure(call: Call<CommentResponse>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }
        })
        return comment
    }
    fun getClusterData(): LiveData<Cluster> {
        val clusterData = MutableLiveData<Cluster>()
        val client = apiService.getClusterData()
        client.enqueue(object:Callback<ClusterResponse>{
            override fun onResponse(
                call: Call<ClusterResponse>,
                response: Response<ClusterResponse>
            ) {
                val obj = response.body() as ClusterResponse
                clusterData.postValue(obj.data)
            }

            override fun onFailure(call: Call<ClusterResponse>, t: Throwable) {
                Log.e("API_FAILURE", t.toString())
            }

        })
        return clusterData
    }
}