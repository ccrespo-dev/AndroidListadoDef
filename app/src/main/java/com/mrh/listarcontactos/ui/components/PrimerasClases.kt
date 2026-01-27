package com.mrh.listarcontactos.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mrh.listarcontactos.Contacto
import com.mrh.listarcontactos.R
import java.math.BigInteger


@Composable
fun HomeView(modifier: Modifier = Modifier) {

    val listaNombres = remember {
        mutableStateListOf(
            "Pepe",
            "Almudena",
            "Alberto",
            "Pepe",
            "Almudena",
            "Alberto"
        )
    }
    val listaContactos = remember {
        mutableStateListOf(
            Contacto(
                nombre = "VINI",
                apellido = "Rios",
                mail = "mario.rios@iepgroup.es",
                telefono = BigInteger("1234567"),
                imagenId = R.drawable.vini
            )
        )
    }
    var inputNombre by remember { mutableStateOf("") }

    /*
    Column(
        modifier = modifier
    )
    {
        Text("nombre")
        //FORMAS DE ITERAR UNA LISTA
        /*
        for(nombre in listaNombres){
            Text(text = nombre)
        }
        */
        listaNombres.forEach { nombre ->
            Text(text = nombre)
        }
    }*/

    LazyVerticalGrid(
        modifier = modifier.padding(20.dp),
        columns = GridCells.Adaptive(minSize = 100.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Pintar un elemento particular
        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            TextField(
                value = inputNombre,
                onValueChange = { textoTeclado ->
                    inputNombre = textoTeclado
                },
                modifier = Modifier.padding(20.dp)
            )
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            Button(
                onClick = {
                    var nuevo = Contacto(
                        nombre = inputNombre,
                        apellido = "",
                        mail = "",
                        telefono = BigInteger("00")
                    )
                    listaContactos.add(nuevo)
                    listaNombres.add(inputNombre)
                    inputNombre = ""
                },
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Text("Hola")
            }
        }

        item(
            span = {
                GridItemSpan(maxLineSpan)
            }
        ) {
            AsyncImage(
                model = "https://media.cnn.com/api/v1/images/stellar/prod/champions-league-gettyimages-2217980503.jpg",
                contentDescription = null,
                modifier = Modifier.size(100.dp, 200.dp).clip(CircleShape),
                contentScale = ContentScale.FillBounds
            )
        }

        items(items = listaContactos) { contacto ->

            Card(

                onClick = {
                    Log.d("DEMO", "Aqui me encuentro")
                    listaContactos.remove(contacto)
                }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
//                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (contacto.imagenId != null) {
                        Image(
                            modifier = Modifier.size(100.dp).clip(CircleShape),
                            painter = painterResource(contacto.imagenId?:0),
                            contentDescription = null,
                            contentScale = ContentScale.Crop

                        )
                    }

                    Text(
                        text = contacto.nombre,
                        color = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}