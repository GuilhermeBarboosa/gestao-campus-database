/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Reuniao;
import view.ReuniaoView;

/**
 *
 * @author Gui
 */
public class ReuniaoDAO {
    
    private int id = 0;

    private Reuniao[] reunioes = new Reuniao[100];

    private ReuniaoView reuniaoV = new ReuniaoView();

    public ReuniaoDAO() {
        this.setReuniao(reuniaoV.setAleatorio1());
        this.setReuniao(reuniaoV.setAleatorio2());
        this.setReuniao(reuniaoV.setAleatorio3());
        this.setReuniao(reuniaoV.setAleatorio4());
    }

    public void setReuniao(Reuniao reuniao) {
        for (int i = 0; i < reunioes.length; i++) {
            if (reunioes[i] == null) {
                reuniao.setId(id);
              
                reunioes[i] = reuniao;
                  id++;
                return;
            }
        }
    }

    public Reuniao getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < reunioes.length; i++) {
            if (reunioes[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < reunioes.length; i++) {
                if (reunioes[i].getId() == auxLoc) {
                    return reunioes[i];
                }
            }
        }

        return null;
    }

    public void update(Reuniao reuniao) {
        this.reunioes[reuniao.getId()] = reuniao;
    }

    public Reuniao[] getAll() {
        return reunioes;
    }

    public void delete(Reuniao reuniaoDelete) {
        reunioes[reuniaoDelete.getId()] = null;
    }
}
