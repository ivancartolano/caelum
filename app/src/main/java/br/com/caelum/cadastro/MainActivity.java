package br.com.caelum.cadastro;

import android.content.Intent;
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

import java.util.List;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        String[] alunos = {"Natalia", "Patricia", "Vanessa"};
        final ListView lista = (ListView) findViewById(R.id.listaAlunos);
//        lista.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,alunos));

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edicao = new Intent(MainActivity.this, FormularioActivity.class);
                edicao.putExtra("alunoSelecionado", (Aluno) lista.getItemAtPosition(position));
                startActivity(edicao);
            }
        });
        registerForContextMenu(lista);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
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

    public void carregaLista(){
        ListView lista = (ListView) findViewById(R.id.listaAlunos);
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.getLista();
        dao.close();
        ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add("MeuMenu");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ListView lista = (ListView) findViewById(R.id.listaAlunos);
        final Aluno alunoSlelecionado = (Aluno)  lista.getAdapter().getItem(info.position);
        menu.add("Ligar");
        menu.add("Enviar SMS");
        menu.add("Achar no mapa");
        menu.add("Navegar no site");
        menu.add(" ");

        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDao dao = new AlunoDao(MainActivity.this);
                dao.delete(alunoSlelecionado);
                dao.close();
                carregaLista();
                return false;
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }
}
