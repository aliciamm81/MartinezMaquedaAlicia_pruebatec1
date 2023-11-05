package org.example.logica;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@Entity
public class Empleado {
    // Atributos de la clase con la clave primaria y la estrategia GenerationType.IDENTITY ya
    // que se va a trabajar con una base de datos MySQL.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private String apellidos;
    private String cargo;
    private Float salario;
    private Date fechaInicio;

    public Empleado() {
    }

    //Constructor con parámetros
    public Empleado(Integer id, String nombre, String apellidos, String cargo, Float salario, Date fechaInicio) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cargo = cargo;
        this.salario = salario;
        this.fechaInicio = fechaInicio;
    }

    // Métodos get y set
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public Float getSalario() {
        return salario;
    }

    public void setSalario(Float salario) {
        this.salario = salario;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    // Método toString para convertir en cadena de carácteres todos los valores.

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

