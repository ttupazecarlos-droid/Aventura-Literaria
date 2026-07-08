package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.UserProgress
import com.example.ui.components.StatusHeader
import com.example.ui.theme.*

@Composable
fun AboutScreen(
    progress: UserProgress,
    onStatsClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Large Header Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(24.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Styled Initials Avatar with deep colorful border
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .background(DuoBlue)
                                .border(4.dp, DuoLightCream, CircleShape)
                                .testTag("creator_avatar"),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "ZT",
                                color = Color.White,
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Black
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "Información del Creador",
                            color = DuoText,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = "Aplicación móvil desarrollada en el marco del programa de prácticas preprofesionales.",
                            color = DuoSubtext,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            textAlign = TextAlign.Center,
                            lineHeight = 18.sp
                        )
                    }
                }

                // Details Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(20.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        // Creator Row
                        AboutItemRow(
                            emoji = "👤",
                            label = "Creador",
                            value = "Zecarlos Tony Davila Ttupa"
                        )

                        HorizontalDivider(color = DuoGrayBorder, thickness = 1.dp)

                        // Student Row
                        AboutItemRow(
                            emoji = "🎓",
                            label = "Alumno de",
                            value = "Instituto Tecnológico Superior La Joya"
                        )

                        HorizontalDivider(color = DuoGrayBorder, thickness = 1.dp)

                        // Role/Activity Row
                        AboutItemRow(
                            emoji = "💼",
                            label = "Actividad",
                            value = "Prácticas Preprofesionales"
                        )

                        HorizontalDivider(color = DuoGrayBorder, thickness = 1.dp)

                        // Location Row
                        AboutItemRow(
                            emoji = "📍",
                            label = "Ubicación",
                            value = "La Joya - Arequipa"
                        )
                    }
                }

                // Academic Badge representation
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(16.dp))
                        .background(DuoLightCream, RoundedCornerShape(16.dp))
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "⭐️", fontSize = 24.sp)
                        Text(
                            text = "¡Orgullosamente formados para el éxito académico!",
                            color = DuoText,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(text = "⭐️", fontSize = 24.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun AboutItemRow(
    emoji: String,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(DuoLightCream, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(text = emoji, fontSize = 20.sp)
        }

        Column {
            Text(
                text = label,
                color = DuoSubtext,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = value,
                color = DuoText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Black
            )
        }
    }
}
