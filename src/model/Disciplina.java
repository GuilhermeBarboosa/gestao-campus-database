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
public class Disciplina {

    private int id;
    private String nome;
    private int id_curso;
    private double cargaHoraria;
    private String periodo;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(double CargaHoraria) {
        this.cargaHoraria = CargaHoraria;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public LocalDate getDtCriacao() {
        return dtCriacao;
    }

    public void setDtCriacao(LocalDate dtCriacao) {
        this.dtCriacao = dtCriacao;
    }

    public LocalDate getDtModificacao() {
        return dtModificacao;
    }

    public void setDtModificacao(LocalDate dtModificacao) {
        this.dtModificacao = dtModificacao;
    }

//    @Override
//    public String toString() {
//        String stringResponse = "";
//        stringResponse += "ID: " + this.id+ "\n";
//        stringResponse += "Nome: " + this.nome + "\n";
//        if (curso != null) {
//            stringResponse += "Curso:" + curso.getNome() + "\n";
//        }
//        stringResponse += "Carga Horaria: " + this.cargaHoraria + "\n";
//        stringResponse += "Periodo: " + this.periodo + "\n";
//        stringResponse += "Data de criacao: " + this.dtCriacao + "\n";
//        if (this.getDtModificacao() != null) {
//            stringResponse += "Data de modificacao: " + this.dtModificacao + "\n";
//        }
//        stringResponse += "\n\n";
//        return stringResponse;
//    }

}
