package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.UserProgress
import com.example.ui.components.StatusHeader
import com.example.ui.theme.*

@Composable
fun AddisonScreen(
    progress: UserProgress,
    onStatsClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    val mapsUrl = "https://www.google.com/maps/place/IEP+Addison/@-16.3621038,-71.5139153,836m/data=!3m2!1e3!4b1!4m6!3m5!1s0x91424bcbe8c907b5:0x573313eb62071529!8m2!3d-16.3621038!4d-71.5139153!16s%2Fg%2F11glxrr1ph?entry=ttu&g_ep=EgoyMDI2MDcwNS4wIKXMDSoASAFQAw%3D%3D"
    val fbAcademiaUrl = "https://www.facebook.com/AcademiaAddisonOficial/?locale=es_LA"
    val phoneAcademia = "+51981477771"
    val emailAcademia = "academiaaddisonoficial@gmail.com"
    
    val fbIepUrl = "https://www.facebook.com/p/Institucion-Educativa-Addison-100057260718288/"
    val phoneIep = "+51923619190"
    val emailIep = "colegio.addison.1@gmail.com"

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DuoLightCream)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // StatusHeader with Menu button
            StatusHeader(
                streak = progress.currentStreak,
                stars = progress.totalStars,
                unlockedBadgesCount = progress.unlockedBadgeIds.size,
                completedLevelsCount = progress.completedLevelIds.size,
                completedExamsCount = progress.completedUnitExamIds.size,
                onStatsClick = onStatsClick,
                onMenuClick = onMenuClick
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // School Logo / Insignia Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ie_adisson_villa_asuncion_insignia_1783480880572),
                            contentDescription = "Insignia I.E.P Addison",
                            modifier = Modifier
                                .size(160.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .testTag("addison_logo"),
                            contentScale = ContentScale.Fit
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        Text(
                            text = "Institución Educativa Addison",
                            color = DuoText,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = "Addison es una institución educativa al servicio de la sociedad y la cultura arequipeña.\n\nOfrecemos una educación integral, donde el estudiante pueda desarrollar sus potenciales con miras al ingreso a la universidad.",
                            color = DuoSubtext,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            lineHeight = 20.sp
                        )
                    }
                }

                // Interactive Address Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { uriHandler.openUri(mapsUrl) }
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(16.dp))
                        .testTag("maps_address_card"),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(DuoOrange.copy(alpha = 0.15f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Ubicación",
                                tint = DuoOrange,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Dirección",
                                color = DuoText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Miguel Grau, Arequipa 04003",
                                color = DuoBlue,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "Toca para abrir en Google Maps 🗺️",
                                color = DuoSubtext,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                // Contact Section: Academia
                ContactSectionCard(
                    title = "Academia Addison",
                    fbUrl = fbAcademiaUrl,
                    phone = phoneAcademia,
                    email = emailAcademia,
                    accentColor = DuoGreen,
                    onFbClick = { uriHandler.openUri(fbAcademiaUrl) },
                    onPhoneClick = { uriHandler.openUri("tel:$phoneAcademia") },
                    onEmailClick = { uriHandler.openUri("mailto:$emailAcademia") }
                )

                // Contact Section: IEP Colegio
                ContactSectionCard(
                    title = "I.E.P. Addison",
                    fbUrl = fbIepUrl,
                    phone = phoneIep,
                    email = emailIep,
                    accentColor = DuoBlue,
                    onFbClick = { uriHandler.openUri(fbIepUrl) },
                    onPhoneClick = { uriHandler.openUri("tel:$phoneIep") },
                    onEmailClick = { uriHandler.openUri("mailto:$emailIep") }
                )

                // Footer
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Arequipa - 2026",
                    color = DuoSubtext,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

@Composable
fun ContactSectionCard(
    title: String,
    fbUrl: String,
    phone: String,
    email: String,
    accentColor: Color,
    onFbClick: () -> Unit,
    onPhoneClick: () -> Unit,
    onEmailClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = title,
                color = accentColor,
                fontSize = 16.sp,
                fontWeight = FontWeight.Black
            )
            
            HorizontalDivider(color = DuoGrayBorder, thickness = 1.dp)

            // Facebook Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onFbClick)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(DuoBlue.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "f", color = DuoBlue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Column {
                    Text(text = "Facebook", color = DuoText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = "Toca para visitar la página oficial", color = DuoSubtext, fontSize = 11.sp)
                }
            }

            // Phone Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onPhoneClick)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(DuoGreen.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Llamar",
                        tint = DuoGreen,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Column {
                    Text(text = "Teléfono / WhatsApp", color = DuoText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = phone, color = DuoText, fontSize = 13.sp, fontWeight = FontWeight.Black)
                }
            }

            // Email Row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .clickable(onClick = onEmailClick)
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(DuoOrange.copy(alpha = 0.1f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Correo",
                        tint = DuoOrange,
                        modifier = Modifier.size(16.dp)
                    )
                }
                Column {
                    Text(text = "Correo Electrónico", color = DuoText, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text(text = email, color = DuoText, fontSize = 13.sp, fontWeight = FontWeight.Medium)
                }
            }
        }
    }
}
