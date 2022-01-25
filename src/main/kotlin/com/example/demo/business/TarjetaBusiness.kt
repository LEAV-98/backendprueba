package com.example.demo.business

import com.example.demo.dao.TarjetaRepositorio
import com.example.demo.excepcion.BusinessException
import com.example.demo.excepcion.NotFoundException
import com.example.demo.model.Tarjeta
import com.sun.org.apache.xpath.internal.operations.Bool
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TarjetaBusiness:ITarjetaBusiness {
    @Autowired
    val tarjetaRepositorio: TarjetaRepositorio?=null
    override fun list(): List<Tarjeta> {
        try{
            return tarjetaRepositorio!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    override fun load(idTarjeta: Long): Tarjeta {
        val op: Optional<Tarjeta>
        try{
            op=tarjetaRepositorio!!.findById(idTarjeta)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la persona")
        }
        return op.get()
    }

    override fun save(tarjeta: Tarjeta): Tarjeta {
        try{
            return  tarjetaRepositorio!!.save(tarjeta)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    override fun remove(idTarjeta: Long) {
        val op:Optional<Tarjeta>
        try{
            op=tarjetaRepositorio!!.findById(idTarjeta)
        }catch(e:Exception){
            throw BusinessException(e.message)
        }
        if(!op.isPresent){
            throw NotFoundException("No se encontró la tarjeta")
        }else{
            try{
                tarjetaRepositorio!!.deleteById(idTarjeta)
            }catch(e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    override fun find(idTarjeta: Long): Boolean {
        val op: Optional<Tarjeta> = tarjetaRepositorio!!.findById(idTarjeta)
        return op.isPresent
    }


}