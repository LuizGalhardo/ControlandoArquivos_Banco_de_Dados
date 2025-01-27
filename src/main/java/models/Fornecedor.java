package models;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Luiz Galhardo
 */
public class Fornecedor {
    
    private int id;
    private String razaoSocial;
    private String email;
    private String telefone;
    private String cnpj;
    
    public Fornecedor() {
        
    }

    public Fornecedor(int id, String razaoSocial, String email, String telefone, String cnpj) {
        this.id = id;
        this.razaoSocial = razaoSocial;
        this.email = email;
        this.telefone = telefone;
        this.cnpj = cnpj;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
