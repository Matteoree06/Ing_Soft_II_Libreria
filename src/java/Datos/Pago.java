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
public class Pago {
    private int id;
    private double monto;

    public Pago() {
    }

    public Pago(int id, double monto) {
        this.id = id;
        this.monto = monto;
    }

    public int getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    
}
