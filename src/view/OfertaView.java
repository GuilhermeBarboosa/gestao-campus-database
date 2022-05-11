/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.ServidorDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Curso;
import model.Disciplina;
import model.Oferta;
import model.Servidor;

/**
 *
 * @author Gui
 */
public class OfertaView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Oferta criarOferta(ServidorDAO servidorDAO, CursoDAO cursoDAO, DisciplinaDAO discDAO) {
        String cursoRead = "";
        String disciplinaRead = "";
        String servidorRead = "";
        Oferta of = new Oferta();
        
        
       
        Curso[] cursoAll = cursoDAO.getAll();
        for (Curso curso : cursoAll) {
            if (curso != null) {
                cursoRead += "ID: " + curso.getId() + " - NOME: " + curso.getNome() + "\n";
            }
        }
        System.out.println(cursoRead);
        System.out.println("Escolha pelo id um curso:");
        Curso cursoAux = cursoDAO.getId(Integer.parseInt(ler.nextLine()));
        of.setCurso(cursoAux);

        
        
        
        Disciplina[] discAll = discDAO.getAll();
        for (Disciplina disc : discAll) {
            if (disc != null) {
                disciplinaRead += "ID: " + disc.getId() + " - NOME: " + disc.getNome() + "\n";
            }
        }
        System.out.println(disciplinaRead);
        System.out.println("Escolha pelo id uma disciplina:");
        Disciplina discAux = discDAO.getId(Integer.parseInt(ler.nextLine()));
        of.setDisciplina(discAux);

        
        
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Escolha pelo id um Servidor:");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        of.setServidor(servAux);


        
        
        System.out.println("Ano: ");
        of.setAno(Integer.parseInt(ler.nextLine()));
        System.out.println("Semestre: ");
        of.setSemestre(Integer.parseInt(ler.nextLine()));
        System.out.println("Aulas Semanais: ");
        of.setAulaSemanais(Integer.parseInt(ler.nextLine()));
        of.setDtCriacao(LocalDate.now());
        return of;
    }

    public Oferta modifOferta(Oferta ofertaAlt, ServidorDAO servidorDAO, CursoDAO cursoDAO, DisciplinaDAO discDAO) {
        String cursoRead = "";
        String disciplinaRead = "";
        String servidorRead = "";

        Curso[] cursoAll = cursoDAO.getAll();
        for (Curso curso : cursoAll) {
            if (curso != null) {
                cursoRead += "ID: " + curso.getId() + " - NOME: " + curso.getNome() + "\n";
            }
        }
        System.out.println(cursoRead);
        System.out.println("Escolha pelo id um curso:");
        Curso cursoAux = cursoDAO.getId(Integer.parseInt(ler.nextLine()));
        ofertaAlt.setCurso(cursoAux);

        
        
        
        Disciplina[] discAll = discDAO.getAll();
        for (Disciplina disc : discAll) {
            if (disc != null) {
                disciplinaRead += "ID: " + disc.getId() + " - NOME: " + disc.getNome() + "\n";
            }
        }
        System.out.println(disciplinaRead);
        System.out.println("Escolha pelo id uma disciplina:");
        Disciplina discAux = discDAO.getId(Integer.parseInt(ler.nextLine()));
        ofertaAlt.setDisciplina(discAux);

        
        
        
        Servidor[] servAll = servidorDAO.getAll();
        for (Servidor serv : servAll) {
            if (serv != null) {
                servidorRead += "ID: " + serv.getId() + " - NOME: " + serv.getNome() + "\n";
            }
        }
        System.out.println(servidorRead);
        System.out.println("Escolha pelo id um Servidor:");
        Servidor servAux = servidorDAO.getById(Integer.parseInt(ler.nextLine()));
        ofertaAlt.setServidor(servAux);

  
        System.out.println("Ano: ");
        ofertaAlt.setAno(Integer.parseInt(ler.nextLine()));
        System.out.println("Semestre: ");
        ofertaAlt.setSemestre(Integer.parseInt(ler.nextLine()));
        System.out.println("Aulas Semanais: ");
        ofertaAlt.setAulaSemanais(Integer.parseInt(ler.nextLine()));
        ofertaAlt.setDtMoficacao(LocalDate.now());
        return ofertaAlt;
    }

    public void mostrarTodasOfertas(Oferta[] ofertas, ServidorDAO servidorDAO, CursoDAO cursoDAO, DisciplinaDAO disciplinaDAO) {
        boolean aux = false;
        String responseString = "";
    
        for (int i = 0; i < ofertas.length; i++) {
            if (ofertas[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < ofertas.length; i++) {
                if (ofertas[i] != null) {
                    System.out.println(ofertas[i].toString());
                }
            }
            System.out.println(responseString);
        } else {
            System.out.println("Não ha oferta cadastrados.");
        }
    }

    public Oferta setTeste1() {
        CursoDAO cursoDAO = new CursoDAO();
        Curso cursoAux = cursoDAO.getId(1);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(0);
        
        DisciplinaDAO discDAO = new DisciplinaDAO();
        Disciplina discAux = discDAO.getId(0);
        
        Oferta of = new Oferta();
        of.setCurso(cursoAux);
        of.setServidor(servAux);
        of.setSemestre(2);
        of.setAno(2022);
        of.setDisciplina(discAux);
        of.setAulaSemanais(5);
        of.setDtCriacao(LocalDate.now());
        return of;
    }

    public Oferta setTeste2() {
        CursoDAO cursoDAO = new CursoDAO();
        Curso cursoAux = cursoDAO.getId(1);
        
        ServidorDAO servDAO = new ServidorDAO();
        Servidor servAux = servDAO.getById(1);
        
        DisciplinaDAO discDAO = new DisciplinaDAO();
        Disciplina discAux = discDAO.getId(1);
        
        Oferta of = new Oferta();
        of.setCurso(cursoAux);
        of.setServidor(servAux);
        of.setSemestre(1);
        of.setAno(2022);
        of.setDisciplina(discAux);
        of.setAulaSemanais(5);
        of.setDtCriacao(LocalDate.now());
        return of;
    }

}
