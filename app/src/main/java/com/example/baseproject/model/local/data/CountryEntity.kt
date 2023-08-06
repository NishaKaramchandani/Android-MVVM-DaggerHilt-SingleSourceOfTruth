package com.example.baseproject.model.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class to represent country from the database/local data source layer
 */
@Entity(tableName = "countries")
data class CountryEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val name: String,
    val capital: String,
    val code: String,
    val region: String
)