package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.ProductDto
import com.diploma.supplyapp.entities.Product
import com.diploma.supplyapp.repositories.ProductRepository
import com.diploma.supplyapp.services.ProductService
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
        val productRepository: ProductRepository
) : ProductService{
    override fun getAllProducts(): List<ProductDto> {
        return productRepository.findAll().map {ProductDto.fromEntity (it)}
    }

    override fun createProduct(productDto: ProductDto) : ProductDto {
        val product = Product(
                productDto.productType,
                productDto.name,
                productDto.approved,
                ArrayList(),
                productDto.image
        )
        val createdProduct = productRepository.save(product)
        return ProductDto.fromEntity(createdProduct)
    }
}