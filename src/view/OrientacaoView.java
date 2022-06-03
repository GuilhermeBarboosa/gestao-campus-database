/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Orientacao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class OrientacaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ServidorView servV = new ServidorView();
    Scanner ler = new Scanner(System.in);

    public Orientacao criarOrientacao(ServidorDAO servidorDAO) {
        try {
            Orientacao or = new Orientacao();

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Insira o ID: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            or.setServidor(servidor);

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

    public Orientacao modifOrientacao(Orientacao orAlt, ServidorDAO servidorDAO) {
        try {

            List<Servidor> servidorVet = servidorDAO.read();

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum campus cadastrado...");
                return null;
            } else {
                servV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Insira o ID: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            orAlt.setServidor(servidor);

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

    public void mostrarTodasOrientacoes(List<Orientacao> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há orientacões cadastrados");
        } else {
            for (Orientacao orientacao : vetResult) {
                System.out.println("ID: " + orientacao.getId());
                System.out.println("TIPO: " + orientacao.getTipo());
                System.out.println("SERVIDOR: " + orientacao.getServidor().getNome());
                System.out.println("ALUNO: " + orientacao.getNomeAluno());
                System.out.println("HORAS SEMANAIS: " + orientacao.getHorasSemanais());
                System.out.println("DATA DE INICIO: " + orientacao.getDtInicio());
                System.out.println("DATA DE TERMINO: " + orientacao.getDtTermino());
                System.out.println("DATA DE CRIAÇÃO: " + orientacao.getDtCriacao());
                System.out.println("DATA DE MODIFICAÇÃO: " + orientacao.getDtModificacao());
            }
        }
    }

    public void mostrarIdTodasOrientacoes(List<Orientacao> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há orientacões cadastrados");
        } else {
            for (Orientacao orientacao : vetResult) {
                System.out.println("ID: " + orientacao.getId());
                System.out.println("TIPO: " + orientacao.getTipo());
                System.out.println("SERVIDOR: " + orientacao.getServidor().getNome());
                System.out.println("ALUNO: " + orientacao.getNomeAluno());
            }
        }
    }

}
