package br.com.caelum.cadastro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.helper.FormularioHelper;
import br.com.caelum.cadastro.modelo.Aluno;

public class FormularioActivity extends AppCompatActivity {

    public static final String ALUNO_SELECIONADO ="alunoSelecionado";
    private FormularioHelper helper;
    private boolean d = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        helper = new FormularioHelper(this);


        Aluno aluno = (Aluno) getIntent().getSerializableExtra(ALUNO_SELECIONADO) ;
        if (aluno != null ){
            helper.colocaNoFormulario(aluno);
        }

        Button salvar = (Button) findViewById(R.id.formulario_botao);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Aluno aluno = helper.pegaAlunoDoFormulario();
                AlunoDao dao = new AlunoDao(FormularioActivity.this);
                if (aluno.getId() != null) {
                    dao.alterar(aluno);
                } else{
                    dao.insert(aluno);
                }
                dao.close();
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_formulario_ok:
                Toast.makeText(this, "ok cliclado", Toast.LENGTH_LONG).show();
                finish();
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
