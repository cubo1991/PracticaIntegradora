package org.example;

import java.util.ArrayList;

public class BonoSueldo {
    private Empleado empleado;
    private int mesLiquidacion;
    private int anioLiquidacion;
    private String [][] haberes = new String [1][4];
    private String [][] deducciones = new String [1][4];

    public String[][] getHaberes() {
        return haberes;
    }

    public void setHaberes(String[][] haberes) {
        this.haberes = haberes;
    }

    public String[][] getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(String[][] deducciones) {
        this.deducciones = deducciones;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public int getMesLiquidacion() {
        return mesLiquidacion;
    }

    public void setMesLiquidacion(int mesLiquidacion) {
        this.mesLiquidacion = mesLiquidacion;
    }

    public int getAnioLiquidacion() {
        return anioLiquidacion;
    }

    public void setAnioLiquidacion(int anioLiquidacion) {
        this.anioLiquidacion = anioLiquidacion;
    }

    public double getMontoLiquidacion() {
        return montoLiquidacion;
    }

    public void setMontoLiquidacion(double montoLiquidacion) {
        this.montoLiquidacion = montoLiquidacion;
    }

    private double montoLiquidacion;

}
