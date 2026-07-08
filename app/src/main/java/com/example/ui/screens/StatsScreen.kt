package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LiteratureDatabase
import com.example.model.UserProgress
import com.example.ui.components.DuoButton
import com.example.ui.theme.*

@Composable
fun StatsScreen(
    progress: UserProgress,
    onBackClick: () -> Unit,
    onResetProgressClick: () -> Unit,
    onMenuClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var showResetDialog by remember { mutableStateOf(false) }
    val levels = LiteratureDatabase.levels

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = {
                Text(
                    text = "¿Quieres reiniciar todo? 🗑️",
                    fontWeight = FontWeight.Black,
                    color = DuoText,
                    fontSize = 18.sp
                )
            },
            text = {
                Text(
                    text = "Perderás tus estrellas, insignias y niveles completados. ¿Estás seguro de que quieres empezar desde cero?",
                    fontSize = 14.sp,
                    color = DuoSubtext
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showResetDialog = false
                        onResetProgressClick()
                    }
                ) {
                    Text(text = "SÍ, EMPEZAR DE NUEVO", color = DuoPink, fontWeight = FontWeight.Black)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text(text = "CANCELAR", color = DuoBlue, fontWeight = FontWeight.Bold)
                }
            }
        )
    }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(DuoLightCream),
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .statusBarsPadding()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    if (onMenuClick != null) {
                        IconButton(
                            onClick = onMenuClick,
                            modifier = Modifier
                                .size(40.dp)
                                .background(DuoLightCream, shape = RoundedCornerShape(12.dp))
                                .border(1.5.dp, DuoGrayBorder, shape = RoundedCornerShape(12.dp))
                                .testTag("stats_menu_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = DuoText
                            )
                        }
                    } else {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier
                                .size(40.dp)
                                .background(DuoLightCream, shape = RoundedCornerShape(12.dp))
                                .border(1.5.dp, DuoGrayBorder, shape = RoundedCornerShape(12.dp))
                                .testTag("stats_back_arrow")
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Atrás",
                                tint = DuoText
                            )
                        }
                    }

                    Text(
                        text = "🏆 Mi Álbum de Logros",
                        color = DuoText,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        fontFamily = FontFamily.SansSerif
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DuoLightCream)
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Stats summary card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 2.dp, shape = RoundedCornerShape(20.dp)),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(20.dp),
                border = BorderStroke(2.dp, DuoGrayBorder)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "RESUMEN DE MI AVENTURA",
                        color = DuoBlue,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.5.sp
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        // Racha (Streak)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "🔥", fontSize = 28.sp)
                            Text(
                                text = "${progress.currentStreak} días",
                                color = DuoOrange,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(text = "Racha", color = DuoSubtext, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        // Estrellas (Stars)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "⭐", fontSize = 28.sp)
                            Text(
                                text = "${progress.totalStars}",
                                color = DuoYellow,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(text = "Estrellas", color = DuoSubtext, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        // Niveles (Completed Levels)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "📚", fontSize = 28.sp)
                            Text(
                                text = "${progress.completedLevelIds.size}/28",
                                color = DuoGreenDark,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(text = "Niveles", color = DuoSubtext, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }

                        // Exámenes (Completed Exams)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "📝", fontSize = 28.sp)
                            Text(
                                text = "${progress.completedUnitExamIds.size}/7",
                                color = DuoBlue,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(text = "Exámenes", color = DuoSubtext, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            // Badges title
            Text(
                text = "MIS INSIGNIAS GANADAS",
                color = DuoText,
                fontSize = 14.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 1.sp,
                modifier = Modifier.align(Alignment.Start)
            )

            // Badge collection grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                items(levels) { level ->
                    val badge = level.badge
                    val isUnlocked = progress.unlockedBadgeIds.contains(badge.id)

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(elevation = if (isUnlocked) 2.dp else 0.dp, shape = RoundedCornerShape(16.dp)),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isUnlocked) Color.White else DuoGrayLight.copy(alpha = 0.4f)
                        ),
                        shape = RoundedCornerShape(16.dp),
                        border = BorderStroke(
                            width = 2.dp,
                            color = if (isUnlocked) DuoGrayBorder else DuoGrayBorder.copy(alpha = 0.5f)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            // Badge icon
                            Box(
                                modifier = Modifier
                                    .size(64.dp)
                                    .background(
                                        color = if (isUnlocked) Color(android.graphics.Color.parseColor(badge.colorHex)).copy(alpha = 0.15f) else DuoGrayLight,
                                        shape = CircleShape
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                if (isUnlocked) {
                                    Text(text = badge.iconEmoji, fontSize = 36.sp)
                                } else {
                                    Text(text = "🔒", fontSize = 24.sp)
                                }
                            }

                            // Badge name
                            Text(
                                text = badge.name,
                                color = if (isUnlocked) DuoText else DuoSubtext,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Center,
                                lineHeight = 15.sp,
                                modifier = Modifier.fillMaxWidth()
                            )

                            // Unlocked Stamp
                            if (isUnlocked) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
                                    modifier = Modifier
                                        .background(DuoGreen.copy(alpha = 0.1f), shape = RoundedCornerShape(6.dp))
                                        .padding(horizontal = 6.dp, vertical = 2.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = "Ganada",
                                        tint = DuoGreen,
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = "GANADA",
                                        color = DuoGreen,
                                        fontSize = 9.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                }
                            } else {
                                Text(
                                    text = "BLOQUEADO",
                                    color = DuoSubtext,
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Return & Reset buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Clear/Reset progress (Trash icon or text)
                IconButton(
                    onClick = { showResetDialog = true },
                    modifier = Modifier
                        .size(56.dp)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .border(2.dp, DuoPink, shape = RoundedCornerShape(16.dp))
                        .testTag("reset_progress_button")
                ) {
                    Text(text = "🗑️", fontSize = 24.sp)
                }

                // Return button
                DuoButton(
                    onClick = onBackClick,
                    modifier = Modifier.weight(1f),
                    backgroundColor = DuoBlue,
                    shadowColor = DuoBlueDark,
                    testTag = "stats_back_button"
                ) {
                    Text(
                        text = "¡VOLVER AL CAMINO! 🗺️",
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}
