package kr.ac.kumoh.s20260000.spring07httprequest.service

import kr.ac.kumoh.s20260000.spring07httprequest.model.Restaurant
import kr.ac.kumoh.s20260000.spring07httprequest.repository.RestaurantRepository
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val repository: RestaurantRepository
) {
    fun registerRestaurant(restaurant: Restaurant): Restaurant {
        return repository.save(restaurant)
    }

    fun getAllRestaurants(): List<Restaurant> = repository.findAll()

    fun getRestaurant(id: Long): Restaurant? {
        return repository.findById(id)
            ?: throw IllegalArgumentException("맛집을 찾을 수 없습니다.")
    }
}