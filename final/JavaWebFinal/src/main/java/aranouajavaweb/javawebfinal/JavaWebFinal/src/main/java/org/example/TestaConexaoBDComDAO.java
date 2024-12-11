package org.example;

import dao.RecursosDAO;
import model.Recursos;

import java.sql.SQLException;
import java.util.List;

public class TestaConexaoBDComDAO {
    public static void main(String[] args){
        RecursosDAO recursosDAO = new RecursosDAO();

        try{
            List<Recursos> recursos = recursosDAO.listar();
            for(Recursos recurso : recursos){
                System.out.println("Numero Tombo: "+ recurso.getNumeroTombo());
                System.out.println("Equipamento: "+ recurso.getEquipamento());
                System.out.println("Marca: "+ recurso.getMarca());
                System.out.println("Modelo: "+ recurso.getModelo());
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}