package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import com.example.model.Level
import com.example.model.Question
import com.example.ui.components.DuoButton
import com.example.ui.theme.*

@Composable
fun QuizScreen(
    level: Level,
    currentQuestionIndex: Int,
    selectedOptionIndex: Int?,
    isAnswerChecked: Boolean,
    answeredCorrectly: Boolean?,
    onOptionSelected: (Int) -> Unit,
    onCheckAnswer: () -> Unit,
    onContinueClick: () -> Unit,
    onExitClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val question = level.questions[currentQuestionIndex]
    val totalQuestions = level.questions.size
    val levelColor = Color(android.graphics.Color.parseColor(level.colorHex))

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
                    // Close/Exit button
                    IconButton(
                        onClick = onExitClick,
                        modifier = Modifier
                            .size(36.dp)
                            .background(DuoLightCream, shape = CircleShape)
                            .testTag("quiz_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Salir del cuestionario",
                            tint = DuoText
                        )
                    }

                    // Question Progress Bar
                    val progressFraction = (currentQuestionIndex.toFloat() + 1) / totalQuestions.toFloat()
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(14.dp)
                            .background(DuoGrayLight, shape = RoundedCornerShape(7.dp))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progressFraction)
                                .fillMaxHeight()
                                .background(DuoGreen, shape = RoundedCornerShape(7.dp))
                        )
                    }

                    // Counter
                    Text(
                        text = "${currentQuestionIndex + 1}/$totalQuestions",
                        color = DuoSubtext,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(DuoLightCream)
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Top Content: Question Prompt & Emojis
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Animated Quiz header
                    Text(
                        text = "🧠 ¿CUÁNTO APRENDISTE? 🧠",
                        color = DuoBlue,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )

                    // Question Text Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(2.dp, RoundedCornerShape(20.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(2.dp, DuoGrayBorder)
                    ) {
                        Text(
                            text = question.text,
                            color = DuoText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Black,
                            textAlign = TextAlign.Center,
                            fontFamily = FontFamily.SansSerif,
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Option Buttons (Alternating letters A, B, C)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        question.options.forEachIndexed { index, option ->
                            val isSelected = selectedOptionIndex == index
                            val optionLetter = when (index) {
                                0 -> "A"
                                1 -> "B"
                                2 -> "C"
                                else -> "D"
                            }

                            // Calculate option state colors
                            val cardBg = if (isSelected) DuoBlue.copy(alpha = 0.08f) else Color.White
                            val cardBorder = if (isSelected) DuoBlue else DuoGrayBorder
                            val letterBg = if (isSelected) DuoBlue else DuoLightCream
                            val letterText = if (isSelected) Color.White else DuoSubtext

                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(elevation = if (isSelected) 3.dp else 1.dp, shape = RoundedCornerShape(16.dp))
                                    .selectable(
                                        selected = isSelected,
                                        enabled = !isAnswerChecked,
                                        onClick = { onOptionSelected(index) }
                                    )
                                    .testTag("quiz_option_$index"),
                                shape = RoundedCornerShape(16.dp),
                                color = cardBg,
                                border = BorderStroke(2.dp, cardBorder)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    // Option Bubble
                                    Box(
                                        modifier = Modifier
                                            .size(36.dp)
                                            .background(letterBg, shape = CircleShape)
                                            .border(1.5.dp, DuoGrayBorder, shape = CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = optionLetter,
                                            color = letterText,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Black
                                        )
                                    }

                                    // Option Text
                                    Text(
                                        text = option,
                                        color = DuoText,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }
                    }
                }

                // Empty space so checking drawer does not cover options
                Spacer(modifier = Modifier.height(110.dp))
            }

            // Interactive Bottom Checking Drawer
            AnimatedVisibility(
                visible = true,
                enter = slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(250)
                ),
                exit = slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(250)
                ),
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(12.dp, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)),
                    color = when {
                        !isAnswerChecked -> Color.White
                        answeredCorrectly == true -> Color(0xFFE8F5E9) // Success Light Green
                        else -> Color(0xFFFFEBEE) // Error Light Red
                    },
                    shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
                    border = BorderStroke(
                        width = 2.dp,
                        color = when {
                            !isAnswerChecked -> DuoGrayBorder
                            answeredCorrectly == true -> DuoGreen
                            else -> DuoPink
                        }
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .navigationBarsPadding()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (isAnswerChecked) {
                            // Feedback Title
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = if (answeredCorrectly == true) "¡EXCELENTE! 🎉" else "¡CASI CASI! 😅",
                                    color = if (answeredCorrectly == true) DuoGreenDark else DuoPinkDark,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }

                            // Explanation Text
                            Text(
                                text = if (answeredCorrectly == true) {
                                    question.explanation
                                } else {
                                    "La respuesta correcta es:\n\"${question.options[question.correctAnswerIndex]}\"\n\n${question.explanation}"
                                },
                                color = DuoText,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                lineHeight = 18.sp,
                                modifier = Modifier.fillMaxWidth()
                            )

                            // Continue button
                            DuoButton(
                                onClick = onContinueClick,
                                modifier = Modifier.fillMaxWidth(),
                                backgroundColor = if (answeredCorrectly == true) DuoGreen else DuoPink,
                                shadowColor = if (answeredCorrectly == true) DuoGreenDark else DuoPinkDark,
                                testTag = "quiz_continue_button"
                            ) {
                                Text(
                                    text = "CONTINUAR 👉",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        } else {
                            // Prompt selected check
                            DuoButton(
                                onClick = onCheckAnswer,
                                modifier = Modifier.fillMaxWidth(),
                                enabled = selectedOptionIndex != null,
                                backgroundColor = DuoGreen,
                                shadowColor = DuoGreenDark,
                                testTag = "quiz_check_button"
                            ) {
                                Text(
                                    text = "COMPROBAR RESPUESTA ✔️",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
