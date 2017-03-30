package br.com.caelum.cadastro.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    private ImageView foto;
    private Button fotoButton;


    public FormularioHelper(FormularioActivity form){
        nome = (EditText) form.findViewById(R.id.formulario_nome);
        telefone = (EditText) form.findViewById(R.id.formulario_telefone);
        endereco = (EditText) form.findViewById(R.id.formulario_endereco);
        site = (EditText) form.findViewById(R.id.formulario_site);
        nota = (RatingBar) form.findViewById(R.id.formulario_nota);
        foto = (ImageView) form.findViewById(R.id.formulario_foto);
        fotoButton = (Button) form.findViewById(R.id.formulario_foto_button);
        aluno = new Aluno();
    }

    public Button getFotoButton(){
        return fotoButton;
    }

    public void colocaNoFormulario(Aluno aluno){
        nome.setText(aluno.getNome());
        telefone.setText(aluno.getTelefone());
        endereco.setText(aluno.getEndereco());
        site.setText(aluno.getSite());
        nota.setProgress(aluno.getNota().intValue());

        carregaImagem(aluno.getCaminhoFoto());
        this.aluno = aluno;
    }

    public Aluno pegaAlunoDoFormulario(){
        aluno.setNome(nome.getEditableText().toString());
        aluno.setTelefone(telefone.getEditableText().toString());
        aluno.setEndereco(endereco.getEditableText().toString());
        aluno.setSite(site.getEditableText().toString());
        aluno.setNota(Double.valueOf(nota.getProgress()));
        aluno.setCaminhoFoto((String) foto.getTag());
        return aluno;
    }

    public void carregaImagem(String localArquivoFoto){
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
