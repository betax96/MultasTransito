package com.btxdev.multastransito.sqlite;


    public class TableMulta {

        public static final String table_name = "multa";
        public static final String col_id = "rowid";
        public static final String col_placa = "placa";
        public static final String col_modelo = "modelo";
        public static final String col_direccion_inf = "direccion_inf";
        public static final String col_tipo_comparendo = "tipo_comparendo";
        public static final String col_cedula_inf = "cedula_inf";

        public static final String create_query = "CREATE TABLE IF NOT EXISTS " + TableMulta.table_name + " ( " +
                TableMulta.col_placa + " text," +
                TableMulta.col_modelo + " text," +
                TableMulta.col_direccion_inf + " text" +
                TableMulta.col_tipo_comparendo + " text" +
                TableMulta.col_cedula_inf + " text" +
                ");";


}
