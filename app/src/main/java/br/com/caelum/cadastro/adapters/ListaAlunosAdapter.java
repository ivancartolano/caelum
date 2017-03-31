package br.com.caelum.cadastro.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.dao.AlunoDao;
import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android7060 on 30/03/17.
 */

public class ListaAlunosAdapter extends BaseAdapter{

    private List<Aluno> alunos;
    private Context ctx;

    public ListaAlunosAdapter(Context ctx){
        AlunoDao dao = new AlunoDao(ctx);
        alunos = dao.getLista();
        dao.close();
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item,null);
        ImageView foto = (ImageView) v.findViewById(R.id.item_foto);
        TextView nome = (TextView) v.findViewById(R.id.item_nome);
        Aluno a = alunos.get(position);
        if (a.getCaminhoFoto()!= null)
            foto.setImageBitmap(BitmapFactory.decodeFile(a.getCaminhoFoto()));
        nome.setText(a.getNome());
        TextView telefone = (TextView) v.findViewById(R.id.telefone);
        if(telefone != null)
            telefone.setText(a.getTelefone());
        return v;
    }
}
