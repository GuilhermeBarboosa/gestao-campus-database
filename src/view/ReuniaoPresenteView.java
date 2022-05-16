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
    Scanner ler = new Scanner(System.in);

    public ReuniaoPresente criarReuniaoPresente(List<String> servidorVet, List<String> comissaoVet) {

        try {
            ReuniaoPresente reuniaoPresente = new ReuniaoPresente();

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o id da comissao: ");
            reuniaoPresente.setId_comissao(Integer.parseInt(ler.nextLine()));

            System.out.println("Ata de reunião:  ");
            reuniaoPresente.setAtaReuniao(ler.nextLine());

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o id do servidor: ");
            reuniaoPresente.setId_servidor(Integer.parseInt(ler.nextLine()));

            reuniaoPresente.setDtCriacao(LocalDate.now());
            return reuniaoPresente;
        } catch (Exception e) {
            return null;
        }
    }

    public ReuniaoPresente modifReuniaoPresente(ReuniaoPresente reunPresenteAlt, List<String> servidorVet, List<String> comissaoVet) {
        try {

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o id da comissao: ");
            reunPresenteAlt.setId_comissao(Integer.parseInt(ler.nextLine()));

            System.out.println("Ata de reunião:  ");
            reunPresenteAlt.setAtaReuniao(ler.nextLine());

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Insira o id do servidor: ");
            reunPresenteAlt.setId_servidor(Integer.parseInt(ler.nextLine()));

            reunPresenteAlt.setDtModificacao(LocalDate.now());
            return reunPresenteAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodosReunioesPresente(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reunião e presente cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }

}
