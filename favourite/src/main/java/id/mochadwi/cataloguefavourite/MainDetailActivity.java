package io.mochadwi.cataloguefavourite;

/**
 * Created by mochadwi on 6/26/18.
 */

//public class MainDetailActivity extends AppCompatActivity {
//
//    // Store movie data here
//    private MovieModel mMovieModel;
//
//    // Setup views here
//    @BindView(R.id.tv_detail_movietitle) TextView mTvMovieTitle;
//    @BindView(R.id.iv_detail_moviephoto) ImageView mIvMoviePhoto;
//    @BindView(R.id.iv_detail_moviefavourite) ImageView mIvMovieFavourite;
//    @BindView(R.id.tv_detail_movidescription) TextView mTvMovieDescription;
//
//    /**
//     * @param savedInstanceState
//     */
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_detail_activity);
//        ButterKnife.bind(this);
//
//        ActionBar ab = getSupportActionBar();
//        if (ab != null) {
//            ab.setDisplayHomeAsUpEnabled(true);
//
//            if (getIntent().getStringExtra(MOVIE_DATA) != null) {
//                mMovieModel = setupData(getIntent().getStringExtra(MOVIE_DATA));
//
//                ab.setTitle(mMovieModel.getTitle());
//                GlideApp.with(mIvMoviePhoto)
//                        .load(BuildConfig.IMAGE_URL + mMovieModel.getPosterPath())
//                        .override(100, 200)
//                        .into(mIvMoviePhoto);
//                mTvMovieTitle.setText(mMovieModel.getTitle() +
//                        " (" + mMovieModel.getReleaseDate().substring(0, 4) + ")");
//                mTvMovieDescription.setText(mMovieModel.getOverview());
//
//                setupFavourite((mMovieModel.isFavourite() ?
//                        R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp));
//            } else {
//                // TODO: handle null movie item here
//            }
//        }
//    }
//
//    // TODO: Handle update / insert for favourite item
//    @OnClick(R.id.iv_detail_moviefavourite)
//    public void clickToFavourite() {
//        if (BuildConfig.DEBUG) {
//            Log.d("DASHBOARDDETAIL", URI_MOVIE.toString());
//        }
//
//        if (mMovieModel.isFavourite()) {
//            setupFavourite(R.drawable.ic_favorite_border_black_24dp);
//            mMovieModel.setFavourite(false);
//            getContentResolver().insert(URI_MOVIE, toContentValues(mMovieModel));
//        } else {
//            setupFavourite(R.drawable.ic_favorite_black_24dp);
//            mMovieModel.setFavourite(true);
//            getContentResolver().insert(URI_MOVIE, toContentValues(mMovieModel));
//        }
//    }
//
//    private void setupFavourite(@DrawableRes int image) {
//        GlideApp.with(MainDetailActivity.this)
//                .load(image)
//                .into(mIvMovieFavourite);
//    }
//
//    private MovieModel setupData(String intentData) {
//        return new Gson().fromJson(intentData, MovieModel.class);
//    }
//
//    // TODO: Setup detail data to get from local DB (through content provider)
//    private MovieModel setupDataById(long id) {
////        return getContentResolver().query(URI_MOVIE, )
//        return null;
//    }
//
//    // TODO: Implement background thread queries?
//    private void insertFavouriteMovie(final MovieModel movie) {
////        new AsyncTask<Void, Void, Void>() {
////            @Override
////            protected Void doInBackground(Void... voids) {
////                CatalogueApplication.get().getDB().movieDao().insert(movie);
////            }
////        }.execute();
//    }
//}
