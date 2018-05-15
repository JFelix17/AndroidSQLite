package com.example.jalzugaray.androidsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jalzugaray.androidsqlite.utilidades.Utilidades;

public class ConsultarUsuariosActivity extends AppCompatActivity {
    EditText campoid, camponombre, campotelefono;
    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuarios);

        con = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        campoid = (EditText)findViewById(R.id.documentoId);
        camponombre = (EditText)findViewById(R.id.campoNombreConsulta);
        campotelefono = (EditText)findViewById(R.id.campoTelefonoConsulta);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnConsultar:
                consultar();
                break;
            case R.id.btnActualizar:
                actualizarUsuario();
                break;
            case R.id.btnEliminar:
                eliminarUsuario();
                break;
        }
    }

    private void consultar() {
        SQLiteDatabase db = con.getReadableDatabase();
        String[] parametros = {campoid.getText().toString()};
        String[] campos = {Utilidades.CAMPO_NOMBRE,Utilidades.CAMPO_TELEFONO};
        try {
            Cursor cursor = db.query(Utilidades.TABLA_USUARIO,campos,Utilidades.CAMPO_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            camponombre.setText(cursor.getString(0));
            campotelefono.setText(cursor.getString(1));
            cursor.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Usuario no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void actualizarUsuario() {
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {campoid.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_NOMBRE,camponombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campotelefono.getText().toString());

        db.update(Utilidades.TABLA_USUARIO,values,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Datos Actualizados Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        limpiar();
    }

    private void eliminarUsuario(){
        SQLiteDatabase db = con.getWritableDatabase();
        String[] parametros = {campoid.getText().toString()};

        db.delete(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Datos Eliminados Correctamente",Toast.LENGTH_LONG).show();
        db.close();
        campoid.setText("");
        limpiar();
    }

    private void limpiar() {
        camponombre.setText("");
        campotelefono.setText("");
    }
}
