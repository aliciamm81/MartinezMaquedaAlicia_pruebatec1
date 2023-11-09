package org.example.logica;

import org.example.excepciones.EmpleadoException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
public class Empleado {
    /**
     * Atributos de la clase con la clave primaria y la estrategia GenerationType.IDENTITY ya que se va a trabajar con
     * una base de datos MySQL.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String cargo;
    private Float salario;
    private LocalDate fechaInicio;

    public Empleado() {
    }

    /**
     * Constructor de la clase
     *
     * @param nombre
     * @param apellidos
     * @param cargo
     * @param salario
     * @param fechaInicio
     */
    public Empleado(String nombre, String apellidos, String cargo, Float salario, LocalDate fechaInicio) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    /**
     * Métodos get y set
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws EmpleadoException {
        if (!nombre.matches("^[A-Za-z][a-zA-Z\s]{3,}$")) {
            throw new EmpleadoException("El nombre tiene que estar compuesto como mínimo por 3 caracteres y no puede contener números ni caracteres especiales como \"ñ\" o tíldes");
        }
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) throws EmpleadoException {
        if (!apellidos.matches("^[A-Za-z][a-zA-Z\s]{3,}$")) {
            throw new EmpleadoException("El apellido tiene que estar compuesto como mínimo por 3 caracteres y no contener números ni caracteres especiales como \"ñ\" o tíldes");
        }
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) throws EmpleadoException {
        if (!cargo.matches("^[A-Za-z][a-zA-Z\s]{3,}$")) {
            throw new EmpleadoException("El cargo tiene que estar compuesto como mínimo por 3 caracteres y no contener números ni caracteres especiales como \"ñ\" o tíldes");
        }
        this.cargo = cargo;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) throws EmpleadoException {
        if (salario == null || salario < 12000) {
            throw new EmpleadoException("El salario no puede ser inferior a 12.000€ anuales");
        }
        this.salario = salario;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Método toString para convertir en cadena de carácteres todos los valores.
     *
     * @return
     */
    @Override
    public String toString() {
        return "EMPLEADO => " +
                "ID: " + id +
                " | NOMBRE: '" + nombre + '\'' +
                " | APELLIDOS: '" + apellidos + '\'' +
                " | CARGO: '" + cargo + '\'' +
                " | SALARIO: " + salario +
                " | FECHA DE INICIO: " + fechaInicio;
    }
}

