package com.example.juniorandroiddevelopertask.data.mapper

import com.example.juniorandroiddevelopertask.data.local.GitHubRepoEntity
import com.example.juniorandroiddevelopertask.data.local.GitHubSavedRepoEntity
import com.example.juniorandroiddevelopertask.data.remote.dto.Item
import com.example.juniorandroiddevelopertask.domain.model.GitHubRepo

fun Item.toGithubEntity(): GitHubRepoEntity {

    return GitHubRepoEntity(
        open_issues_count = open_issues_count,
        forks_count = forks_count,
        stargazers_count = stargazers_count,
        description = description ?: "",
        repo_name = name,
        avatar_url = owner.avatar_url, login = owner.login,
        repo_id = id
    )

}

fun GitHubRepoEntity.toRep(): GitHubRepo {
    return GitHubRepo(
        issuesCount = open_issues_count,
        forksCount = forks_count,
        stargazersCount = stargazers_count,
        description = description,
        repoName = repo_name,
        avatarUrl = avatar_url,
        username = login,
        repoId = repo_id
    )


}

fun GitHubRepo.toGithubRepoEntity(): GitHubSavedRepoEntity {

    return GitHubSavedRepoEntity(
        open_issues_count = issuesCount,
        forks_count = forksCount,
        stargazers_count = stargazersCount,
        description = description ?: "",
        repo_name = repoName,
        isFav = isFav,
        avatar_url = avatarUrl, login = username,
        repo_id = repoId
    )

}


fun GitHubSavedRepoEntity.toGitHubRepo(): GitHubRepo {
    return GitHubRepo(
        issuesCount = open_issues_count,
        forksCount = forks_count,
        stargazersCount = stargazers_count,
        description = description,
        repoName = repo_name,
        avatarUrl = avatar_url,
        isFav = isFav,
        username = login,
        repoId = repo_id
    )


}