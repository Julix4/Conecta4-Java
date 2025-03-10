# 🟡🔴 Connect Four (Conecta 4) - Java Game

Este es un sencillo juego de **Conecta 4** desarrollado en **Java**, donde dos jugadores compiten para alinear **cuatro fichas del mismo color** en una fila, columna o diagonal. 

## 🚀 Características

Implementado en **Java** utilizando una matriz bidimensional (`char[][] board`).  
Verificación de **victoria** en líneas **horizontales, verticales y diagonales**.  
Control de **jugadas inválidas** y tablero **interactivo**.  
**Fácil de ejecutar** en consola sin necesidad de interfaces gráficas.  

---

## 📜 **Reglas del Juego**

**Dos jugadores**: Uno usa fichas **'R'** (rojas) y el otro **'Y'** (amarillas).  
**Turnos alternos**: Cada jugador elige una columna para dejar caer su ficha.  
**Gravedad**: La ficha cae en la **primera posición vacía** desde abajo.  
**Objetivo**: El primer jugador en alinear **cuatro fichas consecutivas** en **horizontal, vertical o diagonal** gana la partida.  
**Tablero lleno**: Si el tablero se llena sin ganador, el juego termina en empate.  

---

## 🛠 **Cómo ejecutar el juego**

### Clona este repositorio
```bash
git clone https://github.com/TU_USUARIO/Conecta4-Java.git
cd Conecta4-Java

### Complila y ejecuta el programa

javac -d out src/edu/uoc/pac1/PAC1Ex3.java
java -cp out edu.uoc.pac1.PAC1Ex3
