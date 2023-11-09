package org.example.persistencia;

import org.example.logica.Empleado;

import javax.persistence.NoResultException;
import java.util.List;

public class ControladoraPersistencia {

    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();

    /**
     * Método que dado un objeto empleado llama al controlador que permite crear en la base de datos
     *
     * @param empleado
     */
    public void createEmpleado(Empleado empleado) {
        try {
            empleadoJpa.create(empleado);
            System.out.println("Registro creado correctamente");
        } catch (Exception e) {
            System.out.println("No se ha podido crear el registro");
        }
    }

    /**
     * Método que dado un id lo pasa como parámetro al método del controlador que busca si hay registros coincidentes en
     * la base de datos, si lo hay setea los valores en un objeto de tipo Empleado.
     *
     * @param id
     * @return object
     */
    public Empleado readEmpleado(Integer id) {
        try {
            return empleadoJpa.read(id);
        } catch (NoResultException e) {
            System.out.println("El id indicado no existe");
            return null;
        }
    }

    /**
     * Método que dado un objeto llama al controlador que permite editar en la base de datos.
     *
     * @param empleado
     */
    public void updateEmpleado(Empleado empleado) {
        System.out.println("********************** Modificar empleado por ID ************************");
        try {
            empleadoJpa.update(empleado);
            System.out.println("El empleado con id: " + empleado.getId() + " Se ha modificado correctamente");
        } catch (Exception e) {
            System.out.println("No se ha podido modificar el registro o no existe");
        }
    }

    /**
     * Método que dado un valor llama al controlador que permite borrar en la base de datos.
     *
     * @param id
     */
    public void deleteEmpleado(Integer id) {
        System.out.println("********************** Borrar empleado por Id **********************");
        try {
            empleadoJpa.delete(id);
            System.out.println("Empleado con id: " + id + " eliminado.");
        } catch (IllegalArgumentException e) {
            System.out.println("No se ha podido borrar el registro o no existe");
        }
    }

    /**
     * Método que llama al controlador que devuelve una lista de empleado
     *
     * @return arraylist
     */
    public List<Empleado> findEmpleados() {
        System.out.println("********************** Lista de empleados ************************");
        return empleadoJpa.findEmpleadoEntities();
    }

    /**
     * Método que dado un cargo lo pasa como parámetro al método del controlador que devuelve un arraylist con los
     * registros que coincidan con este cargo.
     *
     * @param cargo
     * @return arraylist
     */
    public List<Empleado> findEmpleadosByCargo(String cargo) {
        System.out.println("********************** Lista de empleados con el cargo indicado **********************");
        return empleadoJpa.findEmpleadosByCargo(cargo);
    }

}
