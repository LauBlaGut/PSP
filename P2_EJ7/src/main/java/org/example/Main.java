//Crea una clase para gestionar el saldo de una Cuenta. Debe tener métodos para
//obtener el saldo actual, hacer un ingreso (se incrementa al saldo), hacer un reintegro
//(se le resta al saldo), controlar si hay algún error, por ejemplo, si se hace un reintegro y
//no hay saldo; o si se hace un ingreso y el saldo supera el máximo; mostrar mensajes
//con los movimientos que se realicen. La cuenta recibe en su constructor el saldo actual
//y el valor máximo que puede tener. Los métodos de ingreso y reintegro deben
//definirse como synchronized.
//Crea después la clase Persona que extienda Thread y que realice en su método
//run() 2 ingresos y 2 reintegros alternándolos y con algún sleep() en medio. Para crear
//los movimientos de dinero generar números aleatorios entre 1 y 500 con la función
//random(): int aleatorio =((int)) (Math.random()*500+1). El constructor de la clase debe
//llevar el nombre de la persona.
//Crea en el método main() un objeto Cuenta compartido por varios objetos
//Persona e inicia el proceso de realizar movimientos en la cuenta.

package org.example;

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta(100,1900);

        Persona persona1 = new Persona("Pepe", cuenta);
        Persona persona2 = new Persona("Pepe2", cuenta);
        Persona persona3 = new Persona("Pepe3", cuenta);

        persona1.start();
        persona2.start();
        persona3.start();
    }
}