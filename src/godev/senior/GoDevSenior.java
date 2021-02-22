/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godev.senior;

import java.util.Scanner;

/**
 *
 * @author Carlos
 */
public class GoDevSenior {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        printMenu();

        Scanner in = new Scanner(System.in);

        while (true) {
            printMenu();
            System.out.println("Digite a opção escolhida: ");

            try {
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    System.out.println("Informe o nome do participante: ");
                    String nome = in.nextLine();
                    System.out.println("Informe o sobrenome do participante: ");
                    String sobrenome = in.nextLine();
                    Person pessoa = new Person(nome, sobrenome);
                    Person.people.add(pessoa);
                    if (!Room.addPeopleRoom(pessoa)){
                        System.out.println("Não existem salas disponiveis");
                    }
                    if (!Food.addPeopleFood(pessoa)){
                        System.out.println("Não existe Área de alimentação disponível");
                    }
                    break;
                case 2:
                    System.out.println("Informe o nome da sala: ");
                    String nomeSala = in.nextLine();
                    System.out.println("Informe a lotação da sala: ");
                    int lotacao = Integer.parseInt(in.nextLine());
                    Room room = new Room(nomeSala, lotacao);
                    Room.rooms.add(room);
                    break;
                case 3:
                    System.out.println("Informe o nome da área de alimentação: ");
                    String placeName = in.nextLine();
                    Food foodPlace = new Food(placeName);
                    Food.foodPlace.add(foodPlace);
                    break;

                case 4:
                    Room.printRooms();
                    Food.printFoodPlace();
                    break;
            }
            } catch(Exception e) {
                System.out.println("Opção inválida");
            } 
        }

    }

    private static void printMenu() {
        System.out.println("Escolha uma das opção abaixo:");
        System.out.println("[1] Cadastro de participantes");
        System.out.println("[2] Cadastro de salas");
        System.out.println("[3] Área de alimentação");
        System.out.println("");

    }

}