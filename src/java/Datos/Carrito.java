/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author mateo
 */
public class Carrito {
    
    int item;
    int idProducto;
    String nombres;
    String descripcion;
    double precioCompra;
    int cantidad;
    double subTotal;

    public Carrito() {
    }

    public Carrito(int item, int idProducto, String nombres, String descripcion, double precioCompra, int cantidad, double subTotal) {
        this.item = item;
        this.idProducto = idProducto;
        this.nombres = nombres;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getItem() {
        return item;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombres() {
        return nombres;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    
    
}
