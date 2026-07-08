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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.LiteratureUnit
import com.example.model.Question
import com.example.ui.components.DuoButton
import com.example.ui.theme.*

@Composable
fun UnitExamScreen(
    unit: LiteratureUnit,
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
    val question = unit.exam.questions[currentQuestionIndex]
    val totalQuestions = unit.exam.questions.size
    val unitColor = Color(android.graphics.Color.parseColor(unit.colorHex))

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
                    IconButton(
                        onClick = onExitClick,
                        modifier = Modifier
                            .size(36.dp)
                            .background(DuoLightCream, shape = CircleShape)
                            .testTag("exam_close_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Salir del examen",
                            tint = DuoText
                        )
                    }

                    // Progress Bar
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "🏆 EXAMEN DE UNIDAD 🏆",
                        color = DuoPurpleDark,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    
                    Text(
                        text = unit.title.uppercase(),
                        color = DuoSubtext,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 16.dp)
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
                            lineHeight = 24.sp,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Option Buttons
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        question.options.forEachIndexed { idx, option ->
                            val isSelected = selectedOptionIndex == idx
                            
                            val optionBorderColor = when {
                                isAnswerChecked && idx == question.correctAnswerIndex -> DuoGreen
                                isAnswerChecked && isSelected && idx != question.correctAnswerIndex -> DuoPink
                                isSelected -> unitColor
                                else -> DuoGrayBorder
                            }

                            val optionBg = when {
                                isAnswerChecked && idx == question.correctAnswerIndex -> DuoGreen.copy(alpha = 0.1f)
                                isAnswerChecked && isSelected && idx != question.correctAnswerIndex -> DuoPink.copy(alpha = 0.1f)
                                isSelected -> unitColor.copy(alpha = 0.1f)
                                else -> Color.White
                            }

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .shadow(if (isSelected) 3.dp else 1.dp, RoundedCornerShape(16.dp))
                                    .background(optionBg, RoundedCornerShape(16.dp))
                                    .border(
                                        width = if (isSelected || (isAnswerChecked && idx == question.correctAnswerIndex)) 2.5.dp else 2.dp,
                                        color = optionBorderColor,
                                        shape = RoundedCornerShape(16.dp)
                                    )
                                    .clickable(enabled = !isAnswerChecked) { onOptionSelected(idx) }
                                    .padding(horizontal = 20.dp, vertical = 16.dp)
                                    .testTag("exam_option_$idx"),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .background(
                                                color = when {
                                                    isAnswerChecked && idx == question.correctAnswerIndex -> DuoGreen
                                                    isAnswerChecked && isSelected && idx != question.correctAnswerIndex -> DuoPink
                                                    isSelected -> unitColor
                                                    else -> DuoLightCream
                                                },
                                                shape = CircleShape
                                            )
                                            .border(1.5.dp, DuoGrayBorder, CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = (idx + 1).toString(),
                                            color = if (isSelected || isAnswerChecked) Color.White else DuoSubtext,
                                            fontWeight = FontWeight.Black,
                                            fontSize = 12.sp
                                        )
                                    }

                                    Text(
                                        text = option,
                                        color = DuoText,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }

                // Check / Continue Bottom Bar
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut() + slideOutVertically()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        if (!isAnswerChecked) {
                            DuoButton(
                                onClick = onCheckAnswer,
                                enabled = selectedOptionIndex != null,
                                backgroundColor = unitColor,
                                shadowColor = Color(android.graphics.Color.parseColor(unit.shadowColorHex)),
                                modifier = Modifier.fillMaxWidth(),
                                testTag = "exam_check_button"
                            ) {
                                Text(
                                    text = "COMPROBAR",
                                    color = Color.White,
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Black
                                )
                            }
                        } else {
                            val isCorrect = answeredCorrectly == true
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(
                                    containerColor = if (isCorrect) DuoGreenLight else DuoPinkLight
                                ),
                                shape = RoundedCornerShape(16.dp),
                                border = BorderStroke(1.5.dp, if (isCorrect) DuoGreen else DuoPink)
                            ) {
                                Column(modifier = Modifier.padding(16.dp)) {
                                    Text(
                                        text = if (isCorrect) "¡Excelente trabajo! 🎉" else "¡Uh oh! Respuesta incorrecta 🥺",
                                        color = if (isCorrect) DuoGreenDark else DuoPinkDark,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Black
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = question.explanation,
                                        color = DuoText,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium
                                    )
                                    Spacer(modifier = Modifier.height(12.dp))
                                    DuoButton(
                                        onClick = onContinueClick,
                                        backgroundColor = if (isCorrect) DuoGreen else DuoPink,
                                        shadowColor = if (isCorrect) DuoGreenDark else DuoPinkDark,
                                        modifier = Modifier.fillMaxWidth(),
                                        testTag = "exam_continue_button"
                                    ) {
                                        Text(
                                            text = "CONTINUAR",
                                            color = Color.White,
                                            fontSize = 14.sp,
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
    }
}
