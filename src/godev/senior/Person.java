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
}
