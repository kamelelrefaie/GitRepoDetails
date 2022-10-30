package com.example.juniorandroiddevelopertask.presentaion.ui.details_screen

sealed class DetailsScreenEvent {
    object OnSavedBtnClicked : DetailsScreenEvent()
    object OnUndoBtnClicked : DetailsScreenEvent()

    object OnLovedBtnClicked : DetailsScreenEvent()
    object OnUnLovedBtnClicked : DetailsScreenEvent()
}
