package com.aditya.watchit.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.watchit.data.source.local.entity.FilmEntity.Companion.COLUMN_TITLE
import com.aditya.watchit.data.source.local.entity.FilmEntity.Companion.COLUMN_TYPE
import com.aditya.watchit.data.source.local.entity.FilmEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME, primaryKeys = [COLUMN_TITLE, COLUMN_TYPE])
class FilmEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = COLUMN_TITLE)
    var title: String,

    @NonNull
    @ColumnInfo(name = COLUMN_TYPE)
    var type: String,

    @NonNull
    @ColumnInfo(name = COLUMN_DESCRIPTION)
    var description: String,

    @NonNull
    @ColumnInfo(name = COLUMN_BANNER)
    var banner: String
) {
    companion object {
        const val TABLE_NAME = "film"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TYPE = "type"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_BANNER = "banner"
    }
}