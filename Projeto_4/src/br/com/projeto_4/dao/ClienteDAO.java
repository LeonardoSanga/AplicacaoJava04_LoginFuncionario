/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_4.dao;

import java.sql.*;
import br.com.projeto_4.dto.ClienteDTO;


public class ClienteDAO {
    
    public ClienteDAO() {
        
    }
    
    // realizar consultas
    private ResultSet rs = null;
    // manipular o banco de dados
    private Statement stmt = null;
    
    public boolean inserirCliente(ClienteDTO clienteDTO) {
        try {
            // chama o metodo que esta na classe ConexaoDB para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            
            // Instancia o Statement que sera responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into cliente (nome_cli, logradouro_cli, numero_cli, "
                    + "bairro_cli, cidade_cli, estado_cli, cep_cli, cpf_cli, rg_cli) values ( "
                    + "'" + clienteDTO.getNome_cli() + "', "
                    + "'" + clienteDTO.getLogradouro_cli() + "', "
                    + clienteDTO.getNumero_cli() + ", "
                    + "'" + clienteDTO.getBairro_cli() + "', "
                    + "'" + clienteDTO.getCidade_cli() + "', "
                    + "'" + clienteDTO.getEstado_cli() + "', "
                    + "'" + clienteDTO.getCep_cli() + "', "
                    + "'" + clienteDTO.getCpf_cli() + "', "
                    + "'" + clienteDTO.getRg_cli() + "') ";
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public ResultSet consularCliente(ClienteDTO clienteDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch (opcao) {
                case 1:
                    comando = "Select c.* " +
                            "from cliente c " +
                            "where nome_cli like '" + clienteDTO.getNome_cli() + "%' " +
                            "order by c.nome_cli";
                break;
                case 2:
                    comando = "Select c.* " +
                            "from cliente c " +
                            "where c.id_cli = " + clienteDTO.getId_cli();
                break;
                case 3:
                    comando = "Select c.id_cli, c.nome_cli " +
                            "from cliente c";
                break;
                    
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        
        catch (Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public boolean alterarCliente(ClienteDTO clienteDTO) {
        try {
            // chama o metodo que esta na classe ConexaoDB para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            
            // Instancia o Statement que sera responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Update cliente set "
                    + "nome_cli = '" + clienteDTO.getNome_cli() + "', "
                    + "logradouro_cli = '" + clienteDTO.getLogradouro_cli() + "', "
                    + "numero_cli = " + clienteDTO.getNumero_cli() + ", "
                    + "bairro_cli = '" + clienteDTO.getBairro_cli() + "', "
                    + "cidade_cli = '" + clienteDTO.getCidade_cli() + "', "
                    + "estado_cli = '" + clienteDTO.getEstado_cli() + "', "
                    + "cep_cli = '" + clienteDTO.getCep_cli() + "', "
                    + "cpf_cli = '" + clienteDTO.getCpf_cli() + "', "
                    + "rg_cli = '" + clienteDTO.getRg_cli() + "' "
                    + "where id_cli = " + clienteDTO.getId_cli();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
    public boolean excluirCliente(ClienteDTO clienteDTO) {
        try {
            // chama o metodo que esta na classe ConexaoDB para abrir o banco de dados
            ConexaoDAO.ConnectDB();
            
            // Instancia o Statement que sera responsavel por executar alguma coisa no banco de dados
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from cliente where id_cli = "
                    + clienteDTO.getId_cli();
            
            stmt.execute(comando.toUpperCase());
            
            ConexaoDAO.con.commit();
            
            stmt.close();
            
            return true;
        }
        
        catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        
        finally {
            ConexaoDAO.CloseDB();
        }
    }
    
} // fecha classe
