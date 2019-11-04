package io.mochadwi.cataloguefavourite.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.provider.BaseColumns
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = MovieModel.TABLE_NAME)
data class MovieModel(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(index = true, name = COLUMN_ID)
        @SerializedName("id")
        var id: Long = 0,
        @SerializedName("overview")
        var overview: String? = null,
        @SerializedName("original_language")
        var originalLanguage: String? = null,
        @SerializedName("original_title")
        var originalTitle: String? = null,
        @SerializedName("video")
        var isVideo: Boolean = false,
        @SerializedName("title")
        var title: String? = null,
        @Ignore
        @SerializedName("genre_ids")
        var genreIds: List<Int>? = null,
        @SerializedName("poster_path")
        var posterPath: String? = null,
        @SerializedName("backdrop_path")
        var backdropPath: String? = null,
        @SerializedName("release_date")
        var releaseDate: String? = null,
        @SerializedName("vote_average")
        var voteAverage: Double = 0.toDouble(),
        @SerializedName("popularity")
        var popularity: Double = 0.toDouble(),
        @SerializedName("adult")
        var isAdult: Boolean = false,
        @SerializedName("vote_count")
        var voteCount: Int = 0,
        var isFavourite: Boolean? = false
) : Parcelable {
    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {

        const val TABLE_NAME = "movies"
        const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_TITLE = "title"
    }
}