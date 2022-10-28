package com.example.juniorandroiddevelopertask.data.mapper

import com.example.juniorandroiddevelopertask.data.local.GithubRepoEntity
import com.example.juniorandroiddevelopertask.data.remote.dto.GitHubDto
import com.example.juniorandroiddevelopertask.data.remote.dto.Item
import com.example.juniorandroiddevelopertask.domain.model.Repo

fun Item.toGithubEntity(): GithubRepoEntity {

    return GithubRepoEntity(
        open_issues_count = open_issues_count,
        forks_count = forks_count,
        stargazers_count = stargazers_count,
        description = description ?: "",
        repo_name = name,
        avatar_url = owner.avatar_url, login = owner.login,
        repo_id = id
    )

}

fun GithubRepoEntity.toRep(): Repo {
    return Repo(
        issuesCount = open_issues_count,
        forksCount = forks_count,
        stargazersCount = stargazers_count,
        description = description,
        repoName = repo_name,
        avatarUrl = avatar_url,
        username = login,
        isFav = isFav,
        repoId = repo_id
    )


}

fun Repo.toGithubRepoEntity(): GithubRepoEntity {

    return GithubRepoEntity(
        open_issues_count = issuesCount,
        forks_count = forksCount,
        stargazers_count = stargazersCount,
        description = description ?: "",
        repo_name = repoName,
        avatar_url = avatarUrl, login = username,
        isFav = isFav,
        repo_id = repoId

    )

}