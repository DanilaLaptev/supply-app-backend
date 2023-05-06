package com.diploma.supplyapp.repositories

import com.diploma.supplyapp.entities.ContactPerson
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContactPersonRepository : JpaRepository<ContactPerson, Long>