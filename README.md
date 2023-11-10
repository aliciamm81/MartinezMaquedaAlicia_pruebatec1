# Desarrollo de una Aplicación de Gestión de Empleados

**Martinez Maqueda Alicia - pruebatec1**

Ejercicio para la **pruebatec1** de Java básico - Documentación del proyecto.

## Comenzando 🚀

Con estas instrucciones podrás obtener
una copia del proyecto en tu repositorio local y también conocerás el funcionamiento de la aplicación.

## Pre-requisitos 📋

Para arrancar el proyecto es necesario tener instalado JDK 17, un entorno de desarrollo integrado (IDE) y un motor de
base
de datos.

## Instalación 🔧

Para empezar hay que tener creada una base de datos con las siguientes sentencias:

#### Crear base de datos :

```
CREATE DATABASE IF NOT EXISTS `empresa` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_spanish_ci;
USE `empresa`;
```

#### Crear tabla :

```
CREATE TABLE `empleado` (
`ID` int(11) NOT NULL,
`NOMBRE` varchar(100) NOT NULL,
`APELLIDOS` varchar(200) NOT NULL,
`CARGO` varchar(100) NOT NULL,
`SALARIO` decimal(10,0) NOT NULL,
`FECHAINICIO` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish_ci;
```

#### Indicar clave primaria:

```
ALTER TABLE `empleado`
ADD PRIMARY KEY (`ID`);
```

## Despliegue 📦

Se puede desplegar la aplicación en un entorno local descargando el archivo zip desde GitHub, una vez
descargado hay que abrir el proyecto desde un IDE y ejecutar el método main para arrancar el proyecto.

## Instrucciones de la aplicación 📄

La aplicación permite gestionar una base de datos de empleados permitiendo crear registros nuevos, editarlos,
borrarlos y consultarlos.

Para ellos hay que seleccionar una de las siguientes opciones que aparecen en el Menú de inicio:

* 1 => Crear un empleado
* 2 => Editar un empleado
* 3 => Lista de empleados
* 4 => Borrar un empleado
* 5 => Buscar empleados según un cargo
* 6 => Salir

**La opción número 1:** Esta opción va solictiando los datos el empleado, nombre, apellidos, cargo
*(no pueden contener números ní carácteres especiales como tíldes, ñ etc..)*. Si los datos introducidos
són válidos lo guardará en la base de datos y preguntará si se quiere crear más registros, para **crear**
más registros hay que pulsar la tecla "+", para salir hay que pulsar cualquier tecla.

**La opción número 2:** Esta opción permite **editar** usuarios, para ello hay que introducir el id del usuario
que se quiere modificar, si el usuario existe, el programa irá solicitando los datos de la misma forma
que en la opción número 1 *(crear un emplado)*.

**La opción número 3:** Permite **obtener** todo el contenido de la lista de empleados que hay en la base de datos.

**La opción número 4:** Solicita el id del empleado que se desea **borrar**, si existe se eliminará de la base de
datos y se mostrará un mensaje de borrado con éxito.

**La opción número 5:** Muestra por pantalla todos los datos de los empleados con un cargo determinado, para
ello hay que introducir el cargo concreto que se desea **buscar**.

**La opción número 6:** Permite **salir** de la aplicación.

## Pruebas ⚙️

En esta sección realizaré una serie de pruebas que demostrarán si la aplicación puede realizar las funciones básicas
de una base de datos **CRUD**.

### 1. Crear

Voy a crear un usuario con los siguientes datos:

Nombre: *Alicia*,
Apellidos: *Martinez Maqueda*,
Cargo: *Programador*,
Salario: *18000*,
Fecha: *2023 / 11 / 09*

#### Resultado obtenido:

```
Introduce los empleados que quieres registrar
Introduce un nombre:
Alicia
Introduce los apellidos:
Martinez Maqueda
Introduce el cargo:
Programador
Introduce el salario:
18000
Introduce la fecha en el siguiente orden: Año + ENTER, Mes + ENTER, Día + ENTER
2023
11
9
```

```
Registro creado correctamente
Para registrar más empleados marca "+" para terminar marca cualquier tecla*
```

### 2. Leer:

Voy a listar todo el contenido de la tabla empleados:

```
********************** Lista de empleados ************************
*EMPLEADO => ID: 354 | NOMBRE: 'Laura' | APELLIDOS: 'Gonzalez Martinez' | CARGO: 'administrativo' | SALARIO: 17000.0 | FECHA DE INICIO: 2023-12-01
*EMPLEADO => ID: 355 | NOMBRE: 'Alicia ' | APELLIDOS: 'Martinez Rodriguez' | CARGO: 'programador' | SALARIO: 18000.0 | FECHA DE INICIO: 2023-11-09
```

Ahora voy a listar el contenido de la tabla de los empleados con el cargo programador:

#### Resultado obtenido:

```
Introduce el cargo que quieres consultar
programador
```

```
********************** Lista de empleados con el cargo indicado **********************
EMPLEADO => ID: 355 | NOMBRE: 'Alicia ' | APELLIDOS: 'Martinez Rodriguez' | CARGO: 'programador' | SALARIO: 18000.0 |FECHA DE INICIO: 2023-11-09
```

### 3. Actualizar:

Voy a editar el usuario que acabo de crear con los siguientes datos:

```
Introduce el id del empleado que quieres modificar, si no conoce el id puede listar todos los empleados desde la opción
3 del menú:
354
Introduce un nombre:
Laura
Introduce los apellidos:
Gonzalez Martinez
Introduce el cargo:
Administrativo
Introduce el salario:
17000
Introduce la fecha en el siguiente orden: Año + ENTER, Mes + ENTER, Día + ENTER
2023
12
01
```

#### Resultado obtenido:

```
********************** Modificar empleado por ID ************************
El empleado con id: 354 Se ha modificado correctamente*
```

### 4. Borrar:

Voy a borrar el usuario con id 355:

#### Resultado obtenido:

```
Introduce el id del empleado a borrar, si no conoce el id puede listar todos los empleados desde la opción 3 del menú: 
355
```

```
********************** Borrar empleado por Id **********************
*Empleado con id: 355 eliminado.*
```

## Versionado 📌

Versión: 1.0.0

## Autores ✒️

* **Alicia Martínez Maqueda** *https://github.com/aliciamm81*