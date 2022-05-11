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
import model.ReuniaoPresente;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ReuniaoPresenteView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public ReuniaoPresente criarReuniaoPresente(ServidorDAO servidorDAO, ComissaoDAO comDAO) {
        String servidorRead = "";
        String comissaoRead = "";

        ReuniaoPresente reuniaoPresente = new ReuniaoPresente();

        Comissao[] comAll = comDAO.getAll();
        for (Comissao com : comAll) {
            if (com != null) {
                comissaoRead += "ID: " + com.getId() + " - comissao: " + com.getNameComissao() + "\n";
            }
        }
        System.out.println(comissaoRead);
        System.out.println("Comissao: ");
        Comissao comAux = comDAO.getById(Integer.parseInt(ler.nextLine()));
        reuniaoPresente.setComissao(comAux);
        
        
        System.out.println("Ata de reunião:  ");
        reuniaoPresente.setAtaReuniao(ler.nextLine());
        
        
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);

       
     
        System.out.println("Quantos servidores vai inserir: ");
        int tamanhoFor = Integer.parseInt(ler.nextLine());
        for (int i = 0; i < tamanhoFor; i++) {
            System.out.println("Servidor: ");
            Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
            reuniaoPresente.setServidor(servAux);
        }

        reuniaoPresente.setDtCriacao(LocalDate.now());
        return reuniaoPresente;
    }

    public ReuniaoPresente modifReuniaoPresente(ReuniaoPresente reunPresenteAlt, ServidorDAO servidorDAO, ComissaoDAO comDAO) {
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
        reunPresenteAlt.setComissao(comAux);
        
        reunPresenteAlt.clearServidores();
        
        System.out.println("Ata de reunião:  ");
        reunPresenteAlt.setAtaReuniao(ler.nextLine());
        
        
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        

         System.out.println("Quantos servidores vai inserir: ");
        int tamanhoFor = Integer.parseInt(ler.nextLine());
        for (int i = 0; i < tamanhoFor; i++) {
            System.out.println("Servidor: ");
            Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
            reunPresenteAlt.setServidor(servAux);
        }

        reunPresenteAlt.setDtModificacao(LocalDate.now());
        return reunPresenteAlt;
    }

    public void mostrarTodosReunioesPresente(ReuniaoPresente[] reuniao, ServidorDAO servidorDAO, ComissaoDAO comissaoDAO) {
        boolean aux = false;
        String responseString = "";
        for (int i = 0; i < reuniao.length; i++) {
            if (reuniao[i] != null) {
                aux = true;
            }
        }

        if (aux) {
            for (int i = 0; i < reuniao.length; i++) {
                if (reuniao[i] != null) {
                    System.out.println(reuniao[i].toString());
                }
            }
            System.out.println(responseString);
        } else {
            System.out.println("Não ha reunião cadastrados.");
        }
    }
    /////////////////////////////////////////////////////


    public ReuniaoPresente setTeste() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(0);
        
        ServidorDAO servDAO = new ServidorDAO();
        
        
        ReuniaoPresente reuniPresente = new ReuniaoPresente();
        reuniPresente.setComissao(comAux);
        Servidor servAux = servDAO.getById(0);
        reuniPresente.setServidor(servAux);
        
        servAux = servDAO.getById(1);
        reuniPresente.setServidor(servAux);
        
        servAux = servDAO.getById(2);
        reuniPresente.setServidor(servAux);
        reuniPresente.setAtaReuniao("Reuniao sobre o inicio");
        reuniPresente.setDtCriacao(LocalDate.now());
        return reuniPresente;
    }
}
