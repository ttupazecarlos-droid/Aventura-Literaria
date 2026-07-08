package com.example.model

import android.content.Context
import android.content.SharedPreferences

data class UserProgress(
    val completedLevelIds: Set<Int> = emptySet(),
    val completedUnitExamIds: Set<Int> = emptySet(),
    val totalStars: Int = 0,
    val currentStreak: Int = 1,
    val unlockedBadgeIds: Set<String> = emptySet(),
    val lastActiveTimestamp: Long = 0L
) {
    fun isLevelUnlocked(levelId: Int): Boolean {
        if (levelId == 1) return true
        val levels = LiteratureDatabase.levels
        val currentLevel = levels.find { it.id == levelId } ?: return false
        val prevLevel = levels.find { it.id == levelId - 1 } ?: return false
        
        return if (prevLevel.unitId != currentLevel.unitId) {
            completedUnitExamIds.contains(prevLevel.unitId)
        } else {
            completedLevelIds.contains(levelId - 1)
        }
    }

    fun isUnitExamUnlocked(unitId: Int): Boolean {
        val levelsOfUnit = LiteratureDatabase.levels.filter { it.unitId == unitId }
        if (levelsOfUnit.isEmpty()) return false
        return levelsOfUnit.all { completedLevelIds.contains(it.id) }
    }
}

class ProgressRepository(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("aventura_literaria_prefs", Context.MODE_PRIVATE)

    fun loadProgress(): UserProgress {
        val completedIds = prefs.getStringSet("completed_levels", emptySet()) ?: emptySet()
        val completedExamIds = prefs.getStringSet("completed_unit_exams", emptySet()) ?: emptySet()
        val badges = prefs.getStringSet("unlocked_badges", emptySet()) ?: emptySet()
        val stars = prefs.getInt("total_stars", 0)
        val streak = prefs.getInt("current_streak", 1)
        val lastActive = prefs.getLong("last_active_time", 0L)

        // Basic streak update check
        var updatedStreak = streak
        val now = System.currentTimeMillis()
        if (lastActive > 0L) {
            val diffMs = now - lastActive
            val diffDays = diffMs / (1000 * 60 * 60 * 24)
            if (diffDays > 1) {
                // reset streak if more than 1 day missed
                updatedStreak = 1
            } else if (diffDays == 1L) {
                // increment streak if next day
                updatedStreak = streak + 1
            }
        }

        return UserProgress(
            completedLevelIds = completedIds.map { it.toInt() }.toSet(),
            completedUnitExamIds = completedExamIds.map { it.toInt() }.toSet(),
            totalStars = stars,
            currentStreak = updatedStreak,
            unlockedBadgeIds = badges,
            lastActiveTimestamp = if (lastActive == 0L) now else lastActive
        )
    }

    fun saveProgress(progress: UserProgress) {
        val now = System.currentTimeMillis()
        prefs.edit()
            .putStringSet("completed_levels", progress.completedLevelIds.map { it.toString() }.toSet())
            .putStringSet("completed_unit_exams", progress.completedUnitExamIds.map { it.toString() }.toSet())
            .putStringSet("unlocked_badges", progress.unlockedBadgeIds)
            .putInt("total_stars", progress.totalStars)
            .putInt("current_streak", progress.currentStreak)
            .putLong("last_active_time", now)
            .apply()
    }

    fun clearProgress() {
        prefs.edit().clear().apply()
    }
}
