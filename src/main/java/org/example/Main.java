package org.example;

import org.example.excepciones.EmpleadoException;
import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main {
    /**
     * Declaro las variables globales que voy a necesitar en varias ocasiones.
     */
    static ControladoraPersistencia controladora = new ControladoraPersistencia();
    static Scanner scanner = new Scanner(System.in);

    /**
     * Inicio de la aplicación con el método main, solicito al usuario que introduzca una opción, almaceno el valor en
     * una variable y según la opción seleccionada con un switch llama a un método.
     *
     * @param args
     * @throws SQLIntegrityConstraintViolationException
     */
    public static void main(String[] args) {

        int opcion = 0;
        do {
            System.out.println("\n******************* Aplicación de la Empresa *********************");
            System.out.println("Selecciona una opción : " + "\n1 => Crear un empleado " + "\n2 => Editar un empleado " + "\n3 => Lista de empleados " + "\n4 => Borrar un empleado " + "\n5 => Buscar empleados según un cargo " + "\n6 => Salir");
            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Solo se admiten tipos de datos numéricos");
                opcion = 6;
            }
            switch (opcion) {
                case 1:
                    crearEmpleado();
                    break;
                case 2:
                    editarEmpleado();
                    break;
                case 3:
                    listarEmpleado();
                    break;
                case 4:
                    borrarEmpleado();
                    break;
                case 5:
                    buscarEmpleadosPorCargo();
                    break;
                case 6:
                    System.out.println("********* HASTA PRONTO *********");
                    break;
                default:
                    System.out.println("Selecciona una opción correcta");
            }
        } while (opcion != 6);
    }

    /**
     * Método que solicita al usuario por pantalla que introduzca  un cargo, se pasa como parámetro el cargo al
     * controlador de persistencia si hay algún registro que coindica con ese cargo lo devuelve en un arraylist y se
     * imprime el contenido del array por pantalla.
     */
    private static void buscarEmpleadosPorCargo() {
        System.out.println("Introduce el cargo que quieres consultar");
        String cargo = scanner.nextLine().toLowerCase();
        List<Empleado> listaEmpleados = controladora.findEmpleadosByCargo(cargo);
        if (listaEmpleados.size() != 0) {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
        } else {
            System.out.println("No hay empleados con el cargo introducido, si lo desea puede ver la lista de los empleados marcando la opción 3");
        }
    }

    /**
     * Método que solicita al usuario que introduzca un id de un empleado, se pasa ese valor como parámetro a el método
     * en controlador de persistencia si hay algún registro con el mismo id se borrará de la base de datos.
     */
    private static void borrarEmpleado() {

        System.out.println("Introduce el id del empleado a borrar, si no conoce el id puede listar todos los empleados desde la opción 3 del menú: ");
        try {
            Integer id = scanner.nextInt();
            controladora.deleteEmpleado(id);
        } catch (InputMismatchException e) {
            System.out.println("Error al introducir los datos, solo se adminiten datos numéricos");
            scanner.nextLine();
        }
    }

    /**
     * Método que llama al método en controlador de persistencia que devuelve todos los registros de la tabla empleados
     * los almacena en una arraylist y se muestran todos los valores por pantalla.
     */
    private static void listarEmpleado() {
        List<Empleado> listaEmpleados = controladora.findEmpleados();
        if (listaEmpleados == null || listaEmpleados.size() == 0) {
            System.out.println("La tabla empleados no tiene registros");
        } else {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
        }
    }

    /**
     * Método que solicita al usuario un id y los pasa como parámetro a la controladora de persistencia, si el id existe
     * en la base de datos devolverá un objeto del tipo empleado con los valores setados y solicita al usario que
     * ingtroduzca valores nuevos con el método fillEmpleado, después envía el objeto con los nuevos valores a la
     * controladora de persistencia que modifica el registro con los valores nuevos.
     */
    private static void editarEmpleado() {
        try {
            System.out.println("Introduce el id del empleado que quieres modificar, si no conoce el id puede listar todos los empleados desde la opción 3 del menú: ");
            Integer id = scanner.nextInt();
            scanner.nextLine();
            Empleado empleadoMod = controladora.readEmpleado(id);
            if (empleadoMod != null) {
                fillEmpleado(empleadoMod);
                controladora.updateEmpleado(empleadoMod);
            }
        } catch (InputMismatchException e) {
            System.out.println("Error al introducir los datos, solo se adminiten datos numéricos");
            scanner.nextLine();
        } catch (EmpleadoException e) {
            System.out.println("Error al editar usuario => " + e.getMessage());
        }
    }

    /**
     * Método que almacena en una variable de tipo empleado el objeto que devuelve el método fillEmpleado, después llama
     * al controlador de persistencia y le pasa por parámetro el objeto con el que crea el registro nuevo en la base de
     * datos.
     */
    private static void crearEmpleado() {
        try {
            System.out.println("Introduce los empleados que quieres registrar");
            String centinela = "+";

            while (centinela.equals("+")) {
                Empleado empleado = new Empleado();
                fillEmpleado(empleado);
                controladora.createEmpleado(empleado);
                System.out.println("Para registrar más empleados marca \"+\" para terminar marca cualquier tecla ");
                centinela = scanner.next();
            }
            System.out.println("Ejecutando el menú de inicio...");
        } catch (EmpleadoException e) {
            System.out.println("Error al crear usuario => " + e.getMessage());
        }
    }

    /***
     * Método que dado un objeto de tipo empleado como parámetros, solicita unos valores al usuario y
     * retorna el objeto de tipo Empleado con dichos valores seteados.
     * @param empleado
     * @return object
     *
     */
    public static void fillEmpleado(Empleado empleado) throws EmpleadoException {

        System.out.println("Introduce un nombre: ");
        String nombre = scanner.nextLine();
        empleado.setNombre(nombre);
        System.out.println("Introduce los apellidos: ");
        String apellidos = scanner.nextLine();
        empleado.setApellidos(apellidos);
        System.out.println("Introduce el cargo: ");
        String cargo = scanner.nextLine().toLowerCase();
        empleado.setCargo(cargo);
        try {
            System.out.println("Introduce el salario: ");
            Float salario = scanner.nextFloat();
            empleado.setSalario(salario);
        } catch (InputMismatchException e) {
            System.out.println("Error al introducir los datos del salario, solo se adminiten datos numéricos");
            throw new EmpleadoException("Se ha producido el siguiente error => " + e.getMessage());
        }
        try {
            System.out.println("Introduce la fecha en el siguiente orden: Año + ENTER, Mes + ENTER, Día + ENTER");
            LocalDate fecha = LocalDate.of(scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
            empleado.setFechaInicio(fecha);
        } catch (InputMismatchException e) {
            System.out.println("Error al introducir los datos de fecha, solo se adminiten datos numéricos");
            scanner.nextLine();
            throw new EmpleadoException("Se ha producido el siguiente error => " + e.getMessage());
        }
    }

}