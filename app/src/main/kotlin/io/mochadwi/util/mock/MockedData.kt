package io.mochadwi.util.mock

import io.mochadwi.data.datasource.local.room.MovieEntity
import io.mochadwi.data.datasource.network.kotlinx.response.movie.MovieResponse
import io.mochadwi.data.mapper.MovieEntityMapper
import io.mochadwi.data.mapper.MovieResultMapper
import io.mochadwi.util.ext.fromJson
import kotlinx.serialization.list

/**
 * Mock Github Data
 */
// TODO(mochamadiqbaldwicahyo): 2019-08-26 Make this class inside data layer -> mock package
object MockedData {

    val mockMoviesResponse = """
[
    {
      "vote_count": 417,
      "id": 429203,
      "video": false,
      "vote_average": 6.4,
      "title": "The Old Man & the Gun",
      "popularity": 304.393,
      "poster_path": "/a4BfxRK8dBgbQqbRxPs8kmLd8LG.jpg",
      "original_language": "en",
      "original_title": "The Old Man & the Gun",
      "genre_ids": [
        35,
        80,
        18
      ],
      "backdrop_path": "/8bRIfPGDnmWgdy65LO8xtdcFmFP.jpg",
      "adult": false,
      "overview": "The true story of Forrest Tucker, from his audacious escape from San Quentin at the age of 70 to an unprecedented string of heists that confounded authorities and enchanted the public. Wrapped up in the pursuit are a detective, who becomes captivated with Forrest’s commitment to his craft, and a woman, who loves him in spite of his chosen profession.",
      "release_date": "2018-09-27"
    },
    {
      "vote_count": 1997,
      "id": 420818,
      "video": false,
      "vote_average": 7.2,
      "title": "The Lion King",
      "popularity": 221.987,
      "poster_path": "/2bXbqYdUdNVa8VIWXVfclP2ICtT.jpg",
      "original_language": "en",
      "original_title": "The Lion King",
      "genre_ids": [
        12,
        16,
        10751,
        18,
        28
      ],
      "backdrop_path": "/1TUg5pO1VZ4B0Q1amk3OlXvlpXV.jpg",
      "adult": false,
      "overview": "Simba idolises his father, King Mufasa, and takes to heart his own royal destiny. But not everyone in the kingdom celebrates the new cub's arrival. Scar, Mufasa's brother—and former heir to the throne—has plans of his own. The battle for Pride Rock is ravaged with betrayal, tragedy and drama, ultimately resulting in Simba's exile. With help from a curious pair of newfound friends, Simba will have to figure out how to grow up and take back what is rightfully his.",
      "release_date": "2019-07-12"
    }
]
    """.trimIndent().fromJson(MovieResponse.serializer().list)

    val mockMoviesModel = MovieResultMapper.from(mockMoviesResponse)
    val mockMoviesEntity = MovieEntityMapper.from<MovieResponse, MovieEntity>(mockMoviesResponse)
}