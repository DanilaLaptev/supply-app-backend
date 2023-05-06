package com.diploma.supplyapp.controllers

import com.diploma.supplyapp.dto.ImageDto
import com.diploma.supplyapp.services.ImageService
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@RestController
@RequestMapping("/image")
class ImageController (
        val imageService: ImageService
) {
    @PostMapping("/")
    fun upload(@RequestParam("file") file: MultipartFile) : ResponseEntity<ImageDto>{
        return ResponseEntity.ok(imageService.uploadFile(file))
    }

    @GetMapping("/{id}")
    fun getFile(@PathVariable id: String) : ResponseEntity<Resource> {
        val result = imageService.getFile(id)
        return ResponseEntity.ok().contentType(result.second).body(result.first)
    }
}