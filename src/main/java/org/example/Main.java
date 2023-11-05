package org.example;

import org.example.logica.Empleado;
import org.example.persistencia.ControladoraPersistencia;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;


public class Main {

    static ControladoraPersistencia controladora = new ControladoraPersistencia();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLIntegrityConstraintViolationException {
        // Creo los objetos y variables que voy a necesitar:
        int opcion = 0;

        // Inicio de la aplicación solicitando al usuario que seleccione una opción y almaceno el valor en una variable.
        while (opcion != 6) {
            System.out.println("\n******************* Aplicación de la Empresa *********************");
            System.out.println("Selecciona una opción : \n1 => Crear un empleado \n2 => Editar un empleado \n3 => Lista de empleados \n4 => Borrar un empleado \n5 => Buscar empleados según un cargo \n6 => Salir");
            opcion = scanner.nextInt();
            scanner.nextLine();
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
                    System.out.println("Saliendo de la apliación...");
                    break;
                default:
                    System.out.println("Selecciona una opción correcta");
            }
        }
    }

    private static void buscarEmpleadosPorCargo() {
        System.out.println("Introduce el cargo que quieres consultar");
        String cargo = controlValueString().toLowerCase();
        List<Empleado> listaEmpleados = controladora.findEmpleadosByCargo(cargo);
        if (listaEmpleados.size() != 0) {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
        } else {
            System.out.println("No hay empleados con el cargo introducido, si lo desea puede ver la lista de los empleados marcando la opción 3");
        }
    }

    private static void borrarEmpleado() {
        System.out.println("Introduce el id del empleado a borrar, si no conoce el id puede listar todos los empleados desde la opción 3 del menú: ");
        Integer id = scanner.nextInt();
        controladora.deleteEmpleado(id);
    }

    private static void listarEmpleado() {
        List<Empleado> listaEmpleados = controladora.findEmpleados();
        if (listaEmpleados.size() == 0 || listaEmpleados == null) {
            System.out.println("La tabla empleados no tiene registros");
        } else {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado.toString());
            }
        }
    }

    private static void editarEmpleado() {
        System.out.println("Introduce el id del empleado que quieres modificar, si no conoce el id puede listar todos los empleados desde la opción 3 del menú: ");
        Integer id = scanner.nextInt();
        Empleado empleadoMod = controladora.readEmpleado(id);
        if (empleadoMod != null) {
            fillEmpleado(empleadoMod);
            controladora.updateEmpleado(empleadoMod);
        }
    }

    private static void crearEmpleado() {
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
    }

    /**
     * Método que dados unos valores por el usuario devuelve un objeto de tipo Empleado
     *
     * @param empleado
     */
    public static void fillEmpleado(Empleado empleado) {

        System.out.println("Introduce un nombre: ");
        String nombre = controlValueString();
        empleado.setNombre(nombre);

        System.out.println("Introduce los apellidos: ");
        String apellidos = controlValueString();
        empleado.setApellidos(apellidos);

        System.out.println("Introduce el cargo: ");
        String cargo = controlValueString().toLowerCase();
        empleado.setCargo(cargo);

        System.out.println("Introduce el salario: ");
        Float salario = controlValueDouble();
        empleado.setSalario(salario);


        //System.out.println("Introduce la fecha de inicio: ");
        //empleado.setFechaInicio(Instant.now());
        empleado.setFechaInicio(new Date());
    }

    /**
     * Método que devuelve un Float, para ello solicita un valor decimal al usuario y controla que cumple con los
     * criterios correctos.
     *
     * @return
     */
    public static Float controlValueDouble() {
        try {
            Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
            Float valor = scanner.nextFloat();
            while (valor == null) {
                System.out.println("El campo no puede estar vacío");
                valor = scanner.nextFloat();
            }
            return valor;
        } catch (InputMismatchException e) {
            System.out.println("Se ha introducido un valor incorrecto");
            return null;
        }
    }

    /**
     * Método que devuelve una cadena de caracteres, para ello solicita un valor al usuario y controla que cumple con
     * los criterios correctos
     *
     * @return
     */
    public static String controlValueString() {
        Scanner scanner = new Scanner(System.in);
        String valor = scanner.nextLine();
        while (!valor.matches("^[A-Za-z][a-zA-Z\s]{3,}$")) {
            System.out.println("Introduce como mínimo 3 valores que no sean números");
            valor = scanner.nextLine();
        }
        return valor;
    }

}