package hellojpa;

import org.hibernate.Hibernate;
import sun.jvm.hotspot.memory.ParNewGeneration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Address address = new Address("city", "street", "1000");

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setHomeAddress(address);
            member1.setWorkPeriod(new Period());
            em.persist(member1);

            Address copyAddress = new Address(address.getCity(), address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setHomeAddress(copyAddress);
            member2.setWorkPeriod(new Period());
            em.persist(member2);

            member1.getHomeAddress().setCity("newCity");

            tx.commit();

        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }
        finally{
            em.close();
        }
        emf.close();
    }

}
