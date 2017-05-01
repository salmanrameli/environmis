package com.example.salmanrameli.db;

import android.provider.BaseColumns;

/**
 * Created by SalmanRameli on 4/29/17.
 */

public class ReportContract {
    public static final String DB_NAME = "reportDb";
    public static final int DB_VERSION = 2;

    public class ReportResultEntry implements BaseColumns
    {
        public static final String TABLE = "report_table";

        public static final String COL_LOCATION_NAME = "report_location";

        public static final String COL_MEASUREMENT_DATE = "report_date";

        public static final String COL_MEASUREMENT_RESULT = "report_result";

        public static final String COL_LOCATION_LATITUDE = "report_location_latitude";

        public static final String COL_LOCATION_LONGITUDE = "report_location_longitude";
    }
}
