package br.com.caelum.cadastro.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by android7060 on 31/03/17.
 */

public class Prova implements Serializable {

    private String data;
    private String materia;
    private String descicao;
    private List<String> topicos = new ArrayList<String>();

    public Prova(String data,String materia){
        this.data = data;
        this.materia = materia;
    }

    public String toString(){
        return materia;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDescicao() {
        return descicao;
    }

    public void setDescicao(String descicao) {
        this.descicao = descicao;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }
}
