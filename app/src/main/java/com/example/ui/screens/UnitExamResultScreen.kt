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
import com.example.model.LiteratureUnit
import com.example.ui.components.DuoButton
import com.example.ui.components.MascotBubble
import com.example.ui.theme.*

@Composable
fun UnitExamResultScreen(
    unit: LiteratureUnit,
    correctCount: Int,
    totalQuestions: Int,
    approved: Boolean,
    onContinueClick: () -> Unit,
    onRepeatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val unitColor = Color(android.graphics.Color.parseColor(unit.colorHex))

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
                    text = "RESULTADO DE EXAMEN",
                    color = DuoPurpleDark,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp,
                    textAlign = TextAlign.Center
                )

                MascotBubble(
                    text = if (approved) {
                        "¡BRILLANTE! 🌟 Has superado con honores el examen de la \"${unit.title}\". ¡Estás listo para conquistar nuevos imperios literarios! 🦉✨"
                    } else {
                        "¡No pasa nada, aventurero! 🦉 El examen de unidad es un gran reto. Repasemos un poco los temas de la unidad e intentémoslo de nuevo. ¡Tú puedes hacerlo!"
                    },
                    bubbleColor = Color.White
                )

                Image(
                    painter = painterResource(id = R.drawable.img_mascot_owl_1783318823514),
                    contentDescription = "Mito el Búho",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .shadow(4.dp, CircleShape)
                        .border(3.dp, unitColor, CircleShape),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
            }

            // Middle Section: Score Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(24.dp))
                    .testTag("exam_result_score_card"),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                border = BorderStroke(2.5.dp, if (approved) DuoGreen else DuoPink)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = "TU CALIFICACIÓN DEL EXAMEN",
                        color = DuoSubtext,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    )

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

                    Box(
                        modifier = Modifier
                            .background(
                                color = if (approved) DuoGreen else DuoPink,
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(horizontal = 16.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (approved) "¡UNIDAD SUPERADA! 🏆" else "REPASO REQUERIDO 🔁",
                            color = Color.White,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp
                        )
                    }

                    if (approved) {
                        val starsEarned = 30 + (correctCount * 5)
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            modifier = Modifier.padding(top = 4.dp)
                        ) {
                            Text(text = "⭐", fontSize = 18.sp)
                            Text(
                                text = "¡Bono especial de +$starsEarned Estrellas!",
                                color = DuoYellowDark,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black
                            )
                        }
                    } else {
                        Text(
                            text = "Necesitas al menos 5 respuestas correctas (aprox. 70%) para desbloquear la siguiente unidad literaria.",
                            color = DuoSubtext,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }

            // Bottom Section: Navigation
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
                        testTag = "exam_result_continue_button"
                    ) {
                        Text(
                            text = "GUARDAR Y RECLAMAR RECOMPENSA ➡️",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                } else {
                    DuoButton(
                        onClick = onRepeatClick,
                        backgroundColor = DuoPurpleDark,
                        shadowColor = DuoPurple,
                        modifier = Modifier.fillMaxWidth(),
                        testTag = "exam_result_repeat_button"
                    ) {
                        Text(
                            text = "VOLVER A INTENTAR EXAMEN 📝",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black
                        )
                    }

                    Button(
                        onClick = onContinueClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Volver al Mapa de Aprendizaje",
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
