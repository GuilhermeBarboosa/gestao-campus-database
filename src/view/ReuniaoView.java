/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ComissaoDAO;
import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import model.Comissao;
import model.Reuniao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ReuniaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ComissaoView comissaoV = new ComissaoView();
    ServidorView servidorV = new ServidorView();
    Scanner ler = new Scanner(System.in);

    public Reuniao criarReuniao(ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        try {
            Reuniao reuniao = new Reuniao();

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Escolha uma comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            reuniao.setComissao(comissao);

            List<Servidor> servidorVet = servidorDAO.read();
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servidorV.mostrarIdServidores(servidorVet);

            }
            System.out.println("Escolha um servidor: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            reuniao.setServidorSecre(servidor);

            System.out.println("Data da reunião:  ");
            reuniao.setDtReuniao(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Conteudo da ata: ");
            reuniao.setConteudoAta(ler.nextLine());

            reuniao.setDtCriacao(LocalDate.now());
            return reuniao;
        } catch (Exception e) {
            return null;
        }

    }

    public Reuniao modifReuniao(Reuniao reunAlt, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        try {

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Escolha uma comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            reunAlt.setComissao(comissao);

            List<Servidor> servidorVet = servidorDAO.read();
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servidorV.mostrarIdServidores(servidorVet);

            }
            System.out.println("Escolha um servidor: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            reunAlt.setServidorSecre(servidor);

            System.out.println("Data da reunião:  ");
            reunAlt.setDtReuniao(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Conteudo da ata: ");
            reunAlt.setConteudoAta(ler.nextLine());

            reunAlt.setDtModificacao(LocalDate.now());
            return reunAlt;
        } catch (Exception e) {
            return null;
        }

    }

    public void mostrarTodosReunioes(List<Reuniao> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reuniões cadastrados");
        } else {
            for (Reuniao reuniao : vetResult) {
                System.out.println("ID: " + reuniao.getId());
                System.out.println("COMISSAO: " + reuniao.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniao.getServidorSecre().getNome());
                System.out.println("DATA DE REUNIAO: " + reuniao.getDtReuniao());
                System.out.println("CONTEUDO DA ATA: " + reuniao.getConteudoAta());
                System.out.println("DATA DE CRIAÇÃO: " + reuniao.getDtCriacao());
                System.out.println("DATA DE MODIFICAÇÃO: " + reuniao.getDtModificacao());
            }
        }
    }

    public void mostrarIdTodosReunioes(List<Reuniao> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reuniões cadastrados");
        } else {
            for (Reuniao reuniao : vetResult) {
                System.out.println("ID: " + reuniao.getId());
                System.out.println("COMISSAO: " + reuniao.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniao.getServidorSecre().getNome());
                System.out.println("DATA DE REUNIAO: " + reuniao.getDtReuniao());
            }
        }
    }
}
