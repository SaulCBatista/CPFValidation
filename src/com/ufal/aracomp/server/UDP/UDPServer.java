package com.ufal.aracomp.server.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import com.ufal.aracomp.util.CPFValidator;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(12345);
            System.out.println("Servidor UDP aguardando...");

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String cpf = new String(receivePacket.getData(), 0, receivePacket.getLength());
                boolean isValid = CPFValidator.validade(cpf);
                String response = isValid ? "CPF válido " : "CPF inválido ";

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(response.getBytes(), response.length(), clientAddress,
                        clientPort);
                socket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && socket.isClosed()) {
                socket.close();
            }
        }
    }
}
