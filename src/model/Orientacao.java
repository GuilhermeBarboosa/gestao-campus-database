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
public class Orientacao {

    private int id;
    private String tipo;
    private Servidor servidor;
    private String nomeAluno;
    private double horasSemanais;
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public double getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(double horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public LocalDate getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(LocalDate dtInicio) {
        this.dtInicio = dtInicio;
    }

    public LocalDate getDtTermino() {
        return dtTermino;
    }

    public void setDtTermino(LocalDate dtTermino) {
        this.dtTermino = dtTermino;
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
//
//    @Override
//    public String toString() {
//        String aux = "";
//        aux += "ID: " + this.id + "\n";
//        aux += "Tipo: " + this.tipo + "\n";
//
//        if (servidor != null) {
//            aux += "Servidor:" + servidor.getNome() + "\n";
//        }
//
//        aux += "Nome do aluno: " + this.nomeAluno + "\n";
//        aux += "Data de inicio: " + this.dtInicio + "\n";
//        aux += "Data de termino: " + this.dtTermino + "\n";
//        aux += "Data de criacao: " + this.dtCriacao + "\n";
//        if (this.getDtModificacao() != null) {
//            aux += "Data de modificacao: " + this.dtModificacao + "\n";
//        }
//        return aux;
//    }

}
