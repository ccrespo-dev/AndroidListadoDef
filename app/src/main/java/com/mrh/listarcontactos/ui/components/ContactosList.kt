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
fun MainView(navController: NavController) {

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
                nombre = "Pepe",
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
            Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
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
            ContactosList(
                contactos = listaContactos.filter { contacto ->
                    contacto.nombre.uppercase().contains(inputNombre.uppercase())
                },
                navController = navController
            )
        }

        if (showDialog) {
            AddContactoDialog(
                onDismiss = {
                    showDialog = false
                },
                onConfirm = { contacto ->
                    listaContactos.add(contacto)
                    showDialog = false
                }
            )
        }

    }

}

@Composable
fun ContactosList(
    contactos: List<Contacto>,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 10.dp),
    ) {

        items(contactos) { contacto ->
            ContactoRowCard(contacto = contacto, onClick = {
                navController.navigate(
                    DetalleContactoDestination(
                        nombre = contacto.nombre,
                        apellido = contacto.apellido,
                        telefono = contacto.telefono.toString(),
                        mail = contacto.mail
                    )
                )
            })
            Spacer(modifier = Modifier.padding(top = 10.dp))
        }
    }
}


@Composable
fun AddContactoDialog(onDismiss: () -> Unit, onConfirm: (Contacto) -> Unit) {
    var nombreText by remember { mutableStateOf("") }
    var apellidoText by remember { mutableStateOf("") }
    var mailText by remember { mutableStateOf("") }
    var telefonoText by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Card(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = nombreText,
                    onValueChange = { texto ->
                        nombreText = texto
                    },
                    label = {
                        Text("Nombre")
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = apellidoText,
                    onValueChange = { texto ->
                        apellidoText = texto
                    },
                    label = {
                        Text("Apellido")
                    }
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = mailText,
                    onValueChange = { texto ->
                        mailText = texto
                    },
                    label = {
                        Text("E-Mail")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                OutlinedTextField(
                    value = telefonoText,
                    onValueChange = { texto ->
                        telefonoText = texto
                    },
                    label = {
                        Text("Telefono")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = onDismiss
                    ) {
                        Text("Cancelar")
                    }
                    TextButton(
                        onClick = {
                            if (nombreText.isNotBlank() && apellidoText.isNotBlank() && mailText.isNotBlank() && telefonoText.isNotBlank()) {
                                onConfirm(
                                    Contacto(
                                        nombre = nombreText,
                                        apellido = apellidoText,
                                        mail = mailText,
                                        telefono = BigInteger(telefonoText)
                                    )
                                )
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