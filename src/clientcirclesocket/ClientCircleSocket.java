package clientcirclesocket;
/**
 *
 * @author bonnieluong
 * 10 May 20
 * 
 */

/* Problem: Develop a server (server.java) and a client (client.java) program. The client will 
request the decimal number as a user input and then send to the server program. The server
should receive the decimal number and use it to calculate the circle area(A = pie r2) and 
return the area value to the client program. The client receives the area value and print 
it on the console.

Solution: Client code
*/

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class ClientCircleSocket {

    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 8080;

        Socket connSocket;
        BufferedReader serverInput;
        DataOutputStream serverOutput;

        Double radius;
        String circleArea;

        // Read user input
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the circle radius: ");
        while (!input.hasNextDouble()) {
            System.out.println("Value is not a Double");
            input = new Scanner(System.in);
        }
        radius = input.nextDouble();

        // Send the radius to the server
        try {
            System.out.println("Connecting to server on port " + port);
            connSocket = new Socket(hostname, port);

            serverInput = new BufferedReader(
                    new InputStreamReader(connSocket.getInputStream()));
            serverOutput = new DataOutputStream(
                    connSocket.getOutputStream());
            System.out.println("Connection established");
            serverOutput.writeBytes(radius.toString() + "\n");
            System.out.println("Waiting for reply.");
            circleArea = serverInput.readLine();
            System.out.println("Circle area is " + circleArea);

            serverOutput.close();
            serverInput.close();
            connSocket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
