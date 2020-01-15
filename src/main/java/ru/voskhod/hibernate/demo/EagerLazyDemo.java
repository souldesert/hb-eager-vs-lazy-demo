package ru.voskhod.hibernate.demo;

import ru.voskhod.hibernate.demo.entity.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EagerLazyDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence Unit");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // get the instructor from db

            int id = 1;
            Instructor instructor = entityManager.find(Instructor.class, id);

            System.out.println("luv2code: Instructor: " + instructor);
            System.out.println("luv2code: Courses: " + instructor.getCourses());

            // commit the transaction
            entityManager.getTransaction().commit();

            entityManager.close();

            System.out.println("\nluv2code: session is now closed...\n");

            // option 1: call getter method while session is open

            System.out.println("luv2code: Courses: " + instructor.getCourses());
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}
