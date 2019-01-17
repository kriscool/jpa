import model.*;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
	@PersistenceContext
	EntityManager entityManager;


	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("model_persistance");
	static EntityManager em = getEntityManager();
	public static void main(String[] args) {
		saveRole();
		saveUsers();
		saveDataPerson();
		saveCar();
		saveOrder();
		saveInvoices();
		getAllUsers();
		getAllCars();
		getUserByName("email2");
		getPersonDataForUserById(2);
		getPersonDataForUserByEmail("email2");
		getInvoicesByOrderName("pierwsza");
	}

	private static void getInvoicesByOrderName(String pierwsza) {
		CriteriaQuery<Invoices> criteria  =  em.getCriteriaBuilder().createQuery(Invoices.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Root<Invoices> root = criteria.from(Invoices.class);
		Join<Invoices, Object> join = root.join("order");
		criteria = criteria.select(root)
				.where(cb.and(
						cb.equal(join.get("description"), pierwsza)));

		Invoices invoices = em.createQuery(criteria).getSingleResult();
	}

	private static void getAllUsers() {
		CriteriaQuery<User> criteria = em.getCriteriaBuilder().createQuery(User.class);
		criteria.select(criteria.from(User.class));
		List<User> users = em.createQuery(criteria).getResultList();
		System.out.println(1);
	}

	private static void getPersonDataForUserByEmail(String email2) {
		CriteriaQuery<PersonData> criteria  =  em.getCriteriaBuilder().createQuery(PersonData.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Root<PersonData> root = criteria.from(PersonData.class);
		Join<PersonData, Object> join = root.join("user");
		criteria = criteria.select(root)
				.where(cb.and(
						cb.equal(join.get("email"), email2)));

		PersonData u = em.createQuery(criteria).getSingleResult();
	}

	private static void getPersonDataForUserById(Integer id) {
		CriteriaQuery<PersonData> criteria  =  em.getCriteriaBuilder().createQuery(PersonData.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Root<PersonData> root = criteria.from(PersonData.class);
		Join<PersonData, Object> join = root.join("user");
		criteria = criteria.select(root)
				.where(cb.and(
						cb.equal(root.get("user"), id)));

		PersonData u = em.createQuery(criteria).getSingleResult();
	}

	private static User getUserByName(String email0) {
		CriteriaQuery<User> criteria  =  em.getCriteriaBuilder().createQuery(User.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		Root<User> root = criteria.from(User.class);
		criteria = criteria.select(root)
				.where(cb.equal(root.get("email"), email0));
		User u = em.createQuery(criteria).getSingleResult();
		return em.createQuery(criteria).getSingleResult();
	}

	private static List<Car> getAllCars() {
		CriteriaQuery<Car> criteria = em.getCriteriaBuilder().createQuery(Car.class);
		criteria.select(criteria.from(Car.class));
		List<Car> cars = em.createQuery(criteria).getResultList();
		return cars;
	}

	private static void saveInvoices() {
	for(int i=299;i<400;i++){
		em.getTransaction().begin();
		Invoices invoices = new Invoices();
		invoices.setDescription("Wynajem samochodu");
		invoices.setOrder(em.find(Order.class,i));
		em.persist(invoices);
		em.getTransaction().commit();
	}
	}

	private static void saveOrder() {
		em.getTransaction().begin();
		Order order1 = new Order();
		order1.setDate_order(new Date(1346524199000l));
		order1.setDescription("pierwsza");
		Set<Car> carSet1 = new HashSet<Car>();
		carSet1.add(em.find(Car.class,199));
		order1.setCars(carSet1);
		em.persist(order1);
		em.getTransaction().commit();
		for(int i=200;i<299;i++){
			em.getTransaction().begin();
			Order order = new Order();
			order.setDate_order(new Date(1346524199000l));
			order.setDescription("wynajem samochodu");
			Set<Car> carSet = new HashSet<Car>();
			carSet.add(em.find(Car.class,i));
			order.setCars(carSet);
			em.persist(order);
			em.getTransaction().commit();
		}
	}

	private static void saveCar() {
		for(int i=0;i<100;i++){
			em.getTransaction().begin();
			Car car = new Car();
			car.setMarka("Szkoda");
			car.setTablica(Integer.toString(i));
			em.persist(car);
			em.getTransaction().commit();
		}
	}

	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	static Set<UserRole> role = new HashSet<UserRole>();
	public static void saveDataPerson(){
		for(int i=2;i<99;i++){

			em.getTransaction().begin();
			PersonData person_data = new PersonData();
			person_data.setToken("aeaseasea");
			User u = em.find(User.class,i);
			System.out.println(u+"uzytkownik");
			person_data.setUser(u);
			em.persist(person_data);
			em.getTransaction().commit();
		}
	}
	public static void saveRole(){

		UserRole ur = new UserRole();
		ur.setRole("role");
		em.getTransaction().begin();
		em.persist(ur);

		em.getTransaction().commit();

	}
	public static void saveUsers() {

		em.getTransaction().begin();
		UserRole roles = em.getReference(UserRole.class,1);
		System.out.println(roles);
		em.getTransaction().commit();
		role.clear();
		role.add(roles);
		for(int i=0;i<100;i++){

			em.getTransaction().begin();
			User u = new User();
			u.setEmail("email"+i);
			u.setFirstname("first name"+i);

			u.setActive(Boolean.FALSE);
			u.setRoles(role);
			em.persist(u);
			em.getTransaction().commit();
		}
	}

}
