package br.com.caelum.cadastro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.cadastro.R;
import br.com.caelum.cadastro.modelo.Prova;

/**
 * Created by android7060 on 31/03/17.
 */

public class ListaProvasFragment extends Fragment {

    private ListView listViewProvas;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas,container,false);
        this.listViewProvas = (ListView) layoutProvas.findViewById(R.id.lista_provas_listview);

        Prova prova1 = new Prova("20/06/2017", "Matematica");
        prova1.setTopicos(Arrays.asList("Algebra linear", "Calculo", "Estatistica"));

        Prova prova2 = new Prova("25/07/2017","Portugues");
        prova2.setTopicos(Arrays.asList("Complemento nominal", "Analise sintatica"));

        List<Prova> provas = Arrays.asList(prova1,prova2);

        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(),android.R.layout.simple_list_item_1, provas));

        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova selecionada= (Prova) parent.getItemAtPosition(position);

                //Toast.makeText(getActivity(), "Prova selecionada: " + selecionada,Toast.LENGTH_LONG).show();
                //Intent detalhe  = new Intent(this, )
            }
        });
        return  layoutProvas;
    }
}
