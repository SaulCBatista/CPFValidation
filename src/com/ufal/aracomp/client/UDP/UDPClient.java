package com.ufal.aracomp.client.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try (Scanner scanner = new Scanner(System.in)) {
            try {
                socket = new DatagramSocket();
                InetAddress serverAddress = InetAddress.getByName("localhost");

                System.out.println("Digite o CPF para validar:");
                String cpf = scanner.nextLine();

                DatagramPacket sendPacket = new DatagramPacket(cpf.getBytes(), cpf.length(), serverAddress, 12345);
                socket.send(sendPacket);

                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Reposta do servidor: " + response);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            }
        }
    }
}
