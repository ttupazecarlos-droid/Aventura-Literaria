package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.screens.*
import com.example.ui.theme.*
import com.example.viewmodel.GameViewModel
import com.example.viewmodel.ScreenState
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainAppContent()
                }
            }
        }
    }
}

@Composable
fun MainAppContent() {
    val viewModel: GameViewModel = viewModel()
    val progress by viewModel.progress.collectAsStateWithLifecycle()
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    var showSplash by remember { mutableStateOf(true) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    if (showSplash) {
        SplashScreen(onTimeout = { showSplash = false })
    } else {
        val gesturesEnabled = screenState is ScreenState.Dashboard ||
                screenState is ScreenState.StatsPanel ||
                screenState is ScreenState.Addison ||
                screenState is ScreenState.About

        ModalNavigationDrawer(
            drawerState = drawerState,
            gesturesEnabled = gesturesEnabled,
            drawerContent = {
                ModalDrawerSheet(
                    drawerContainerColor = Color.White,
                    modifier = Modifier.width(300.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(DuoBlue)
                            .padding(top = 48.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
                    ) {
                        Column {
                            Text(
                                text = "IEP Addison",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "Educación para el éxito",
                                color = Color.White.copy(alpha = 0.8f),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    val menuItems = listOf(
                        Triple("Inicio", ScreenState.Dashboard, "🏠"),
                        Triple("Addison", ScreenState.Addison, "🏫"),
                        Triple("Mi progreso", ScreenState.StatsPanel, "🏆"),
                        Triple("Acerca de", ScreenState.About, "ℹ️")
                    )

                    menuItems.forEach { (label, targetState, icon) ->
                        val isSelected = when (targetState) {
                            is ScreenState.Dashboard -> screenState is ScreenState.Dashboard
                            is ScreenState.Addison -> screenState is ScreenState.Addison
                            is ScreenState.StatsPanel -> screenState is ScreenState.StatsPanel
                            is ScreenState.About -> screenState is ScreenState.About
                            else -> false
                        }

                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = label,
                                    fontWeight = if (isSelected) FontWeight.Black else FontWeight.Bold,
                                    color = if (isSelected) DuoBlue else DuoText,
                                    fontSize = 15.sp
                                )
                            },
                            selected = isSelected,
                            onClick = {
                                scope.launch { drawerState.close() }
                                viewModel.navigateTo(targetState)
                            },
                            icon = { Text(text = icon, fontSize = 20.sp) },
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            colors = NavigationDrawerItemDefaults.colors(
                                selectedContainerColor = DuoLightCream,
                                unselectedContainerColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        ) {
            Crossfade(targetState = screenState, label = "screen_routing") { state ->
                when (state) {
                    is ScreenState.StartMenu -> {
                        StartScreen(
                            onStartClick = { viewModel.navigateTo(ScreenState.Dashboard) }
                        )
                    }
                    is ScreenState.Dashboard -> {
                        DashboardScreen(
                            progress = progress,
                            onLevelClick = { level -> viewModel.startLevel(level) },
                            onUnitExamClick = { unit -> viewModel.startUnitExam(unit) },
                            onStatsClick = { viewModel.navigateTo(ScreenState.StatsPanel) },
                            onMenuClick = { scope.launch { drawerState.open() } }
                        )
                    }
                    is ScreenState.Addison -> {
                        AddisonScreen(
                            progress = progress,
                            onStatsClick = { viewModel.navigateTo(ScreenState.StatsPanel) },
                            onMenuClick = { scope.launch { drawerState.open() } }
                        )
                    }
                    is ScreenState.About -> {
                        AboutScreen(
                            progress = progress,
                            onStatsClick = { viewModel.navigateTo(ScreenState.StatsPanel) },
                            onMenuClick = { scope.launch { drawerState.open() } }
                        )
                    }
                    is ScreenState.ReadingSummary -> {
                        ReadingScreen(
                            level = state.level,
                            currentSlideIndex = state.currentSlideIndex,
                            onNextClick = { viewModel.nextSlide(state.level, state.currentSlideIndex) },
                            onBackClick = { viewModel.prevSlide(state.level, state.currentSlideIndex) }
                        )
                    }
                    is ScreenState.PracticeQuiz -> {
                        QuizScreen(
                            level = state.level,
                            currentQuestionIndex = state.currentQuestionIndex,
                            selectedOptionIndex = state.selectedOptionIndex,
                            isAnswerChecked = state.isAnswerChecked,
                            answeredCorrectly = state.answeredCorrectly,
                            onOptionSelected = { optionIndex -> viewModel.selectQuizOption(optionIndex) },
                            onCheckAnswer = { viewModel.checkQuizAnswer() },
                            onContinueClick = { viewModel.continueQuiz() },
                            onExitClick = { viewModel.navigateTo(ScreenState.Dashboard) }
                        )
                    }
                    is ScreenState.BadgeUnlockCelebration -> {
                        BadgeUnlockScreen(
                            level = state.level,
                            onContinueClick = { viewModel.navigateTo(ScreenState.Dashboard) }
                        )
                    }
                    is ScreenState.StatsPanel -> {
                        StatsScreen(
                            progress = progress,
                            onBackClick = { viewModel.navigateTo(ScreenState.Dashboard) },
                            onResetProgressClick = { viewModel.resetAllProgress() },
                            onMenuClick = { scope.launch { drawerState.open() } }
                        )
                    }
                    is ScreenState.QuizResult -> {
                        QuizResultScreen(
                            level = state.level,
                            correctCount = state.correctCount,
                            totalQuestions = state.totalQuestions,
                            approved = state.approved,
                            onContinueClick = {
                                if (state.approved) {
                                    viewModel.completeLevel(state.level, state.correctCount)
                                } else {
                                    viewModel.navigateTo(ScreenState.Dashboard)
                                }
                            },
                            onRepeatClick = {
                                viewModel.startLevel(state.level)
                            }
                        )
                    }
                    is ScreenState.UnitExamQuiz -> {
                        UnitExamScreen(
                            unit = state.unit,
                            currentQuestionIndex = state.currentQuestionIndex,
                            selectedOptionIndex = state.selectedOptionIndex,
                            isAnswerChecked = state.isAnswerChecked,
                            answeredCorrectly = state.answeredCorrectly,
                            onOptionSelected = { idx -> viewModel.selectUnitExamOption(idx) },
                            onCheckAnswer = { viewModel.checkUnitExamAnswer() },
                            onContinueClick = { viewModel.continueUnitExam() },
                            onExitClick = { viewModel.navigateTo(ScreenState.Dashboard) }
                        )
                    }
                    is ScreenState.UnitExamResult -> {
                        UnitExamResultScreen(
                            unit = state.unit,
                            correctCount = state.correctCount,
                            totalQuestions = state.totalQuestions,
                            approved = state.approved,
                            onContinueClick = {
                                if (state.approved) {
                                    viewModel.completeUnitExam(state.unit, state.correctCount)
                                } else {
                                    viewModel.navigateTo(ScreenState.Dashboard)
                                }
                            },
                            onRepeatClick = {
                                viewModel.startUnitExam(state.unit)
                            }
                        )
                    }
                }
            }
        }
    }
}
