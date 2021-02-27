/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package godev.senior;

import java.util.ArrayList;

/**
 *
 * @author Sávio Bertoldi
 */
public class Person {

    public static final String fileName = "people.data";

    public static ArrayList<Person> people = new ArrayList();

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
    private String sobrenome;

    public Person(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;

    }

    public static boolean savePeople() {

        String content = "";
        for (Person person : people) {
            content += person.getNome() + ";" + person.getSobrenome() + "\n";
        }
        return Arquivo.Write(fileName, content);

    }

    public static void loadPeople() {
        String content = Arquivo.Read(fileName);
        if (!content.isEmpty()) {
            for (String line : content.split("\n")) {
                String[] data = line.split(";");
                String nome = data[0];
                String sobrenome = data[1];
                people.add(new Person(nome, sobrenome));

            }
        }
    }

    public void print() {
        Room firstRoom = null;
        Room secondRoom = null;
        for (Room room : Room.rooms) {
            if (room.getPeople().get(1).contains(this)) {
                firstRoom = room;

            }
            if (room.getPeople().get(2).contains(this)) {
                secondRoom = room;

            }
        }
        Food firstPlaceFood = null;
        Food secondPlaceFood = null;
        for (Food food : Food.foods) {
            if (food.getPeople().get(1).contains(this)) {
                firstPlaceFood = food;

            }
            if (food.getPeople().get(2).contains(this)) {
                secondPlaceFood = food;

            }
        }

        if (firstRoom != null) {
            System.out.println("Sala da primeira etapa: " + firstRoom.getNome());
        }
        if (secondRoom != null) {
            System.out.println("Sala da segunda etapa: " + secondRoom.getNome());
        }
        if (firstPlaceFood != null) {
            System.out.println("Área de alimentação da primeira etapa: " + firstPlaceFood.getNome());
        }
        if (secondPlaceFood != null) {
            System.out.println("Área de alimentação da segunda etapa: " + secondPlaceFood.getNome());
        }
    }

    public static Person getPersonByName(String nome, String sobrenome) {
        for (Person person : people) {
            if (person.getNome().equalsIgnoreCase(nome) && person.getSobrenome().equalsIgnoreCase(sobrenome)) {
                return person;
            }
        }
        return null;
    }
}
