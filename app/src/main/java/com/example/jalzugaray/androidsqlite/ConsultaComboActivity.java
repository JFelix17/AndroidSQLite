package com.example.jalzugaray.androidsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jalzugaray.androidsqlite.entidades.Usuario;
import com.example.jalzugaray.androidsqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultaComboActivity extends AppCompatActivity {
    Spinner comboPersonas;
    TextView txtNombre, txtDocumento, txtTelefono;
    ArrayList<String> listaPersonas;
    ArrayList<Usuario> personasList;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_combo);
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        comboPersonas = (Spinner)findViewById(R.id.comboPersonas);

        txtNombre = (TextView)findViewById(R.id.txtNombre);
        txtDocumento = (TextView)findViewById(R.id.txtDocumento);
        txtTelefono = (TextView)findViewById(R.id.txtTelefono);

        consultarListaPersonas();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,android.R.layout.simple_spinner_item,listaPersonas);

        comboPersonas.setAdapter(adaptador);

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    txtDocumento.setText(personasList.get(position-1).getId().toString());
                    txtNombre.setText(personasList.get(position-1).getNombre());
                    txtTelefono.setText(personasList.get(position-1).getTelefono());
                }else {
                    txtDocumento.setText("");
                    txtNombre.setText("");
                    txtTelefono.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = con.getReadableDatabase();

        Usuario persona = null;
        personasList = new ArrayList<Usuario>();
        //SELECT * FROM usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            persona = new Usuario();
            persona.setId(cursor.getInt(0));
            persona.setNombre(cursor.getString(1));
            persona.setTelefono(cursor.getString(2));

            Log.i("id",persona.getId().toString());
            Log.i("Nombre",persona.getNombre().toString());
            Log.i("Tel",persona.getTelefono().toString());

            personasList.add(persona);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaPersonas = new ArrayList<String>();
        listaPersonas.add("Seleccione");

        for (int i = 0; i<personasList.size();i++){
            listaPersonas.add(personasList.get(i).getId()+" - "+personasList.get(i).getNombre());
        }
    }
}
