package pt.ulusofona.lp2.greatprogrammingjourney;

import javax.swing.*;
import java.util.*;

public class GameManager {
    ArrayList<Integer> idsJogadores;
    HashMap<Integer, Jogador> listaJogadores;
    boolean jogoFinalizado;
    int tamanhoTabuleiro;
    int nrTurnos;

    public GameManager() {
        this.idsJogadores = new ArrayList<>();
        this.listaJogadores = new HashMap<>();
        this.jogoFinalizado = false;
        this.nrTurnos = 1;
        this.tamanhoTabuleiro = 0;
    }

    public boolean createInitialBoard(String[][] playerInfo, int worldSize) {

        ArrayList<Integer> idJaUtilizados = new ArrayList<>();
        ArrayList<String> coresPossiveis = new ArrayList<>();

        coresPossiveis.add("Green");
        coresPossiveis.add("Blue");
        coresPossiveis.add("Brown");
        coresPossiveis.add("Purple");

        //verifica se espaço do tabuleiro é suficiente e o número de jogadores necessário
        if (worldSize < playerInfo.length * 2 || playerInfo.length < 2 || playerInfo.length > 4) {
            return false;
        }
        tamanhoTabuleiro = worldSize;

        //Verifica jogador a jogador se os dados estão bem
        for (String[] jogadores : playerInfo) {

            if (jogadores == null) {
                return false;
            }

            String id = jogadores[0];
            String nome = jogadores[1];
            String linguagemFavorita = jogadores[2];
            String cor = jogadores[3];

            Jogador jogadorTeste = new Jogador(id, nome, linguagemFavorita, cor, worldSize);

            if (jogadorTeste.isAtributosInvalido(idJaUtilizados, coresPossiveis)) {
                return false;
            } else {
                this.idsJogadores.add(jogadorTeste.getId());
                this.listaJogadores.put(jogadorTeste.getId(), jogadorTeste);
            }
        }
        this.jogoFinalizado = false;
        Collections.sort(this.idsJogadores);
        return true;
    }

    public String getImagePng(int nrSquare) {
        if (nrSquare == this.tamanhoTabuleiro) {
            return "glory.png";
        } else {
            return null;
        }
    }

    public String[] getProgrammerInfo(int id) {

        if (id <= 0) {
            return null;
        }

        String[] resultado = null;

        for (Jogador jogador : this.listaJogadores.values()) {
            if (jogador.getId() == id) {
                resultado = new String[]{String.valueOf(jogador.getId()), jogador.getNome(), jogador.getLinguagemFavorita(),
                        jogador.getCor(), String.valueOf(jogador.getPosicao())};
            }
        }
        return resultado;
    }

    public String getProgrammerInfoAsStr(int id) {

        if (id <= 0) {
            return null;
        }

        String resultado = null;

        for (Jogador jogador : this.listaJogadores.values()) {
            if (jogador.getId() == id) {
                resultado = jogador.id + " | " + jogador.getNome() + " | " + jogador.getPosicao() + " | " +
                        jogador.getLinguagemFavorita() + " | " + jogador.getEstado();
            }
        }
        return resultado;

    }

    public String[] getSlotInfo(int position) {

        String[] jogadoresPresentesNaPosicao = new String[]{""};
        int count = 0;

        if (position < 1 || position > this.tamanhoTabuleiro) {
            return null;
        }

        for (Jogador jogador : this.listaJogadores.values()) {
            if (jogador.getPosicao() == position) {
                if (count == 0) {
                    jogadoresPresentesNaPosicao[0] += jogador.getId();
                    count++;
                } else {
                    jogadoresPresentesNaPosicao[0] += "," + jogador.getId();
                }
            }
        }

        return jogadoresPresentesNaPosicao;
    }

    public int getCurrentPlayerID() {
        return this.idsJogadores.get(0);
    }

    public boolean moveCurrentPlayer(int nrSpaces) {
        if (nrSpaces < 1 || nrSpaces > 6) {
            return false;
        }

        //obter jogador que vai jogar a seguir, e já o colocar como último da fila
        int idJogadorAtual = getCurrentPlayerID();

        //implemetar função para isto
        if (this.listaJogadores.get(idJogadorAtual).getPosicao() + nrSpaces > this.tamanhoTabuleiro) {
            int excesso = (this.listaJogadores.get(idJogadorAtual).getPosicao() + nrSpaces) - this.tamanhoTabuleiro;
            this.listaJogadores.get(idJogadorAtual).setPosicao(this.tamanhoTabuleiro - excesso);
            this.nrTurnos++;
            int idPrimeiroJogador = this.idsJogadores.get(0);
            this.idsJogadores.remove(0);
            this.idsJogadores.add(idPrimeiroJogador);
            return true;
        } else {
            this.listaJogadores.get(idJogadorAtual).moveJogador(nrSpaces);
            this.nrTurnos++;
            int idPrimeiroJogador = this.idsJogadores.get(0);
            this.idsJogadores.remove(0);
            this.idsJogadores.add(idPrimeiroJogador);
            return true;
        }
    }

    public boolean gameIsOver() {
        for (Jogador jogador : this.listaJogadores.values()) {
            if (jogador.getPosicao() == this.tamanhoTabuleiro) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> getGameResults() {

        ArrayList<String> resultado = new ArrayList<>();
        ArrayList<Jogador> jogadoresFimDeJogo = new ArrayList<>();

        for (Jogador jogador : listaJogadores.values()) {
            jogadoresFimDeJogo.add(jogador);
        }

        jogadoresFimDeJogo.sort(Comparator.comparingInt(Jogador::getPosicao).reversed());

        resultado.add("THE GREAT PROGRAMMING JOURNEY");
        resultado.add("");
        resultado.add("NR. DE TURNOS");
        resultado.add(String.valueOf(this.nrTurnos));
        resultado.add("");
        resultado.add("VENCEDOR");
        resultado.add(jogadoresFimDeJogo.get(0).getNome());
        jogadoresFimDeJogo.remove(0);
        resultado.add("");
        resultado.add("RESTANTES");
        for (Jogador jogador : jogadoresFimDeJogo) {
            resultado.add(jogador.getNome() + " " + jogador.getPosicao());
        }

        return resultado;
    }

    public JPanel getAuthorsPanel() {
        JPanel panel = new JPanel();
        return null;
    }

    public HashMap<String, String> customizeBoard() {
        HashMap<String, String> resultado = new HashMap<>();
        return resultado;
    }


}
