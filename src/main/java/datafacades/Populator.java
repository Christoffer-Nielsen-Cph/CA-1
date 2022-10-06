/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datafacades;

import javax.persistence.EntityManagerFactory;

import entities.*;
import errorhandling.DuplicateException;
import errorhandling.EntityNotFoundException;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate() throws EntityNotFoundException, DuplicateException {
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade pf = PersonFacade.getPersonFacade(emf);
        HobbyFacade hf = HobbyFacade.getHobbyFacade(emf);
        PhoneFacade phoneFacade = PhoneFacade.getPhoneFacade(emf);
        phoneFacade.create(new Phone(911,"samsung"));









    }

    public static void main(String[] args) throws EntityNotFoundException, DuplicateException {
        populate();
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
        PersonFacade FACADE;

    }
}

