package kr.ac.kumoh.s20260000.spring07httprequest.repository

import kr.ac.kumoh.s20260000.spring07httprequest.model.Restaurant

interface RestaurantRepository {
    fun save(restaurant: Restaurant): Restaurant
    fun findAll(): List<Restaurant>
    fun findById(id: Long): Restaurant?
}