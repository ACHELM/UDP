package tcp;

import java.io.*;
import java.net.*;
import java.util.Scanner;

@SuppressWarnings("unused")
class ServerTCP {
    public static String extract_text(String s){
        String res = "";
        int salto = Character.getNumericValue(s.charAt(0));
        for(int i = 1; i < s.length(); i += salto + 1)
            res += s.charAt(i);
        return res;
    }

    @SuppressWarnings({ "null", "resource" })
	public static void main(String[] args) throws IOException
    {
        // DATOS DEL SERVIDOR
        //* FIJO: Si se lee de línea de comando debe comentarse
        int port = 12345; // puerto del servidor
        int tamanoCola = 1; // Recordar modificar tanto los 2 sockets como el buffer y el PrintWritter
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        // int port = Integer.parseInt(args[0]);

        // SOCKETS
        ServerSocket server = null; // Pasivo (recepción de peticiones)
        Socket client = null;       // Activo (atención al cliente)

        // FLUJOS PARA EL ENVÍO Y RECEPCIÓN
        BufferedReader in = null;
        PrintWriter out = null;

        //* COMPLETAR: Crear e inicalizar el socket del servidor (socket pasivo)
        server = new ServerSocket(port, tamanoCola); 


        while (true) // Bucle de recepción de conexiones entrantes
        {
            //* COMPLETAR: Esperar conexiones entrantes
        	client = server.accept();
            //* COMPLETAR: Una vez aceptada una conexion, inicializar flujos de entrada/salida del socket conectado
        	in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
        	
            boolean salir = false;
            while(!salir) // Inicio bucle del servicio de un cliente
            {
                //* COMPLETAR: Recibir texto en line enviado por el cliente a través del flujo de entrada del socket conectado
                String line = null;
                in.readLine();
                //* COMPLETAR: Comprueba si es fin de conexion - SUSTITUIR POR LA CADENA DE FIN enunciado
                if (line.compareTo("FINISH") != 0){
                    line = extract_text(line);
                    
                    //* COMPLETAR: Enviar texto al cliente a traves del flujo de salida del socket conectado
                    out.println(line);
                } else { // El cliente quiere cerrar conexión
                    salir = true;
                    out.println("OK");
                }
            } // fin del servicio

            //* COMPLETAR: Cerrar flujos y socket
            in.close();
            out.close();
            client.close();
        } // fin del bucle
    } // fin del metodo
}
