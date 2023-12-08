/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Orientacao;
import model.Servidor;
import service.ServidorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class OrientacaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ServidorView servidorView = new ServidorView();

    ServidorService servidorService = new ServidorService();
    Scanner ler = new Scanner(System.in);

    public Orientacao create() {
        try {
            return inputInfoOrientacao(new Orientacao());
        } catch (Exception e) {
            return null;
        }

    }

    public Orientacao update(Orientacao orientacao) {
        try {
            return inputInfoOrientacao(orientacao);
        } catch (Exception e) {
            return null;
        }
    }

    private Orientacao inputInfoOrientacao(Orientacao orientacao) throws Exception {
        List<Servidor> listServidor = servidorService.read();

        if (listServidor.size() == 0) {
            System.out.println("Nenhum campus cadastrado...");
            return null;
        } else {
            servidorView.printId(listServidor);
        }
        System.out.println("Insira o ID: ");
        Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
        orientacao.setServidor(servidor);

        System.out.println("Tipo: ");
        orientacao.setTipo(ler.nextLine());
        System.out.println("Nome do aluno:  ");
        orientacao.setNomeAluno(ler.nextLine());
        System.out.println("Horas semanais: ");
        orientacao.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        orientacao.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        orientacao.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        orientacao.setDtCriacao(LocalDate.now());
        return orientacao;
    }

    public void printAll(List<Orientacao> listOrientacao) {
        if (listOrientacao.size() == 0) {
            System.out.println("Não há orientacões cadastrados");
        } else {
            for (Orientacao orientacao : listOrientacao) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + orientacao.getId());
                System.out.println("TIPO: " + orientacao.getTipo());
                System.out.println("SERVIDOR: " + orientacao.getServidor().getNome());
                System.out.println("ALUNO: " + orientacao.getNomeAluno());
                System.out.println("HORAS SEMANAIS: " + orientacao.getHorasSemanais());
                System.out.println("DATA DE INICIO: " + orientacao.getDtInicio());
                System.out.println("DATA DE TERMINO: " + orientacao.getDtTermino());
                System.out.println("DATA DE CRIAÇÃO: " + orientacao.getDtCriacao());
                if (orientacao.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + orientacao.getDtModificacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void printId(List<Orientacao> listOrientacao) {
        if (listOrientacao.size() == 0) {
            System.out.println("Não há orientacões cadastrados");
        } else {
            for (Orientacao orientacao : listOrientacao) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + orientacao.getId());
                System.out.println("TIPO: " + orientacao.getTipo());
                System.out.println("SERVIDOR: " + orientacao.getServidor().getNome());
                System.out.println("ALUNO: " + orientacao.getNomeAluno());
                System.out.println("-----------------------------------");
            }
        }
    }

}
