package com.example.demo.model

import javax.persistence.*


class Pedido(var estado:String="Sin procesar", var orden: Orden?=null) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id:Long = 0

}