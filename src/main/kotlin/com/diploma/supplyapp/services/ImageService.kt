package com.diploma.supplyapp.services

import com.diploma.supplyapp.dto.ImageDto
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

interface ImageService {

    fun uploadFile(file: MultipartFile): ImageDto

    fun getFile(id: String): Pair<Resource, MediaType>
}