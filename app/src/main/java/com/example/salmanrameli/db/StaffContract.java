package com.example.salmanrameli.db;

import android.provider.BaseColumns;

public class StaffContract {
    static final String DB_NAME = "staffDb";
    static final int DB_VERSION = 1;

    public class StaffEntry implements BaseColumns {
        public static final String TABLE = "staff_table";

        public static final String COL_STAFF_NAME = "staff_name";

        public static final String COL_STAFF_PASS = "staff_password";

        public static final String COL_STAFF_ROLE = "staff_role";
    }
}

