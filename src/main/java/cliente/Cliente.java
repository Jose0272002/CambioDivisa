package cliente;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Socket socket = new Socket("localhost",46202);
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            String mensaje1 =in.readLine();
            System.out.println(mensaje1);
            String nombre =sc.next();
            //. Recibe instrucciones: "Dime el valor y divisa que quieres transformar
            // [valor divisa] o el valor, la divisa y la divisa a transformarlo [valor divisa a divisa]".
            String mensaje2 =in.readLine();
            System.out.println(mensaje2);
            System.out.println("valor");
            String valor = sc.next();
            String cant =valor.split(" ")[0];
            String moneda =valor.split(" ")[1];
            //envia cantidad
            out.println(cant);
            //envia moneda
            out.println(moneda);



        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
