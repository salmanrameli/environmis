package com.example.salmanrameli.db;

import android.provider.BaseColumns;

public class ReportContract {
    static final String DB_NAME = "reportDb";

    static final int DB_VERSION = 3;

    public class ReportResultEntry implements BaseColumns
    {
        public static final String TABLE = "report_table";

        public static final String COL_MEASUREMENT_DATE = "report_date";

        public static final String COL_MEASUREMENT_RESULT = "report_result";

        public static final String COL_LOCATION_LATITUDE = "report_location_latitude";

        public static final String COL_LOCATION_LONGITUDE = "report_location_longitude";

        public static final String COL_MEASUREMENT_STAFF_ID = "report_staff_id";
    }
}
