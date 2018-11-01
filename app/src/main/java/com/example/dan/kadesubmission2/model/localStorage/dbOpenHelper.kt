package com.example.dan.kadesubmission2.model.localStorage

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.dan.kadesubmission2.model.entity.Favorite
import com.example.dan.kadesubmission2.model.entity.FavoriteTeams
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables

        db.createTable(Favorite.TABLE_FAVORITE_EVENTS, true,
                Favorite.ID_FAVORITE to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.ID_EVENT to TEXT + UNIQUE,
                Favorite.HOME_ID to TEXT,
                Favorite.AWAY_ID to TEXT,
                Favorite.HOME_TEAM to TEXT,
                Favorite.AWAY_TEAM to TEXT,
                Favorite.HOME_SCORE to TEXT,
                Favorite.AWAY_SCORE to TEXT,
                Favorite.EVENT_DATE to TEXT)

        db.createTable(FavoriteTeams.TABLE_FAVORITE_TEAMS,true,
                FavoriteTeams.ID_FAVORITE_TEAMS to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.ID_TEAMS to TEXT + UNIQUE,
                FavoriteTeams.TEAM_NAME to TEXT,
                FavoriteTeams.TEAM_LOGO_URL to TEXT,
                FavoriteTeams.TEAM_STADIUM to TEXT,
                FavoriteTeams.TEAM_YEARS to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE_EVENTS, true)
        db.dropTable(FavoriteTeams.TABLE_FAVORITE_TEAMS, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)