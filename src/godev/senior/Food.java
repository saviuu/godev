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
public class Food {

    public static ArrayList<Food> foods = new ArrayList();
    public static String fileName = "foods.data";

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

    public Food(String nome, int lotacao) {
        this.nome = nome;
        this.lotacao = lotacao;

        people.put(1, new ArrayList());
        people.put(2, new ArrayList());

    }

    public static boolean addPeopleFood(Person person, int etapa) {

        Food small = null;
        for (Food food : foods) {
            if (((small == null) || (food.people.get(etapa).size() < small.people.get(etapa).size())) && food.people.get(etapa).size() < food.lotacao) {

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
        foods.forEach((food) -> {
            System.out.println(food.toString());
        });

    }

    public static boolean save() {

        String content = "";
        for (Food food : foods) {
            content += food.getNome() + ";" + food.getLotacao() + ";1:" + makePeopleList(food.people.get(1), "/") + ";2:" + makePeopleList(food.people.get(2), "/") + "\n";
        }
        return Arquivo.Write(fileName, content);

    }

    public static ArrayList<Person> returnPeopleListFromFile(String peopleList) {
        ArrayList<Person> list = new ArrayList();
        String[] peopleListData = peopleList.split(":");
        int etapa = Integer.parseInt(peopleListData[0]);
        if (peopleListData.length < 2) {
            return list;
        }
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
                Food food = new Food(placeName, lotacao);
                Food.foods.add(food);
                food.people.put(1, returnPeopleListFromFile(data[2]));
                food.people.put(2, returnPeopleListFromFile(data[3]));

            }
        }
    }
}
