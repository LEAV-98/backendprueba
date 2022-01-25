package com.example.demo.business

import com.example.demo.dao.PersonaRepositorio
import com.example.demo.excepcion.BusinessException
import com.example.demo.excepcion.NotFoundException
import com.example.demo.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PersonaBusiness: IPersonaBusiness {
    @Autowired
    val personaRepositorio: PersonaRepositorio?=null


    override fun list(): List<Persona> {
        try{
            return personaRepositorio!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun load(idPersona: Long): Persona {
        val op:Optional<Persona>
        try{
            op=personaRepositorio!!.findById(idPersona)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona")
        }
        return op.get()
    }

    override fun save(persona: Persona): Persona {
        try{
            return  personaRepositorio!!.save(persona)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun remove(idPersona: Long) {
        val op:Optional<Persona>
        try{
            op=personaRepositorio!!.findById(idPersona)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona")
        }else{
            try{
                personaRepositorio!!.deleteById(idPersona)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

}