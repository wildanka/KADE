package com.example.dan.kadesubmission2.model.entity

data class Favorite(val id: Long?,
                    val idEvent: String?,
                    val homeID: String?,
                    val awayID: String?,
                    val homeTeam: String?,
                    val awayTeam: String?,
                    val homeScore: String?,
                    val awayScore: String?,
                    val eventDate: String?,
                    val eventTime: String?) {

    companion object {
        const val TABLE_FAVORITE_EVENTS: String = "TABLE_FAVORITE_EVENTS"
        const val ID_FAVORITE: String = "ID_FAVORITE"
        const val ID_EVENT: String = "ID_EVENT"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val EVENT_TIME: String = "EVENT_TIME"
    }
}

