package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.junit.Assert.assertEquals
import org.junit.Test

class StatisticsUtilsTest{

    //If there is no completed tasks and one active task, then it should show 100% active tasts and 0% completed tasks
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsZeroAndHundred() {
        // Create an active tasks (the false makes this active)
        val tasks = listOf(
                Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.completedTasksPercent )
        assertEquals(100f, result.activeTasksPercent )
    }

    //If there are 2 completed tasks and no active tasks, then it should show 100% complete and 0% active tasks
    @Test
    fun getActiveAndCompletedStats_allCompleted_returnsHundredAndZero() {
        val tasks = listOf(
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = true)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(100f, result.completedTasksPercent)
        assertEquals(0f, result.activeTasksPercent)
    }

    //If there are 2 completed and 3 active tasks, then it should show 60% active and 40% completed tasks
    @Test
    fun getActiveAndCompletedStats_2Completed3Active_returns40And60() {
        // Create an active tasks (the false makes this active)
        val tasks = listOf(
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = true),
                Task("title", "desc", isCompleted = false),
                Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)
    }

    //If there are no tasks, then it should show 0% for both
    @Test
    fun getActiveAndCompletedStats_noTasks_returnsZeroAndZero() {
        val tasks = listOf<Task>()

        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0.0f, result.completedTasksPercent )
        assertEquals(0.0f, result.activeTasksPercent )
    }

    //If list of task list is null, then it should show 0% for both
    @Test
    fun getActiveAndCompletedStats_nullTaskList_returnsZeroAndZero() {
        val tasks = null

        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0.0f, result.completedTasksPercent )
        assertEquals(0.0f, result.activeTasksPercent )
    }
}