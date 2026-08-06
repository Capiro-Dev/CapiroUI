package com.capiro.composables.model

import androidx.compose.runtime.MutableState

data class SyncItem (
    val messageDisplayed : MutableState<Int>,
    val syncState: MutableState<SyncStatus>)


sealed class SyncStatus {
    class IsDone : SyncStatus()
    class Error : SyncStatus()
    class IsLoading : SyncStatus()
}