package ua.com.anyapps.alicrab.repository

interface BrowseReposotiry {
    fun getStartUrl(): String
    fun saveLastUrl(lastUrl: String)
}