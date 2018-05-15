package com.example.jalzugaray.androidsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper con = new ConexionSQLiteHelper(this,"bd_usuarios",null,1);
    }

    public void onClick(View view){
        Intent i = null;
        switch (view.getId()){
            case R.id.btnOpcionRegistro:
                i = new Intent(MainActivity.this,RegistroUsuariosActivity.class);
                break;
            case R.id.btnConsultaIndividual:
                i = new Intent(MainActivity.this,ConsultarUsuariosActivity.class);
                break;
            case R.id.btnConsultaSpinner:
                i = new Intent(MainActivity.this,ConsultaComboActivity.class);
                break;
            case R.id.btnConsultarListView:
                i = new Intent(MainActivity.this,ConsultarListaListViewActivity.class);
                break;
        }
        if (i!=null){
            startActivity(i);
        }
    }
}
