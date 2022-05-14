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
public class Curso {

    private int id;
    private int id_campus;
    private String nome;
    private String estado;
    private int anoInicio;
    private int anoTermino;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_campus() {
        return id_campus;
    }

    public void setId_campus(int id_campus) {
        this.id_campus = id_campus;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getAnoTermino() {
        return anoTermino;
    }

    public void setAnoTermino(int anoTermino) {
        this.anoTermino = anoTermino;
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
//        stringResponse += "ID: " + this.id + "\n";
//        stringResponse += "Nome: " + this.nome + "\n";
//        if (campus != null) {
//          stringResponse += "Campus:" + campus.getNome()+ "\n";
//        }
//        stringResponse += "Estado: " + this.estado + "\n";
//        stringResponse += "Ano de inicio: " + this.anoInicio + "\n";
//        stringResponse += "Ano de termino: " + this.anoTermino + "\n";
//        stringResponse += "Data de criação: " + this.dtCriacao + "\n";
//        if (this.getDtModificacao() != null) {
//            stringResponse += "Data de modificação: " + this.dtModificacao + "\n";
//        }
//        stringResponse += "\n\n";
//        return stringResponse;
//    }

}
