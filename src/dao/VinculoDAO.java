/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Comissao;
import model.Vinculo;
import view.VinculoView;

/**
 *
 * @author Gui
 */
public class VinculoDAO {
     private int id = 0;

    private Vinculo[] vinculos = new Vinculo[50];

    private VinculoView vincV = new VinculoView();

    public VinculoDAO() {
            this.setVinculo(vincV.setAleatorio1());
            this.setVinculo(vincV.setAleatorio2());
    }

    public void setVinculo(Vinculo vinc) {
        for (int i = 0; i < vinculos.length; i++) {
            if (vinculos[i] == null) {
                vinc.setId(id);
                vinculos[i] = vinc;
                id++;
                return;
            }
        }
    }

    public Vinculo getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < vinculos.length; i++) {
            if (vinculos[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < vinculos.length; i++) {
                if (vinculos[i].getId() == auxLoc) {
                    return vinculos[i];
                }
            }
        }

        return null;
    }

    public void update(Vinculo vinculo) {
        this.vinculos[vinculo.getId()] = vinculo;
    }

    public Vinculo[] getAll() {
        return vinculos;
    }

    public void delete(Vinculo vincDelete) {
        vinculos[vincDelete.getId()] = null;
    }

    public Vinculo[] findVinculo(int idServ) {
        Vinculo[] vetVinculo = new Vinculo[10];
        int cont = 0;
        for (Vinculo vinculo : vinculos) {
            if (vinculo != null) {
                if (vinculo.getServidor().getId() == idServ) {
                    vetVinculo[cont] = vinculo;
                    cont++;
                }
            }
        }
        return vetVinculo;
    }
    
    
    public void encerrarVinculos(Comissao comAux) {
        
        for (int i = 0; i < vinculos.length; i++) {
            if(vinculos[i] != null){
                if (vinculos[i].getId() == comAux.getId()) {
                    vinculos[i] = null;
                    return;
                }
            }
            
        }
    }
}
