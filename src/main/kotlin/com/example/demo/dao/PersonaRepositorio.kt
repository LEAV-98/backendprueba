package com.example.demo.dao

import com.example.demo.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonaRepositorio: JpaRepository<Persona,Long> {

}