package com.example.jalzugaray.androidsqlite.utilidades;

/**
 * Created by jalzugaray on 15/05/2018.
 */

public class Utilidades {

    public static final String TABLA_USUARIO = "usuarios";
    public static final String CAMPO_ID = "id";
    public static final String CAMPO_NOMBRE = "nombre";
    public static final String CAMPO_TELEFONO = "telefono";

    public static final String CREAR_TABLA_USUARIOS="CREATE TABLE "+TABLA_USUARIO+" ("+CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_TELEFONO+" TEXT)";
}
