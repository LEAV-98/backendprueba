package com.example.demo.web

import com.example.demo.business.IPersonaBusiness
import com.example.demo.excepcion.BusinessException
import com.example.demo.excepcion.NotFoundException
import com.example.demo.model.Persona
import com.example.demo.utils.Constantes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@RestController
@RequestMapping(Constantes.URL_BASE_PERSONAS)
class PersonaRestController {

    @Autowired
    val personasBusiness: IPersonaBusiness ? = null

    @GetMapping()
    fun list(): ResponseEntity<List<Persona>>{
        return try{
            ResponseEntity(personasBusiness!!.list(), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idPersona:Long): ResponseEntity<Any>{
        return try{
            ResponseEntity(personasBusiness!!.load(idPersona),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("")
    fun insert(@RequestBody persona : Persona): ResponseEntity<Any>{
        return try{
            personasBusiness!!.save(persona)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constantes.URL_BASE_PERSONAS+"/"+persona.id)
            ResponseEntity(responseHeader,HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try{
            personasBusiness!!.save(persona)
            ResponseEntity(HttpStatus.CREATED)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idPersona: Long):ResponseEntity<Any>{
        return try{
            personasBusiness!!.remove(idPersona)
            ResponseEntity(HttpStatus.OK)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/prueba")
    @ResponseBody
    fun prueba():String{
        return "Esto es una prueba"
    }
}