package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry builder = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(builder).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            /*Candidate candidate1 = new Candidate("Alex", 1, 50000);
            Candidate candidate2 = new Candidate("Nikolay", 2, 100000);
            Candidate candidate3 = new Candidate("Nikita", 3, 150000);
            session.save(candidate1);
            session.save(candidate2);
            session.save(candidate3);*/

            session.createQuery("from Candidate ").list().forEach(System.out::println);

            System.out.println((session.createQuery("from Candidate c where c.id = 2")).uniqueResult());

            session.createQuery("from Candidate c where c.name = :name").setParameter("name", "Alex")
                    .list().forEach(System.out::println);

            session.createQuery("delete from Candidate where id = 3").executeUpdate();

            session.createQuery("update Candidate c set c.name = :name, c.experience = :exp, c.salary = :sal where id = 2")
                    .setParameter("name", "Bob").setParameter("exp", 2).setParameter("sal", 75000).executeUpdate();

            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(builder);
        }
    }
}
