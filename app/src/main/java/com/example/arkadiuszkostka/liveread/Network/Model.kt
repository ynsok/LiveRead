package com.example.arkadiuszkostka.liveread.Network

import org.simpleframework.xml.ElementList

object Model {

    data class Result(
            @ElementList(name = "articles")
            val articles: List<Articles>?)


      class Articles(val author: String?,
                    val title: String?,
                    val description: String?,
                    val url: String?,
                    val urlToImage: String?,
                    val publishedAt: String?,
                    val source: Source)





    data class Source(val id: String, val name: String)
}