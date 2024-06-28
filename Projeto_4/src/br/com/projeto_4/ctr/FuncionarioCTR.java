/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_4.ctr;

/* Importando as classes necessárias para trabalhar nesta classe */
import java.sql.ResultSet;
import br.com.projeto_4.dto.FuncionarioDTO;
import br.com.projeto_4.dao.FuncionarioDAO;
import br.com.projeto_4.dao.ConexaoDAO;

public class FuncionarioCTR {
    
    FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    public FuncionarioCTR() {
        
    }
    
    public String inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            
            if(funcionarioDAO.inserirFuncionario(funcionarioDTO)) {
                return "Cliente cadastrado com sucesso!!!";
            } else {
                return "Cliente NÃO cadastrado!!!";
            }
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO cadastrado!!!";
        }
    }
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        ResultSet rs = null;
        
        rs = funcionarioDAO.consultarFuncionario(funcionarioDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
    public String alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            
            if(funcionarioDAO.alterarFuncionario(funcionarioDTO)) {
                return "Cliente Alterado com sucesso!!!";
            } else {
                return "Cliente NÃO Alterado!!!";
            }
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Alterado!!!";
        }
    }
    
    public String excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            
            if(funcionarioDAO.excluirFuncionario(funcionarioDTO)) {
                return "Cliente Excluído com sucesso!!!";
            } else {
                return "Cliente NÃO Excluído!!!";
            }
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "Cliente NÃO Excluído!!!";
        }
    }
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        return funcionarioDAO.logarFuncionario(funcionarioDTO);
    }
} // fecha classe
