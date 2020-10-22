package com.btxdev.multastransito.sqlite;


    public class TableMulta {

        public static final String table_name = "multa";
        public static final String col_id = "id";
        public static final String col_placa = "placa";
        public static final String col_modelo = "modelo";
        public static final String col_direccion_inf = "direccion_inf";
        public static final String col_tipo_comparendo = "tipo_comparendo";
        public static final String col_cedula_inf = "cedula_inf";

        public static final String create_query = "CREATE TABLE IF NOT EXISTS " + TableMulta.table_name + " ( " +
                TableMulta.col_id +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                TableMulta.col_placa + " TEXT," +
                TableMulta.col_modelo + " TEXT," +
                TableMulta.col_direccion_inf + " TEXT," +
                TableMulta.col_tipo_comparendo + " TEXT," +
                TableMulta.col_cedula_inf + " TEXT" +
                ");";


}
