package com.example.demo.utils

class Constantes {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION="/v1"
        private const val URL_BASE = URL_API_BASE+ URL_API_VERSION
        //api endopint para personas
        const val URL_BASE_PERSONAS="$URL_BASE/personas"
        const val URL_BASE_TARJETA="$URL_BASE/tarjeta"
    }
}