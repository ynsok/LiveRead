package com.example.arkadiuszkostka.liveread.Db

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index

@Entity(tableName = "news", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class BusinessEntry(
        mAuthor: String? = null,
        mTitle: String? = null,
        mDescription: String? = null,
        mUrl: String? = null,
        mUrlToImage: String? = null,
        mPublishedAt: String? = null,
        mIdOfSource: String? = null,
        mNameOfSource: String? = null,
         mId: Int = 0) : NewsBase(author = mAuthor, title = mTitle
        , description = mDescription, url = mUrl, urlToImage = mUrlToImage, publishedAt = mPublishedAt
        , idOfSource = mIdOfSource, nameOfSource = mNameOfSource,id = mId)

@Entity(tableName = "entertainment", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class EntertainmentEntry(mAuthor: String? = null,
                         mTitle: String? = null,
                         mDescription: String? = null,
                         mUrl: String? = null,
                         mUrlToImage: String? = null,
                         mPublishedAt: String? = null,
                         mIdOfSource: String? = null,
                         mNameOfSource: String? = null,
                         id:Int =0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,
                id = id)

@Entity(tableName = "health", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class HealthEntry(mAuthor: String? = null,
                  mTitle: String? = null,
                  mDescription: String? = null,
                  mUrl: String? = null,
                  mUrlToImage: String? = null,
                  mPublishedAt: String? = null,
                  mIdOfSource: String? = null,
                  mNameOfSource: String? = null,
                  id: Int=0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,id = id)

@Entity(tableName = "science", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class ScienceEntry(mAuthor: String? = null,
                   mTitle: String? = null,
                   mDescription: String? = null,
                   mUrl: String? = null,
                   mUrlToImage: String? = null,
                   mPublishedAt: String? = null,
                   mIdOfSource: String? = null,
                   mNameOfSource: String? = null,
                   id: Int=0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,
                id = id)

@Entity(tableName = "sports", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class SportsEntry(mAuthor: String? = null,
                  mTitle: String? = null,
                  mDescription: String? = null,
                  mUrl: String? = null,
                  mUrlToImage: String? = null,
                  mPublishedAt: String? = null,
                  mIdOfSource: String? = null,
                  mNameOfSource: String? = null,
                  id: Int=0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,
                id = id)

@Entity(tableName = "technology", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class TechnologyEntry(mAuthor: String? = null,
                      mTitle: String? = null,
                      mDescription: String? = null,
                      mUrl: String? = null,
                      mUrlToImage: String? = null,
                      mPublishedAt: String? = null,
                      mIdOfSource: String? = null,
                      mNameOfSource: String? = null,
                      id: Int =0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,id = id)

@Entity(tableName = "keyword", indices = [(Index(value = arrayOf("publishedAt"), unique = true))])
class KeywordEntry(mAuthor: String? = null,
                      mTitle: String? = null,
                      mDescription: String? = null,
                      mUrl: String? = null,
                      mUrlToImage: String? = null,
                      mPublishedAt: String? = null,
                      mIdOfSource: String? = null,
                      mNameOfSource: String? = null,
                      id: Int =0) :
        NewsBase(author = mAuthor,
                title = mTitle, description = mDescription,
                url = mUrl,
                urlToImage = mUrlToImage,
                publishedAt = mPublishedAt,
                idOfSource = mIdOfSource,
                nameOfSource = mNameOfSource,id = id)





