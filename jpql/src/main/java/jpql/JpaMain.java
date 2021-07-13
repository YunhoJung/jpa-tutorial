package jpql;

import org.hibernate.Hibernate;
import sun.jvm.hotspot.memory.ParNewGeneration;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            TypedQuery<Member> query1 =  em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = query1.getResultList(); // query1.getSingleResult()



            TypedQuery<String> query2 =  em.createQuery("select m.username from Member m", String.class);


//            Query query3 =  em.createQuery("select m.usernamem, m.age from Member m");

            TypedQuery<Member> query4 =  em.createQuery("select m from Member m where m.username = :username", Member.class);
            query4.setParameter("username", "member1");
            Member singleResult = query4.getSingleResult();
            System.out.println("singleResult = " + singleResult);

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
