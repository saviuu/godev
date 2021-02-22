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
public class Food {
    
    public static ArrayList<Food> foodPlace = new ArrayList();
    
    private String nome;
    private ArrayList<Person> people = new ArrayList();
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public ArrayList<Person> getPeople() {
        return people;
    }
    
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
    
    public Food(String nome) {
        this.nome = nome;
        
    }
    
    public static boolean addPeopleFood(Person person) {
        
        Food small = null;
        for (Food food : foodPlace) {
            if ((small == null) || (food.people.size() < small.people.size())) {
                small = food;
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
        return "Food{" + "nome=" + nome + ", people=" + people.size() + '}';
        
    }

    public static void printFoodPlace() {
        foodPlace.forEach(food -> {
            System.out.println(food.toString());
        });
    }
}
