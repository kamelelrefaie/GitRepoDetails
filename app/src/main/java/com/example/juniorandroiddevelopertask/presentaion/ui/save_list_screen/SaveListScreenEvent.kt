package com.example.juniorandroiddevelopertask.presentaion.ui.save_list_screen

sealed class SaveListScreenEvent {
    object LikedRepos : SaveListScreenEvent()
    object SavedRepos : SaveListScreenEvent()
    object LoadMore : SaveListScreenEvent()

}