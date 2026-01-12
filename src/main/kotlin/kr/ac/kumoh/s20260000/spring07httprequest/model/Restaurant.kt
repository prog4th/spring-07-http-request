package kr.ac.kumoh.s20260000.spring07httprequest.model

data class Restaurant(
    var id: Long? = null,
    val name: String,
    val category: String,
    val address: String,
    val score: Double = 0.0
)
