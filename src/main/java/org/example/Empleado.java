package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empleado {
    private String nombreEmpleado;
    private long cuil;
    private int anioIngreso;
    private double montoAntiguedad;
    private double sueldoBasico;
    private List<BonoSueldo> bonos;
    LocalDate fecha = LocalDate.now();

    public void nuevoEmpleado() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, ingrese el nombre completo del empleado");
        this.nombreEmpleado = sc.next();

        System.out.println("Por favor, ingrese el cuil del empleado");
        this.cuil = Long.parseLong(sc.next());
        System.out.println("Por favor, ingrese el año de ingreso del empleado");
        this.anioIngreso= Integer.parseInt(sc.next());
        while (this.getAnioIngreso() > 2024) {
            System.out.println("Por favor, ingrese un anio valido");
            this.anioIngreso= Integer.parseInt(sc.next());

        }

        System.out.println("Por favor, ingrese el sueldo básico del empleado");
        this.sueldoBasico = Double.parseDouble(sc.next());
        double antiguedad = ((fecha.getYear() - this.anioIngreso) * 2) / 100.0;

        this.montoAntiguedad = (this.sueldoBasico * antiguedad);


    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public long getCuil() {
        return cuil;
    }

    public void setCuil(long cuil) {
        this.cuil = cuil;
    }

    public int getAnioIngreso() {
        return anioIngreso;
    }

    public void setAnioIngreso(int anioIngreso) {
        this.anioIngreso = anioIngreso;
    }

    public double getMontoAntiguedad() {
        return montoAntiguedad;
    }

    public void setMontoAntiguedad(double montoAntiguedad) {
        this.montoAntiguedad = montoAntiguedad;
    }

    public double getSueldoBasico() {
        return sueldoBasico;
    }

    public void setSueldoBasico(double sueldoBasico) {
        this.sueldoBasico = sueldoBasico;
    }

    public List<BonoSueldo> getBonos() {
        return bonos;
    }

    public void setBonos(List<BonoSueldo> bonos) {
        this.bonos = bonos;
    }



}
