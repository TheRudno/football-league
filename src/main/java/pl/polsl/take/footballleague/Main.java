package pl.polsl.take.footballleague;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.polsl.take.footballleague.database.Club;
import pl.polsl.take.footballleague.database.Footballer;
import pl.polsl.take.footballleague.database.Goal;
import pl.polsl.take.footballleague.database.Match;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){

        System.out.println("Hello Stowski!");

        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Club.class)
                .addAnnotatedClass(Goal.class)
                .addAnnotatedClass(Match.class)
                .addAnnotatedClass(Footballer.class);
        SessionFactory sf = configuration.buildSessionFactory();


        try (sf; Session session = sf.getCurrentSession()) {
            Transaction tx = session.beginTransaction();
            tx.commit();
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
        }

        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
    }
}
