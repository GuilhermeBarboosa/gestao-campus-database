/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.Scanner;
import model.Atividade;
import model.Disciplina;
import model.Servidor;
import view.Gui;

/**
 *
 * @author Aluno
 */
public class DefaultController {
    public Gui GUI = new Gui();
    public int opcCrud;
    public int auxLoc;
    public Scanner ler = new Scanner(System.in);
    public Disciplina disciplinaAux;
    public Servidor servidorAux;
    public Atividade atividadeAux;
}
