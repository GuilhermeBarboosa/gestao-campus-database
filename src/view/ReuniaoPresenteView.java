/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Comissao;
import model.ReuniaoPresente;
import model.Servidor;
import service.ComissaoService;
import service.ServidorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class ReuniaoPresenteView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ComissaoView comissaoView = new ComissaoView();
    ServidorView servidorView = new ServidorView();

    ComissaoService comissaoService = new ComissaoService();
    ServidorService servidorService = new ServidorService();
    Scanner ler = new Scanner(System.in);

    public ReuniaoPresente create() {

        try {
            return inputInfoReuniaoPresente(new ReuniaoPresente());
        } catch (Exception e) {
            return null;
        }
    }

    public ReuniaoPresente update(ReuniaoPresente reuniaoPresente) {
        try {
            return inputInfoReuniaoPresente(reuniaoPresente);
        } catch (Exception e) {
            return null;
        }
    }

    private ReuniaoPresente inputInfoReuniaoPresente(ReuniaoPresente reuniaoPresente) throws Exception {
        List<Comissao> listComissao = comissaoService.read();
        if (listComissao.size() == 0) {
            System.out.println("Nenhum comissao cadastrado...");
            return null;
        } else {
            comissaoView.printId(listComissao);
        }
        System.out.println("Insira o id da comissao: ");
        Comissao comissao = comissaoService.getById(Integer.parseInt(ler.nextLine()));
        reuniaoPresente.setComissao(comissao);

        System.out.println("Ata de reunião:  ");
        reuniaoPresente.setAtaReuniao(ler.nextLine());

        List<Servidor> servidorViewet = servidorService.read();
        if (servidorViewet.size() == 0) {
            System.out.println("Nenhum servidor cadastrado...");
            return null;
        } else {
            servidorView.printId(servidorViewet);
        }
        System.out.println("Insira o id do servidor: ");
        Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
        reuniaoPresente.setServidor(servidor);

        reuniaoPresente.setDtCriacao(LocalDate.now());
        return reuniaoPresente;
    }

    public void mostrarTodosReunioesPresente(List<ReuniaoPresente> listReuniaoPresente) {
        if (listReuniaoPresente.size() == 0) {
            System.out.println("Não há reunião e presente cadastrados");
        } else {
            for (ReuniaoPresente reuniaoPresente : listReuniaoPresente) {
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

    public void mostrarIdTodosReunioesPresente(List<ReuniaoPresente> listReuniaoPresente) {
        if (listReuniaoPresente.size() == 0) {
            System.out.println("Não há reunião e presente cadastrados");
        } else {
            for (ReuniaoPresente reuniaoPresente : listReuniaoPresente) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + reuniaoPresente.getId());
                System.out.println("COMISSAO: " + reuniaoPresente.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniaoPresente.getServidor().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
