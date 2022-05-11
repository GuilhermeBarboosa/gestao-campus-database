/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import dao.CampusDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Campus;
import model.Curso;

/**
 *
 * @author Usuario
 */
public class CursoView {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    Scanner ler = new Scanner(System.in);

    public Curso criarCurso(CampusDAO campusDAO) {
        String campusRead = "";
        Curso curso = new Curso();
        System.out.println("Nome: ");
        curso.setNome(ler.nextLine());
        System.out.println("Estado: ");
        curso.setEstado(ler.nextLine());
        System.out.println("Data de inicio: ");
        curso.setAnoInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de término: ");
        curso.setAnoTermino(LocalDate.parse(ler.nextLine(), formatter));
        
        Campus[] campusAll = campusDAO.read();
        for (Campus campus : campusAll) {
            if (campus != null) {
                campusRead += "ID: " + campus.getId() + " - NOME: " + campus.getNome() + "\n";
            }
        }
        System.out.println(campusRead);
        System.out.println("Escolha pelo id um campus:");
        Campus campusAux = campusDAO.getId(Integer.parseInt(ler.nextLine()));
       
        
        curso.setCampus(campusAux);

        curso.setDtCriacao(LocalDate.now());
        return curso;
    }

    public Curso modifCurso(Curso cursoAlt, CampusDAO campusDAO) {
        String campusRead = "";
        System.out.println("Nome: ");
        cursoAlt.setNome(ler.nextLine());
        System.out.println("Estado: ");
        cursoAlt.setEstado(ler.nextLine());
        System.out.println("Data de inicio: ");
        cursoAlt.setAnoInicio(LocalDate.parse(ler.nextLine(), formatter));
        System.out.println("Data de término: ");
        cursoAlt.setAnoTermino(LocalDate.parse(ler.nextLine(), formatter));
        
        
        Campus[] campusAll = campusDAO.read();
        for (Campus campus : campusAll) {
            if (campus != null) {
                campusRead += "ID: " + campus.getId() + " - NOME: " + campus.getNome() + "\n";
            }
        }
        System.out.println(campusRead);
        System.out.println("Escolha pelo id um campus:");
        Campus campusAux = campusDAO.getId(Integer.parseInt(ler.nextLine()));
        
        
        cursoAlt.setCampus(campusAux);

        cursoAlt.setDtModificacao(LocalDate.now());
        return cursoAlt;
    }

    public void mostrarTodosCursos(Curso[] cursos, CampusDAO campusDAO) {
        boolean aux = false;
        String stringResponse = "";
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] != null) {
                aux = true;
            }
        }

        if (aux) {
            for (int i = 0; i < cursos.length; i++) {
                if (cursos[i] != null) {
                    System.out.println(cursos[i].toString());
                }
            }
            System.out.println(stringResponse);
        } else {
            System.out.println("Não ha cursos cadastrados.");
        }
    }
   
   
  //--------------------------------------------------------------
    public Curso setAleatorio1() {
        CampusDAO campusDAO = new CampusDAO();
        Campus campusAux = campusDAO.getId(0);
        
        Curso curso = new Curso();
        curso.setNome("ADS");
        curso.setEstado("Ativo");
        curso.setCampus(campusAux);
        curso.setAnoInicio(LocalDate.parse("11/03/2022", formatter));
        curso.setAnoTermino(LocalDate.parse("11/07/2025", formatter));
        curso.setDtCriacao(LocalDate.now());
        return curso;
    }

    public Curso setAleatorio2() {
         CampusDAO campusDAO = new CampusDAO();
       
        Campus campusAux = campusDAO.getId(1);
        
        Curso curso = new Curso();
        curso.setNome("ENGENHARIA DE COMPUTAÇÃO");
        curso.setEstado("Ativo");
        curso.setCampus(campusAux);
        curso.setAnoInicio(LocalDate.parse("11/03/2022", formatter));
        curso.setAnoTermino(LocalDate.parse("11/07/2025", formatter));
        curso.setDtCriacao(LocalDate.now());
        return curso;
    }
  //--------------------------------------------------------------
 
}
