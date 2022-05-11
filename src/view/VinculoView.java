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

    public Vinculo criarVinculo(ServidorDAO servidorDAO, ComissaoDAO comDAO) {
        String servidorRead = "";
        String comissaoRead = "";

        Vinculo vinc = new Vinculo();

        Comissao[] comAll = comDAO.getAll();
        for (Comissao com : comAll) {
            if (com != null) {
                comissaoRead += "ID: " + com.getId() + " - comissao: " + com.getNameComissao() + "\n";
            }
        }
        System.out.println(comissaoRead);
        System.out.println("Comissao: ");
        Comissao comAux = comDAO.getById(Integer.parseInt(ler.nextLine()));
        vinc.setComissao(comAux);

        Servidor[] servAll = servidorDAO.getAll();

        menu = tipoEscolha();
        if (menu == 1) {
            Servidor servResponse = servidorMenorCarga(servAll);
            vinc.setServidor(servResponse);
        } else if (menu == 2) {

            for (Servidor serv : servAll) {
                if (serv != null) {
                    servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
                }
            }
            System.out.println(servidorRead);
            System.out.println("Escolha pelo id um Servidor:");
            Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
            vinc.setServidor(servAux);

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

    public Vinculo modifVinculo(Vinculo vinAlt, ServidorDAO servidorDAO, ComissaoDAO comDAO) {

        String servidorRead = "";
        String comissaoRead = "";

        Comissao[] comAll = comDAO.getAll();
        for (Comissao com : comAll) {
            if (com != null) {
                comissaoRead += "ID: " + com.getId() + " - comissao: " + com.getNameComissao() + "\n";
            }
        }
        System.out.println(comissaoRead);
        System.out.println("Comissao: ");
        Comissao comAux = comDAO.getById(Integer.parseInt(ler.nextLine()));
        vinAlt.setComissao(comAux);

        Servidor[] servAll = servidorDAO.getAll();

        menu = tipoEscolha();
        if (menu == 1) {
            Servidor servResponse = servidorMenorCarga(servAll);
            vinAlt.setServidor(servResponse);
        } else if (menu == 2) {

            for (Servidor serv : servAll) {
                if (serv != null) {
                    servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
                }
            }
            System.out.println(servidorRead);
            System.out.println("Escolha pelo id um Servidor:");
            Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
            vinAlt.setServidor(servAux);

        }

        System.out.println("Papel: ");
        vinAlt.setPapel(ler.nextLine());
        System.out.println("Data de entrada: ");
        vinAlt.setDtEntrada(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de saida: ");
        vinAlt.setDtSaida(LocalDate.parse(ler.nextLine(), formatter));
        vinAlt.setDtModificacao(LocalDate.now());
        return vinAlt;
    }

    private Servidor servidorMenorCarga(Servidor[] servAll) {
        Servidor servResponse = servAll[0];
        for (Servidor serv1 : servAll) {
            if (serv1 != null) {
                if (serv1.getHorasTotais() < servResponse.getHorasTotais()) {
                    servResponse = serv1;
                }
            }
        }
        return servResponse;
    }

    public void mostrarTodosVinculos(Vinculo[] vincs, ComissaoDAO comissaoDAO, ServidorDAO servidorDAO) {
        boolean aux = false;
        String responseString = "";
        for (int i = 0; i < vincs.length; i++) {
            if (vincs[i] != null) {
                aux = true;
            }
        }

        if (aux) {
            for (int i = 0; i < vincs.length; i++) {
                if (vincs[i] != null) {
                    System.out.println(vincs[i].toString());
                }

            }
            System.out.println(responseString);
        } else {
            System.out.println("Não ha vinculo cadastrados.");
        }
    }

    ///////////////////////////////////////////////
    //SO PARA TESTES
    public Vinculo setAleatorio1() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(0);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(0);
        
        
        Vinculo vinc = new Vinculo();
        vinc.setComissao(comAux);
        vinc.setServidor(servAux);
        vinc.setPapel("Presidente");
        vinc.setDtEntrada(LocalDate.parse("29/03/2022", formatter));
        vinc.setDtSaida(LocalDate.parse("15/04/2022", formatter));
        vinc.setDtCriacao(LocalDate.now());
        return vinc;
    }

    public Vinculo setAleatorio2() {
         ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(0);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(1);
        
        Vinculo vinc = new Vinculo();
        vinc.setComissao(comAux);
        vinc.setServidor(servAux);
        vinc.setPapel("Vice");
        vinc.setDtEntrada(LocalDate.parse("27/03/2022", formatter));
        vinc.setDtSaida(LocalDate.parse("28/04/2022", formatter));
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

}
