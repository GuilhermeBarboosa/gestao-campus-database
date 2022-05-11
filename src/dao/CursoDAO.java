/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Curso;
import view.CursoView;

/**
 *
 * @author Usuario
 */
public class CursoDAO {
    private int id = 0;
 
    private Curso[] cursos = new Curso[10];
    
    private CursoView cursoV = new CursoView();
    
    public CursoDAO(){
        Curso curso1 = new Curso();
        curso1 = cursoV.setAleatorio1();
        this.setCurso(curso1);
        Curso curso2 = new Curso();
        curso2 = cursoV.setAleatorio2();
        this.setCurso(curso2);
    }
    
    public void setCurso(Curso curso) {
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] == null) {
                curso.setId(id);
                cursos[i] = curso;
                  id++;
                return;
            }
        }
    }

    public Curso getId(int auxLoc) {
        boolean aux = false;
        for (int i = 0; i < cursos.length; i++) {
            if (cursos[i] != null) {
                aux = true;
            }
        }
        if (aux) {
            for (int i = 0; i < cursos.length; i++) {
                if (cursos[i].getId() == auxLoc) {
                    return cursos[i];
                }
            }
        }
        return null;
    }

    public Curso findCurso(int idCurso) {
        for (Curso curso : cursos) {
            if(curso != null){
                if(curso.getId() == idCurso){
                    return curso;
                }
            }
        }
        return null;
    }
     
     
    public void update(Curso curso) {
        this.cursos[curso.getId()] = curso;
    }

    public Curso[] getAll() {
        return cursos;
    }

    public void delete(Curso cursoDelete) {
        cursos[cursoDelete.getId()] = null;
    }

    public Curso[] findCursoCampus(int auxCampus) {
        Curso[] vetCurso = new Curso[10];
        int cont = 0;
         for (Curso curso : cursos) {
            if(curso != null){
                if(curso.getCampus().getId() == auxCampus){
                    vetCurso[cont] = curso;
                    cont++;
                }
            }
        }
        return vetCurso;
    }

   
}
