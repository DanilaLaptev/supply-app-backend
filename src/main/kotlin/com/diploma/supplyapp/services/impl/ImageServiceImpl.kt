package com.diploma.supplyapp.services.impl

import com.diploma.supplyapp.dto.ImageDto
import com.diploma.supplyapp.services.ImageService
import  org.springframework.core.io.Resource
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.UrlResource
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Path
import java.util.*


@Service
class ImageServiceImpl(
        @Value("\${files.directory}")
        var filesDirectory: String
) : ImageService {
    override fun uploadFile(file: MultipartFile): ImageDto {
        if (filesDirectory.lastIndexOf("/") != filesDirectory.length - 1) {
            filesDirectory += "/"
        }
        val id = UUID.randomUUID().toString()
        val generatedName: String = id + file.originalFilename!!.substring(file.originalFilename!!.lastIndexOf("."))
        try {
            val createdFile = File(filesDirectory + generatedName)
            createdFile.createNewFile()
            file.transferTo(createdFile)
        } catch (e: IOException) {
            throw IllegalArgumentException(e.message, e)
        }
        return ImageDto(generatedName)
    }

    override fun getFile(id: String): Pair<Resource, MediaType> {
        if (filesDirectory.lastIndexOf("/") != filesDirectory.length - 1) {
            filesDirectory += "/"
        }
        val file = File(filesDirectory + id.toString())
        return try {
            val resource = UrlResource(file.toURI().toURL())
            if (resource.exists()) {
                Pair(resource, MediaType.valueOf(Files.probeContentType(Path.of(filesDirectory + id.toString()))))
            } else {
                throw java.lang.IllegalArgumentException("Resource does not exist")
            }
        } catch (e: IOException) {
            throw java.lang.IllegalArgumentException("Input Output error")
        }
    }
}