package com.futuretraxex.statushub.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.futuretraxex.statushub.Utility.Utility;

/**
 * Created by hudelabs on 10/24/2015.
 */
public class StatusHubContract {
    //Content authority to be register by provider.
    public static final String CONTENT_AUTHORITY = "com.futuretraxex.statushub.provider";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);


    //Possible paths appended to BASE_CONTENT_URI.
    public static final String PATH_USERS = "users";

    public static final String PATH_USERS_ALL = "users_all";

    public static final String PATH_USERS_FAVOURITES = "favourites";

    public static final String PATH_USERS_WEIGHT = "weight";

    public static final String PATH_USERS_HEIGHT = "height";

    public static final String PATH_USERS_ETHNICITY = "ethnicity";




    public static final class UsersSchema implements BaseColumns   {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USERS).build();

        //For multiple items.
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;
        //For single item.
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USERS;

        public static final String TABLE_NAME = "users";

        public static final String COLUMN_TABLE_DOB = "dob";
        public static final String COLUMN_TABLE_STATUS = "status";
        public static final String COLUMN_TABLE_ETHNICITY = "ethnicity";
        public static final String COLUMN_TABLE_WEIGHT = "weight";
        public static final String COLUMN_TABLE_HEIGHT = "height";
        public static final String COLUMN_TABLE_IS_VEG = "is_veg";
        public static final String COLUMN_TABLE_DRINK = "drink";
        public static final String COLUMN_TABLE_IMAGE = "image";
        public static final String COLUMN_IS_FAVOURITE = "is_favourite";

        public static final String SQL_CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME + "(\n" +
                "\t`"+ _ID  +"`\tINTEGER NOT NULL PRIMARY KEY,\n" +
                "\t`" + COLUMN_TABLE_DOB + "`\tTEXT NOT NULL,\n" +
                "\t`" + COLUMN_TABLE_STATUS + "`\tTEXT NOT NULL,\n" +
                "\t`" + COLUMN_TABLE_ETHNICITY + "`\tINTEGER NOT NULL,\n" +
                "\t`"+ COLUMN_TABLE_WEIGHT +"`\tLONG NOT NULL,\n" +
                "\t`"+ COLUMN_TABLE_HEIGHT +"`\tINTEGER NOT NULL,\n" +
                "\t`"+ COLUMN_TABLE_IS_VEG +"`\tINTEGER NOT NULL\n" +
                "\t`"+ COLUMN_TABLE_DRINK +"`\tINTEGER NOT NULL\n" +
                "\t`"+ COLUMN_TABLE_IMAGE +"`\tTEXT\n" +
                "\t`"+ COLUMN_IS_FAVOURITE +"`\tINTEGER NOT NULL\n" +
                ")";



        public static final String SELECT_BY_USER_ID = _ID + " = ?";
        public static final String SELECT_BY_WEIGHT_SORT = "ORDER BY weight ASC";
        public static final String SELECT_BY_HEIGHT_SORT = _ID + "ORDER BY height DESC";
        public static final String SELECT_BY_WEIGHT_FILTER = _ID + "WHERE weight <= ?";
        public static final String SELECT_BY_HEIGHT_FILTER = _ID + "WHERE height >= ?";
        public static final String SELECT_BY_ETHNICITY_FILTER = _ID + "WHERE ethnicity = ?";
        public static final String SELECT_BY_FAVOURITES_FILTER = _ID + "WHERE FAVOURITES = 1";

        //below build Uris are working, See TestUriGenerator.java for detailed Tests.

        //Build Uri with id.
        public static Uri buildUsersUriWithId(long id)   {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //Build Uri with Favourites.
        public static Uri buildUsersUriWithFavourites() {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_FAVOURITES)
                    .build();
        }

        //Build Uri with Weight Sort Filter : Sorts from low to high ASC ?.
        public static Uri buildUsersUriWithWeightSortFilter()   {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_WEIGHT)
                    .build();
        }

        //Build Uri with Height Sort Filter : Sorts from high to low DESC ?.
        public static Uri buildUsersUriWithHeightSortFilter()   {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_HEIGHT)
                    .build();
        }

        //Build Uri with Weight Sort Filter : Sorts from high to low using given weight ?.
        public static Uri buildUsersUriWithWeightFilter(long weight)   {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_WEIGHT)
                    .appendEncodedPath(String.valueOf(weight))
                    .build();
        }

        //Build Uri with Height Sort Filter : Sorts from low to high using given height ?.
        public static Uri buildUsersUriWithHeightFilter(int height)   {
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_HEIGHT)
                    .appendEncodedPath(String.valueOf(height))
                    .build();
        }

        //Build Uri with Ethnicity Filter : Gets users belonging to X ethnicity.
        public static Uri buildUsersWithEthnicityFilter(String ethnicity)   {
            String ethnic = String.valueOf(Utility.getIdFromEthnicity(ethnicity));
            return CONTENT_URI.buildUpon()
                    .appendEncodedPath(PATH_USERS)
                    .appendEncodedPath(PATH_USERS_ETHNICITY)
                    .appendEncodedPath(ethnic)
                    .build();
        }

        //Get users id from Uri of the form <content_authority>/users/3
        //returns 3
        public static String getUserIdFromUsersByIdUri(Uri uri) {
            return uri.getPathSegments().get(1);
        }

        //Below 3 functions can be combined in to a single generic function
        //do it shall we ?
        //less readability over , space
        //space I choose you ! gotta catch'em all.
        //comment out what you need.

//        public static String getUserWeightFromUsersWeightFilterUri(Uri uri) {
//            return uri.getPathSegments().get(2);
//        }
//
//        public static String getUserHeightFromUsersHeightFilterUri(Uri uri) {
//            return uri.getPathSegments().get(2);
//        }
//
//        public static String getUserEthnicityFromUsersEthnicityFilterUri(Uri uri) {
//            return uri.getPathSegments().get(2);
//        }

        //Get users id from Uri of the form
        // - > <content_authority>/height/170
        // - > <content_authority>/weight/3000
        // - > <content_authority>/ethnicity/3
        //respectively returns
        //170
        //3000
        //3
        public static String getGenericFilterValueFromUsersUri(Uri uri)  {
            return uri.getPathSegments().get(2);
        }


    }

}
