/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Comissao;
import model.Reuniao;
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
public class ReuniaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ComissaoView comissaoView = new ComissaoView();
    ComissaoService comissaoService = new ComissaoService();
    ServidorView servidorView = new ServidorView();
    ServidorService servidorService = new ServidorService();
    Scanner ler = new Scanner(System.in);

    public Reuniao create() {
        try {
            return inputInfoReuniao(new Reuniao());
        } catch (Exception e) {
            return null;
        }

    }

    public Reuniao update(Reuniao reuniao) {
        try {
            return inputInfoReuniao(reuniao);
        } catch (Exception e) {
            return null;
        }

    }


    private Reuniao inputInfoReuniao(Reuniao reuniao) throws Exception {
        List<Comissao> listComissao = comissaoService.read();
        if (listComissao.size() == 0) {
            System.out.println("Nenhum comissao cadastrado...");
            return null;
        } else {
            comissaoView.printId(listComissao);
        }
        System.out.println("Escolha uma comissao: ");
        Comissao comissao = comissaoService.getById(Integer.parseInt(ler.nextLine()));
        reuniao.setComissao(comissao);

        List<Servidor> listServidor = servidorService.read();
        if (listServidor.size() == 0) {
            System.out.println("Nenhum servidor cadastrado...");
            return null;
        } else {
            servidorView.printId(listServidor);

        }
        System.out.println("Escolha um servidor: ");
        Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
        reuniao.setServidorSecre(servidor);

        System.out.println("Data da reunião:  ");
        reuniao.setDtReuniao(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Conteudo da ata: ");
        reuniao.setConteudoAta(ler.nextLine());

        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }


    public void printAll(List<Reuniao> listReuniao) {
        if (listReuniao.size() == 0) {
            System.out.println("Não há reuniões cadastrados");
        } else {
            for (Reuniao reuniao : listReuniao) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + reuniao.getId());
                System.out.println("COMISSAO: " + reuniao.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniao.getServidorSecre().getNome());
                System.out.println("DATA DE REUNIAO: " + reuniao.getDtReuniao());
                System.out.println("CONTEUDO DA ATA: " + reuniao.getConteudoAta());
                System.out.println("DATA DE CRIAÇÃO: " + reuniao.getDtCriacao());
                if (reuniao.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + reuniao.getDtModificacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void printId(List<Reuniao> listReuniao) {
        if (listReuniao.size() == 0) {
            System.out.println("Não há reuniões cadastrados");
        } else {
            for (Reuniao reuniao : listReuniao) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + reuniao.getId());
                System.out.println("COMISSAO: " + reuniao.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + reuniao.getServidorSecre().getNome());
                System.out.println("DATA DE REUNIAO: " + reuniao.getDtReuniao());
                System.out.println("-----------------------------------");
            }
        }
    }
}
