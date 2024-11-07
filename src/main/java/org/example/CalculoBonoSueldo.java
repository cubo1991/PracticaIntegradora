package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class CalculoBonoSueldo {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int opcion;
        List<BonoSueldo> bonoNuevo = new ArrayList<>();
        BonoSueldo bonoSueldo = new BonoSueldo();
        Empleado empleado = new Empleado();
        empleado.nuevoEmpleado();
    do {
            int indice = 1;
            int mesFacturacion = 0;
            int anioFacturacion = 0;
            String[][] haberes = {
                    {"100", "Presentismo", "9"},
                    {"101", "Titulo Profesional", "9"},
                    {"102", "Horas Extraordinarias", "M"},
                    {"103", "Horas Nocturnas", "M"},
                    {"104", "Otros Haberes", "M"}
            };
            String[][] deducciones = {
                    {"200", "Obra Social", "3"},
                    {"201", "Jubilacion", "11"},
                    {"202", "Sindicato", "2"},
                    {"203", "Seguro", "1.5"},
                    {"204", "Otros", "M"}
            };
            List<Integer> codigosIngresados = new ArrayList<>();




            while (anioFacturacion == 0 && mesFacturacion == 0) {
                System.out.println("ingrese año de facturacion");
                anioFacturacion = Integer.parseInt(sc.nextLine());
                System.out.println("Ingrese mes de facturacion");
                mesFacturacion = Integer.parseInt(sc.nextLine());
            }


            bonoSueldo.setEmpleado(empleado);
            bonoSueldo.setMesLiquidacion(mesFacturacion);
            bonoSueldo.setAnioLiquidacion(anioFacturacion);

            String[][] bonoCalculado = new String[10][4];


            bonoCalculado[0][0] = "Codigo Item";
            bonoCalculado[0][1] = "Denominacion";
            bonoCalculado[0][2] = "Haberes";
            bonoCalculado[0][3] = "Deducciones";
        String[][] haberesCalculados = new String[10][4];
        String[][] deduccionesCalculadas = new String[10][4];

        int codigoItem = Integer.parseInt(sc.next());
            boolean haberCargado = false;
            while (codigoItem != 000 || !haberCargado) {
                while (codigosIngresados.contains(codigoItem)) {
                    System.out.println("El código ya ha sido cargado, por favor ingrese otro código");
                    codigoItem = Integer.parseInt(sc.next());
                }
                boolean codigoEncontrado = false;

                for (int i = 0; i < haberes.length; i++) {

                    if (haberes[i][0].equals(String.valueOf(codigoItem))) {
                        codigoEncontrado = true;
                        haberCargado = true;

                        for (int j = indice; j < bonoCalculado[0].length; j++) {
                            if (bonoCalculado[j][0] == null) {
                                indice = j + 1;
                                bonoCalculado[j][0] = haberes[i][0];
                                bonoCalculado[j][1] = haberes[i][1];
                                haberesCalculados[j][0] = haberes[i][0];
                                haberesCalculados[j][1] = haberes[i][1];


                                if (haberes[i][2].equals("M")) {
                                    System.out.println("Por favor, ingrese el monto");
                                    bonoCalculado[j][2] = sc.next();
                                    haberesCalculados[j][2] = bonoCalculado[j][2];
                                } else {
                                    int porcentaje = Integer.parseInt(haberes[i][2]);
                                    Double calculo = empleado.getSueldoBasico() * (porcentaje / 100.0);
                                    bonoCalculado[j][2] = String.valueOf(calculo);
                                    haberesCalculados[j][2] = bonoCalculado[j][2];

                                }


                            }
                            break;
                        }
                        codigosIngresados.add(codigoItem);
                        break;

                    }
                }
                bonoSueldo.setHaberes(haberesCalculados);
                if (!codigoEncontrado && codigoItem != 000) {

                    System.out.println("El código ingresado es incorrecto");


                }
                if (!haberCargado) {
                    System.out.println("Por favor, cargue por lo menos un haber");
                }
                System.out.println("Por favor, ingrese los Haberes del Empleado");
                System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000'");
                codigoItem = Integer.parseInt(sc.next());
            }

            //Comienzan  las deducciones
            System.out.println("Por favor, ingrese las deducciones del Empleado");
            System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000' ");
            codigoItem = Integer.parseInt(sc.next());

            boolean deduccionCargada = false;
            while (codigoItem != 000 || !deduccionCargada) {
                while (codigosIngresados.contains(codigoItem)) {
                    System.out.println("El código ya ha sido cargado, por favor ingrese otro código");
                    codigoItem = Integer.parseInt(sc.next());
                }
                boolean codigoEncontrado = false;

                for (int i = 0; i < deducciones.length; i++) {

                    if (deducciones[i][0].equals(String.valueOf(codigoItem))) {
                        codigoEncontrado = true;
                        deduccionCargada = true;

                        for (int j = indice; j < bonoCalculado[0].length; j++) {
                            if (bonoCalculado[j][0] == null) {
                                indice = j + 1;
                                bonoCalculado[j][0] = deducciones[i][0];
                                bonoCalculado[j][1] = deducciones[i][1];
                                deduccionesCalculadas[j][0] = deducciones[i][0];
                                deduccionesCalculadas[j][1] = deducciones[i][1];


                                if (deducciones[i][2].equals("M")) {
                                    System.out.println("Por favor, ingrese el monto");
                                    bonoCalculado[j][3] = sc.next();
                                    deduccionesCalculadas[j][3] = bonoCalculado[j][3];
                                } else {
                                    int porcentaje = Integer.parseInt(deducciones[i][2]);
                                    Double calculo = empleado.getSueldoBasico() * (porcentaje / 100.0);
                                    bonoCalculado[j][3] = String.valueOf(calculo);
                                    deduccionesCalculadas[j][3] = bonoCalculado[j][3];

                                }


                            }
                            break;
                        }
                        codigosIngresados.add(codigoItem);
                        break;

                    }
                }
                bonoSueldo.setDeducciones(deduccionesCalculadas);
                if (!codigoEncontrado && codigoItem != 000) {

                    System.out.println("El código ingresado es incorrecto");


                }
                if (!deduccionCargada) {
                    System.out.println("Por favor, cargue por lo menos una deduccion");
                }
                System.out.println("Por favor, ingrese las deducciones del Empleado");
                System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000'");
                codigoItem = Integer.parseInt(sc.next());
            }
            //Comienza el proceso de liquidacion
            double haberesTotales = 0.0;
            for (int i = 1; i < bonoCalculado.length; i++) {
                if (bonoCalculado[i][2] != null) {
                    haberesTotales += Double.parseDouble(bonoCalculado[i][2]);
                }

            }
            double deduccionesTotales = 0.0;
            for (int i = 1; i < bonoCalculado.length; i++) {
                if (bonoCalculado[i][3] != null) {
                    deduccionesTotales += Double.parseDouble(bonoCalculado[i][3]);
                }


            }
            double neto = (empleado.getSueldoBasico() + empleado.getMontoAntiguedad() + haberesTotales) - deduccionesTotales;

            bonoSueldo.setMontoLiquidacion(neto);

            bonoNuevo.add(bonoSueldo);
            empleado.setBonos(bonoNuevo);
        // Preguntar si desea cargar un nuevo bono de sueldo
        opcion = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            System.out.println("¿Desea cargar un nuevo bono? 1- Si 2- No");
            String entrada = sc.nextLine();
            try {
                opcion = Integer.parseInt(entrada);
                if (opcion == 1 || opcion == 2) {
                    entradaValida = true;  // Solo aceptar si es 1 o 2
                } else {
                    System.out.println("Por favor, ingrese 1 para Sí o 2 para No.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor ingrese un número (1 o 2).");
            }
        }

        if (opcion == 2) {
            break;
        }

    } while (opcion == 1);





        for (BonoSueldo bono : bonoNuevo) {
            System.out.printf("Nombre             : %-20s%n", bono.getEmpleado().getNombreEmpleado());
            System.out.printf("CUIL               : %-20d%n", bono.getEmpleado().getCuil());
            System.out.printf("Mes Liquidación    : %-10s  Año Liquidación : %-10d%n", bono.getMesLiquidacion(), bono.getAnioLiquidacion());
            System.out.printf("Sueldo Básico      : %-10.2f  Año Ingreso     : %-10d%n", bono.getEmpleado().getSueldoBasico(), bono.getEmpleado().getAnioIngreso());

            // Línea separadora para la tabla
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-12s %-20s %-10s %-10s%n", "Código Ítem", "Denominación", "Haberes", "Deducciones");

            // Imprimir los haberes
            for (String[] haber : bono.getHaberes()) {
                if (haber[0] != null) {
                    System.out.printf("%-12s %-20s %-10s %-10s%n",
                            haber[0], haber[1], haber[2] != null ? haber[2] : "", "");
                }
            }

            // Imprimir las deducciones
            for (String[] deduccion : bono.getDeducciones()) {
                if (deduccion[0] != null) {
                    System.out.printf("%-12s %-20s %-10s %-10s%n",
                            deduccion[0], deduccion[1], "", deduccion[2] != null ? deduccion[2] : "");
                }
            }

            // Línea separadora para los totales
            System.out.println("--------------------------------------------------------------------------------");

            // Cálculo de subtotales de haberes y deducciones
            double haberesTotales = 0.0;
            for (String[] haber : bono.getHaberes()) {
                if (haber[2] != null && !haber[2].equals("M")) {
                    haberesTotales += Double.parseDouble(haber[2]);
                }
            }

            double deduccionesTotales = 0.0;
            for (String[] deduccion : bono.getDeducciones()) {
                if (deduccion[2] != null && !deduccion[2].equals("M")) {
                    deduccionesTotales += Double.parseDouble(deduccion[2]);
                }
            }

            // Imprimir subtotal y neto
            System.out.printf("%-32s %-10.2f %-10.2f%n", "SUB TOTAL", haberesTotales, deduccionesTotales);
            double neto = haberesTotales - deduccionesTotales;
            System.out.printf("%-32s %-10.2f%n", "NETO", neto);

            // Línea separadora final
            System.out.println("--------------------------------------------------------------------------------");


        }



    }


}



