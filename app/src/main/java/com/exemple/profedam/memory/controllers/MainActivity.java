package com.exemple.profedam.memory.controllers;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import com.exemple.profedam.memory.R;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * De este on create viene to do porque basicamente llama al menu
     * y se hace el listener del boton
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCallActivity = (Button) findViewById(R.id.btnEmpezar);
        btnCallActivity.setOnClickListener(this);
    }

    /**
     * En este onclick veremos como se va a un intent y dependiendo que opcion se pase por parametro
     * hará una cosa u otra
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnEmpezar) {
            boolean salir = false;
            Intent intent = new Intent(this, Joc.class);
            RadioGroup rg = (RadioGroup) findViewById(R.id.radioGrupo);


            switch (rg.getCheckedRadioButtonId()) {
                case R.id.rbGodMode: {
                    intent.putExtra("dificultad", 15);
                    salir = true;
                    break;
                }
                case R.id.rbFacil: {
                    intent.putExtra("dificultad", 8);
                    salir = true;
                    break;
                }
                case R.id.rbMedio: {
                    intent.putExtra("dificultad", 12);
                    salir = true;
                    break;
                }

            }
            startActivity(intent);
            if (salir) {
                finish();
            }
        }
    }
}
