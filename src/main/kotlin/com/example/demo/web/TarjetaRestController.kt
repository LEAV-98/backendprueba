package com.example.demo.web

import com.example.demo.business.ITarjetaBusiness
import com.example.demo.excepcion.BusinessException
import com.example.demo.excepcion.NotFoundException
import com.example.demo.firebase.FirebaseInitializerJava
import com.example.demo.model.Orden
import com.example.demo.model.Pedido
import com.example.demo.model.Tarjeta
import com.example.demo.utils.Constantes
import com.google.api.core.ApiFuture
import com.google.cloud.firestore.CollectionReference
import com.google.cloud.firestore.DocumentSnapshot
import com.google.cloud.firestore.QuerySnapshot
import com.google.cloud.firestore.WriteResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constantes.URL_BASE_TARJETA)
class TarjetaRestController {
    var firebaseDatabase: FirebaseDatabase? = null
    var databaseReference: DatabaseReference? = null
    @Autowired
    val tarjetaBusiness: ITarjetaBusiness? = null

    @Autowired
    val firebase:FirebaseInitializerJava?=null

    @GetMapping()
    fun list(): ResponseEntity<List<Tarjeta>> {
        return try{


           ResponseEntity(tarjetaBusiness!!.list(), HttpStatus.OK)

        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun load(@PathVariable("id") idTarjeta:Long): ResponseEntity<Any> {
        return try{
            ResponseEntity(tarjetaBusiness!!.load(idTarjeta), HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @PostMapping("")
    fun insert(@RequestBody tarjeta : Tarjeta): ResponseEntity<Any> {
        return try{
            tarjetaBusiness!!.save(tarjeta)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constantes.URL_BASE_TARJETA+"/"+tarjeta.id)
            val tarjetas:CollectionReference=firebase!!.getFirestore().collection("tarjetas")
            val writeResultApiFuture:ApiFuture<WriteResult> = tarjetas.document().create(tarjeta)

            ResponseEntity(responseHeader, HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody tarjeta: Tarjeta): ResponseEntity<Any> {
        return try{
            tarjetaBusiness!!.save(tarjeta)
            ResponseEntity(HttpStatus.CREATED)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") idTarjeta: Long): ResponseEntity<Any> {
        return try{
            tarjetaBusiness!!.remove(idTarjeta)
            ResponseEntity(HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/prueba")
    @ResponseBody
    fun prueba():String{
        return "Esto es una prueba"
    }

    @PostMapping("/pagar")
    fun pagar(@RequestBody orden:Orden):ResponseEntity<Any>{
        return if (tarjetaBusiness!!.find(orden.tarjetaId)){
            orden!!.estado="Pago procesado"
            val ordenes:CollectionReference=firebase!!.getFirestore().collection("ordenes")
            val writeResultApiFuture:ApiFuture<WriteResult> = ordenes.document().create(orden)
            ResponseEntity(orden,HttpStatus.OK)
        }else{
            val pedido:Pedido?=null
            orden!!.estado="Pago rechazado"
            ResponseEntity(orden,HttpStatus.INTERNAL_SERVER_ERROR)

        }

    }
}