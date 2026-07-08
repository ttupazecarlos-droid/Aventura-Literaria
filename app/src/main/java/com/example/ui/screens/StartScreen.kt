package com.example.ui.screens

import androidx.compose.animation.*
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
import com.example.ui.components.DuoButton
import com.example.ui.theme.*

@Composable
fun StartScreen(
    onStartClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isLoaded by remember { mutableStateOf(false) }
    
    LaunchedEffect(Unit) {
        isLoaded = true
    }

    val scaleAnim by animateFloatAsState(
        targetValue = if (isLoaded) 1.0f else 0.8f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow),
        label = "mascot_bounce"
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFFE3F2FD), DuoLightCream)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Welcome Header with beautiful clouds/stars decoration (styled emojis)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "⭐", fontSize = 24.sp)
                Text(
                    text = "¡BIENVENIDO A TU!",
                    color = DuoBlue,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 2.sp
                )
                Text(text = "⭐", fontSize = 24.sp)
            }

            Text(
                text = "Aventura\nLiteraria",
                color = DuoText,
                fontSize = 42.sp,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center,
                lineHeight = 48.sp,
                fontFamily = FontFamily.SansSerif,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Animated Mascot Container (Bouncy Entry)
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .scale(scaleAnim)
                    .background(Color.White, shape = RoundedCornerShape(40.dp))
                    .border(4.dp, DuoBlue, shape = RoundedCornerShape(40.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_mascot_owl_1783318823514),
                    contentDescription = "Mito el Búho",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(32.dp))
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Subtitle
            Text(
                text = "¡Aprende literatura jugando! Viaja con dioses griegos, caballeros reales e historias increíbles.",
                color = DuoSubtext,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 48.dp)
            )

            // Main physical play button
            DuoButton(
                onClick = onStartClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                backgroundColor = DuoGreen,
                shadowColor = DuoGreenDark,
                testTag = "start_adventure_button"
            ) {
                Text(
                    text = "¡COMENZAR AVENTURA! 🚀",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}
