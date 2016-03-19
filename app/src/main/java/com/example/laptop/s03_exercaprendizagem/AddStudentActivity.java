package com.example.laptop.s03_exercaprendizagem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by laptop on 15/03/2016.
 */
public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);


        /***BOTAO OK**/
        Button bOk = (Button) findViewById(R.id.btnAdicNovo);
        assert bOk != null;
        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ler do ediText

                EditText edT = (EditText) findViewById(R.id.novoNome);
                String novoNome = edT.getText().toString();

                //intent e retorno
                Intent i = new Intent();
                i.putExtra("EXTRA_MESSAGE", novoNome);
                setResult(1,i);
                finish();
            }
        });
        /***BOTAO CANCELAR**/
        Button bCancelar = (Button) findViewById(R.id.btnCancelarNovo);
        assert bCancelar != null;
        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //setResult(2, i);
                finish();
            }
        });
    }


}
