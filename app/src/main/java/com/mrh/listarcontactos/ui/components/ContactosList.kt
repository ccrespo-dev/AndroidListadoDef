package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mrh.listarcontactos.Contacto
import com.mrh.listarcontactos.R
import java.math.BigInteger


@Composable
fun MainView(modifier: Modifier = Modifier) {

    var inputNombre by remember { mutableStateOf("") }

    val listaContactos = remember {
        mutableStateListOf(
            Contacto(
                nombre = "VINI",
                apellido = "Rios",
                mail = "mario.rios@iepgroup.es",
                telefono = BigInteger("1234567"),
                imagenId = R.drawable.vini
            ),
            Contacto(
                nombre = "VINI",
                apellido = "Rios",
                mail = "mario.rios@iepgroup.es",
                telefono = BigInteger("1234567"),

            )
        )
    }

    Column(
        modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            value = inputNombre,
            onValueChange = { textoTeclado ->
                inputNombre = textoTeclado
            },
            modifier = Modifier.padding(20.dp)
        )

            Button(
                onClick = {
                    var nuevo = Contacto(
                        nombre = inputNombre,
                        apellido = "",
                        mail = "",
                        telefono = BigInteger("00")
                    )
                    listaContactos.add(nuevo)
                    inputNombre = ""
                },
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Text(text = "Crear")
            }

        ContactosList(listaContactos)
    }




}

@Composable
fun ContactosList(contactos: List<Contacto>, modifier: Modifier = Modifier){
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp),
    ) {

        items(contactos){  contacto ->
            ContactoRowCard(contacto=contacto, onClick = {

            })
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}