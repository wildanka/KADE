package com.example.dan.kadesubmission2.model.entity

data class FavoriteTeams(val id: Long?,
                    val idTeams: String?,
                    val teamName: String?,
                    val teamLogoUrl: String?,
                    val teamStadium: String?,
                    val teamYears: String?) {

    companion object {
        const val TABLE_FAVORITE_TEAMS: String = "TABLE_FAVORITE_TEAMS"
        const val ID_FAVORITE_TEAMS: String = "ID_FAVORITE_TEAMS"
        const val ID_TEAMS: String = "ID_TEAMS"
        const val TEAM_NAME : String = "TEAM_NAME"
        const val TEAM_LOGO_URL : String = "TEAM_LOGO_URL"
        const val TEAM_STADIUM : String = "TEAM_STADIUM"
        const val TEAM_YEARS : String = "TEAM_YEARS"

    }
}