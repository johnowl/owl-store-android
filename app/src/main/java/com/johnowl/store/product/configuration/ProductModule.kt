package com.johnowl.store.product.configuration

import com.johnowl.store.product.network.ProductRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class ProductModule {

    @Provides
    @Singleton
    fun provideProductAPI(retrofit: Retrofit): ProductRetrofit {
        return retrofit.create(ProductRetrofit::class.java)
    }
}