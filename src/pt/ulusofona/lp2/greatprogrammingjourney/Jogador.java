package pt.ulusofona.lp2.greatprogrammingjourney;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Jogador {
    String id;
    String nome;
    String linguagemFavorita;
    String cor;
    int posicao;
    int meta;
    String estado;


    public Jogador(String id, String nome, String linguagemFavorita, String cor, int meta) {
        this.id = id;
        this.nome = nome;
        this.linguagemFavorita = formataLinguagens(linguagemFavorita);
        this.cor = cor;
        this.meta = meta;
        this.posicao = 1;
    }

    String formataLinguagens(String linguagemNaoFormatada){

        String linguagensFormatadas = "";

        if (linguagemNaoFormatada != null && !linguagemNaoFormatada.isEmpty()) {

            String[] linguagensDiferentes = linguagemNaoFormatada.split(";");
            Arrays.sort(linguagensDiferentes);

            for (int i = 0; i < linguagensDiferentes.length; i++) {
                linguagensFormatadas += linguagensDiferentes[i];
                if (i != linguagensDiferentes.length - 1 ){
                    linguagensFormatadas += ";";
                }
            }
        }
        return linguagensFormatadas;
    }

    boolean isAtributosInvalido(ArrayList<Integer> idsJaUtilizados, ArrayList<String> coresPossiveis) {

        int id = Integer.parseInt(this.id);

        if ((id <= 0 || idsJaUtilizados.contains(id)) || nome == null || nome.isEmpty() || linguagemFavorita == null ||
                linguagemFavorita.isEmpty() || cor == null || cor.isEmpty() || !coresPossiveis.contains(cor)) {
            return true;
        } else {
            idsJaUtilizados.add(id);
            coresPossiveis.remove(cor);
            return false;
        }
    }

    void moveJogador(int resultadoDado){
        this.posicao += resultadoDado;
    }
    void setPosicao(int posicao){
        this.posicao = posicao;
    }

    int getId() {
        return Integer.parseInt(this.id);
    }

    String getNome() {
        return this.nome;
    }

    String getLinguagemFavorita() {
        return this.linguagemFavorita;
    }

    String getCor() {
        String resultado = "";
        for (int i = 0; i < this.cor.length(); i++) {
            if (i == 0) {
                resultado += this.cor.charAt(0);
                resultado.toUpperCase();
            } else {
                resultado += this.cor.charAt(i);

            }
        }
        return resultado;
    }

    int getPosicao() {
        return this.posicao;
    }

    String getEstado() {
        return this.estado;
    }
    int getMeta(){
        return this.meta;
    }

}
