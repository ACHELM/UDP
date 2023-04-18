package udp;

import java.io.IOException;
import java.net.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author <su nombre aquí>
 */
public class ServerUDP {
    public static String extraerTexto(String texto) {
        String resultado = "";
        int salto = Character.getNumericValue(texto.charAt(0));
    
        for (int i = 1; i < texto.length(); i += salto + 1) {
            resultado += texto.charAt(i);
        }
    
        return resultado;
    }

    public static void main(String[] args) throws IOException
    {
        // DATOS DEL SERVIDOR
        //* FIJO: Si se lee de línea de comando debe comentarse
        int port = 54322; // puerto del servidor
        //* VARIABLE: Si se lee de línea de comando debe descomentarse
        // int port = Integer.parseInt(args[0]); // puerto del servidor

        // SOCKET
        DatagramSocket server = null;

        //* COMPLETAR Crear e inicalizar el socket del servidor
        server = new DatagramSocket(port);
        
        // Funcion PRINCIPAL del servidor
        while (true)
        {
            //* COMPLETAR: Crear e inicializar un datagrama VACIO para recibir la respuesta de máximo 400 bytes
        	int tamanoBuffer = 800;
            byte[] buffer = new byte[tamanoBuffer];
            DatagramPacket dp = new DatagramPacket(buffer, tamanoBuffer);
        	
            //* COMPLETAR: Recibir datagrama
            server.receive(dp);
            //* COMPLETAR: Obtener texto recibido
            String line = new String(
            		dp.getData(),
            		dp.getOffset(),
            		dp.getLength(),
            		StandardCharsets.UTF_8);

            //* COMPLETAR: Mostrar por pantalla la direccion socket (IP y puerto) del cliente y su texto
            System.out.println("Mensaje recibido de: " + dp.getAddress() +
            		" puerto: " + dp.getPort());
            // Capitalizamos la linea
            line = extraerTexto(line);

            //* COMPLETAR: crear datagrama de respuesta
            dp = new DatagramPacket(
            		line.getBytes(),
            		line.getBytes().length,
            		dp.getAddress(),
            		dp.getPort()
            		);
            		
            //* COMPLETAR: Enviar datagrama de respuesta
            server.send(dp);

        } // Fin del bucle del servicio
    }

}
