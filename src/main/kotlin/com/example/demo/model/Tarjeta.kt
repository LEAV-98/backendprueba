package com.example.demo.model

import java.time.LocalDate
import java.util.*
import javax.persistence.*

@Entity
@Table(name="tarjeta")
data class Tarjeta(val numero:String ="",val cvv:String="",val nombre:String="", val fechaVec:String="", val correo:String="") {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0



}