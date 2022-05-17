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
    Scanner ler = new Scanner(System.in);

    public Reuniao criarReuniao(List<String> servidorVet, List<String> comissaoVet) {
        try {
            Reuniao reuniao = new Reuniao();

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha uma comissao: ");
            reuniao.setId_comissao(Integer.parseInt(ler.nextLine()));

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            reuniao.setId_servidorSecre(Integer.parseInt(ler.nextLine()));

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

    public Reuniao modifReuniao(Reuniao reunAlt, List<String> servidorVet, List<String> comissaoVet) {
        try {

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhum comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha uma comissao: ");
            reunAlt.setId_comissao(Integer.parseInt(ler.nextLine()));

            if (servidorVet.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                for (String string : servidorVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha um servidor: ");
            reunAlt.setId_servidorSecre(Integer.parseInt(ler.nextLine()));

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

    public void mostrarTodosReunioes(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há reuniões cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
    }
}
