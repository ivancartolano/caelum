package br.com.caelum.cadastro;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import br.com.caelum.cadastro.fragment.ListaProvasFragment;

/**
 * Created by android7060 on 31/03/17.
 */

public class ProvasActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.provas_view, new ListaProvasFragment());
        transaction.commit();
    }
}
