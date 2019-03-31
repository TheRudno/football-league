package pl.polsl.take.footballleague.test;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class ItemEJB {
	
	@PersistenceContext(name="football")
	EntityManager manager;

	public void create(Item item) {
		manager.persist(item);
	}

	public Item find(int idc) {
		return manager.find(Item.class, idc);
	}

	public List<Item> get() {
		Query q = manager.createQuery("select i from Item i");
		@SuppressWarnings("unchecked")
		List<Item> list = q.getResultList();
		return list;
	}

}