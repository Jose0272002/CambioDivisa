package servidor;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

public class Gestor extends Thread{
    private LocalDateTime ahora= LocalDateTime.now();
    private DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm:ss");
    private Semaphore semaphore =new Semaphore(3);
    private Socket socket;
    public Gestor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try(PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        ) {
            semaphore.acquire();
           out.println("Nombre");
           //. Envía instrucciones: "Dime el valor y divisa que quieres transformar
            // [valor divisa] o el valor, la divisa y la divisa a transformarlo [valor divisa a divisa]".
            out.println("""
            Dime el valor y divisa que quieres transformar [valor divisa] 
            o el valor, la divisa y la divisa a transformarlo [valor divisa a divisa]
            """);
            in.readLine();
            out.println();
            //recibe cantidad
            String cantidadStr = in.readLine();
            //recibe moneda
            String moneda = in.readLine();
            //3a. Envía instrucciones: "Dime la divisa a lo que lo quieres transformar"
            out.println("Dime la divisa a lo que lo quieres transformar");
            //Recibe la divisa
            in.readLine();
            //Envía la transformación: "48.69 EUR son 52.58 USD" y termina.
            out.println("");
            semaphore.release();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
