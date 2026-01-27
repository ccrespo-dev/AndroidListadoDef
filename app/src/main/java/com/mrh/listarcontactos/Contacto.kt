package com.mrh.listarcontactos

import java.math.BigInteger

data class Contacto(
    var nombre: String,
    var apellido: String,
    var mail: String,
    var telefono: BigInteger,
    var imagenId: Int? = null
)
