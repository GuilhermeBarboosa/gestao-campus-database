/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Comissao;
import view.ComissaoView;

/**
 *
 * @author Gui
 */
public class ComissaoDAO {

    private int id = 0;

    private Comissao[] comissoes = new Comissao[40];
    private ComissaoView comV = new ComissaoView();

    public ComissaoDAO() {
        this.setComissao(comV.setAleatorio1());
        this.setComissao(comV.setAleatorio2());
        this.setComissao(comV.setAleatorio3());
    }

    public void setComissao(Comissao com) {
        for (int i = 0; i < comissoes.length; i++) {
            if (comissoes[i] == null) {
                com.setId(id);
                comissoes[i] = com;
                id++;
                return;
            }
        }
    }

    public Comissao getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < comissoes.length; i++) {
            if (comissoes[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < comissoes.length; i++) {
                if (comissoes[i].getId() == auxLoc) {
                    return comissoes[i];
                }
            }
        }

        return null;
    }

    public Comissao getById(int id) {
        return comissoes[id];
    }

    public void update(Comissao comissao) {
        this.comissoes[comissao.getId()] = comissao;
    }

    public Comissao[] getAll() {
        return comissoes;
    }

    public void delete(Comissao comissaoDelete) {
        comissoes[comissaoDelete.getId()] = null;
    }

    public Comissao findComissao(int idCom) {
        int cont = 0;
        for (Comissao comissao : comissoes) {
            if (comissao != null) {
                if (comissao.getId() == idCom) {
                    return comissao;
                }
            }
        }
        return null;
    }

    public void modificarEncerradomento(Comissao encerrarComissao) {
        for (Comissao comissao : comissoes) {
            if(comissao != null){
                if(comissao.getId() == encerrarComissao.getId()){
                    comissao = encerrarComissao;
                }
            }
        }
    }
}
