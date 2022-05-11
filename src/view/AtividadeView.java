/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.DisciplinaDAO;
import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Atividade;
import model.Disciplina;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class AtividadeView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Atividade criarAtividade(ServidorDAO servidorDAO) {
        String servidorRead = "";

        Atividade at = new Atividade();
        
        Servidor[] servAll = servidorDAO.getAll();
        System.out.println("Escolha pelo id um Servidor:");
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Servidor: ");
        Servidor servAux = servidorDAO.getId(Integer.parseInt(ler.nextLine()));
        at.setServidor(servAux);
        
        
        
        System.out.println("Descricao: ");
        at.setDescricao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        at.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        at.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        at.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }

    public Atividade modifAtividade(Atividade atAlt, ServidorDAO servidorDAO) {
        String servidorRead = "";
        System.out.println("Escolha pelo id um Servidor:");
        
         Servidor[] servAll = servidorDAO.getAll();
        System.out.println("Escolha pelo id um Servidor:");
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Servidor: ");
        Servidor servAux = servidorDAO.getId(Integer.parseInt(ler.nextLine()));
       
        atAlt.setServidor(servAux);

        System.out.println("Descricao: ");
        atAlt.setDescricao(ler.nextLine());
        System.out.println("Horas Semanais:  ");
        atAlt.setHorasSemanais(Double.parseDouble(ler.nextLine()));
        System.out.println("Data de inicio: ");
        atAlt.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        atAlt.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        atAlt.setDtModificacao(LocalDate.now());
        return atAlt;
    }

    public void mostrarTodasAtividades(Atividade[] at) {
        boolean aux = false;
        for (int i = 0; i < at.length; i++) {
            if (at[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < at.length; i++) {
                if (at[i] != null) {
                    System.out.println(at[i].toString());
                }
            }
        } else {
            System.out.println("Não ha atividades cadastrados.");
        }
    }

    public Atividade criarAula(Servidor servAux, Disciplina discAux, double horasSemanais) {
        Atividade at = new Atividade();
        at.setServidor(servAux);
        at.setDescricao("Preparação da aula " + discAux.getNome());
        at.setHorasSemanais(horasSemanais);
        System.out.println("Data de inicio: ");
        at.setDtInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de termino: ");
        at.setDtTermino(LocalDate.parse(ler.nextLine(), formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }

    public Atividade criarTesteAlg() {
        ServidorDAO servDAO = new ServidorDAO(); 
        Servidor servAux = servDAO.getById(0);
        
        
        Atividade at = new Atividade();
        at.setServidor(servAux);
        at.setDescricao("AULA DE ALGORITMO");
        at.setHorasSemanais(4);
        at.setDtInicio(LocalDate.parse("01/02/2022", formatter));
         at.setDtTermino(LocalDate.parse("15/05/2022", formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }

     public Atividade criarTesteED() {
        ServidorDAO servDAO = new ServidorDAO(); 
        Servidor servAux = servDAO.getById(1); 
         
        Atividade at = new Atividade();
        at.setServidor(servAux);
        at.setDescricao("AULA DE ED");
        at.setHorasSemanais(4);
         at.setDtInicio(LocalDate.parse("01/02/2022", formatter));
         at.setDtTermino(LocalDate.parse("15/05/2022", formatter));
        at.setDtCriacao(LocalDate.now());
        return at;
    }

  
}
