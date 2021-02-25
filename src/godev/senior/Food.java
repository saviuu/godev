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
public class Food {

    public static ArrayList<Food> foodPlace = new ArrayList();

    private String nome;
    private HashMap<Integer, ArrayList<Person>> people = new HashMap();

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public HashMap<Integer, ArrayList<Person>> getPeople() {
        return people;

    }

    public Food(String nome) {
        this.nome = nome;
        people.put(1, new ArrayList());
        people.put(2, new ArrayList());
    }

    public static boolean addPeopleFood(Person person, int etapa) {

        Food small = null;
        for (Food food : foodPlace) {
            if ((small == null) || (food.people.get(etapa).size() < small.people.get(etapa).size())) {

                if (etapa > 1 && !food.people.get(etapa - 1).contains(person)) {
                    small = food;
                } else if (etapa <= 1) {
                    small = food;
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
        textConsult += "Participantes da primeira etapa: " + peopleList1 + " \n";
        textConsult += "Participantes da segunda etapa: " + peopleList2 + " \n";

        return textConsult;
    }

    public static void printFoodPlace() {
        foodPlace.forEach(food -> {
            System.out.println(food.toString());
        });
    }
}
