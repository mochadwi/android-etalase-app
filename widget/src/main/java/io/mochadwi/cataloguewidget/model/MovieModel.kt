package io.mochadwi.cataloguewidget.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.content.Context
import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import io.mochadwi.cataloguewidget.utils.AppHelper
import kotlinx.android.parcel.Parcelize

@Entity(tableName = MovieModel.TABLE_NAME)
@Parcelize
data class MovieModel(
        var _id: Long = 0,
        @PrimaryKey
        @SerializedName("id")
        var movieId: Long = 0,
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
        var isFavourite: Boolean = false,
        var isClickedState: Boolean = false
) : Parcelable {

    override fun toString(): String {
        return Gson().toJson(this)
    }

    companion object {

        const val TABLE_NAME = "movies"
        const val COLUMN_ID = "movieId"

        /**
         * To create a movie list from external json file
         *
         * @param context  to receive context from activity / fragment
         * @param jsonFile the actual external json file
         * @return List of movie avaialable
         */
        @Throws(Exception::class)
        fun createMovieList(context: Context, jsonFile: String): List<MovieModel>? {
            val listType = object : TypeToken<BaseResponse<MovieModel>>() {

            }.type
            val result = Gson().fromJson<BaseResponse<MovieModel>>(
                    AppHelper.Strings.loadJSONFromAsset(context, jsonFile),
                    listType
            )

            return if (result != null) {
                result!!.results
            } else {
                null
            }
        }
    }
}