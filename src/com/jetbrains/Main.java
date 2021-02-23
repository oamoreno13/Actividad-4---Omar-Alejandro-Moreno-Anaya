package com.jetbrains;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        AddressBook AdBk = new AddressBook();
        Scanner scn = new Scanner(System.in);
        String result = "";
        boolean salir = false;
        while(!salir){
            System.out.println("Escriba el número de la opción deseada: \n " +
                    "1)Listar contactos \n " +
                    "2)Crear contacto \n " +
                    "3)Eliminar contacto \n " +
                    "4)Salir");
            result = scn.nextLine();
            switch (result) {
                case "1":
                    AdBk.list();
                    break;
                case "2":
                    AdBk.create();
                    break;
                case "3":
                    AdBk.delete();
                    break;
                case "4":
                    salir= true;
                    break;
            }
        }
        System.out.println("Hasta luego");
    }
}