package com.ufal.aracomp.server.TCP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import com.ufal.aracomp.util.CPFValidator;

public class TCPServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor TCP aguardando conexões...");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                    System.out.println("Conexão recebida de " + clientSocket.getInetAddress());

                    String cpf = in.readLine();
                    boolean isValid = CPFValidator.validade(cpf);
                    out.println(isValid ? "CPF válido" : "CPF inválido");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
