package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android7060 on 28/03/17.
 */

public class AlunoDao extends SQLiteOpenHelper {

    private static final String DATABASE = "CADASTRO";
    private static final int VERSAO =2;
    public static final String TABELA = "ALUNO";

    public AlunoDao(Context ctx){
        super(ctx, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABELA  +
                " (id INTEGER PRIMARY KEY, "+
                " nome TEXT NOT NULL, "+
                " telefone TEXT, "+
                " endereco TEXT, "+
                " site TEXT, "+
                " nota REAL, "+
                " caminhoFoto Text"+
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "ALTER TABLE " + TABELA +" ADD COLUNM caminhoFoto Text;";
        db.execSQL(sql);
    }

    public void insert(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto",aluno.getCaminhoFoto());

        getWritableDatabase().insert(TABELA,null,values);
    }

    public void alterar(Aluno aluno){
        ContentValues values= new ContentValues();

        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        getWritableDatabase().update(TABELA, values, "id=?",new String[]{aluno.getId().toString()});
    }

    public List<Aluno> getLista(){
        List<Aluno> alunos = new ArrayList<>();
        Cursor c = getWritableDatabase().query(TABELA, null, null, null, null, null,null);
        //c.moveToFirst();
        while (c.moveToNext()){
            Aluno a = new Aluno();
            a.setId(c.getLong(c.getColumnIndex("id")));
            a.setNome(c.getString(c.getColumnIndex("nome")));
            a.setTelefone(c.getString(c.getColumnIndex("telefone")));
            a.setEndereco(c.getString(c.getColumnIndex("endereco")));
            a.setSite(c.getString(c.getColumnIndex("site")));
            a.setNota(c.getDouble(c.getColumnIndex("nota")));
            a.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            alunos.add(a);
        }

        c.close();
        return alunos;
    }

    public void delete(Aluno aluno){
        getWritableDatabase().delete(TABELA, "id = ?", new String[]{aluno.getId().toString()});
    }

    public boolean isAluno(String telefone){

        String[] parametros= {telefone};

        Cursor rawQuerry = getReadableDatabase().rawQuery("SELECT telefone FROM " + TABELA + " WHERE telefone = ?", parametros);

        int total = rawQuerry.getCount();
        rawQuerry.close();
        return total> 0;
    }
}
