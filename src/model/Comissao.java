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
public class Comissao {
    private int id;
    private String nameComissao;
    private double horasSemanais;
    private LocalDate dtInicio;
    private LocalDate dtTermino;
    private String estado;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNameComissao() {
        return nameComissao;
    }

    public void setNameComissao(String nameComissao) {
        this.nameComissao = nameComissao;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    @Override
    public String toString() {
        String aux = "";
        aux += "ID: " + this.id + "\n";
        aux += "Comissao: " + this.nameComissao + "\n";
        aux += "Horas semanais: " + this.horasSemanais + "\n";
        aux += "Data de inicio: " + this.dtInicio + "\n";
        aux += "Data de termino: " + this.dtTermino + "\n";
        aux += "Estado: " + this.estado + "\n";
        aux += "Data de criação: " + this.dtCriacao + "\n";
       if(this.dtModificacao != null){
           aux += "Data de modificacao: " + this.dtModificacao;
       }
       return aux;
    }
    
    
}
