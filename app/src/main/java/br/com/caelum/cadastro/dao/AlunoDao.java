package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by android7060 on 28/03/17.
 */

public class AlunoDao extends SQLiteOpenHelper {

    private static final String DATABASE = "CADASTRO";
    private static final int VERSAO =1;
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
                " nota REAL "+
                ");";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA;
        db.execSQL(sql);
    }

    public void insert(Aluno aluno){
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("nota", aluno.getNota());

        getWritableDatabase().insert(TABELA,null,values);
    }
}
