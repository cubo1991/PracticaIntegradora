package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class CalculoBonoSueldo {
    int indice = 1;
    int mesFacturacion = 1;
    int anioFacturacion = 2014;
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
    List<BonoSueldo> bonoNuevo = new ArrayList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CalculoBonoSueldo calculo = new CalculoBonoSueldo();
        Empleado empleado = calculo.nuevoEmpleado(sc);
        calculo.bonoDeSueldo(sc, empleado);
    }

    public Empleado nuevoEmpleado(Scanner sc) {
        LocalDate fecha = LocalDate.now();

        Empleado empleado = new Empleado();


        System.out.println("Por favor, ingrese el nombre completo del empleado");
        empleado.setNombreEmpleado(sc.next());
        System.out.println("Por favor, ingrese el cuil del empleado");
        empleado.setCuil(Long.parseLong(sc.next()));
        System.out.println("Por favor, ingrese el año de ingreso del empleado");
        empleado.setAnioIngreso(Integer.parseInt(sc.next()));
        while (empleado.getAnioIngreso() > 2024) {
            System.out.println("Por favor, ingrese un anio valido");
            empleado.setAnioIngreso(Integer.parseInt(sc.next()));

        }
        System.out.println("Por favor, ingrese el mes de la liquidacion");
        mesFacturacion = Integer.parseInt(sc.next());
        System.out.println("Por favor, ingrese año de la liquidacion");
        anioFacturacion = Integer.parseInt(sc.next());

        System.out.println("Por favor, ingrese el sueldo básico del empleado");
        empleado.setSueldoBasico(Double.parseDouble(sc.next()));
        double antiguedad = ((fecha.getYear() - empleado.getAnioIngreso()) * 2) / 100.0;

        empleado.setMontoAntiguedad(empleado.getSueldoBasico() * antiguedad);


        return empleado;
    }

    public void bonoDeSueldo(Scanner sc, Empleado empleado) {
        BonoSueldo bonoSueldo = new BonoSueldo();
        bonoSueldo.setAnioLiquidacion(anioFacturacion);
        bonoSueldo.setMesLiquidacion(mesFacturacion);
        bonoSueldo.setEmpleado(empleado);

        String[][] bonoCalculado = new String[10][4];


        bonoCalculado[0][0] = "Codigo Item";
        bonoCalculado[0][1] = "Denominacion";
        bonoCalculado[0][2] = "Haberes";
        bonoCalculado[0][3] = "Deducciones";
        System.out.println("Por favor, ingrese los Haberes del Empleado");
        System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000' ");
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

                            if (haberes[i][2].equals("M")) {
                                System.out.println("Por favor, ingrese el monto");
                                bonoCalculado[j][2] = sc.next();

                            } else {
                                int porcentaje = Integer.parseInt(haberes[i][2]);
                                Double calculo = empleado.getSueldoBasico() * (porcentaje / 100.0);
                                bonoCalculado[j][2] = String.valueOf(calculo);


                            }


                        }
                        break;
                    }
                    codigosIngresados.add(codigoItem);
                    break;

                }
            }
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

            for (int i = 0; i < haberes.length; i++) {

                if (deducciones[i][0].equals(String.valueOf(codigoItem))) {
                    codigoEncontrado = true;
                    deduccionCargada = true;

                    for (int j = indice; j < bonoCalculado[0].length; j++) {
                        if (bonoCalculado[j][0] == null) {
                            indice = j + 1;
                            bonoCalculado[j][0] = deducciones[i][0];
                            bonoCalculado[j][1] = deducciones[i][1];

                            if (deducciones[i][2].equals("M")) {
                                System.out.println("Por favor, ingrese el monto");
                                bonoCalculado[j][3] = sc.next();

                            } else {
                                int porcentaje = Integer.parseInt(deducciones[i][2]);
                                Double calculo = empleado.getSueldoBasico() * (porcentaje / 100.0);
                                bonoCalculado[j][3] = String.valueOf(calculo);


                            }


                        }
                        break;
                    }
                    codigosIngresados.add(codigoItem);
                    break;

                }
            }
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

        System.out.println("¿Desea cargar uno nuevo bono? 1- Si 2- No");
        int opcion = Integer.parseInt(sc.next());
        if (opcion == 1) {
            main(new String[]{""});
        } else {
            for (BonoSueldo bono : bonoNuevo) {
                // Encabezado del recibo, este debe imprimirse una vez por bono
                System.out.println("Nombre             : " + empleado.getNombreEmpleado());
                System.out.println("CUIL               : " + empleado.getCuil());
                System.out.println("Mes Liquidación    : " + bono.getMesLiquidacion() + "            Año Liquidación: " + bono.getAnioLiquidacion());
                System.out.println("Sueldo Básico      : " + empleado.getSueldoBasico() + "        Año Ingreso: " + empleado.getAnioIngreso());

                // Línea separadora para la tabla
                System.out.println("--------------------------------------------------------------------------------");


                // Imprimir los haberes y deducciones de bonoCalculado
                for (int i = 0; i < bonoCalculado.length; i++) {
                    if (bonoCalculado[i][0] != null) {
                        System.out.printf("%-12s %-20s %-10s %-10s%n",
                                bonoCalculado[i][0],
                                bonoCalculado[i][1],
                                bonoCalculado[i][2] != null ? bonoCalculado[i][2] : "",
                                bonoCalculado[i][3] != null ? bonoCalculado[i][3] : "");
                    }
                }

                // Línea separadora para los totales
                System.out.println("--------------------------------------------------------------------------------");

                // Imprimir subtotales de haberes y deducciones
                System.out.printf("%-32s %-10s %-10s%n", "SUB TOTAL", haberesTotales, "");

                // Imprimir el total neto
                System.out.printf("%-32s %-10s %-10s%n", "NETO", neto, "");

                // Línea separadora final
                System.out.println("--------------------------------------------------------------------------------");
            }



        }


    }
}



