package com.example.jalzugaray.androidsqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jalzugaray.androidsqlite.entidades.Usuario;
import com.example.jalzugaray.androidsqlite.utilidades.Utilidades;

import java.util.ArrayList;

public class ConsultarListaListViewActivity extends AppCompatActivity {
    ListView listViewPersonas;
    ArrayList<String> listaInformacion;
    ArrayList<Usuario> listaUsuario;

    ConexionSQLiteHelper con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_lista_list_view);
        con = new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);

        listViewPersonas = (ListView)findViewById(R.id.listViewPersonas);

        consultarListaPersonas();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaInformacion);
        listViewPersonas.setAdapter(adaptador);

        listViewPersonas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String informacion = "id: "+listaUsuario.get(position).getId()+"\n";
                informacion+= "Nombre: "+listaUsuario.get(position).getNombre()+"\n";
                informacion+= "Telefono: "+listaUsuario.get(position).getTelefono()+"\n";

                Toast.makeText(getApplicationContext(),informacion,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void consultarListaPersonas() {
        SQLiteDatabase db = con.getReadableDatabase();

        Usuario usuario = null;
        listaUsuario = new ArrayList<Usuario>();
        //SELECT * FROM usuarios
        Cursor cursor = db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_USUARIO,null);

        while (cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));

            listaUsuario.add(usuario);
        }
        obtenerLista();
    }

    private void obtenerLista() {
        listaInformacion = new ArrayList<String>();
        for (int i =0; i<listaUsuario.size();i++){
            listaInformacion.add(listaUsuario.get(i).getId()+" - "+listaUsuario.get(i).getNombre());
        }
    }
}
