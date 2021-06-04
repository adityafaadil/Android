package com.dicoding.capspro.data.remote.forum.cluster

data class Cluster(
    val _id: String,
    val region: ArrayList<String>,
    val reportCase: ArrayList<Int>,
    val clusteringCode: ArrayList<Int>
)
