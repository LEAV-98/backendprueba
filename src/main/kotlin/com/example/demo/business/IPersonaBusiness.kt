package com.example.demo.business

import com.example.demo.model.Persona

interface IPersonaBusiness {
    fun list(): List<Persona>
    fun load(idPersona:Long):Persona
    fun save(persona:Persona):Persona
    fun remove(idPersona: Long)

}