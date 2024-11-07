package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculoBonoSueldo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int opcion;
        List<BonoSueldo> bonoNuevo = new ArrayList<>();
        Empleado empleado = new Empleado();
        empleado.nuevoEmpleado();

        do {
            // Inicializar el nuevo BonoSueldo
            BonoSueldo bonoSueldo = new BonoSueldo();
            bonoSueldo.setEmpleado(empleado);

            // Pedir año y mes de facturación
            System.out.println("Ingrese año de facturación:");
            int anioFacturacion = Integer.parseInt(sc.nextLine());
            System.out.println("Ingrese mes de facturación:");
            int mesFacturacion = Integer.parseInt(sc.nextLine());

            bonoSueldo.setMesLiquidacion(mesFacturacion);
            bonoSueldo.setAnioLiquidacion(anioFacturacion);


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

            calcularHaberes(codigosIngresados, sc, empleado, bonoSueldo, haberes);
            calcularDeducciones(codigosIngresados, sc, empleado, bonoSueldo, deducciones);
            calcularLiquidacion(bonoSueldo, empleado, bonoNuevo);

            // Preguntar si se desea cargar otro bono
            System.out.println("¿Desea cargar un nuevo bono? 1- Sí, 2- No");
            opcion = Integer.parseInt(sc.nextLine());
        } while (opcion == 1);

        mostrarBono(bonoNuevo);
    }

    public static void mostrarBono(List<BonoSueldo> bonos) {
        for (BonoSueldo bono : bonos) {
            // Encabezado del recibo, este debe imprimirse una vez por bono
            System.out.println("Nombre             : " + bono.getEmpleado().getNombreEmpleado());
            System.out.println("CUIL               : " + bono.getEmpleado().getCuil());
            System.out.println("Mes Liquidación    : " + bono.getMesLiquidacion() + "            Año Liquidación: " + bono.getAnioLiquidacion());
            System.out.println("Sueldo Básico      : " + bono.getEmpleado().getSueldoBasico() + "        Año Ingreso: " + bono.getEmpleado().getAnioIngreso());

            // Línea separadora para la tabla
            System.out.println("--------------------------------------------------------------------------------");

            // Imprimir los haberes y deducciones
            System.out.printf("%-12s %-20s %-10s %-10s%n", "Ítem", "Denominación", "Haberes", "Deducciones");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-12s %-20s %-10s %-10s%n", "", "Sueldo básico", bono.getEmpleado().getSueldoBasico(), "");
            System.out.printf("%-12s %-20s %-10s %-10s%n", "", "Antiguedad", bono.getEmpleado().getMontoAntiguedad(), "");

            // Imprimir los haberes y deducciones del bono
            for (String[] haber : bono.getHaberes()) {
                if (haber[0] != null) {
                    System.out.printf("%-12s %-20s %-10s %-10s%n",
                            haber[0], haber[1], haber[2] != null ? haber[2] : "", haber[3] != null ? haber[3] : "");
                }
            }

            for (String[] deduccion : bono.getDeducciones()) {
                if (deduccion[0] != null) {
                    System.out.printf("%-12s %-20s %-10s %-10s%n",
                            deduccion[0], deduccion[1], deduccion[2] != null ? deduccion[2] : "", deduccion[3] != null ? deduccion[3] : "");
                }
            }

            // Línea separadora para los totales
            System.out.println("--------------------------------------------------------------------------------");

            // Imprimir subtotales de haberes y deducciones
            System.out.printf("%-12s %-20s %-10s %-10s%n", "", "SUB TOTAL", (bono.getHaberesTotales() + bono.getEmpleado().getSueldoBasico() + bono.getEmpleado().getMontoAntiguedad()), bono.getDeduccionesTotales());

            // Imprimir el total neto
            System.out.printf("%-12s %-20s %-10s %-10s%n", "", "", "NETO", bono.getMontoLiquidacion());

            // Línea separadora final
            System.out.println("--------------------------------------------------------------------------------");
        }
    }

    public static void calcularHaberes(List<Integer> codigosIngresados, Scanner sc, Empleado empleado, BonoSueldo bonoSueldo, String[][] haberes) {
        System.out.println("Por favor, ingrese los Haberes del Empleado");
        String[][] haberesCalculados = new String[10][4];
        double haberesTotales = 0.0;

        while (true) {
            System.out.println("Ingrese el código del haber o '000' para terminar:");
            int codigoItem = Integer.parseInt(sc.nextLine());
            if (codigoItem == 000) break;

            if (codigosIngresados.contains(codigoItem)) {
                System.out.println("Código ya ingresado. Ingrese otro.");
                continue;
            }

            boolean encontrado = false;
            for (String[] haber : haberes) {
                if (haber[0].equals(String.valueOf(codigoItem))) {
                    encontrado = true;
                    int indice = codigosIngresados.size();

                    haberesCalculados[indice][0] = haber[0];
                    haberesCalculados[indice][1] = haber[1];

                    if (haber[2].equals("M")) {
                        System.out.println("Ingrese el monto:");
                        haberesCalculados[indice][2] = sc.nextLine();
                    } else {
                        Double porcentaje = Double.parseDouble(haber[2]);
                        haberesCalculados[indice][2] = String.valueOf(empleado.getSueldoBasico() * porcentaje / 100.0);
                    }
                    haberesTotales += Double.parseDouble(haberesCalculados[indice][2]);
                    bonoSueldo.setHaberesTotales(haberesTotales);
                    codigosIngresados.add(codigoItem);
                    break;
                }
            }
            if (!encontrado) System.out.println("Código no encontrado.");
        }
        bonoSueldo.setHaberes(haberesCalculados);
    }

    public static void calcularDeducciones(List<Integer> codigosIngresados, Scanner sc, Empleado empleado, BonoSueldo bonoSueldo, String[][] deducciones) {
        System.out.println("Por favor, ingrese las Deducciones del Empleado");
        String[][] deduccionesCalculadas = new String[10][4];
        double deduccionesTotales = 0.0;
        codigosIngresados.clear();

        while (true) {
            System.out.println("Ingrese el código de deducción o '0' para terminar:");
            int codigoItem = Integer.parseInt(sc.nextLine());
            if (codigoItem == 0) break;

            if (codigosIngresados.contains(codigoItem)) {
                System.out.println("Código ya ingresado. Ingrese otro.");
                continue;
            }

            boolean encontrado = false;
            for (String[] deduccion : deducciones) {
                if (deduccion[0].equals(String.valueOf(codigoItem))) {
                    encontrado = true;
                    int indice = codigosIngresados.size();

                    deduccionesCalculadas[indice][0] = deduccion[0];
                    deduccionesCalculadas[indice][1] = deduccion[1];

                    if (deduccion[2].equals("M")) {
                        System.out.println("Ingrese el monto:");
                        deduccionesCalculadas[indice][3] = sc.nextLine();
                    } else {
                        Double porcentaje = Double.parseDouble(deduccion[2]);
                        deduccionesCalculadas[indice][3] = String.valueOf(empleado.getSueldoBasico() * porcentaje / 100.0);
                    }
                    deduccionesTotales += Double.parseDouble(deduccionesCalculadas[indice][3]);
                    bonoSueldo.setDeduccionesTotales(deduccionesTotales);
                    codigosIngresados.add(codigoItem);
                    break;
                }
            }
            if (!encontrado) System.out.println("Código no encontrado.");
        }
        bonoSueldo.setDeducciones(deduccionesCalculadas);
    }
    public static void calcularLiquidacion (BonoSueldo bonoSueldo, Empleado empleado,  List<BonoSueldo> bonoNuevo){
        bonoSueldo.setMontoLiquidacion((empleado.getSueldoBasico() + empleado.getMontoAntiguedad() + bonoSueldo.getHaberesTotales()) - bonoSueldo.getDeduccionesTotales());
        bonoNuevo.add(bonoSueldo);

    }
}
