package kr.ac.kumoh.s20260000.spring07httprequest.repository

import kr.ac.kumoh.s20260000.spring07httprequest.model.Restaurant
import org.springframework.stereotype.Repository

@Repository
class InMemoryRestaurantRepository : RestaurantRepository {
    private val restaurants = mutableMapOf<Long, Restaurant>()
    private var currentId = 1L

    init {
        val data = listOf(
            Restaurant(name = "징기스", category = "아시안", address = "경상북도 구미시", score = 5.0),
            Restaurant(name = "옥계 양꼬치", category = "중식", address = "경상북도 구미시", score = 5.0),
            Restaurant(name = "새벽시장", category = "일식", address = "경상북도 구미시", score = 4.9),
            Restaurant(name = "구미가당김", category = "양식", address = "경상북도 구미시", score = 4.9),
            Restaurant(name = "미진곰탕", category = "한식", address = "서울시 종로구", score = 4.5),
            Restaurant(name = "스시쿠라", category = "일식", address = "서울시 강남구", score = 4.8),
            Restaurant(name = "파스타노모", category = "양식", address = "서울시 마포구", score = 4.2),
            Restaurant(name = "북경반점", category = "중식", address = "서울시 송파구", score = 3.9),
            Restaurant(name = "카페 아우라", category = "카페", address = "서울시 용산구", score = 4.7),
            Restaurant(name = "돈까스 하우스", category = "일식", address = "서울시 서초구", score = 4.0),
            Restaurant(name = "김밥천국", category = "분식", address = "서울시 성동구", score = 3.5),
            Restaurant(name = "인도커리나라", category = "아시안", address = "서울시 동대문구", score = 4.3),
            Restaurant(name = "버거킹덤", category = "패스트푸드", address = "서울시 관악구", score = 4.1),
            Restaurant(name = "멕시칸 타코", category = "멕시칸", address = "서울시 서대문구", score = 4.6)
        )

        data.forEach { save(it) }
    }

    override fun save(restaurant: Restaurant): Restaurant {
        val id = restaurant.id ?: currentId++
        val newRestaurant = restaurant.copy(id = id)
        restaurants[id] = newRestaurant
        return newRestaurant
    }

    override fun findAll(): List<Restaurant> = restaurants.values.toList()

    override fun findById(id: Long): Restaurant? = restaurants[id]
}