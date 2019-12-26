package com.example.helloworld

data class SearchResult (
    val Items: List<Contents>,
    val page: Int,
    val pageCount: Int
)

data class Contents (
    val Item: BookResult
)

data class BookResult (
    val title: String,
    // val isbn: Int,
    val author: String,
    val publisher: String,
    val smallImageUrl: String,
    val mediumImageUrl: String,
    val itemPrice: Int,
    val itemUrl: String,
    val affiliateUrl: String,
    val itemCaption: String
)
