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
public class Reuniao {

    private int id;
    private int id_comissao;
    private LocalDate dtReuniao;
    private String conteudoAta;
    private int id_servidorSecre;
    private LocalDate dtCriacao;
    private LocalDate dtModificacao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_comissao() {
        return id_comissao;
    }

    public void setId_comissao(int id_comissao) {
        this.id_comissao = id_comissao;
    }

    public LocalDate getDtReuniao() {
        return dtReuniao;
    }

    public void setDtReuniao(LocalDate dtReuniao) {
        this.dtReuniao = dtReuniao;
    }

    public String getConteudoAta() {
        return conteudoAta;
    }

    public void setConteudoAta(String conteudoAta) {
        this.conteudoAta = conteudoAta;
    }

    public int getId_servidorSecre() {
        return id_servidorSecre;
    }

    public void setId_servidorSecre(int id_servidorSecre) {
        this.id_servidorSecre = id_servidorSecre;
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
//        String aux = "";
//
//        aux += "ID: " + this.id + "\n";
//
//        if (comissao != null) {
//            aux += "Comissao: " + comissao.getNameComissao() + "\n";
//        }
//
//        aux += "Data de reunião: " + this.dtReuniao + "\n";
//        aux += "Conteudo da ata: " + this.conteudoAta + "\n";
//
//        if (servidorSecre != null) {
//            aux += "Servidor: " + servidorSecre.getNome() + "\n";
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
