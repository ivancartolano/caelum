package br.com.caelum.cadastro;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import br.com.caelum.cadastro.adapters.ListaAlunosAdapter;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;
import br.com.caelum.cadastro.permissao.Permissao;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Permissao.fazPermissao(this);

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

        for (Aluno aluno: alunos){
            Log.i("Aluno", ""+aluno.getCaminhoFoto());
        }

        dao.close();
        //ArrayAdapter<Aluno> adapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);
        //ListaAlunosAdapter adapter = new ListaAlunosAdapter(this,alunos);
        ListaAlunosAdapter adapter = new ListaAlunosAdapter(this);
        lista.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item = menu.add(0,1,0,"MeuMenu");

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

        switch (item.getItemId()){
            case 1:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ListView lista = (ListView) findViewById(R.id.listaAlunos);
        final Aluno alunoSlelecionado = (Aluno)  lista.getAdapter().getItem(info.position);

        MenuItem ligar = menu.add("Ligar");
        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse(("tel" + alunoSlelecionado.getTelefone())));
        ligar.setIntent(intentLigar);

        MenuItem sms = menu.add("Enviar SMS");
        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms: "+ alunoSlelecionado.getTelefone()));
        sms.setIntent(intentSms);

        MenuItem mapa = menu.add("Achar no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?z=14&q=" + alunoSlelecionado.getEndereco()));
        mapa.setIntent(intentMapa);

        MenuItem site = menu.add("Navegar no site");
        Intent intentSite = new Intent(Intent.ACTION_VIEW);
        String pagina = alunoSlelecionado.getSite();

        if(!pagina.startsWith("http://")){
            pagina = "http://" + pagina;
        }
        intentSite.setData(Uri.parse(pagina));

        site.setIntent(intentSite);
        //menu.add(" ");

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
