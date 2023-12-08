/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.LocalDate;

/**
 * @author Usuario
 */
public class Reuniao {

    private int id;
    private Comissao comissao;
    private LocalDate dtReuniao;
    private String conteudoAta;
    private Servidor servidorSecre;
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

    public Servidor getServidorSecre() {
        return servidorSecre;
    }

    public void setServidorSecre(Servidor servidorSecre) {
        this.servidorSecre = servidorSecre;
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


}
