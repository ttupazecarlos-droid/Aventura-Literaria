package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LiteratureDatabase
import com.example.model.Level
import com.example.model.LiteratureUnit
import com.example.model.UserProgress
import com.example.ui.components.DuoButton
import com.example.ui.components.MascotBubble
import com.example.ui.components.StatusHeader
import com.example.ui.theme.*

@Composable
fun DashboardScreen(
    progress: UserProgress,
    onLevelClick: (Level) -> Unit,
    onUnitExamClick: (LiteratureUnit) -> Unit,
    onStatsClick: () -> Unit,
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val units = LiteratureDatabase.units
    val levels = LiteratureDatabase.levels

    // Next uncompleted level in the entire database
    val nextLevelToComplete = levels.firstOrNull { !progress.completedLevelIds.contains(it.id) } ?: levels.last()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DuoLightCream)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            // Header Stats Bar
            StatusHeader(
                streak = progress.currentStreak,
                stars = progress.totalStars,
                unlockedBadgesCount = progress.unlockedBadgeIds.size,
                completedLevelsCount = progress.completedLevelIds.size,
                completedExamsCount = progress.completedUnitExamIds.size,
                onStatsClick = onStatsClick,
                onMenuClick = onMenuClick
            )

            // Scrolling Tree Map
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(top = 16.dp, bottom = 24.dp, start = 16.dp, end = 16.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Intro banner from mascot owl
                item {
                    MascotBubble(
                        text = "¡Hola, aventurero! 🦉 Soy Mito. Completa los temas de cada unidad y aprueba su examen final de 7 preguntas para desbloquear el siguiente reto.",
                        bubbleColor = Color.White
                    )
                }

                units.forEach { unit ->
                    // 1. Render Unit Header
                    item {
                        UnitHeader(unit = unit, progress = progress)
                    }

                    // 2. Render Levels inside this Unit
                    items(unit.levels) { level ->
                        val isUnlocked = progress.isLevelUnlocked(level.id)
                        val isCompleted = progress.completedLevelIds.contains(level.id)
                        val isTarget = level.id == nextLevelToComplete.id

                        // Calculate alternating alignment
                        val alignment = when (level.id % 4) {
                            1 -> Alignment.CenterStart
                            3 -> Alignment.CenterEnd
                            else -> Alignment.Center
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 24.dp),
                            contentAlignment = alignment
                        ) {
                            LevelNode(
                                level = level,
                                isUnlocked = isUnlocked,
                                isCompleted = isCompleted,
                                isTarget = isTarget,
                                onClick = {
                                    if (isUnlocked) {
                                        onLevelClick(level)
                                    }
                                }
                            )
                        }
                    }

                    // 3. Render Unit Exam Node!
                    item {
                        val isExamUnlocked = progress.isUnitExamUnlocked(unit.id)
                        val isExamCompleted = progress.completedUnitExamIds.contains(unit.id)
                        val isExamTarget = isExamUnlocked && !isExamCompleted

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 12.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            UnitExamNode(
                                unit = unit,
                                isUnlocked = isExamUnlocked,
                                isCompleted = isExamCompleted,
                                isTarget = isExamTarget,
                                onClick = {
                                    if (isExamUnlocked) {
                                        onUnitExamClick(unit)
                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun UnitHeader(
    unit: LiteratureUnit,
    progress: UserProgress,
    modifier: Modifier = Modifier
) {
    val unitColor = Color(android.graphics.Color.parseColor(unit.colorHex))
    
    val completedCount = unit.levels.count { progress.completedLevelIds.contains(it.id) }
    val totalCount = unit.levels.size
    val isUnitFullyCompleted = completedCount == totalCount && progress.completedUnitExamIds.contains(unit.id)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = unitColor),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, Color.White.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "UNIDAD ${unit.id}",
                    color = Color.White.copy(alpha = 0.9f),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp
                )
                
                Box(
                    modifier = Modifier
                        .background(
                            if (isUnitFullyCompleted) DuoGreen else Color.White.copy(alpha = 0.25f),
                            RoundedCornerShape(12.dp)
                        )
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (isUnitFullyCompleted) "¡COMPLETO! 👑" else "$completedCount/$totalCount TEMAS",
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(6.dp))
            
            Text(
                text = unit.title.substringAfter(":").trim(),
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                lineHeight = 22.sp
            )
            
            Spacer(modifier = Modifier.height(4.dp))
            
            Text(
                text = unit.description,
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                lineHeight = 16.sp
            )
        }
    }
}

@Composable
fun LevelNode(
    level: Level,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isTarget: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nodeColor = if (isUnlocked) Color(android.graphics.Color.parseColor(level.colorHex)) else DuoGrayLight
    val nodeShadowColor = if (isUnlocked) Color(android.graphics.Color.parseColor(level.shadowColorHex)) else DuoGrayBorder

    val infiniteTransition = rememberInfiniteTransition(label = "target_breathe")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_pulse"
    )

    val currentScale = if (isTarget && isUnlocked) scale else 1.0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (isTarget && isUnlocked) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(DuoGreen, shape = RoundedCornerShape(10.dp))
                    .border(1.5.dp, DuoGreenDark, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "¡JUEGA AQUÍ! 👇",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .size(86.dp * currentScale)
                .clickable(enabled = isUnlocked, onClick = onClick)
                .shadow(
                    elevation = if (isUnlocked) 4.dp else 1.dp,
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(nodeShadowColor, shape = CircleShape)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = if (isUnlocked) 6.dp else 0.dp)
                    .background(nodeColor, shape = CircleShape)
                    .border(2.dp, Color.White.copy(alpha = 0.25f), shape = CircleShape)
                    .testTag("level_node_${level.id}"),
                contentAlignment = Alignment.Center
            ) {
                if (isUnlocked) {
                    Text(
                        text = level.badge.iconEmoji,
                        fontSize = 38.sp,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Bloqueado",
                        tint = DuoSubtext,
                        modifier = Modifier.size(28.dp)
                    )
                }

                if (isCompleted) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .background(DuoYellow, shape = CircleShape)
                            .border(1.5.dp, DuoYellowDark, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Completado",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .background(
                    color = if (isUnlocked) Color.White else DuoGrayLight.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = if (isUnlocked) DuoGrayBorder else DuoGrayBorder.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .widthIn(max = 140.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "TEMA ${level.id}",
                    color = if (isUnlocked) DuoBlue else DuoSubtext,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = level.subtitle,
                    color = if (isUnlocked) DuoText else DuoSubtext,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    lineHeight = 14.sp,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun UnitExamNode(
    unit: LiteratureUnit,
    isUnlocked: Boolean,
    isCompleted: Boolean,
    isTarget: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val nodeColor = if (isUnlocked) Color(0xFF8E44AD) else DuoGrayLight
    val nodeShadowColor = if (isUnlocked) Color(0xFF70298F) else DuoGrayBorder

    val infiniteTransition = rememberInfiniteTransition(label = "exam_breathe")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "exam_scale"
    )

    val currentScale = if (isTarget && isUnlocked) scale else 1.0f

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        if (isTarget && isUnlocked) {
            Box(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .background(DuoPurpleDark, shape = RoundedCornerShape(10.dp))
                    .border(1.5.dp, Color.White, shape = RoundedCornerShape(10.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = "¡EXAMEN DE UNIDAD! 📝",
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .size(92.dp * currentScale)
                .clickable(enabled = isUnlocked, onClick = onClick)
                .shadow(
                    elevation = if (isUnlocked) 6.dp else 1.dp,
                    shape = CircleShape
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(nodeShadowColor, shape = CircleShape)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = if (isUnlocked) 6.dp else 0.dp)
                    .background(nodeColor, shape = CircleShape)
                    .border(2.dp, Color.White.copy(alpha = 0.3f), shape = CircleShape)
                    .testTag("unit_exam_node_${unit.id}"),
                contentAlignment = Alignment.Center
            ) {
                if (isUnlocked) {
                    Text(
                        text = if (isCompleted) "🏆" else "📝",
                        fontSize = 42.sp,
                        textAlign = TextAlign.Center
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Bloqueado",
                        tint = DuoSubtext,
                        modifier = Modifier.size(28.dp)
                    )
                }

                if (isCompleted) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .align(Alignment.TopEnd)
                            .background(DuoYellow, shape = CircleShape)
                            .border(1.5.dp, DuoYellowDark, shape = CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Completado",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .background(
                    color = if (isUnlocked) Color.White else DuoGrayLight.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = if (isUnlocked) DuoGrayBorder else DuoGrayBorder.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 10.dp, vertical = 4.dp)
                .widthIn(max = 140.dp)
        ) {
            Text(
                text = "EXAMEN U${unit.id}",
                color = if (isUnlocked) DuoPurpleDark else DuoSubtext,
                fontSize = 11.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
