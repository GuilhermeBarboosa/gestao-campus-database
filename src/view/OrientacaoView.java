/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Orientacao;

/**
 *
 * @author Gui
 */
public class OrientacaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Orientacao criarOrientacao(List<String> servidorVet) {
        try {
            Orientacao or = new Orientacao();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o ID: ");
            or.setId_servidor(Integer.parseInt(ler.nextLine()));

            System.out.println("Tipo: ");
            or.setTipo(ler.nextLine());
            System.out.println("Nome do aluno:  ");
            or.setNomeAluno(ler.nextLine());
            System.out.println("Horas semanais: ");
            or.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            or.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            or.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            or.setDtCriacao(LocalDate.now());
            return or;
        } catch (Exception e) {
            return null;
        }

    }

    public Orientacao modifOrientacao(Orientacao orAlt, List<String> servidorVet) {
        try {
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o ID: ");
            orAlt.setId_servidor(Integer.parseInt(ler.nextLine()));

            System.out.println("Tipo: ");
            orAlt.setTipo(ler.nextLine());
            System.out.println("Nome do aluno:  ");
            orAlt.setNomeAluno(ler.nextLine());
            System.out.println("Horas semanais: ");
            orAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
            System.out.println("Data de inicio: ");
            orAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de termino: ");
            orAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
            orAlt.setDtModificacao(LocalDate.now());
            return orAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodasOrientacoes(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há orientacões cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

}
