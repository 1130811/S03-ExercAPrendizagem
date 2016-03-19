package com.example.laptop.s03_exercaprendizagem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by laptop on 19/03/2016.
 */
public class StudentEditionActivity extends AppCompatActivity {

    int pos;
    String estudante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studet_edition);


        final EditText eText = (EditText) findViewById(R.id.studenteditionText);
        Intent intent = this.getIntent();
        pos = intent.getIntExtra("POSICAO", -1); //caso nao haja considera -1
        estudante=intent.getStringExtra("NOME");

        eText.setText(estudante);
        //BOTAO OK
        Button bOk = (Button) findViewById(R.id.btnGuardaEdicao);

        bOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ler do ediText

                //intent e retorno
                Intent i = new Intent();
                i.putExtra("ACAO","editado");
                i.putExtra("NOVO_NOME",String.valueOf(eText.getText()));
                i.putExtra("POSICAO",pos);
                setResult(2, i);
                finish();
            }
        });
        //BOTAO CANCELAR
        Button bCancelar = (Button) findViewById(R.id.btnApagaEstudante);

        bCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("ACAO","eliminado");
                i.putExtra("POSICAO",pos);
                setResult(2, i);
                finish();
            }
        });


    }
}
