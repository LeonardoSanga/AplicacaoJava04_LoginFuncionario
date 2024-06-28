/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_4.ctr;

/* Importando as classes necessárias para trabalhar nesta classe */
import java.sql.ResultSet;
import br.com.projeto_4.dto.ClienteDTO;
import br.com.projeto_4.dao.ClienteDAO;
import br.com.projeto_4.dao.ConexaoDAO;

public class ClienteCTR {
    
    ClienteDAO clienteDAO = new ClienteDAO();
    
    public ClienteCTR() {
        
    }
    
    public String inserirCliente(ClienteDTO clienteDTO) {
        try {
            
            if(clienteDAO.inserirCliente(clienteDTO)) {
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
    
    public ResultSet consultarCliente(ClienteDTO clienteDTO, int opcao) {
        ResultSet rs = null;
        
        rs = clienteDAO.consularCliente(clienteDTO, opcao);
        
        return rs;
    }
    
    public void CloseDB() {
        ConexaoDAO.CloseDB();
    }
    
    public String alterarCliente(ClienteDTO clienteDTO) {
        try {
            
            if(clienteDAO.alterarCliente(clienteDTO)) {
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
    
    public String excluirCliente(ClienteDTO clienteDTO) {
        try {
            
            if(clienteDAO.excluirCliente(clienteDTO)) {
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
} // fecha classe
