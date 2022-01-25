package com.example.demo.dao

import com.example.demo.model.Tarjeta
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TarjetaRepositorio:JpaRepository<Tarjeta,Long> {
}