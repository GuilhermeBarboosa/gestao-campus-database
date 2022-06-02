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
public class ReuniaoPresente {

    private int id;
    private Comissao comissao;
    private String ataReuniao;
    private Servidor servidor;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Comissao getComissao() {
        return comissao;
    }

    public void setComissao(Comissao comissao) {
        this.comissao = comissao;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getAtaReuniao() {
        return ataReuniao;
    }

    public void setAtaReuniao(String ataReuniao) {
        this.ataReuniao = ataReuniao;
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
//
//        String aux = "";
//        aux += "ID: " + this.id + "\n";
//        Servidor[] servAll = this.getServidor();
//
//        if (comissao != null) {
//            aux += "Comissao: " + comissao.getNameComissao() + "\n";
//        }
//
//        aux += "Ata de reunião: " + this.ataReuniao + "\n";
//
//        for (Servidor servidorVet : servAll) {
//            if (servidorVet != null) {
//
//                aux += "Servidor: " + servidorVet.getNome() + "\n";
//
//            }
//        }
//
//        aux += "Data de criação: " + this.dtCriacao + "\n";
//        if (this.getDtModificacao() != null) {
//            aux += "Data de modificação: " + this.dtModificacao + "\n";
//        }
//        aux += "\n\n";
//        return aux;
//    }

   

}
