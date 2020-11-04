package com.example.aula3.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cliente {
    
    private int codigo;
    private String nome;
    private String endereco;
    private double saldo;

    @JsonIgnore
    private ArrayList<Pedido> pedidos = new ArrayList<Pedido>();

    // ----------------- MÉTODOS -----------------
    public boolean addPedido(Pedido pedido){
        return pedidos.add(pedido);
    }

    public boolean removePedido(Pedido pedido){
        return pedidos.remove(pedido);
    }

    public double somaTotalPedidos() {
        double soma = 0;

        for (Pedido pedido : pedidos) {
            soma+= pedido.totalPedido();
        }

        return soma;
        
    }

    public double somaTotalPedidosFechados() {
        double soma = 0;

        for (Pedido pedido : pedidos) {
            if (pedido.isPedidoFechado()) {
                soma+= pedido.totalPedido();
            }
        }

        return soma;
        
    }
    
    // ----------------- CONSTRUCTORS -----------------
    public Cliente() {
    }

    public Cliente(int codigo, String nome, double saldo) {
        this.codigo = codigo;
        this.nome = nome;
        this.saldo = saldo;
    }

    // ----------------- GETTERS AND SETTERS -----------------
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public String getEndereco() {
        return endereco;
    }
    
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    // ----------------- TO STRING -----------------
    @Override
    public String toString() {
        return "Cliente [codigo=" + codigo + ", nome=" + nome + ", saldo=" + saldo + "]";
    }

}
