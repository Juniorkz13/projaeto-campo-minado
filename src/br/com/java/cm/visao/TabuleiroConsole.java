package br.com.java.cm.visao;

import javax.swing.JOptionPane;

import br.com.java.cm.excecao.ExplosaoException;
import br.com.java.cm.excecao.SairException;
import br.com.java.cm.modelo.Tabuleiro;

public class TabuleiroConsole {

    private Tabuleiro tabuleiro;

    public TabuleiroConsole(Tabuleiro tabuleiro) {
        this.tabuleiro = tabuleiro;

        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
                int opcao = JOptionPane.showConfirmDialog(null, "Deseja jogar novamente?");
                if (opcao == JOptionPane.YES_OPTION) {
                    tabuleiro.reiniciar();
                } else if (opcao == JOptionPane.NO_OPTION) {
                    continuar = false;
                    throw new SairException();
                } else {
                    throw new SairException();
                }
            }

        } catch (SairException e) {
            JOptionPane.showMessageDialog(null, "Obrigado por jogar!");
        }

    }

    private void cicloDoJogo() {
        try {
            while (!tabuleiro.objetivoAlcancado()) {
                JOptionPane.showMessageDialog(null, tabuleiro.toString());

                int linha = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a linha: "));
                int coluna = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite a coluna: "));

                String opcao = JOptionPane.showInputDialog(null, "1 - Abrir ou 2 - (Des)Marcar");
                if ("1".equals(opcao)) {
                    tabuleiro.abrir(linha - 1, coluna - 1);
                } else if ("2".equals(opcao)) {
                    tabuleiro.alternarMarcacao(linha - 1, coluna - 1);
                }

            }
            JOptionPane.showMessageDialog(null, tabuleiro.toString());
            JOptionPane.showMessageDialog(null, "Você ganhou!");
        } catch (ExplosaoException e) {
            JOptionPane.showMessageDialog(null, tabuleiro.toString());
            JOptionPane.showMessageDialog(null, "Você perdeu!");
        }
    }

}
