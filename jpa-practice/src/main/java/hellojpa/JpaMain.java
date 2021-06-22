package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try{
            Movie movie = new Movie();
            movie.setDirector("a");
            movie.setActor("bb");
            movie.setName("바람과함께사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Item findItem = em.find(Item.class, movie.getId());
            System.out.println("findItem = " + findItem);

//            Movie findMovie = em.find(Movie.class, movie.getId());
//            System.out.println("findMovie = " + findMovie);

            tx.commit();

        } catch (Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }
        emf.close();
    }
}
