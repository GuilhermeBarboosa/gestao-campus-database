/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.Comissao;
import model.Servidor;
import model.Vinculo;
import service.ComissaoService;
import service.ServidorService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

/**
 * @author Gui
 */
public class VinculoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    ComissaoView comissaoView = new ComissaoView();
    ServidorView servidorView = new ServidorView();

    ComissaoService comissaoService = new ComissaoService();

    ServidorService servidorService = new ServidorService();
    Scanner ler = new Scanner(System.in);

    int menu = 0;
    int auxOPC = 0;

    public Vinculo create() {
        try {
            return inputInfoVinculo(new Vinculo());
        } catch (Exception e) {
            return null;
        }

    }

    public Vinculo update(Vinculo vinculo) {
        try {
            return inputInfoVinculo(vinculo);
        } catch (Exception e) {
            return null;
        }
    }


    private Vinculo inputInfoVinculo(Vinculo vinc) throws Exception {
        List<Comissao> listComissao = comissaoService.read();
        if (listComissao.size() == 0) {
            System.out.println("Nenhuma comissao cadastrado...");
            return null;
        } else {
            comissaoView.printId(listComissao);
        }
        System.out.println("Escolha pelo id uma comissao: ");
        Comissao comissao = comissaoService.getById(Integer.parseInt(ler.nextLine()));
        vinc.setComissao(comissao);

        menu = tipoEscolha();
        if (menu == 1) {
            Servidor servResponse = servidorMenorCarga(servidorService.read());
            System.out.println("O servidor " + servResponse.getNome() + " foi selecionado");
            vinc.setServidor(servResponse);
        } else if (menu == 2) {

            List<Servidor> listServidor = servidorService.read();
            if (listServidor.size() == 0) {
                System.out.println("Nenhum servidor cadastrado...");
                return null;
            } else {
                servidorView.printId(listServidor);
            }
            System.out.println("Escolha pelo id um Servidor:");
            Servidor servidor = servidorService.getById(Integer.parseInt(ler.nextLine()));
            vinc.setServidor(servidor);

        }

        System.out.println("Papel: ");
        vinc.setPapel(ler.nextLine());
        System.out.println("Data de entrada: ");
        vinc.setDtEntrada(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de saida: ");
        vinc.setDtSaida(LocalDate.parse(ler.nextLine(), formatter));
        vinc.setDtCriacao(LocalDate.now());
        return vinc;
    }


    /////////////////////////////////////////////////////
    private int tipoEscolha() {
        System.out.println("Deseja escolher aleatoriamente ou escolher por servidor?");
        System.out.println("1 - Aleatorio/2 - Escolher Servidor");
        int auxOp = Integer.parseInt(ler.nextLine());
        while (auxOp < 1 || auxOp > 2) {
            tipoEscolha();
        }
        return auxOp;
    }

    private Servidor servidorMenorCarga(List<Servidor> servidoresHoras) {
        Servidor servidorMenor = servidoresHoras.get(0);

        for (Servidor servidor : servidoresHoras) {
            if (servidor.getHorasTotais() < servidorMenor.getHorasTotais()) {
                servidorMenor = servidor;
            }
        }
        return servidorMenor;
    }

    public void printAll(List<Vinculo> listVinculo) {
        if (listVinculo.size() == 0) {
            System.out.println("Não há vinculo cadastrados");
        } else {
            for (Vinculo vinculo : listVinculo) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + vinculo.getId());
                System.out.println("COMISSAO: " + vinculo.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + vinculo.getServidor().getNome());
                System.out.println("PAPEL: " + vinculo.getPapel());
                System.out.println("DATA DE ENTRADA: " + vinculo.getDtEntrada());
                System.out.println("DATA DE SAIDA: " + vinculo.getDtSaida());
                System.out.println("DATA DE CRIAÇÃO: " + vinculo.getDtCriacao());
                if (vinculo.getDtModificacao() != null) {
                    System.out.println("DATA DE MODIFICAÇÃO: " + vinculo.getDtModificacao());
                }
                System.out.println("-----------------------------------");
            }
        }
    }

    public void printId(List<Vinculo> listVinculo) {
        if (listVinculo.size() == 0) {
            System.out.println("Não há vinculo cadastrados");
        } else {
            for (Vinculo vinculo : listVinculo) {
                System.out.println("-----------------------------------");
                System.out.println("ID: " + vinculo.getId());
                System.out.println("COMISSAO: " + vinculo.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + vinculo.getServidor().getNome());
                System.out.println("-----------------------------------");
            }
        }
    }

}
