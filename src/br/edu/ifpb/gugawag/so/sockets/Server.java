package br.edu.ifpb.gugawag.so.sockets;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("== Servidor ==");

        ServerSocket serverSocket = new ServerSocket(7001);
        Socket socket = serverSocket.accept();

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        while (true) {
            System.out.println("Cliente: " + socket.getInetAddress());

            String mensagem = dis.readUTF();

            String[] list = mensagem.split(" ");

            switch (list[0]) {
                case "reddir": {
                    String resposta = "";
                    File path = new File(list[1]);
                    File[] arquivos = path.listFiles();

                    if (arquivos != null) {
                        for (File arquivo : arquivos) {
                            resposta += arquivo.getName() + " ";
                        }
                        dos.writeUTF(resposta);
                    }
                    break;
                }
                case "rename":{
                    File arquivo = new File(list[1]);
                    File novoNome = new File(list[2]);

                    if (arquivo.renameTo(novoNome))
                        dos.writeUTF("Arquivo renomeado: " + arquivo.getName());
                    else
                        dos.writeUTF("Arquivo não foi renomeado!");
                    break;
                }
                case "create": {
                    File arquivo = new File(list[1]);

                    if (arquivo.createNewFile())
                        dos.writeUTF("Arquivo criado: " + arquivo.getName());
                    else
                        dos.writeUTF("Arquivo não foi criado!");
                    break;
                }
                case "remove": {
                    File arquivo = new File(list[1]);

                    if (arquivo.delete())
                        dos.writeUTF("Arquivo removido" + arquivo.getName());
                    else
                        dos.writeUTF("Arquivo não foi removido!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        }
    }
}
