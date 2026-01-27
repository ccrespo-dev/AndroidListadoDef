package com.mrh.listarcontactos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrh.listarcontactos.Contacto


@Composable
fun ContactoRowCard(contacto: Contacto, onClick : () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().height(60.dp),
        onClick = onClick
    ){
        Row(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ){
            if(contacto.imagenId != null){
                Image(
                    painter = painterResource(contacto.imagenId!!),
                    contentDescription = null,
                    modifier = Modifier.padding(start = 20.dp).size(50.dp, 50.dp).clip(CircleShape),
                    contentScale = ContentScale.FillBounds
                )
            }else{
                Card(
                    modifier = Modifier.padding(start = 20.dp).size(50.dp, 50.dp).clip(CircleShape),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.tertiary,
                        contentColor = MaterialTheme.colorScheme.onTertiary
                    )
                ){
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = contacto.nombre[0].toString(),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 24.sp
                        )
                    }

                }
            }
            Text(
                text = contacto.nombre,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 20.dp)
            )
        }
    }
}