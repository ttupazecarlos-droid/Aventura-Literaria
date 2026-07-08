package com.example.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.theme.*

@Composable
fun DuoButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = DuoGreen,
    shadowColor: Color = DuoGreenDark,
    enabled: Boolean = true,
    height: Dp = 56.dp,
    testTag: String = "",
    content: @Composable RowScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val displayBackgroundColor = if (enabled) backgroundColor else DuoGrayLight
    val displayShadowColor = if (enabled) shadowColor else DuoGrayBorder

    // When pressed, the button shifts down by 4dp to create a physical squish effect
    val yOffset by animateDpAsState(
        targetValue = if (isPressed && enabled) 4.dp else 0.dp,
        label = "button_squish_offset"
    )
    val shadowHeight = 6.dp

    Box(
        modifier = modifier
            .height(height + shadowHeight)
            .then(if (testTag.isNotEmpty()) Modifier.testTag(testTag) else Modifier)
            .clickable(
                interactionSource = interactionSource,
                indication = null, // Disable default ripple to preserve custom 3D effect
                enabled = enabled,
                onClick = onClick
            )
    ) {
        // Shadow Layer (Darker base)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .offset(y = shadowHeight)
                .background(
                    color = displayShadowColor,
                    shape = RoundedCornerShape(16.dp)
                )
        )

        // Front Face Layer (Vibrant top)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .offset(y = yOffset)
                .background(
                    color = displayBackgroundColor,
                    shape = RoundedCornerShape(16.dp)
                )
                .border(
                    width = 2.dp,
                    color = if (enabled) Color.White.copy(alpha = 0.3f) else Color.Transparent,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(horizontal = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                content()
            }
        }
    }
}

@Composable
fun MascotBubble(
    text: String,
    modifier: Modifier = Modifier,
    bubbleColor: Color = Color.White,
    borderColor: Color = DuoGrayBorder,
    mascotEmoji: String = "🦉"
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Mascot representation (Using generated image R.drawable.img_mascot_owl_1783318823514)
        Box(
            modifier = Modifier
                .size(90.dp)
                .background(DuoBlue.copy(alpha = 0.15f), shape = RoundedCornerShape(24.dp))
                .border(2.dp, DuoBlue.copy(alpha = 0.4f), shape = RoundedCornerShape(24.dp))
                .padding(4.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_mascot_owl_1783318823514),
                contentDescription = "Mito el Búho",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(20.dp))
            )
        }

        // Comic-style Speech Bubble
        Box(
            modifier = Modifier
                .weight(1f)
                .shadow(elevation = 2.dp, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .background(bubbleColor, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .border(2.dp, borderColor, shape = RoundedCornerShape(topStart = 0.dp, topEnd = 20.dp, bottomStart = 20.dp, bottomEnd = 20.dp))
                .padding(16.dp)
        ) {
            Text(
                text = text,
                color = DuoText,
                fontSize = 15.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
fun StatusHeader(
    streak: Int,
    stars: Int,
    unlockedBadgesCount: Int,
    totalBadgesCount: Int = 28,
    completedLevelsCount: Int,
    totalLevelsCount: Int = 28,
    completedExamsCount: Int,
    totalExamsCount: Int = 7,
    onStatsClick: () -> Unit,
    onMenuClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (onMenuClick != null) {
                    IconButton(
                        onClick = onMenuClick,
                        modifier = Modifier
                            .size(36.dp)
                            .testTag("menu_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = DuoText,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                // Clickable Progress Panel Entry
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(12.dp))
                        .clickable(onClick = onStatsClick)
                        .background(DuoLightCream)
                        .border(1.5.dp, DuoGrayBorder, RoundedCornerShape(12.dp))
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(text = "🏅", fontSize = 16.sp)
                    Text(
                        text = "Insignias: $unlockedBadgesCount",
                        color = DuoText,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // Stats Section (Stars only)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // Stars
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Estrellas",
                            tint = DuoYellow,
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = "$stars",
                            color = DuoYellow,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Animated Progress Bar across completed levels + exams (35 items total)
            val completedItems = completedLevelsCount + completedExamsCount
            val totalItems = totalLevelsCount + totalExamsCount
            val progressPercent = if (totalItems > 0) completedItems.toFloat() / totalItems.toFloat() else 0f
            val animatedProgress by animateFloatAsState(
                targetValue = progressPercent,
                label = "top_progress_bar_anim"
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "📚", fontSize = 16.sp)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(14.dp)
                        .background(DuoGrayLight, shape = RoundedCornerShape(7.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(animatedProgress)
                            .fillMaxHeight()
                            .background(DuoGreen, shape = RoundedCornerShape(7.dp))
                            .border(1.dp, Color.White.copy(alpha = 0.2f), shape = RoundedCornerShape(7.dp))
                    )
                }
                Text(
                    text = "$completedItems/$totalItems",
                    color = DuoSubtext,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
