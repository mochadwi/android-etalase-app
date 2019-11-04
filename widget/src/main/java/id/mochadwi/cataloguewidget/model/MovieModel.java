package io.mochadwi.cataloguewidget.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.mochadwi.cataloguewidget.utils.AppHelper;

@Entity(tableName = MovieModel.TABLE_NAME)
public class MovieModel implements Parcelable {

    public static final String TABLE_NAME = "movies";
    public static final String COLUMN_ID = "movieId";
    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel source) {
            return new MovieModel(source);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };
    private long _id;
    @PrimaryKey
    @SerializedName("id")
    private long movieId;
    @SerializedName("overview")
    private String overview;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("video")
    private boolean video;
    @SerializedName("title")
    private String title;
    @Ignore
    @SerializedName("genre_ids")
    private List<Integer> genreIds;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("vote_average")
    private double voteAverage;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("adult")
    private boolean adult;
    @SerializedName("vote_count")
    private int voteCount;
    private boolean favourite = false;
    private boolean clickedState = false;

    public MovieModel() {
    }

    public MovieModel(long movieId, String overview, String originalLanguage, String originalTitle,
                      boolean video, String title, List<Integer> genreIds, String posterPath,
                      String backdropPath, String releaseDate, double voteAverage, double popularity,
                      boolean adult, int voteCount, boolean favourite, boolean clickedState) {
        this.movieId = movieId;
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.video = video;
        this.title = title;
        this.genreIds = genreIds;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.popularity = popularity;
        this.adult = adult;
        this.voteCount = voteCount;
        this.favourite = favourite;
        this.clickedState = clickedState;
    }

    protected MovieModel(Parcel in) {
        this._id = in.readLong();
        this.movieId = in.readLong();
        this.overview = in.readString();
        this.originalLanguage = in.readString();
        this.originalTitle = in.readString();
        this.video = in.readByte() != 0;
        this.title = in.readString();
        this.genreIds = new ArrayList<Integer>();
        in.readList(this.genreIds, Integer.class.getClassLoader());
        this.posterPath = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.voteAverage = in.readDouble();
        this.popularity = in.readDouble();
        this.adult = in.readByte() != 0;
        this.voteCount = in.readInt();
        this.favourite = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.clickedState = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    /**
     * To create a movie list from external json file
     *
     * @param context  to receive context from activity / fragment
     * @param jsonFile the actual external json file
     * @return List of movie avaialable
     */
    public static List<MovieModel> createMovieList(Context context, String jsonFile) throws Exception {
        Type listType = new TypeToken<BaseResponse<MovieModel>>() {
        }.getType();
        BaseResponse<MovieModel> result = new Gson().fromJson(
                AppHelper.Strings.loadJSONFromAsset(context, jsonFile),
                listType
        );

        if (result != null) {
            return result.getResults();
        } else {
            return null;
        }
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }

    public boolean isClickedState() {
        return clickedState;
    }

    public void setClickedState(boolean clickedState) {
        this.clickedState = clickedState;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this._id);
        dest.writeLong(this.movieId);
        dest.writeString(this.overview);
        dest.writeString(this.originalLanguage);
        dest.writeString(this.originalTitle);
        dest.writeByte(this.video ? (byte) 1 : (byte) 0);
        dest.writeString(this.title);
        dest.writeList(this.genreIds);
        dest.writeString(this.posterPath);
        dest.writeString(this.backdropPath);
        dest.writeString(this.releaseDate);
        dest.writeDouble(this.voteAverage);
        dest.writeDouble(this.popularity);
        dest.writeByte(this.adult ? (byte) 1 : (byte) 0);
        dest.writeInt(this.voteCount);
        dest.writeValue(this.favourite);
        dest.writeValue(this.isClickedState());
    }
}