package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.ProductDto
import com.diploma.supplyapp.services.ProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/product")
class ProductController(
        val productService: ProductService
) {

    @GetMapping("/")
    fun getAllProducts() : ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(productService.getAllProducts())
    }

    @PostMapping("/")
    fun createProduct(@RequestBody productDto: ProductDto) : ResponseEntity<ProductDto> {
        return ResponseEntity.ok(productService.createProduct(productDto))
    }

}