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
    ComissaoView comissaoV = new ComissaoView();
    ServidorView servidorV = new ServidorView();
    Scanner ler = new Scanner(System.in);

    int menu = 0;
    int auxOPC = 0;

    public Vinculo criarVinculo(ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        try {
            Vinculo vinc = new Vinculo();

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhuma comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Escolha pelo id uma comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            vinc.setComissao(comissao);

            menu = tipoEscolha();
            if (menu == 1) {
                Servidor servResponse = servidorMenorCarga(servidorDAO.read());
                System.out.println("O servidor " + servResponse.getNome() + " foi selecionado");
                vinc.setServidor(servResponse);
            } else if (menu == 2) {

                List<Servidor> servidorVet = servidorDAO.read();
                if (servidorVet.size() == 0) {
                    System.out.println("Nenhum servidor cadastrado...");
                    return null;
                } else {
                    servidorV.mostrarIdServidores(servidorVet);
                }
                System.out.println("Escolha pelo id um Servidor:");
                Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
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
        } catch (Exception e) {
            return null;
        }

    }

    public Vinculo modifVinculo(Vinculo vinAlt, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {

        try {

            List<Comissao> comissaoVet = comissaoDAO.read();
            if (comissaoVet.size() == 0) {
                System.out.println("Nenhuma comissao cadastrado...");
                return null;
            } else {
                comissaoV.mostrarIdTodosComissao(comissaoVet);
            }
            System.out.println("Escolha pelo id uma comissao: ");
            Comissao comissao = comissaoDAO.find(Integer.parseInt(ler.nextLine()));
            vinAlt.setComissao(comissao);

            menu = tipoEscolha();
            if (menu == 1) {
                Servidor servResponse = servidorMenorCarga(servidorDAO.read());
                System.out.println("O servidor " + servResponse.getNome() + " foi selecionado");
                vinAlt.setServidor(servResponse);
            } else if (menu == 2) {

                List<Servidor> servidorVet = servidorDAO.read();
                if (servidorVet.size() == 0) {
                    System.out.println("Nenhum servidor cadastrado...");
                    return null;
                } else {
                    servidorV.mostrarIdServidores(servidorVet);
                }
                System.out.println("Escolha pelo id um Servidor:");
                Servidor servidor = servidorDAO.find(Integer.parseInt(ler.nextLine()));
                vinAlt.setServidor(servidor);

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

    public void mostrarTodosVinculos(List<Vinculo> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há vinculo cadastrados");
        } else {
            for (Vinculo vinculo : vetResult) {
                System.out.println("ID: " + vinculo.getId());
                System.out.println("COMISSAO: " + vinculo.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + vinculo.getServidor().getNome());
                System.out.println("PAPEL: " + vinculo.getPapel());
                System.out.println("DATA DE ENTRADA: " + vinculo.getDtEntrada());
                System.out.println("DATA DE SAIDA: " + vinculo.getDtSaida());
                System.out.println("DATA DE CRIAÇÃO: " + vinculo.getDtCriacao());
                System.out.println("DATA DE MODIFICAÇÃO: " + vinculo.getDtModificacao());
            }
        }
    }

    public void mostrarIdTodosVinculos(List<Vinculo> vetResult) {
        if (vetResult.size() == 0) {
            System.out.println("Não há vinculo cadastrados");
        } else {
            for (Vinculo vinculo : vetResult) {
                System.out.println("ID: " + vinculo.getId());
                System.out.println("COMISSAO: " + vinculo.getComissao().getNameComissao());
                System.out.println("SERVIDOR: " + vinculo.getServidor().getNome());
            }
        }
    }

}
