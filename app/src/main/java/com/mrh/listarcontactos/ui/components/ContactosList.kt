package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.mrh.listarcontactos.Contacto
import com.mrh.listarcontactos.R
import org.intellij.lang.annotations.JdkConstants
import java.math.BigInteger


@Composable
fun MainView(navController: NavController, modifier : Modifier) {

    var inputNombre by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

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

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier
                .fillMaxWidth()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = inputNombre,
                onValueChange = { textoTeclado ->
                    inputNombre = textoTeclado
                },
                label = {
                    Text("Buscar...")
                },
                modifier = Modifier.padding(20.dp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = null
                    )

                }
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

            ContactosList(listaContactos.filter{contacto ->
                contacto.nombre.uppercase().contains(inputNombre.uppercase())
            },
                navController = navController)
        }
        if(showDialog)
        {
            AddContactoDialog(
                onDismis = {
                    showDialog = false
                }
            ) { contacto ->
                listaContactos.add(contacto);
                showDialog = false
            }
        }
    }
}

@Composable
fun ContactosList(contactos: List<Contacto>, modifier: Modifier = Modifier, navController : NavController){
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp),
    ) {

        items(contactos){  contacto ->
            ContactoRowCard(contacto=contacto, onClick = {
                navController.navigate(DetalleContactoDestination(
                    nombre = contacto.nombre,
                    apellido = contacto.apellido,
                    telefono = contacto.telefono.toString(),
                    mail = contacto.mail
                ))
            })
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}

@Composable
fun AddContactoDialog(onDismis:() -> Unit, onConfirm: (Contacto) -> Unit){
    var nombreText by remember { mutableStateOf(value = "") }
    var apellidoText by remember { mutableStateOf(value = "") }
    var mailText by remember { mutableStateOf(value = "") }
    var telefonoText by remember { mutableStateOf(value = "") }

    Dialog(
        onDismissRequest = onDismis
    ){
        Card(
            modifier = Modifier.padding(16.dp)
        ){
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nombreText,
                    onValueChange = {
                        texto ->
                        nombreText = texto
                    },
                    label = {
                        Text(text = "Nombre")
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = apellidoText,
                    onValueChange = {
                            texto ->
                        apellidoText = texto
                    },
                    label = {
                        Text(text = "Apellido")
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = mailText,
                    onValueChange = {
                            texto ->
                        mailText = texto
                    },
                    label = {
                        Text(text = "E-Mail")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = telefonoText,
                    onValueChange = {
                            texto ->
                        telefonoText = texto
                    },
                    label = {
                        Text(text = "Telefono")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ){
                    TextButton(
                        onClick = onDismis
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            if(nombreText.isNullOrBlank() && apellidoText.isNullOrBlank() && mailText.isNullOrBlank() && telefonoText.isNullOrBlank()){
                                onConfirm(Contacto(
                                    nombre = nombreText,
                                    apellido = apellidoText,
                                    mail = mailText,
                                    telefono = BigInteger(telefonoText)
                                ))
                            }
                        }
                    ) {
                        Text("Crear")
                    }
                }
            }
        }
    }
}