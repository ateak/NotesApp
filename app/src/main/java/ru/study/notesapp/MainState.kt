package ru.study.notesapp

import kotlinx.coroutines.flow.Flow

/**
 * Состояние списка заметок на главном экране
 */
data class MainState(val noteList: Flow<List<Note>>)