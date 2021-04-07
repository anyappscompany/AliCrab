package ua.com.anyapps.alicrab.utils

import android.util.Log
import java.net.URL

class Utils {
    companion object {
        // Если это страница с товаром
        fun isValidGoodPage(url: String, html: String): Boolean{
            if(Regex("(http|https):\\/\\/m\\.(|[a-z]+\\.)aliexpress(.*?)\\/item\\/[0-9]+\\.html").containsMatchIn(url.toString()) && html.contains("ERR_INTERNET_DISCONNECTED", true)== false && html.contains("ERR_NAME_NOT_RESOLVED", true)== false) {
                return true
            }
            return false
        }
    }
}