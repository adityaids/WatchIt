package com.aditya.watchit.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.aditya.watchit.data.source.local.entity.FavoritEntity.Companion.TABLE_NAME
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME)
class FavoritEntity(

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
    var banner: String,

    @NonNull
    @ColumnInfo
    var isFavorit: Boolean = false
):Parcelable{
    companion object {
        const val TABLE_NAME = "favorit"
        const val COLUMN_TITLE = "title"
        const val COLUMN_TYPE = "type"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_BANNER = "banner"
        const val COLUMN_IS_FAVORIT = "is_favorit"
    }
}