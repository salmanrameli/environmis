package com.example.salmanrameli.db;

import android.provider.BaseColumns;

public class AssignTaskContract {
    static final String DB_NAME = "assignTaskDb";

    static final int DB_VERSION = 1;

    public class AssignTaskEntry implements BaseColumns {
        public static final String TABLE = "assign_task_table";

        public static final String COL_LOCATION_LATITUDE = "location_latitude";

        public static final String COL_LOCATION_LONGITUDE = "location_longitude";

        public static final String COL_STAFF_ID = "staff_id";

        public static final String COL_IS_DONE = "is_done";
    }
}
