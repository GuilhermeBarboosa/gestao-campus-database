/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Disciplina;
import view.DisciplinaView;

/**
 *
 * @author Gui
 */
public class DisciplinaDAO {
    private int id=0;
    
    private Disciplina[] disciplinas = new Disciplina[10];
    
    private DisciplinaView discV = new DisciplinaView();
    
    
    
    public DisciplinaDAO() {
        Disciplina disc1 = new Disciplina();
        disc1 = discV.setAleatorio1();
        this.setDisciplina(disc1);
        Disciplina disc2 = new Disciplina();
        disc2 = discV.setAleatorio2();
        this.setDisciplina(disc2);
    }

    public void setDisciplina(Disciplina disciplina) {
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] == null) {
                disciplina.setId(id);
                disciplinas[i] = disciplina;
                id++;
                return;
            }
        }
    }

    public Disciplina getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < disciplinas.length; i++) {
            if (disciplinas[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < disciplinas.length; i++) {
                if (disciplinas[i].getId() == auxLoc) {
                    return disciplinas[i];
                }
            }
        }

        return null;
    }

    public Disciplina findDisciplina(int id){
        for (Disciplina disciplina : disciplinas) {
            if(disciplina != null){
                if(disciplina.getId() == id){
                    return disciplina;
                }
            }
        }
        return null;
    }
    
    public void update(Disciplina disciplinaAux) {
        this.disciplinas[disciplinaAux.getId()] = disciplinaAux;
    }

    public Disciplina[] getAll() {
        return disciplinas;
    }

    public void delete(Disciplina discDelete) {
        disciplinas[discDelete.getId()] = null;
    }

}
