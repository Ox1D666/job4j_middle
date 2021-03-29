package ru.job4j.hql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.hql.model.Base;
import ru.job4j.hql.model.Candidate;
import ru.job4j.hql.model.Vacancy;

public class HbmRun {
    public static void main(String[] args) {
        final StandardServiceRegistry builder = new StandardServiceRegistryBuilder().configure().build();
        try {
            SessionFactory sf = new MetadataSources(builder).buildMetadata().buildSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();

//            Vacancy vacancy1 = new Vacancy("Developer");
//            Vacancy vacancy2 = new Vacancy("Administrator");
//            session.save(vacancy1);
//            session.save(vacancy2);

//            Base base = new Base();
//            base.addVacancy(vacancy1);
//            base.addVacancy(vacancy2);
//            session.save(base);

//            Candidate candidate1 = new Candidate("Alex", 1, 50000);
//            Candidate candidate2 = new Candidate("Nikolay", 2, 100000);
//            candidate1.setVacancyBase(session.get(Base.class, 1));
//            candidate2.setVacancyBase(session.get(Base.class, 1));
//            session.save(candidate1);
//            session.save(candidate2);


            System.out.println(session.createQuery("select distinct c from Candidate c " +
                    "join fetch c.vacancyBase base " +
                    "join fetch base.vacancies " +
                    "where c.id = 1").uniqueResult());
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(builder);
        }
    }
}
