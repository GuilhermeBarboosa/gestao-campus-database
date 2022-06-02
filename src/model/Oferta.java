/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author Usuario
 */
public class Oferta {

    private int id;
    private Curso curso;
    private Servidor servidor;
    private Disciplina disciplina;
    private int ano;
    private int semestre;
    private int aulaSemanais;
    private LocalDate dtCriacao;
    private LocalDate dtMoficacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getAulaSemanais() {
        return aulaSemanais;
    }

    public void setAulaSemanais(int aulaSemanais) {
        this.aulaSemanais = aulaSemanais;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public LocalDate getDtMoficacao() {
        return dtMoficacao;
    }

    public void setDtMoficacao(LocalDate dtMoficacao) {
        this.dtMoficacao = dtMoficacao;
    }

//    @Override
//    public String toString() {
//
//        String aux = "";
//        aux += "ID: " + this.id + "\n";
//
//        if (curso != null) {
//            aux += "Curso: " + curso.getNome() + "\n";
//        }
//
//        if (disciplina != null) {
//            aux += "Disciplina: " + disciplina.getNome() + "\n";
//        }
//
//        if (servidor != null) {
//            aux += "Professor: " + servidor.getNome() + "\n";
//        }
//
//        aux += "Ano: " + this.ano + "\n";
//        aux += "Semestre: " + this.semestre + "\n";
//        aux += "Aula Semanais: " + this.aulaSemanais + "\n";
//        aux += "Data de criação: " + this.dtCriacao+ "\n";
//        if (this.getDtMoficacao() != null) {
//            aux += "Aula Semanais: " + this.dtMoficacao + "\n";
//        }
//        aux += "\n\n";
//        return aux;
//    }
}
