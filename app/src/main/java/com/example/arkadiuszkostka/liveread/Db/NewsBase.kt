package com.example.arkadiuszkostka.liveread.Db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
abstract class NewsBase(var author: String?,
                        var title: String?,
                        var description: String?,
                        var url: String?,
                        var urlToImage: String?,
                        var publishedAt: String?,
                        var idOfSource: String?,
                        var nameOfSource: String?,
                        @PrimaryKey(autoGenerate = true)
                        var id: Int)