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
}
