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
import model.Servidor;
import model.Vinculo;

/**
 *
 * @author Gui
 */
public class VinculoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    int menu = 0;
    int auxOPC = 0;

    public Vinculo criarVinculo(List<String> servidorVet, List<String> comissaoVet, ServidorDAO servidorDAO) {
        try {
            Vinculo vinc = new Vinculo();

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhuma comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id uma comissao: ");
            vinc.setId_comissao(Integer.parseInt(ler.nextLine()));

            menu = tipoEscolha();
            if (menu == 1) {
                Servidor servResponse = servidorMenorCarga(servidorDAO.getServidoresHoras());
                System.out.println("O servidor " + servResponse.getNome() + " foi selecionado");
                vinc.setId_servidor(servResponse.getId());
            } else if (menu == 2) {

                if (servidorVet.size() == 0) {
                    System.out.println("Nenhum servidor cadastrado...");
                    return null;
                } else {
                    for (String string : servidorVet) {
                        System.out.println(string);
                    }
                }
                System.out.println("Escolha pelo id um Servidor:");
                vinc.setId_servidor(Integer.parseInt(ler.nextLine()));

            }

            System.out.println("Papel: ");
            vinc.setPapel(ler.nextLine());
            System.out.println("Data de entrada: ");
            vinc.setDtEntrada(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de saida: ");
            vinc.setDtSaida(LocalDate.parse(ler.nextLine(), formatter));
            vinc.setDtCriacao(LocalDate.now());
            return vinc;
        } catch (Exception e) {
            return null;
        }

    }

    public Vinculo modifVinculo(Vinculo vinAlt, List<String> servidorVet, List<String> comissaoVet, ServidorDAO servidorDAO) {

        try {

            if (comissaoVet.size() == 0) {
                System.out.println("Nenhuma comissao cadastrado...");
                return null;
            } else {
                for (String string : comissaoVet) {
                    System.out.println(string);
                }
            }
            System.out.println("Escolha pelo id uma comissao: ");
            vinAlt.setId_comissao(Integer.parseInt(ler.nextLine()));

            menu = tipoEscolha();
            if (menu == 1) {
                Servidor servResponse = servidorMenorCarga(servidorDAO.getServidoresHoras());
                System.out.println("O servidor " + servResponse.getNome() + " foi selecionado");
                vinAlt.setId_servidor(servResponse.getId());
            } else if (menu == 2) {

                if (servidorVet.size() == 0) {
                    System.out.println("Nenhum servidor cadastrado...");
                    return null;
                } else {
                    for (String string : servidorVet) {
                        System.out.println(string);
                    }
                }
                System.out.println("Escolha pelo id um Servidor:");
                vinAlt.setId_servidor(Integer.parseInt(ler.nextLine()));

            }

            System.out.println("Papel: ");
            vinAlt.setPapel(ler.nextLine());
            System.out.println("Data de entrada: ");
            vinAlt.setDtEntrada(LocalDate.parse(ler.nextLine(), formatter));
            System.out.println("Data de saida: ");
            vinAlt.setDtSaida(LocalDate.parse(ler.nextLine(), formatter));
            vinAlt.setDtModificacao(LocalDate.now());
            return vinAlt;
        } catch (Exception e) {
            return null;
        }
    }

    public void mostrarTodosVinculos(List<String> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há vinculo cadastrados");
        } else {
            for (String string : vetResult) {
                System.out.println(string);
            }
        }
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

}
