package com.example.demo.business

import com.example.demo.model.Tarjeta

interface ITarjetaBusiness {
    fun list(): List<Tarjeta>
    fun load(idTarjeta:Long): Tarjeta
    fun save(tarjeta: Tarjeta): Tarjeta
    fun remove(idTarjeta: Long)
    fun find(idTarjeta:Long):Boolean

}