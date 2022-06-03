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
import model.ReuniaoPresente;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ReuniaoPresenteView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ComissaoView comissaoV = new ComissaoView();
    ServidorView servidorV = new ServidorView();
    Scanner ler = new Scanner(System.in);

    public ReuniaoPresente criarReuniaoPresente(ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {

        try {
            ReuniaoPresente reuniaoPresente = new ReuniaoPresente();

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Insira o id da comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            reuniaoPresente.setComissao(comissao);

            System.out.println("Ata de reunião:  ");
            reuniaoPresente.setAtaReuniao(ler.nextLine());

            List<Servidor> servidorVet = servidorDAO.read();
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servidorV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Insira o id do servidor: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            reuniaoPresente.setServidor(servidor);

            reuniaoPresente.setDtCriacao(LocalDate.now());
            return reuniaoPresente;
        } catch (Exception e) {
            return null;
        }
    }

    public ReuniaoPresente modifReuniaoPresente(ReuniaoPresente reunPresenteAlt, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        try {

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Insira o id da comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            reunPresenteAlt.setComissao(comissao);

            System.out.println("Ata de reunião:  ");
            reunPresenteAlt.setAtaReuniao(ler.nextLine());

            List<Servidor> servidorVet = servidorDAO.read();
            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servidorV.mostrarIdServidores(servidorVet);
            }
            System.out.println("Insira o id do servidor: ");
            Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
            reunPresenteAlt.setServidor(servidor);

            reunPresenteAlt.setDtModificacao(LocalDate.now());
            return reunPresenteAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodosReunioesPresente(List<ReuniaoPresente> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reunião e presente cadastrados");
        } else {
            for (ReuniaoPresente reuniaoPresente : vetResult) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + reuniaoPresente.getId());
                System.out.println("COMISSAO: " + reuniaoPresente.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniaoPresente.getServidor().getNome());
                System.out.println("ATA DA REUNIÃO: " + reuniaoPresente.getAtaReuniao());
                System.out.println("DATA DE CRIAÇÃO: " + reuniaoPresente.getDtCriacao());
                if (reuniaoPresente.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + reuniaoPresente.getDtModificacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void mostrarIdTodosReunioesPresente(List<ReuniaoPresente> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reunião e presente cadastrados");
        } else {
            for (ReuniaoPresente reuniaoPresente : vetResult) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + reuniaoPresente.getId());
                System.out.println("COMISSAO: " + reuniaoPresente.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniaoPresente.getServidor().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
