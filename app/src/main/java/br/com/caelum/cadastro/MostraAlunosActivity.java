package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragment.MapaFragment;

/**
 * Created by android7060 on 31/03/17.
 */

public class MostraAlunosActivity extends AppCompatActivity {

    protected void onCreate(Bundle bundle){
        super.onCreate(bundle);
        setContentView(R.layout.activity_mostra_alunos);

        MapaFragment mapaFragment = new MapaFragment();

        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.mostra_aluno_mapa, mapaFragment);
        tx.commit();
    }

}
