import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class libretaDeNotas {

    //Aquí se le pide al usuario que ingrese la cantidad de alumnnos
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de alumnos:");
        int cantidadAlumnos = scanner.nextInt();

//HashMap y notas

        HashMap<String, ArrayList<Double>> libreta = new HashMap<>();

        for (int i = 0; i < cantidadAlumnos; i++) {
            System.out.println("Ingrese el nombre del alumno " + (i + 1) + ":");
            scanner.nextLine();
            String nombre = scanner.nextLine();

            //Arraylist

            ArrayList<Double> notas = new ArrayList<>();
            System.out.println("Ingrese la cantidad de notas por alumno:");
            int cantidadNotas = scanner.nextInt();

            for (int j = 0; j < cantidadNotas; j++) {
                double nota;
                do {
                    System.out.println("Ingrese la nota " + (j + 1) + "para " + nombre + ":");
                    nota = scanner.nextDouble();
                    if (nota < 1.0 || nota > 7.0) {
                        System.out.println("Nota inválida. Intente nuevamente.");
                    }
                } while (nota < 1.0 || nota > 7.0);
                notas.add(nota);
            }
            libreta.put(nombre, notas);
        }

        //Aquí se recorre el HashMap con el cálculo de notas

        System.out.println("\n----Resultados por alumno---\n");

        for (String nombre : libreta.keySet()) {
            ArrayList<Double> notas = libreta.get(nombre);

            double suma = 0;
            double max = notas.get(0);
            double min = notas.get(0);

            for (double nota : notas) {
                suma += nota;
                if (nota > max) max = nota;
                if (nota < min) min = nota;
            }

            double promedio = suma / notas.size();
            System.out.println("Alumno: " + nombre);
            System.out.printf("  Promedio: %.2f\n", promedio);
            System.out.printf("  Nota Máxima: %.2f\n", max);
            System.out.printf("  Nota Mínima: %.2f\n", min);
            System.out.println();
        }


        double sumaTotal = 0;
        int totalNotas = 0;

        for (ArrayList<Double> notas : libreta.values()) {
            for (double nota : notas) {
                sumaTotal += nota;
                totalNotas++;
            }
        }

        double promedioCurso = sumaTotal / totalNotas;



        int opcion;
        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Mostrar el Promedio de Notas por Estudiante");
            System.out.println("2. Mostrar si una Nota es Aprobatoria o Reprobatoria");
            System.out.println("3. Mostrar si una Nota está por Sobre o por Debajo del Promedio del Curso");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("\n--- Promedio de Notas por Estudiante ---");
                    for (Map.Entry<String, ArrayList<Double>> entry : libreta.entrySet()) {
                        String nombre = entry.getKey();
                        ArrayList<Double> notas = entry.getValue();

                        double suma = 0;
                        for (double nota : notas) {
                            suma += nota;
                        }

                        double promedio = suma / notas.size();
                        System.out.printf("Alumno: %s | Promedio: %.2f\n", nombre, promedio);
                    }
                    break;

                case 2:
                    scanner.nextLine(); // limpiar buffer
                    System.out.println("Ingrese el nombre del estudiante:");
                    String nombreEst = scanner.nextLine();

                    if (!libreta.containsKey(nombreEst)) {
                        System.out.println("El estudiante no existe.");
                        break;
                    }

                    System.out.println("Ingrese la nota a evaluar:");
                    double notaEval = scanner.nextDouble();
                    if (notaEval < 1.0 || notaEval > 7.0) {
                        System.out.println("Nota inválida. Debe estar entre 1.0 y 7.0.");
                    } else if (notaEval >= 4.0) {
                        System.out.println("La nota es Aprobatoria.");
                    } else {
                        System.out.println("La nota es Reprobatoria.");
                    }
                    break;

                case 3:
                    scanner.nextLine(); // limpiar buffer
                    System.out.println("Ingrese el nombre del estudiante:");
                    String nombreEst2 = scanner.nextLine();

                    if (!libreta.containsKey(nombreEst2)) {
                        System.out.println("El estudiante no existe.");
                        break;
                    }

                    System.out.println("Ingrese la nota a comparar:");
                    double notaComp = scanner.nextDouble();
                    if (notaComp < 1.0 || notaComp > 7.0) {
                        System.out.println("Nota inválida. Debe estar entre 1.0 y 7.0.");
                    } else if (notaComp > promedioCurso) {
                        System.out.println("La nota está por SOBRE el promedio del curso.");
                    } else if (notaComp < promedioCurso) {
                        System.out.println("La nota está por DEBAJO del promedio del curso.");
                    } else {
                        System.out.println("La nota es IGUAL al promedio del curso.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }

        } while (opcion != 0);

        scanner.close();

//Está roto profe, pero lo intenté :d
    }
}