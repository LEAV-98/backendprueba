package com.example.demo

import com.example.demo.dao.PersonaRepositorio
import com.example.demo.dao.TarjetaRepositorio
import com.example.demo.model.Persona
import com.example.demo.model.Tarjeta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SpringBootApplication
class DemoApplication:CommandLineRunner{
	@Autowired
	val personaRepositorio:PersonaRepositorio?=null
	@Autowired
	val tarjetaRepositorio:TarjetaRepositorio?=null
	override fun run(vararg args: String?) {
		/*val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
		val persona1 = Persona(12343,"Luis","Avila", LocalDate.parse("10-10-1998",formatter))
		personaRepositorio!!.save(persona1)*/
		val tarjeta = Tarjeta("4444333322221111", "123","Luis Avila","06-24", "leav@leav.leav")
		tarjetaRepositorio!!.save(tarjeta)
	}
}

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
