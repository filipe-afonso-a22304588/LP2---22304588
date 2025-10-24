package pt.ulusofona.lp2.greatprogrammingjourney;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        ArrayList<Integer> idJaUtilizados = new ArrayList<>();
        ArrayList<String> coresPossiveis = new ArrayList<>();

        coresPossiveis.add("Green");
        coresPossiveis.add("Blue");
        coresPossiveis.add("Brown");
        coresPossiveis.add("Purple");

        Jogador teste = new Jogador("123","123","","Orange",15);
        Jogador teste2 = new Jogador("526","456","Java;C","Green",30);

       // System.out.println(teste.isAtributosInvalido(idJaUtilizados,coresPossiveis));
       // System.out.println(teste2.isAtributosInvalido(idJaUtilizados,coresPossiveis));

        GameManager novo = new GameManager();
        novo.tamanhoTabuleiro = 79;
        teste.posicao=5;
        teste2.posicao = 5;
        novo.listaJogadores.put(teste.getId(),teste);
        novo.listaJogadores.put(teste2.getId(),teste2);
        System.out.println(Arrays.toString(novo.getSlotInfo(5)));
        //teste para o github


    }
}
