package com.example.ui.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.Level
import com.example.model.Slide
import com.example.ui.components.DuoButton
import com.example.ui.components.MascotBubble
import com.example.ui.theme.*

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ReadingScreen(
    level: Level,
    currentSlideIndex: Int,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val slide = level.slides[currentSlideIndex]
    val levelColor = Color(android.graphics.Color.parseColor(level.colorHex))
    val levelShadowColor = Color(android.graphics.Color.parseColor(level.shadowColorHex))

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
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Back to map button
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(40.dp)
                            .background(DuoLightCream, shape = RoundedCornerShape(12.dp))
                            .border(1.5.dp, DuoGrayBorder, shape = RoundedCornerShape(12.dp))
                            .testTag("reading_back_arrow")
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás",
                            tint = DuoText
                        )
                    }

                    // Progress indicators
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = level.subtitle,
                            color = DuoText,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Black
                        )
                        Text(
                            text = "Lectura divertida - Tarjeta ${currentSlideIndex + 1} de ${level.slides.size}",
                            color = DuoSubtext,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Slide progress steps bar
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    repeat(level.slides.size) { index ->
                        val isPastOrActive = index <= currentSlideIndex
                        val color = if (isPastOrActive) levelColor else DuoGrayLight
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(8.dp)
                                .background(color, shape = RoundedCornerShape(4.dp))
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DuoLightCream)
                .padding(innerPadding)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Interactive reading container
            AnimatedContent(
                targetState = slide,
                transitionSpec = {
                    slideInHorizontally(
                        animationSpec = tween(300),
                        initialOffsetX = { fullWidth -> if (currentSlideIndex > 0) fullWidth else -fullWidth }
                    ) togetherWith slideOutHorizontally(
                        animationSpec = tween(300),
                        targetOffsetX = { fullWidth -> if (currentSlideIndex > 0) -fullWidth else fullWidth }
                    )
                },
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                label = "slide_transition"
            ) { targetSlide ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = 12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Big Decorative Card
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .shadow(elevation = 3.dp, shape = RoundedCornerShape(24.dp)),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(24.dp),
                        border = BorderStroke(2.dp, DuoGrayBorder)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            // Big Cartoon Emoji
                            Text(
                                text = targetSlide.emoji,
                                fontSize = 72.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(bottom = 16.dp)
                            )

                            // Title
                            Text(
                                text = targetSlide.title,
                                color = levelColor,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(bottom = 12.dp)
                            )

                            // Narrative text
                            Text(
                                text = targetSlide.text,
                                color = DuoText,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center,
                                lineHeight = 24.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Supporting tip from owl mascot
                    val companionTip = when (targetSlide.illustrationType) {
                        "universe" -> "¡Vaya! Al principio todo era un gran desorden. ¡El Caos no tenía límites!"
                        "earth" -> "¡Gea fue muy generosa! Ella creó los árboles y las lagunas para nosotros."
                        "cyclops" -> "¡Ten cuidado! Los Cíclopes daban un poco de miedo, ¡pero eran excelentes herreros!"
                        "olympus" -> "¡Zeus vigila desde arriba! Si ves nubes oscuras, ¡tal vez esté preparando un rayo!"
                        "poseidon" -> "¡Poseidón ama a los delfines! Siempre que el mar está calmado, está feliz."
                        "hermes" -> "¡Hermes vuela más rápido que un avión! ¿Te imaginas correr con alitas?"
                        "heroes" -> "¡Los semidioses no se rinden! Siempre usan el corazón para tomar decisiones."
                        "medusa" -> "¡Mira a su espejo! Usar el reflejo del escudo fue la mejor idea de Perseo."
                        "minotaur" -> "¡No sueltes el hilo! Gracias al hilo de Ariadna, Teseo no se perdió jamás."
                        "hercules" -> "¡Hércules era increíble! Pero sus mejores armas eran su paciencia y valentía."
                        "lion" -> "¡Vencer al León de Nemea fue muy difícil! ¡Su piel era fuerte como el diamante!"
                        "hydra" -> "¡Cuidado con las cabezas! Hércules usó fuego para evitar que crecieran de nuevo."
                        "trojan_horse" -> "¡La astucia vence a la fuerza! El caballo de madera fue un invento genial de Odiseo."
                        "trojan_inside" -> "¡Sorpresa! Los soldados griegos esperaron pacientemente a que se hiciera de noche."
                        "odyssey" -> "¡10 años viajando! Odiseo tuvo paciencia de sabio ante tantas tormentas."
                        "library" -> "¡Las familias de libros! Son el Narrativo (cuentos), Lírico (poemas) y Teatral (actores)."
                        "poems" -> "¡Los poemas tienen música! La rima ayuda a cantar los sentimientos."
                        "theater" -> "¡Luces, cámara, acción! En el teatro cobramos vida y nos divertimos actuando."
                        "cid_horse" -> "¡Qué gran caballero! El Cid defendía su honor y amaba a su fiel Babieca."
                        "cid_king" -> "¡La lealtad es un gran valor! Aunque el rey lo castigó, el Cid le perdonó."
                        "juglar" -> "¡Los juglares eran los cantantes de la época! No tenían micrófonos pero todos los oían."
                        "dante_forest" -> "¡Dante se perdió en un sueño! Pero no te preocupes, ¡este viaje tiene final feliz!"
                        "virgilio" -> "¡Un buen amigo es un tesoro! Virgilio guió a Dante con mucha paciencia."
                        "paradise" -> "¡Qué hermoso Paraíso! Lleno de estrellas y amor. ¡Felicidades por llegar hasta aquí!"
                        else -> "¡Increíble historia! Vamos a seguir leyendo para ganar nuestra insignia."
                    }

                    MascotBubble(
                        text = companionTip,
                        bubbleColor = Color.White,
                        borderColor = levelColor.copy(alpha = 0.5f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Navigation and Actions bottom drawer
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Back button
                if (currentSlideIndex > 0) {
                    IconButton(
                        onClick = onBackClick,
                        modifier = Modifier
                            .size(56.dp)
                            .background(Color.White, shape = RoundedCornerShape(16.dp))
                            .border(2.dp, DuoGrayBorder, shape = RoundedCornerShape(16.dp))
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Slide Anterior",
                            tint = DuoText
                        )
                    }
                }

                // Progress Action (Next / Practice)
                val isLastSlide = currentSlideIndex == level.slides.size - 1
                val btnColor = if (isLastSlide) DuoOrange else levelColor
                val btnShadowColor = if (isLastSlide) DuoOrangeDark else levelShadowColor
                val btnText = if (isLastSlide) "¡PROBAR LO APRENDIDO! 📝" else "CONTINUAR 👉"

                DuoButton(
                    onClick = onNextClick,
                    modifier = Modifier.weight(1f),
                    backgroundColor = btnColor,
                    shadowColor = btnShadowColor,
                    testTag = if (isLastSlide) "start_quiz_button" else "next_slide_button"
                ) {
                    Text(
                        text = btnText,
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}
