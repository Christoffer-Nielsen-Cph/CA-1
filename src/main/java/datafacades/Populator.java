/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import entities.*;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade <Person> pf = PersonFacade.getPersonFacade(emf);
        PersonFacade personFacade = (PersonFacade) PersonFacade.getPersonFacade(emf);
        IDataFacade <Hobby> hobby = HobbyFacade.getHobbyFacade(emf);
        IDataFacade <Phone> phone = PhoneFacade.getPhoneFacade(emf);
        Person p1 = new Person("test@test.com","Test","Testesen",new Address("Testvej 1","Testtest",new Cityinfo(1,2323,"Testby")));
        Person p2 = new Person("second@second.com","Second","Secondsen",new Address("Secondvej 2","Secondsecond",new Cityinfo(2,2323,"Secondby")));




        personFacade.addHobbyToPerson(1,5);
        personFacade.addPhoneToPerson(1,5);

    }

    public static void main(String[] args) {
        populate();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        IDataFacade mf = PersonFacade.getPersonFacade(emf);

    }
}

