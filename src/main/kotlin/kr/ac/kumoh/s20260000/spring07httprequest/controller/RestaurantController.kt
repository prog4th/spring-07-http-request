package kr.ac.kumoh.s20260000.spring07httprequest.controller

import kr.ac.kumoh.s20260000.spring07httprequest.model.Restaurant
import kr.ac.kumoh.s20260000.spring07httprequest.service.RestaurantService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/restaurants")
class RestaurantController(
    private val service: RestaurantService
) {
    companion object {
        private val log = LoggerFactory.getLogger(RestaurantController::class.java)
    }

    @GetMapping
    fun list(
        @RequestParam(required = false) category: String?,
    ): ResponseEntity<List<Restaurant>> {
        log.info("맛집 목록 조회 요청 - category: $category")

        val  allRestaurants = service.getAllRestaurants()
        val filteredList = if (category != null) {
            allRestaurants.filter { it.category == category }
        } else {
            allRestaurants
        }

        log.info("조회된 맛집 수: ${filteredList.size}")

        return ResponseEntity.ok(filteredList)
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long): ResponseEntity<Restaurant> {
        log.info("맛집 상세 조회 - ID: {}", id)

        val restaurant = service.getRestaurant(id)

        log.info("맛집 상세 조회 성공 - 이름: {}", restaurant?.name)

        return ResponseEntity.ok(restaurant)
    }
}