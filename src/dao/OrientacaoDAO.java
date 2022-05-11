/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Orientacao;
import view.OrientacaoView;

/**
 *
 * @author Gui
 */
public class OrientacaoDAO {

    private int id = 0;

    private Orientacao[] orientacoes = new Orientacao[10];
    private OrientacaoView orientacaoV = new OrientacaoView();

    public OrientacaoDAO(){
        this.setOrientacao(orientacaoV.criarTeste());
        this.setOrientacao(orientacaoV.criarTesteDois());
    }
    public void setOrientacao(Orientacao or) {
        for (int i = 0; i < orientacoes.length; i++) {
            if (orientacoes[i] == null) {
                or.setId(id);
              
                orientacoes[i] = or;
                  id++;
                return;
            }
        }
    }

    public Orientacao getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < orientacoes.length; i++) {
            if (orientacoes[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < orientacoes.length; i++) {
                if (orientacoes[i].getId() == auxLoc) {
                    return orientacoes[i];
                }
            }
        }

        return null;
    }

    public Orientacao[] findOrientacao(int idServ) {
      Orientacao[] vetOrientacao = new Orientacao[10];
        int cont = 0;
        for (Orientacao orientacao : orientacoes) {
            if (orientacao != null) {
                if (orientacao.getServidor().getId() == idServ) {
                    vetOrientacao[cont] = orientacao;
                    cont++;
                }
            }
        }
        return vetOrientacao;
    }

    public void update(Orientacao orientacaoAux) {
        this.orientacoes[orientacaoAux.getId()] = orientacaoAux;
    }

    public Orientacao[] getAll() {
        return orientacoes;
    }

    public void delete(Orientacao orientacaoDelete) {
        orientacoes[orientacaoDelete.getId()] = null;
    }

}
