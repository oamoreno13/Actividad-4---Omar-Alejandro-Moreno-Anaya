package com.jetbrains;
import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBook {

    String separador = FileSystems.getDefault().getSeparator();
    String filepath = String.format(
            "C:%sUsers%somore%sIdeaProjects%sActividad 4 - Omar Alejandro Moreno Anaya%ssrc%sAddressBook.txt",
            separador, separador, separador, separador, separador, separador
    );
    File file = new File(filepath);
    Scanner scn = new Scanner(System.in);


    private HashMap<String, String> load (){
        HashMap<String, String> contactos = new HashMap<>();
        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(filepath));
                String linea;
                while (( linea = br.readLine()) != null){
                    String[] temp = linea.split(",");
                    try{contactos.put(temp[0], temp[1]);}catch (ArrayIndexOutOfBoundsException e){ }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return contactos;
    }

    private void save (String numero, String nombre, boolean delete){
        try {
            if (file.exists()) {
                BufferedWriter bw;
                if (delete){
                    bw = new BufferedWriter(new FileWriter(filepath));
                    if (numero.isEmpty()){
                        bw.write("");
                    }else{
                        bw.write(numero + "," + nombre);
                    }

                }else{
                    bw = new BufferedWriter(new FileWriter(filepath, true));
                    bw.append(numero + "," + nombre);
                }
                bw.newLine();
                bw.flush();
                if (!delete){
                    System.out.println("¡Contacto guardado exitosamente!");
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void list(){
        HashMap<String, String> contactos = load();
        if (contactos.size() > 0){
            System.out.println("Contactos:");
            contactos.forEach((k,v) -> System.out.println(k + " : " + v));
        }else{
            System.out.println("No hay contactos registrados en la agenda.");
        }

    }

    public void create(){
        HashMap<String, String> contactos = load();
        String numero = "";
        String nombre = "";
        boolean registrar = false;
        while(!registrar){
            System.out.println("Introduzca el número de contacto:");
            numero = scn.nextLine();
            if (contactos.containsKey(numero)){
                System.out.println("El número de contacto ya está registrado.");
            }else{
                if (!numero.matches("[0-9]*")){
                    System.out.println("El campo número recibe unicamente valores numéricos y en formato de 10 dígitos.");
                }else{
                    if (numero.length() != 10){
                        System.out.println("El formato del número debe ser a 10 dígitos.");
                    }else{
                        registrar = true;
                    }
                }
            }
        }
        registrar = false;
        while(!registrar){
            System.out.println("Introduzca el nombre de contacto:");
            nombre = scn.nextLine();
            if(nombre.isEmpty()){
                System.out.println("El campo nombre no puede ir vacío.");
            }else{
                save(numero, nombre, false);
                registrar = true;
            }
        }
    }

    public void delete(){
        HashMap<String, String> contactos = load();
        if (contactos.size() > 0) {
            boolean existente = false;
            while (!existente) {
                System.out.println("Introduzca el número de contacto a eliminar:");
                String numero = scn.nextLine();
                if (contactos.containsKey(numero)) {
                    contactos.remove(numero);
                    existente = true;
                } else {
                    System.out.println("El número no existe en agenda");
                }
            }
            System.out.println("Contacto se ha eliminado.");
            if (contactos.size() == 0) {
                save("","", true);
            }else{
                contactos.forEach((k, v) -> save(k, v, true));
            }
        }else{
            System.out.println("No hay contacto registrado con ese número");
        }
    }
}
