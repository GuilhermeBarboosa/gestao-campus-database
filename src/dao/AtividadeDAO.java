/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Atividade;
import model.Disciplina;
import model.Servidor;
import view.AtividadeView;

/**
 *
 * @author Gui
 */
public class AtividadeDAO {
    private int id=0;
    
    private Atividade[] atividades = new Atividade[5];
    private AtividadeView atividadeV = new AtividadeView();

    public AtividadeDAO(){
        this.setAtividade(atividadeV.criarTesteAlg());
        this.setAtividade(atividadeV.criarTesteED());
    }
    public void setAtividade(Atividade at) {
        for (int i = 0; i < atividades.length; i++) {
            if (atividades[i] == null) {
                at.setId(id);
                atividades[i] = at;
                id++;
                return;
            }
        }
    }

    public Atividade getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < atividades.length; i++) {
            if (atividades[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < atividades.length; i++) {
                if (atividades[i].getId() == auxLoc) {
                    return atividades[i];
                }
            }
        }

        return null;
    }

    public Atividade[] findAtividade(int idServidor) {
       Atividade[] vetAtividade = new Atividade[10];
        int cont = 0;
        for (Atividade atividade : atividades) {
            if (atividade != null) {
                if (atividade.getServidor().getId() == idServidor) {
                    vetAtividade[cont] = atividade;
                    cont++;
                }
            }
        }
        return vetAtividade;
    }
     
     
    public void update(Atividade atividadeAux) {
        this.atividades[atividadeAux.getId()] = atividadeAux;
    }

    public Atividade[] getAll() {
        return atividades;
    }

    public void delete(Atividade atividadeDelete) {
        atividades[atividadeDelete.getId()] = null;
    }

   
}
