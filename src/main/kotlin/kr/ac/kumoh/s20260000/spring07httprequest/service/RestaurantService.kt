package kr.ac.kumoh.s20260000.spring07httprequest.service

import kr.ac.kumoh.s20260000.spring07httprequest.model.Restaurant
import kr.ac.kumoh.s20260000.spring07httprequest.repository.RestaurantRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class RestaurantService(
    private val repository: RestaurantRepository
) {
    companion object {
        private val log = LoggerFactory.getLogger(RestaurantService::class.java)
    }

    fun registerRestaurant(restaurant: Restaurant): Restaurant {
        // {}는 placeholder(anchor 또는 치환 문자라고 부르기도 함)임
        log.info("RestaurantService: 맛집 등록 - {}", restaurant.name)
        return repository.save(restaurant)
    }

    fun getAllRestaurants(): List<Restaurant> {
        log.info("RestaurantService: 전체 맛집 조회")
        return repository.findAll()
    }

    fun getRestaurant(id: Long): Restaurant? {
        return repository.findById(id)
            ?: run {
                // DB 연결 에러 등은 error() 사용
                // User의 잘못된 request로 예외 발생할 때는 warn() 사용
                log.warn("맛집 조회 실패 - 존재하지 않는 ID: {}", id)
                throw IllegalArgumentException("맛집을 찾을 수 없습니다.")
            }
    }
}