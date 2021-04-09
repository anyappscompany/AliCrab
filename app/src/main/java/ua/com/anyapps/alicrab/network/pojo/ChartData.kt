package ua.com.anyapps.alicrab.network.pojo

data class ChartData(
    val errors: List<Any>,
    val product_id: String,
    val product_title: String,
    val total_reviews: String,
    val variations: List<Variation>
)