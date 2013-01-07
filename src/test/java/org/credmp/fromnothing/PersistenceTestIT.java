package org.credmp.fromnothing;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.credmp.dbunit.CleanInsertTestExecutionListener;
import org.credmp.dbunit.DataSetLocation;
import org.credmp.fromnothing.model.Actor;
import org.credmp.fromnothing.model.Movie;
import org.credmp.fromnothing.model.MovieCredits;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;


/**
 * Integration Test to verify the correct workings of the persistence
 * layer. Most notable the Hibernate DDLs.
 *
 * @version 1.0
 * @author Arjen Wiersma <core@credmp.org>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@DataSetLocation("die-hard-set.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, CleanInsertTestExecutionListener.class, TransactionalTestExecutionListener.class})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
@Transactional
public class PersistenceTestIT {
    private final static Logger logger = LoggerFactory.getLogger(PersistenceTestIT.class); 

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * Resetting the sequences is necessary for PostgreSQL together with DBUnit.
     */
    @Before
    public void resetSequences() {
        logger.info("Resetting sequences");
        try {
            // Try to reset the PostgreSQL sequences, this will fail on mysql
            getSession().createSQLQuery("SELECT setval('actor_id_seq', (SELECT MAX(id) FROM actor))").list();
            getSession().createSQLQuery("SELECT setval('movie_id_seq', (SELECT MAX(id) FROM movie))").list();
            getSession().createSQLQuery("SELECT setval('moviecredits_id_seq', (SELECT MAX(id) FROM moviecredits))").list();
        } catch (Exception ex) {}
    }

    @Test
    public void basicCRUD() {
        try {            
            Actor actor = new Actor();
            actor.setFirstName("Arnold");
            actor.setLastName("Schwarzenegger");
            actor.setNickName("Arnie");
            Date date1 = new SimpleDateFormat("MM/dd/yy").parse("06/30/47");
            actor.setBirthDate(date1);

            getSession().save(actor);

            Query query = getSession().createQuery("from Actor where nickname=:nickname");
            query.setParameter("nickname", "Arnie");

            Actor arnie = (Actor) query.uniqueResult();
            assertEquals(2, arnie.getId());
            arnie.setNickName("Big Guy");
            getSession().save(arnie);

            query.setParameter("nickname", "Big Guy");
            Actor arnie2 = (Actor) query.uniqueResult();
            assertEquals("Version should match", 1, arnie2.getVersion());

            getSession().delete(actor);
            Actor arnie3 = (Actor) query.uniqueResult();
            assertEquals("Arnie should no longer exist!", null, arnie3);

        } catch (Exception ex) {
            logger.error("Excption thrown: ", ex.getMessage());
            assertTrue(ex.getMessage(), false);
            ex.printStackTrace();
        }
    }

    @Test
    public void theMovieRelationship() {
        List list = getSession().createQuery("from MovieCredits").list();
        assertTrue(list.size() > 0);

        for (Object o : list) {
            MovieCredits mc = (MovieCredits) o;
            Actor a = mc.getActor();
            assertNotNull("Actor should be set", a);
            Movie m = mc.getMovie();
            assertNotNull("Movie should be set", m);
        }
    }

    @Test
    public void basicDBUnitDataSet() {
        List list = getSession().createQuery("from Actor where firstname=:firstname").setParameter("firstname", "Bruce").list();
        assertTrue(list.size() > 0);
        assertEquals("Bruno",((Actor)list.get(0)).getNickName()); 

    }
}
