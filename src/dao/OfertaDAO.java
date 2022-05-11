/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Oferta;
import view.OfertaView;

/**
 *
 * @author Gui
 */
public class OfertaDAO {

    private int id = 0;

    private Oferta[] ofertas = new Oferta[10];
    private OfertaView ofertaV = new OfertaView();

    public OfertaDAO() {
        this.setDisciplina(ofertaV.setTeste1());
        this.setDisciplina(ofertaV.setTeste2());
    }

    public void setDisciplina(Oferta of) {
        for (int i = 0; i < ofertas.length; i++) {
            if (ofertas[i] == null) {
                of.setId(id);
                ofertas[i] = of;
                id++;
                return;
            }
        }
    }

    public Oferta getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < ofertas.length; i++) {
            if (ofertas[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < ofertas.length; i++) {
                if (ofertas[i].getId() == auxLoc) {
                    return ofertas[i];
                }
            }
        }

        return null;
    }

    public Oferta[] findOfertaServidor(int id) {
        Oferta[] vetOfertas = new Oferta[10];
        int cont = 0;
        for (Oferta oferta : ofertas) {
            if (oferta != null) {
                if (oferta.getServidor().getId() == id) {
                    vetOfertas[cont] = oferta;
                    cont++;
                }
            }
        }
        return vetOfertas;
    }

    public void update(Oferta ofertaAux) {
        this.ofertas[ofertaAux.getId()] = ofertaAux;
    }

    public Oferta[] getAll() {
        return ofertas;
    }

    public void delete(Oferta ofertaDelete) {
        ofertas[ofertaDelete.getId()] = null;
    }

}
