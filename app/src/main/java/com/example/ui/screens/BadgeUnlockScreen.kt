package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.Level
import com.example.ui.components.DuoButton
import com.example.ui.theme.*

@Composable
fun BadgeUnlockScreen(
    level: Level,
    onContinueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val badge = level.badge
    var startAnimation by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
    }

    // Interactive scale-up animation
    val scaleAnim by animateFloatAsState(
        targetValue = if (startAnimation) 1.0f else 0.4f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "badge_scale_anim"
    )

    // Sunburst spin background drawing
    val infiniteTransition = rememberInfiniteTransition(label = "sunburst_spin")
    val angleAnim by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(12000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "sunburst_angle"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFFFF9C4), DuoLightCream) // Soft gold to cream
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ) {
            // Header Title
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Text(
                    text = "🌟 ¡INCREÍBLE! 🌟",
                    color = DuoOrange,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "¡NUEVA INSIGNIA!",
                    color = DuoText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )
            }

            // Central Glowing Badge Icon with spinning background
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(220.dp)
                        .scale(scaleAnim)
                        .rotate(angleAnim / 4f), // slow rotation for extra flair
                    contentAlignment = Alignment.Center
                ) {
                    // Outer golden rays (simulated with layered circle vectors)
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(DuoYellow.copy(alpha = 0.4f), Color.Transparent)
                                ),
                                shape = CircleShape
                            )
                    )

                    // Inner golden ring
                    Box(
                        modifier = Modifier
                            .size(170.dp)
                            .background(Color.White, shape = CircleShape)
                            .border(6.dp, DuoYellow, shape = CircleShape)
                            .padding(12.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = listOf(Color(0xFFFFF59D), Color(0xFFFFFDE7))
                                    ),
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = badge.iconEmoji,
                                fontSize = 80.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Badge Name
                Text(
                    text = badge.name,
                    color = DuoText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Badge Description
                Text(
                    text = badge.description,
                    color = DuoSubtext,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(horizontal = 24.dp)
                )
            }

            // Cheering Mascot with Speech bubble
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(DuoBlue.copy(alpha = 0.15f), shape = RoundedCornerShape(20.dp))
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_mascot_owl_1783318823514),
                        contentDescription = "Mito festejando",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(16.dp))
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color.White, shape = RoundedCornerShape(16.dp))
                        .border(2.dp, DuoGreen, shape = RoundedCornerShape(16.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "¡Yujuuu! ✨ Eres súper brillante. ¡Ya tienes la insignia del nivel ${level.id}! Te la has ganado con mucho esfuerzo.",
                        color = DuoText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 18.sp
                    )
                }
            }

            // Action button
            DuoButton(
                onClick = onContinueClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .testTag("badge_celebration_continue_button"),
                backgroundColor = DuoGreen,
                shadowColor = DuoGreenDark
            ) {
                Text(
                    text = "¡VOLVER AL CAMINO! 🗺️",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
