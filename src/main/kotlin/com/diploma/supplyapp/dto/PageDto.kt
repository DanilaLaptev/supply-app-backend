package com.diploma.supplyapp.dto

data class PageDto<I> (
        var items: List<I>,
        var total: Long
)