package ru.voskhod.hibernate.demo;

import ru.voskhod.hibernate.demo.entity.Instructor;

import javax.persistence.*;

public class FetchJoinDemo {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistence Unit");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // get the instructor from db

            int id = 1;
            TypedQuery<Instructor> query =
                    entityManager.createQuery("select i from Instructor i " +
                                                "join fetch i.courses " +
                            "                    where i.id=:instructorId", Instructor.class);

            query.setParameter("instructorId", id);

            // execute query
            Instructor instructor = query.getSingleResult();

            System.out.println("luv2code: Instructor: " + instructor);

            // commit the transaction
            entityManager.getTransaction().commit();

            entityManager.close();

            System.out.println("\nluv2code: session is now closed...\n");



            System.out.println("luv2code: Courses: " + instructor.getCourses());
        } finally {
            entityManager.close();
            emf.close();
        }
    }
}
