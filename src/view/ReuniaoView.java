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
import model.Reuniao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class ReuniaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Reuniao criarReuniao(ServidorDAO servidorDAO, ComissaoDAO comDAO) {
        String servidorRead = "";
        String comissaoRead = "";

        Reuniao reuniao = new Reuniao();

        Comissao[] comAll = comDAO.getAll();
        for (Comissao com : comAll) {
            if (com != null) {
                comissaoRead += "ID: " + com.getId() + " - comissao: " + com.getNameComissao() + "\n";
            }
        }
        System.out.println(comissaoRead);
        System.out.println("Comissao: ");
        Comissao comAux = comDAO.getById(Integer.parseInt(ler.nextLine()));
        reuniao.setComissao(comAux);
        

        System.out.println("Data da reunião:  ");
        reuniao.setDtReuniao(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Conteudo da ata: ");
        reuniao.setConteudoAta(ler.nextLine());

        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);  
        System.out.println("Servidor: ");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        reuniao.setServidorSecre(servAux);

        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }

    public Reuniao modifReuniao(Reuniao reunAlt, ServidorDAO servidorDAO, ComissaoDAO comDAO) {
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
        reunAlt.setComissao(comAux);
        
        System.out.println("Data da reunião:  ");
        reunAlt.setDtReuniao(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Conteudo da ata: ");
        reunAlt.setConteudoAta(ler.nextLine());
       
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);  
        System.out.println("Servidor: ");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        reunAlt.setServidorSecre(servAux);
        
        
        
        reunAlt.setDtModificacao(LocalDate.now());
        return reunAlt;
    }

    public void mostrarTodosReunioes(Reuniao[] reuniao, ServidorDAO servidoDAO, ComissaoDAO comissaoDAO) {
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


    public Reuniao setAleatorio1() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(0);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(0);
        
        
        Reuniao reuniao = new Reuniao();
        reuniao.setComissao(comAux);
        reuniao.setConteudoAta("bla bla bla");
        reuniao.setDtReuniao(LocalDate.parse("02/04/2022", formatter));
        reuniao.setServidorSecre(servAux);
        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }

    public Reuniao setAleatorio2() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(1);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(1);
        
        Reuniao reuniao = new Reuniao();
        reuniao.setComissao(comAux);
        reuniao.setConteudoAta("ble ble ble");
        reuniao.setDtReuniao(LocalDate.parse("10/04/2022", formatter));
        reuniao.setServidorSecre(servAux);
        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }

    public Reuniao setAleatorio3() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(2);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(2);
        
        Reuniao reuniao = new Reuniao();
        reuniao.setComissao(comAux);
        reuniao.setConteudoAta("blu blu blu");
        reuniao.setDtReuniao(LocalDate.parse("05/07/2022", formatter));
        reuniao.setServidorSecre(servAux);
        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }

    public Reuniao setAleatorio4() {
        ComissaoDAO comDAO = new ComissaoDAO();
        Comissao comAux = comDAO.getById(1);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(3);
        
        Reuniao reuniao = new Reuniao();
        reuniao.setComissao(comAux);
        reuniao.setConteudoAta("blo blo blo");
        reuniao.setDtReuniao(LocalDate.parse("10/10/2022", formatter));
        reuniao.setServidorSecre(servAux);
        reuniao.setDtCriacao(LocalDate.now());
        return reuniao;
    }

}
