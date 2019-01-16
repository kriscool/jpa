import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

public class App {
	@PersistenceContext
	EntityManager entityManager;


	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("model_persistance");
	public static void main(String[] args) {
		saveMovie();
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}


	public static void saveMovie() {
		EntityManager em = getEntityManager();

		em.getTransaction().begin();/*
		Student student = new Student();
		student.setId("1");
		student.setStudentName("Pandit");
		student.setAddress("Vijayawada");

		em.persist(student);*/

		em.getTransaction().commit();
	}
}
