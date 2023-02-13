package br.com.alura.loja.modelo;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CategoriaID implements Serializable {

    private String nome;
    private String tipo;

    public CategoriaID() {
    }

    public CategoriaID(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
