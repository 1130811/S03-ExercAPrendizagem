package com.example.laptop.s03_exercaprendizagem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> listaStudents = new ArrayList<>();
    ArrayAdapter<String> adapter;

    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] students = getResources().getStringArray(R.array.students);
        Collections.addAll(listaStudents, students);
        lv = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaStudents);
        lv.setAdapter(adapter);
        registerForContextMenu(lv);
    }

    /**
     * CONTEXT MENU
     **/
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        long id = info.id;
        String str = lv.getItemAtPosition(pos).toString();
        switch (item.getItemId()) {
            case R.id.edit:
                Intent i = new Intent(this, StudentEditionActivity.class);
                i.putExtra("NOME", str);
                i.putExtra("POSICAO", pos);
                startActivityForResult(i, 2); //a activity de edição/apagar está identificado com 2 no metodo onActivityResult

                return true;

            default:
                return super.onContextItemSelected(item);
        }
    }


    /***
     * OPTION MENU
     ****/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu ; this adds items to the action bar if it is present .
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here . The action bar will
// automatically handle clicks on the Home /Up button , so long
// as you specify a parent activity in AndroidManifest . xml .
        int id = item.getItemId();
// noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.Prefs:
                Toast.makeText(getApplicationContext(), "Favorite", Toast.LENGTH_LONG).show();
                return true;
            case R.id.Add:
                Intent i = new Intent(this, AddStudentActivity.class);
                startActivityForResult(i, 1);//a activity de edição/apagar está identificado com 1 no método onActivityResult
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    /**
     * Metodo que vai receber o novo nome a introduzir no Array
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//neste caso não preciso/vou analisar o resultCode
        if (data != null) {
            switch (requestCode) {
                case 1:

                    String msg = data.getStringExtra("EXTRA_MESSAGE");
                    listaStudents.add(msg);
                    adapter.notifyDataSetChanged();//faz refresh à listView
                    break;

                case 2:


                    int posicao = data.getIntExtra("POSICAO", -1);//se quiser eliminar ca -1
                    String acao = data.getStringExtra("ACAO");

                    if (posicao!=-1){
                        if(acao.equalsIgnoreCase("editado")){
                            String novoNome = data.getStringExtra("NOVO_NOME");
                            listaStudents.set(posicao,novoNome);
                            adapter.notifyDataSetChanged();//faz refresh à listView
                            Toast.makeText(this, "Alterado nome para: " + novoNome + "!!", Toast.LENGTH_SHORT).show();
                            break;
                        }else{
                            String nomeAEliminar = listaStudents.get(posicao);
                            listaStudents.remove(posicao);
                            Toast.makeText(this, " Estudante: " + nomeAEliminar + " eliminado!!", Toast.LENGTH_LONG).show();
                            break;
                        }
                    }
                                }
        }
    }
}
