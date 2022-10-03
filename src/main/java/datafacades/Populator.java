/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import entities.*;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate() throws EntityNotFoundException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        HobbyFacade hf = HobbyFacade.getHobbyFacade(emf);
        Person p1 = new Person("test@test.com","Test","Testesen",new Address("Testvej 1","Testtest",new Cityinfo(1,2323,"Testby")));
        Person p2 = new Person("second@second.com","Second","Secondsen",new Address("Secondvej 2","Secondsecond",new Cityinfo(2,2323,"Secondby")));
        Hobby h1 = new Hobby("Fishing");

       // pf.create(p1);
       // pf.create(p2);
       // pf.addHobbyToPerson(1,1);
       // pf.addPhoneToPerson(1,1);






    }

    public static void main(String[] args) throws EntityNotFoundException {
        populate();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();


    }
}

