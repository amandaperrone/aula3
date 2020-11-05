package com.example.aula3.model;

public class ItemPedido {
    private int numero;
    private double precoUnitario;
    private String produto;
    private int quantidade;

    // ----------------- MÉTODOS -----------------
    public double getTotalItem() {
        return precoUnitario * quantidade;
    }

    // ----------------- CONSTRUCTOR -----------------
    public ItemPedido(int numero, double preco, String produto, int quantidade) {
        this.numero = numero;
        this.precoUnitario = preco;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ItemPedido(){

    }

    // ----------------- GETTERS AND SETTERS -----------------
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double preco) {
        this.precoUnitario = preco;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
