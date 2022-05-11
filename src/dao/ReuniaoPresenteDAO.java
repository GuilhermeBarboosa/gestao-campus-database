/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.ReuniaoPresente;
import view.ReuniaoPresenteView;

/**
 *
 * @author Gui
 */
public class ReuniaoPresenteDAO {

    private int id = 0;

    private ReuniaoPresente[] reuniaoPresente = new ReuniaoPresente[100];

    private ReuniaoPresenteView reuniaoPresenteV = new ReuniaoPresenteView();

    public ReuniaoPresenteDAO() {
        this.setReuniaoPresente(reuniaoPresenteV.setTeste());
    }

    public void setReuniaoPresente(ReuniaoPresente reuniao) {
        for (int i = 0; i < reuniaoPresente.length; i++) {
            if (reuniaoPresente[i] == null) {
                reuniao.setId(id);
               
                reuniaoPresente[i] = reuniao;
                 id++;
                return;
            }
        }
    }

    public ReuniaoPresente getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < reuniaoPresente.length; i++) {
            if (reuniaoPresente[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < reuniaoPresente.length; i++) {
                if (reuniaoPresente[i].getId() == auxLoc) {
                    return reuniaoPresente[i];
                }
            }
        }

        return null;
    }

    public void update(ReuniaoPresente reuniao) {
        this.reuniaoPresente[reuniao.getId()] = reuniao;
    }

    public ReuniaoPresente[] getAll() {
        return reuniaoPresente;
    }

    public void delete(ReuniaoPresente reuniaoDelete) {
        reuniaoPresente[reuniaoDelete.getId()] = null;
    }
}
