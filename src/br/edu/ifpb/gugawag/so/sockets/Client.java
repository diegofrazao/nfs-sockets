package br.edu.ifpb.gugawag.so.sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        System.out.println("\n###   Comandos do Cliente    ###\n" +
                            "# reddir {CAMINHO DA PASTA}    #\n" +
                            "# rename {ARQUIVO} {NOVO NOME} #\n" +
                            "# create {ARQUIVO}             #\n" +
                            "# remove {ARQUIVO}             #\n");

        Socket socket = new Socket("127.0.0.1", 7001);
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true) {
            System.out.print("~ ");
            Scanner teclado = new Scanner(System.in);
            dos.writeUTF(teclado.nextLine());

            System.out.println(dis.readUTF());
        }
    }
}
