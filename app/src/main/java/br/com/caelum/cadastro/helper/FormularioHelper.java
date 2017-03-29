package br.com.caelum.cadastro.helper;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.cadastro.FormularioActivity;
import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android7060 on 28/03/17.
 */

public class FormularioHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private Aluno aluno;


    public FormularioHelper(FormularioActivity form){
        nome = (EditText) form.findViewById(R.id.formulario_nome);
        telefone = (EditText) form.findViewById(R.id.formulario_telefone);
        endereco = (EditText) form.findViewById(R.id.formulario_endereco);
        site = (EditText) form.findViewById(R.id.formulario_site);
        nota = (RatingBar) form.findViewById(R.id.formulario_nota);
        aluno = new Aluno();
    }

    public void colocaNoFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

        this.aluno = aluno;
    }

    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getEditableText().toString());
        aluno.setTelefone(telefone.getEditableText().toString());
        aluno.setEndereco(endereco.getEditableText().toString());
        aluno.setSite(site.getEditableText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        return aluno;
    }
}
