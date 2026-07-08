package com.example.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.model.LiteratureDatabase
import com.example.model.Level
import com.example.model.LiteratureUnit
import com.example.model.UserProgress
import com.example.model.ProgressRepository

sealed class ScreenState {
    object StartMenu : ScreenState()
    object Dashboard : ScreenState()
    data class ReadingSummary(val level: Level, val currentSlideIndex: Int) : ScreenState()
    data class PracticeQuiz(
        val level: Level,
        val currentQuestionIndex: Int,
        val selectedOptionIndex: Int?,
        val isAnswerChecked: Boolean,
        val answeredCorrectly: Boolean?,
        val correctCount: Int
    ) : ScreenState()
    data class BadgeUnlockCelebration(val level: Level) : ScreenState()
    object StatsPanel : ScreenState()
    object Addison : ScreenState()
    object About : ScreenState()
    data class QuizResult(
        val level: Level,
        val correctCount: Int,
        val totalQuestions: Int,
        val approved: Boolean
    ) : ScreenState()
    data class UnitExamQuiz(
        val unit: LiteratureUnit,
        val currentQuestionIndex: Int,
        val selectedOptionIndex: Int?,
        val isAnswerChecked: Boolean,
        val answeredCorrectly: Boolean?,
        val correctCount: Int
    ) : ScreenState()
    data class UnitExamResult(
        val unit: LiteratureUnit,
        val correctCount: Int,
        val totalQuestions: Int,
        val approved: Boolean
    ) : ScreenState()
}

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ProgressRepository(application)
    
    private val _progress = MutableStateFlow(UserProgress())
    val progress: StateFlow<UserProgress> = _progress.asStateFlow()

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.StartMenu)
    val screenState: StateFlow<ScreenState> = _screenState.asStateFlow()

    var selectedLevel: Level? = null
        private set

    var selectedUnit: LiteratureUnit? = null
        private set

    init {
        loadUserProgress()
    }

    private fun loadUserProgress() {
        _progress.value = repository.loadProgress()
    }

    fun navigateTo(state: ScreenState) {
        _screenState.value = state
    }

    fun startLevel(level: Level) {
        selectedLevel = level
        // Navigate to reading summary, starting at slide 0
        _screenState.value = ScreenState.ReadingSummary(level, 0)
    }

    fun nextSlide(level: Level, currentIndex: Int) {
        if (currentIndex < level.slides.size - 1) {
            _screenState.value = ScreenState.ReadingSummary(level, currentIndex + 1)
        } else {
            // Summary finished! Start the practice quiz
            startQuiz(level)
        }
    }

    fun prevSlide(level: Level, currentIndex: Int) {
        if (currentIndex > 0) {
            _screenState.value = ScreenState.ReadingSummary(level, currentIndex - 1)
        } else {
            // Go back to dashboard
            _screenState.value = ScreenState.Dashboard
        }
    }

    private fun startQuiz(level: Level) {
        val shuffledQuestions = level.questions.map { it.shuffled() }
        val shuffledLevel = level.copy(questions = shuffledQuestions)
        _screenState.value = ScreenState.PracticeQuiz(
            level = shuffledLevel,
            currentQuestionIndex = 0,
            selectedOptionIndex = null,
            isAnswerChecked = false,
            answeredCorrectly = null,
            correctCount = 0
        )
    }

    fun selectQuizOption(optionIndex: Int) {
        val current = _screenState.value
        if (current is ScreenState.PracticeQuiz && !current.isAnswerChecked) {
            _screenState.value = current.copy(selectedOptionIndex = optionIndex)
        }
    }

    fun checkQuizAnswer() {
        val current = _screenState.value
        if (current is ScreenState.PracticeQuiz && current.selectedOptionIndex != null && !current.isAnswerChecked) {
            val question = current.level.questions[current.currentQuestionIndex]
            val isCorrect = current.selectedOptionIndex == question.correctAnswerIndex
            
            _screenState.value = current.copy(
                isAnswerChecked = true,
                answeredCorrectly = isCorrect,
                correctCount = if (isCorrect) current.correctCount + 1 else current.correctCount
            )
        }
    }

    fun continueQuiz() {
        val current = _screenState.value
        if (current is ScreenState.PracticeQuiz && current.isAnswerChecked) {
            val nextIndex = current.currentQuestionIndex + 1
            if (nextIndex < current.level.questions.size) {
                // Load next question
                _screenState.value = ScreenState.PracticeQuiz(
                    level = current.level,
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswerChecked = false,
                    answeredCorrectly = null,
                    correctCount = current.correctCount
                )
            } else {
                // Quiz completed! Evaluate results with QuizResult screen state
                val isApproved = current.correctCount >= 4
                _screenState.value = ScreenState.QuizResult(
                    level = current.level,
                    correctCount = current.correctCount,
                    totalQuestions = current.level.questions.size,
                    approved = isApproved
                )
            }
        }
    }

    fun completeLevel(level: Level, correctCount: Int) {
        val isFirstTime = !_progress.value.completedLevelIds.contains(level.id)
        
        // Let's reward the student!
        val starsEarned = 10 + (correctCount * 5)
        
        val newCompletedIds = _progress.value.completedLevelIds.toMutableSet().apply {
            add(level.id)
        }
        
        val newBadges = _progress.value.unlockedBadgeIds.toMutableSet().apply {
            add(level.badge.id)
        }

        val currentStreak = _progress.value.currentStreak

        val updatedProgress = _progress.value.copy(
            completedLevelIds = newCompletedIds,
            totalStars = _progress.value.totalStars + starsEarned,
            unlockedBadgeIds = newBadges,
            currentStreak = currentStreak
        )

        _progress.value = updatedProgress
        repository.saveProgress(updatedProgress)

        if (isFirstTime) {
            // Unlock badge celebration!
            _screenState.value = ScreenState.BadgeUnlockCelebration(level)
        } else {
            // Just go back to dashboard
            _screenState.value = ScreenState.Dashboard
        }
    }

    // --- UNIT EXAM FLOW ---
    fun startUnitExam(unit: LiteratureUnit) {
        selectedUnit = unit
        val shuffledQuestions = unit.exam.questions.map { it.shuffled() }
        val shuffledUnit = unit.copy(exam = unit.exam.copy(questions = shuffledQuestions))
        _screenState.value = ScreenState.UnitExamQuiz(
            unit = shuffledUnit,
            currentQuestionIndex = 0,
            selectedOptionIndex = null,
            isAnswerChecked = false,
            answeredCorrectly = null,
            correctCount = 0
        )
    }

    fun selectUnitExamOption(optionIndex: Int) {
        val current = _screenState.value
        if (current is ScreenState.UnitExamQuiz && !current.isAnswerChecked) {
            _screenState.value = current.copy(selectedOptionIndex = optionIndex)
        }
    }

    fun checkUnitExamAnswer() {
        val current = _screenState.value
        if (current is ScreenState.UnitExamQuiz && current.selectedOptionIndex != null && !current.isAnswerChecked) {
            val question = current.unit.exam.questions[current.currentQuestionIndex]
            val isCorrect = current.selectedOptionIndex == question.correctAnswerIndex
            
            _screenState.value = current.copy(
                isAnswerChecked = true,
                answeredCorrectly = isCorrect,
                correctCount = if (isCorrect) current.correctCount + 1 else current.correctCount
            )
        }
    }

    fun continueUnitExam() {
        val current = _screenState.value
        if (current is ScreenState.UnitExamQuiz && current.isAnswerChecked) {
            val nextIndex = current.currentQuestionIndex + 1
            if (nextIndex < current.unit.exam.questions.size) {
                _screenState.value = ScreenState.UnitExamQuiz(
                    unit = current.unit,
                    currentQuestionIndex = nextIndex,
                    selectedOptionIndex = null,
                    isAnswerChecked = false,
                    answeredCorrectly = null,
                    correctCount = current.correctCount
                )
            } else {
                // Exam completed! 7 questions, so let's require at least 5 correct (approx 70%)
                val isApproved = current.correctCount >= 5
                _screenState.value = ScreenState.UnitExamResult(
                    unit = current.unit,
                    correctCount = current.correctCount,
                    totalQuestions = current.unit.exam.questions.size,
                    approved = isApproved
                )
            }
        }
    }

    fun completeUnitExam(unit: LiteratureUnit, correctCount: Int) {
        // Reward 30 stars for completing a unit exam + 5 stars per correct answer!
        val starsEarned = 30 + (correctCount * 5)
        
        val newCompletedExamIds = _progress.value.completedUnitExamIds.toMutableSet().apply {
            add(unit.id)
        }

        val updatedProgress = _progress.value.copy(
            completedUnitExamIds = newCompletedExamExamIdsAndCheck(newCompletedExamIds),
            totalStars = _progress.value.totalStars + starsEarned
        )

        _progress.value = updatedProgress
        repository.saveProgress(updatedProgress)
        
        _screenState.value = ScreenState.Dashboard
    }

    private fun newCompletedExamExamIdsAndCheck(set: Set<Int>): Set<Int> {
        return set
    }

    fun resetAllProgress() {
        repository.clearProgress()
        _progress.value = UserProgress()
        _screenState.value = ScreenState.StartMenu
    }

    private fun com.example.model.Question.shuffled(): com.example.model.Question {
        val correctAnswer = options[correctAnswerIndex]
        val shuffledOptions = options.shuffled()
        val newCorrectAnswerIndex = shuffledOptions.indexOf(correctAnswer)
        return this.copy(
            options = shuffledOptions,
            correctAnswerIndex = if (newCorrectAnswerIndex != -1) newCorrectAnswerIndex else correctAnswerIndex
        )
    }
}
