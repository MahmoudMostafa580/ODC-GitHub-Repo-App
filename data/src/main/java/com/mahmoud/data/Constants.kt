package com.mahmoud.data

class Constants {
    companion object {


        // keys
        const val OWNER_KEY = "owner"
        const val REPO_NAME_KEY = "repo"

        const val BASE_URL = "https://api.github.com/"
        const val GITHUB_REPOS_ENDPOINT = "search/repositories?q=language"
        const val GITHUB_REPOS_ISSUES_ENDPOINT = "repos/{$OWNER_KEY}/{$REPO_NAME_KEY}/issues"
        const val GITHUB_REPOS_SEARCH_ENDPOINT = "search/repositories"

        // room
        const val GITHUB_REPOSITORIES_TABLE = "github_repositories_table"

        // data store
        const val PREFERENCES_NAME = "trending_preference"
        const val PREFERENCES_IS_FIRST_TIME = "is_first_time"



    }
}