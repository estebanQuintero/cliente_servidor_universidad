package Ibero.cliente_servidor.cliente;

import Ibero.cliente_servidor.servidor.GoogleDocsExcelPage;
import Ibero.cliente_servidor.servidor.Helpers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Scanner;

public class PeticionesCliente {


    public static void main(String[] args) throws GeneralSecurityException, IOException {
        Scanner leer = new Scanner(System.in);
        Helpers helpers = new Helpers();

        //Establecemos la clase donde está la conexión con el documento Drive
        GoogleDocsExcelPage googleDocsExcelPage = new GoogleDocsExcelPage();

        String consultar = "si";

        //ciclo para poder realizar varias peticiones si así el cliente lo desea
        while (consultar.equals("si")) {
            //Se solicita un dato de ingreso
            System.out.println("Por favor ingrese el número del estudiante");

            int numero = leer.nextInt();

            //Se envia la solicitud para consultar en la hoja y obtener los datos
            googleDocsExcelPage.obtenerDataDrivenExcelOnline(numero, "Estudiante");

            //Por consola muestro los datos de la columna especifica
            System.out.println(Helpers.data.get("Nombre"));
            System.out.println(Helpers.data.get("Apellido"));
            System.out.println(Helpers.data.get("Ciudad"));
            System.out.println(Helpers.data.get("Rol"));

            String validacion = Helpers.data.get("Rol");


            //Si el usuario no tiene un rol puede ser asignado
            if (validacion == "") {
                System.out.println("¿Desea asiganrle un rol?, responde Si o No");
                String asignar = leer.next().toLowerCase();

                if (asignar.equals("si")) {
                    System.out.println("Que rol le asigna al usuario " + Helpers.data.get("Nombre"));
                    String rol = leer.next();

                    //Aqui realizo una petición para escribir en el drive y quede almacenado el rol del usuario
                    googleDocsExcelPage.asignarRol(numero, rol);

                    //Leo nuevamente los datos guardados y los muestro por consola
                    googleDocsExcelPage.obtenerDataDrivenExcelOnline(numero, "Estudiante");

                    System.out.println("Información actualizada");
                    System.out.println(Helpers.data.get("Nombre"));
                    System.out.println(Helpers.data.get("Apellido"));
                    System.out.println(Helpers.data.get("Ciudad"));
                    System.out.println(Helpers.data.get("Rol"));
                }
            }

            System.out.println("Desea realizar otra consulta?");
            consultar = leer.next().toLowerCase();
        }

    }
}
