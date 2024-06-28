/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto_4.dao;

import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import br.com.projeto_4.dto.FuncionarioDTO;

/**
 *
 * @author gabri
 */
public class FuncionarioDAO {
    
    
    public FuncionarioDAO(){
        
    }
    
    private ResultSet rs = null;
    private Statement stmt = null;
    
    private static MessageDigest md = null;
    
    static {
        try {
            md = MessageDigest.getInstance("MD5");           
        }catch(NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
    
    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 3];
        String hexString;
        
        for(int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toUpperCase().getChars(hexString.length() - 3, hexString.length(), hexOutput, i * 3);
        }
        return hexOutput;
    }
    
    public static String criptografar(String pwd) {
        if(md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }
    
    public boolean inserirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Insert into funcionario (nome_fun, cpf_fun, "
                    + "login_fun, senha_fun, tipo_fun) values ( "
                    + "'" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "'" + funcionarioDTO.getCpf_fun() + "', "
                    + "'" + funcionarioDTO.getLogin_fun() + "', "
                    + "'" + criptografar(funcionarioDTO.getSenha_fun()) + "', "
                    + "'" + funcionarioDTO.getTipo_fun().toUpperCase() + "') ";
            
            stmt.execute(comando);
            
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
    
    public boolean alterarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            comando = "Update funcionario set "
                    + "nome_fun = '" + funcionarioDTO.getNome_fun().toUpperCase() + "', "
                    + "cpf_fun = '" + funcionarioDTO.getCpf_fun() + "', "
                    + "login_fun = '" + funcionarioDTO.getLogin_fun() + "', ";
            
            if(funcionarioDTO.getSenha_fun() != null) {
                comando += "senha_fun = '" + criptografar(funcionarioDTO.getSenha_fun()) + "', ";
            }
            
            comando += "tipo_fun = '" + funcionarioDTO.getTipo_fun().toUpperCase() + "' "
                    + "where id_fun = " + funcionarioDTO.getId_fun();
            
            stmt.execute(comando);
            
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
    
    public boolean excluirFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Delete from funcionario where id_fun = "
                    + funcionarioDTO.getId_fun();
            
            stmt.execute(comando);
            
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
    
    public ResultSet consultarFuncionario(FuncionarioDTO funcionarioDTO, int opcao) {
        try {
            ConexaoDAO.ConnectDB();
           
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "";
            
            switch(opcao) {
                case 1:
                    comando = "Select f.* " +
                            "from funcionario f " +
                            "where nome_fun ilike '" + funcionarioDTO.getNome_fun() + "%' " +
                            "order by f.nome_fun";
                break;
                case 2:
                    comando = "Select f.* " +
                            "from funcionario f " +
                            "where f.id_fun = " + funcionarioDTO.getId_fun();
            }
            
            rs = stmt.executeQuery(comando.toUpperCase());
            return rs;
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return rs;
        }
    }
    
    public String logarFuncionario(FuncionarioDTO funcionarioDTO) {
        try {
            ConexaoDAO.ConnectDB();
            
            stmt = ConexaoDAO.con.createStatement();
            
            String comando = "Select f.tipo_fun " +
                    "from funcionario f " + 
                    "where f.login_fun = '" + funcionarioDTO.getLogin_fun() + "'" +
                    " and f.senha_fun = '" + criptografar(funcionarioDTO.getSenha_fun()) + "'";
            
            rs = null;
            rs = stmt.executeQuery(comando);
            
            if(rs.next()) {
                return rs.getString("tipo_fun");
            }
            else {
                return "";
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
        finally {
            ConexaoDAO.CloseDB();
        }
    }
}
