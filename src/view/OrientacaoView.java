/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Orientacao;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class OrientacaoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Orientacao criarOrientacao(ServidorDAO servidorDAO) {
        String servidorRead = "";
        Orientacao or = new Orientacao();
        
        
         Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Escolha pelo id um Servidor:");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        or.setServidor(servAux);
        
        
        
        
        System.out.println("Tipo: ");
        or.setTipo(ler.nextLine());
        System.out.println("Nome do aluno:  ");
        or.setNomeAluno(ler.nextLine());
        System.out.println("Horas semanais: ");
        or.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        or.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        or.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        or.setDtCriacao(LocalDate.now());
        return or;
    }

    public Orientacao modifOrientacao(Orientacao orAlt, ServidorDAO servidorDAO) {
        String servidorRead = "";
        
        
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Escolha pelo id um Servidor:");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        orAlt.setServidor(servAux);
        
        
        
        System.out.println("Tipo: ");
        orAlt.setTipo(ler.nextLine());
        System.out.println("Nome do aluno:  ");
        orAlt.setNomeAluno(ler.nextLine());
        System.out.println("Horas semanais: ");
        orAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        orAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        orAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        orAlt.setDtModificacao(LocalDate.now());
        return orAlt;

    }

    public void mostrarTodasOrientacoes(Orientacao[] orientacoes, ServidorDAO servidorDAO) {
        boolean aux = false;
        String responseString = "";
        for (int i = 0; i < orientacoes.length; i++) {
            if (orientacoes[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < orientacoes.length; i++) {
                if (orientacoes[i] != null) {
                    System.out.println(orientacoes[i].toString());
                }

            }
            System.out.println(responseString);
        } else {
            System.out.println("Não ha orientacao cadastrados.");
        }
    }

    public Orientacao criarTeste() {
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(0);
        
        
        Orientacao ot = new Orientacao();
        ot.setServidor(servAux);
        ot.setNomeAluno("Gabriel");
        ot.setTipo("Estagio");
        ot.setHorasSemanais(5);
        ot.setDtInicio(LocalDate.parse("08/04/2022", formatter));
        ot.setDtTermino(LocalDate.parse("15/06/2022", formatter));
        ot.setDtCriacao(LocalDate.now());
        return ot;
    }

    public Orientacao criarTesteDois() {
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(0);
        
        Orientacao ot = new Orientacao();
        ot.setServidor(servAux);
        ot.setNomeAluno("Joao");
        ot.setTipo("TCC");
        ot.setHorasSemanais(5);
        ot.setDtInicio(LocalDate.parse("08/05/2022", formatter));
        ot.setDtTermino(LocalDate.parse("20/07/2022", formatter));
        ot.setDtCriacao(LocalDate.now());
        return ot;
    }

}
