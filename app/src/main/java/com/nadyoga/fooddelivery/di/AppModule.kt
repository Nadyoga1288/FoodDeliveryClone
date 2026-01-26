package com.nadyoga.fooddelivery.di

import com.nadyoga.fooddelivery.data.repository.FoodRepository

object AppModule {
    
    fun provideFoodRepository(): FoodRepository {
        return FoodRepository()
    }
}
