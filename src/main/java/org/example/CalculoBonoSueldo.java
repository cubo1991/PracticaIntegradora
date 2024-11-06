package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;




public class CalculoBonoSueldo {
    int indice =1;
    String [][] haberes = {
            {"100","Presentismo", "9"},
            {"101", "Titulo Profesional" , "9"} ,
            {"102","Horas Extraordinarias", "M"} ,
            {"103","Horas Nocturnas", "M"} ,
            {"104","Otros Haberes", "M"}
    };
    String [][] deducciones = {
            {"200","Obra Social", "3"} ,
            {"201","Jubilacion", "11"} ,
            {"202","Sindicato", "2"} ,
            {"203","Seguro", "1.5"} ,
            {"204","Otros", "M"}
    };
    List<Integer> codigosIngresados = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Empleado empleado = nuevoEmpleado(sc);
        CalculoBonoSueldo calculo = new CalculoBonoSueldo();
        calculo.bonoDeSueldo(sc, empleado);
    }

    public static Empleado nuevoEmpleado (Scanner sc) {
        LocalDate fecha = LocalDate.now();

        Empleado empleado = new Empleado();
        System.out.println("Por favor, ingrese el nombre completo del empleado");
        empleado.setNombreEmpleado(sc.next());
        System.out.println("Por favor, ingrese el cuil del empleado");
        empleado.setCuil(Long.parseLong(sc.next()));
        System.out.println("Por favor, ingrese el año de ingreso del empleado");
        empleado.setAnioIngreso(Integer.parseInt(sc.next()));
        while(empleado.getAnioIngreso() > 2024){
            System.out.println("Por favor, ingrese un anio valido");
            empleado.setAnioIngreso(Integer.parseInt(sc.next()));

        }
        empleado.setMontoAntiguedad((fecha.getYear()-empleado.getAnioIngreso()) * 2 );
        System.out.println("Por favor, ingrese el sueldo básico del empleado");
        empleado.setSueldoBasico(Double.parseDouble(sc.next()));

        return empleado;
    }

    public void bonoDeSueldo (Scanner sc, Empleado empleado) {
        BonoSueldo bonoSueldo = new BonoSueldo();

        String [][] bonoCalculado = new String[10][4];
        bonoCalculado[0][0] = "Codigo Item";
        bonoCalculado[0][1] = "Denominacion";
        bonoCalculado[0][2] = "Haberes";
        bonoCalculado[0][3] = "Deducciones";
        System.out.println("Por favor, ingrese los Haberes del Empleado");
        System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000' ");
        int codigoItem = Integer.parseInt(sc.next());
        while(codigoItem != 000){
        while(codigosIngresados.contains(codigoItem)){
            System.out.println("El código ya ha sido cargado, por favor ingrese otro código");
            codigoItem = Integer.parseInt(sc.next());
        }
        boolean codigoEncontrado = false;

        for(int i = 0; i < haberes.length;i++){

            if(haberes[i][0].equals(String.valueOf(codigoItem))){
                codigoEncontrado = true;

                for(int j = indice; j < bonoCalculado[0].length; j++){
                    if (bonoCalculado[j][0] == null){
                        indice = j+1;
                        bonoCalculado[j][0] = haberes[i][0];
                        bonoCalculado[j][1] = haberes[i][1];

                        if (haberes[i][2].equals("M")){
                            System.out.println("Por favor, ingrese el monto");
                            bonoCalculado[j][2] = sc.next();

                        } else {
                            int porcentaje = Integer.parseInt(haberes[i][2]);
                            Double calculo = empleado.getSueldoBasico() * (porcentaje/100.0);
                            bonoCalculado[j][2] = String.valueOf(calculo);


                        }



                    }
                    break;
                }
                codigosIngresados.add(codigoItem);
                break;

            }
            }
        if(!codigoEncontrado && codigoItem != 000){

                System.out.println("El código ingresado es incorrecto");


        }
        System.out.println("Por favor, ingrese el código del item. Para salir, ingrese el código '000'");
            codigoItem = Integer.parseInt(sc.next());
        }








    }

};



