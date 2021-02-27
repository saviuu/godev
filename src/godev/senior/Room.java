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
 * @author Sávio Bertoldi
 */
public class Room {

    public static ArrayList<Room> rooms = new ArrayList();
    public static String fileName = "rooms.data";

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

    public static String makePeopleList(ArrayList<Person> peopleList, String separator) {

        String list = "";
        for (Person p : peopleList) {
            list += p.getNome() + " " + p.getSobrenome() + separator;
        }

        if (list.length() > 1) {
            list = list.substring(0, list.length() - separator.length());
        }

        return list;
    }

    @Override
    public String toString() {
        String textConsult = "";
        String peopleList1 = makePeopleList(this.people.get(1), ", ");
        String peopleList2 = makePeopleList(this.people.get(2), ", ");

        textConsult += "Nome: " + this.nome + " \n";
        textConsult += "Lotação: " + this.lotacao + " \n";
        textConsult += "Participantes da primeira etapa: " + peopleList1 + " \n";
        textConsult += "Participantes da segunda etapa: " + peopleList2 + " \n";

        return textConsult;
    }

    public static void print() {
        rooms.forEach((room) -> {
            System.out.println(room.toString());
        });
        
    }
     public static boolean save() {

        String content = "";
        for (Room room : rooms) {
            content += room.getNome() + ";" + room.getLotacao() + ";1:" + makePeopleList(room.people.get(1), "/") + ";2:" + makePeopleList(room.people.get(2), "/") + "\n";
        }
        return Arquivo.Write(fileName, content);

    }

    public static ArrayList<Person> returnPeopleListFromFile(String peopleList) {
        ArrayList<Person> list = new ArrayList();
        String[] peopleListData = peopleList.split(":");
        int etapa = Integer.parseInt(peopleListData[0]);
        String[] pData = peopleListData[1].split("/");
        for (String person : pData) {
            Person p = null;
            for (Person pe : Person.people) {
                if ((pe.getNome() + pe.getSobrenome()).equals(person.replaceAll(" ", ""))) {
                    p = pe;
                }
            }

            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }

    public static void load() {
        String content = Arquivo.Read(fileName);
        if (!content.isEmpty()) {
            for (String line : content.split("\n")) {
                String[] data = line.split(";");
                String placeName = data[0];
                int lotacao = Integer.parseInt(data[1]);
                Room room = new Room(placeName, lotacao);
                Room.rooms.add(room);
                room.people.put(1, returnPeopleListFromFile(data[2]));
                room.people.put(2, returnPeopleListFromFile(data[3]));

            }
        }
    }
}
