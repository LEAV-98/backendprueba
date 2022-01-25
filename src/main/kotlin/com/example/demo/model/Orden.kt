package com.example.demo.model

import javax.persistence.*


class Orden(val precioTotal:Float= 0.0F, val cantProductos:Int=0, val tarjetaId:Long, var estado:String?="", val productos:List<ItemOrden>) {


}
