package com.example.jalzugaray.androidsqlite;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jalzugaray.androidsqlite.utilidades.Utilidades;

public class RegistroUsuariosActivity extends AppCompatActivity {
    EditText campoid, camponombre, campotelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuarios);

        campoid = (EditText)findViewById(R.id.campoId);
        camponombre = (EditText)findViewById(R.id.campoNombre);
        campotelefono = (EditText)findViewById(R.id.campoTelefono);
    }

    public void onClick(View view){
        registrarUsuarios();
        //registrarUsuariosSQL();
    }

    private void registrarUsuariosSQL() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = con.getWritableDatabase();
        String insert = "INSERT INTO "+Utilidades.TABLA_USUARIO
                + "("+Utilidades.CAMPO_ID+","+Utilidades.CAMPO_NOMBRE+","+Utilidades.CAMPO_TELEFONO+")" +
                " VALUES ("+campoid.getText().toString()+",'"+camponombre.getText().toString()+"','"+campotelefono.getText().toString()+"')";


        //insert into usuario (id,nombre,telefono) values (1,'Jose',5555555)
        db.execSQL(insert);

        db.close();
    }

    private void registrarUsuarios() {
        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
        SQLiteDatabase db = con.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID,campoid.getText().toString());
        values.put(Utilidades.CAMPO_NOMBRE,camponombre.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO,campotelefono.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_USUARIO,Utilidades.CAMPO_ID,values);
        Toast.makeText(getApplicationContext(),"Numero de Registro: " + idResultante,Toast.LENGTH_SHORT).show();
        db.close();

        campoid.setText("");
        camponombre.setText("");
        campotelefono.setText("");
    }
}
