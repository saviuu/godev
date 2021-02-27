/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godev.senior;

import java.util.Scanner;

/**
 *
 * @author Sávio Bertoldi
 */
public class GoDevSenior {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Person.loadPeople();
        Food.load();
        Room.load();

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
                        if (!Room.addPeopleRoom(pessoa, 1) || !Room.addPeopleRoom(pessoa, 2)) {
                            System.out.println("Não existem salas disponiveis");
                        }
                        if (!Food.addPeopleFood(pessoa, 1) || !Food.addPeopleFood(pessoa, 2)) {
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
                        System.out.println("Informe a lotação da sala: ");
                        int lotacaoPlace = Integer.parseInt(in.nextLine());
                        Food foodPlace = new Food(placeName, lotacaoPlace);
                        Food.foods.add(foodPlace);
                        break;

                    case 4:
                        Room.print();
                        break;
                        
                    case 5:
                        Food.print();
                        break;
                        
                    case 6:
                       System.out.println("Informe o nome do participante: ");
                        String nomeP = in.nextLine();
                        System.out.println("Informe o sobrenome do participante: ");
                        String sobrenomeP = in.nextLine();
                        Person person = Person.getPersonByName(nomeP, sobrenomeP);
                        if(person != null){
                            person.print();
                        } else {
                            System.out.println("O participante informado não foi encontrado");
                        }
                        break;

                    case 7:
                        Person.savePeople();
                        Food.save();
                        Room.save();
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida");
            }
        }

    }

    private static void printMenu() {
        System.out.println("Escolha uma das opção abaixo:");
        System.out.println("[1] Cadastro de Participantes");
        System.out.println("[2] Cadastro de Salas");
        System.out.println("[3] Cadastro de Área de alimentação");
        System.out.println("[4] Consulta de Salas");
        System.out.println("[5] Consulta de Áreas de alimentação");
        System.out.println("[6] Consulta de Participantes");
        System.out.println("[7] Salvar Dados");
        System.out.println("");

    }

}
