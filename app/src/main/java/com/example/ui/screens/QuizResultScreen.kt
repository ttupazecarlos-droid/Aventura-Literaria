package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Level
import com.example.ui.components.DuoButton
import com.example.ui.components.MascotBubble
import com.example.ui.theme.*

@Composable
fun QuizResultScreen(
    level: Level,
    correctCount: Int,
    totalQuestions: Int,
    approved: Boolean,
    onContinueClick: () -> Unit,
    onRepeatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val levelColor = Color(android.graphics.Color.parseColor(level.colorHex))
    val levelShadowColor = Color(android.graphics.Color.parseColor(level.shadowColorHex))

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(DuoLightCream)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DuoLightCream)
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Top Section: Title and Mascot encouragement
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "RESULTADO DE EVALUACIÓN",
                    color = DuoSubtext,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                // Speach bubble with owl
                MascotBubble(
                    text = if (approved) {
                        "¡Felicidades, aventurero! 🎉 Has respondido con éxito y aprobado el nivel de \"${level.subtitle}\". ¡Estoy súper orgulloso de ti! 🦉✨"
                    } else {
                        "¡No te preocupes! El camino del aprendizaje está lleno de intentos. Casi lo logras, pero para dominar este tema y avanzar, es ideal repasar las diapositivas una vez más. ¡Intentémoslo juntos! 🦉📖"
                    },
                    bubbleColor = Color.White
                )

                // Mascot Owl image
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_owl_1783318823514),
                    contentDescription = "Mito el Búho",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .shadow(4.dp, CircleShape)
                        .border(3.dp, levelColor, CircleShape),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }

            // Middle Section: Dynamic Score Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(24.dp))
                    .testTag("result_score_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.dp, if (approved) DuoGreen else DuoPink)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "TU CALIFICACIÓN",
                        color = DuoSubtext,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )

                    // Large Score Circle
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .background(
                                color = if (approved) DuoGreen.copy(alpha = 0.1f) else DuoPink.copy(alpha = 0.1f),
                                shape = CircleShape
                            )
                            .border(
                                width = 3.dp,
                                color = if (approved) DuoGreen else DuoPink,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                text = "$correctCount/$totalQuestions",
                                color = if (approved) DuoGreenDark else DuoPinkDark,
                                fontSize = 32.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "aciertos",
                                color = DuoSubtext,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // Approved / Try Again Indicator Badge
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (approved) DuoGreen else DuoPink,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (approved) "¡APROBADO! 🏆" else "EVALUACIÓN REQUERIDA 🔁",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }

                    // Star details
                    if (approved) {
                        val starsEarned = 10 + (correctCount * 5)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text(text = "⭐", fontSize = 18.sp)
                            Text(
                                text = "¡Recompensa de +$starsEarned Estrellas!",
                                color = DuoYellowDark,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    } else {
                        Text(
                            text = "Necesitas al menos 4 respuestas correctas para desbloquear el siguiente nivel.",
                            color = DuoSubtext,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }

            // Bottom Section: Dynamic Navigation Buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                if (approved) {
                    DuoButton(
                        onClick = onContinueClick,
                        backgroundColor = DuoGreen,
                        shadowColor = DuoGreenDark,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "result_continue_button"
                    ) {
                        Text(
                            text = "GUARDAR Y CONTINUAR ➡️",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                } else {
                    DuoButton(
                        onClick = onRepeatClick,
                        backgroundColor = DuoBlue,
                        shadowColor = DuoBlueDark,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "result_repeat_button"
                    ) {
                        Text(
                            text = "REPETIR LECCIÓN 📖",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    // Optional back to dashboard if they want to exit
                    Button(
                        onClick = onContinueClick, // Just goes back to dashboard
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Volver al Mapa",
                            color = DuoSubtext,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}
