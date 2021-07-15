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
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m left join Team t on m.username = t.name";
            List<Member> resultList = em.createQuery(query, Member.class)
                    .getResultList();


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
