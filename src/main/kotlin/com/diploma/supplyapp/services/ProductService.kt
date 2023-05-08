package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.ProductDto

interface ProductService {
    fun getAllProducts(): List<ProductDto>

    fun createProduct(productDto: ProductDto): ProductDto
}