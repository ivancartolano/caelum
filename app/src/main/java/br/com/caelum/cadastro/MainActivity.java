package br.com.caelum.cadastro;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] alunos = {"Natalia", "Patricia", "Vanessa"};
        ListView lista = (ListView) findViewById(R.id.listaAlunos);
        lista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,alunos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Vc clicou na posicao" + " "+position, Toast.LENGTH_LONG).show();
            }
        });
        registerForContextMenu(lista);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//                alert.setTitle("vc clicou longamente");
//                alert.setMessage((String) parent.getItemAtPosition(position));
//                alert.setNeutralButton("OK",null);
//                alert.show();
                return false;
            }
        });

        Button flutuante= (Button) findViewById(R.id.lista_alunos_floating_button);
        flutuante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "flutuante",Toast.LENGTH_SHORT).show();
                Intent novo = new Intent(MainActivity.this, FormularioActivity.class);
                startActivity(novo);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("MeuMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("outro menu");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
