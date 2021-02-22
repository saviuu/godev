/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godev.senior;

import java.util.ArrayList;

/**
 *
 * @author Carlos
 */
public class Room {

    public static ArrayList<Room> rooms = new ArrayList();

    private String nome;
    private int lotacao;
    private ArrayList<Person> people = new ArrayList();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

    public Room(String nome, int lotacao) {
        this.nome = nome;
        this.lotacao = lotacao;

    }

    public static boolean addPeopleRoom(Person person) {

        Room small = null;
        for (Room room : rooms) {
            if (((small == null) || (room.people.size() < small.people.size()))&& room.people.size() < room.lotacao ) {
                small = room;
            }
        }

        if (small != null) {
            small.people.add(person);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public String toString() {
        return "Room{" + "nome=" + nome + ", lotacao=" + lotacao + ", people=" + people.size() + '}';
    }
    public static void printRooms() {
        rooms.forEach((room) -> {
            System.out.println(room.toString());
        });
    }
}
