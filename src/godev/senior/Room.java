/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godev.senior;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Carlos
 */
public class Room {

    public static ArrayList<Room> rooms = new ArrayList();

    private String nome;
    private int lotacao;
    private HashMap<Integer, ArrayList<Person>> people = new HashMap();

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

    public HashMap<Integer, ArrayList<Person>> getPeople() {
        return people;

    }

    public Room(String nome, int lotacao) {
        this.nome = nome;
        this.lotacao = lotacao;

        people.put(1, new ArrayList());
        people.put(2, new ArrayList());

    }

    public static boolean addPeopleRoom(Person person, int etapa) {

        Room small = null;
        for (Room room : rooms) {
            if (((small == null) || (room.people.get(etapa).size() < small.people.get(etapa).size())) && room.people.get(etapa).size() < room.lotacao) {

                if (etapa > 1 && !room.people.get(etapa - 1).contains(person)) {
                    small = room;
                } else if (etapa <= 1) {
                    small = room;
                }
            }
        }

        if (small != null) {
            ArrayList<Person> peopleList = small.people.get(etapa);
            if (peopleList != null) {
                peopleList.add(person);
                return true;
            }

        } else {
            return false;
        }
        return false;
    }

    public String makePeopleList(ArrayList<Person> peopleList) {

        String list = "";
        for (Person p : peopleList) {
            list += p.getNome() + " " + p.getSobrenome() + "; ";
        }

        if (list.length() > 1) {
            list = list.substring(0, list.length() - 2);
        }

        return list;
    }

    @Override
    public String toString() {
        String textConsult = "";
        String peopleList1 = makePeopleList(this.people.get(1));
        String peopleList2 = makePeopleList(this.people.get(2));

        textConsult += "Nome: " + this.nome + " \n";
        textConsult += "Lotação: " + this.lotacao + " \n";
        textConsult += "Participantes da primeira etapa: " + peopleList1 + " \n";
        textConsult += "Participantes da segunda etapa: " + peopleList2 + " \n";

        return textConsult;
    }

    public static void printRooms() {
        rooms.forEach((room) -> {
            System.out.println(room.toString());
        });
    }
}
