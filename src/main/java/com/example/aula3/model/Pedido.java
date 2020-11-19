package com.example.aula3.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;

public class Pedido {
    
    private long numero;
    private String descricao;

    @JsonFormat(pattern = "dd/MM/yyyy@HH:mm:ss")
    private LocalDateTime dataPedido;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEntrega;

    private Cliente cliente;
    private boolean pedidoFechado;
    private ArrayList<ItemPedido> itens = new ArrayList<ItemPedido>();

    // ----------------- MÉTODOS -----------------
    public boolean addItemPedido(int numero, double preco, String produto, int quantidade) {
        return itens.add(new ItemPedido(numero, preco, produto, quantidade));
    }

    @JsonGetter // Indica pro JSON para incluir o total do pedido
    public double totalPedido() {
        double total = 0;
        for (ItemPedido item : itens) {
            total += item.getTotalItem(); 
        }
        return total;
    }
    
    // ----------------- CONSTRUCTOR -----------------
    public Pedido(long numero) {
        this.numero = numero;
    }

    public Pedido(){
        
    }

    // ----------------- GETTERS AND SETTERS -----------------
    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /*
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    */

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean isPedidoFechado() { // GET de boolean é diferente e começa com 'is'
        return pedidoFechado;
    }

    public void setPedidoFechado(boolean pedidoFechado) {
        this.pedidoFechado = pedidoFechado;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }
    
    // ----------------- TO STRING -----------------
    @Override
    public String toString() {
        return "Pedido [cliente=" + cliente.getCodigo() + ", dataPedido=" + dataPedido + ", descricao=" + descricao + ", numero="
                + numero  + /*", valor=" + valor +*/ "]";
    }
    
}
