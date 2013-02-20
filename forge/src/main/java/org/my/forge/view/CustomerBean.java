package org.my.forge.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.my.forge.model.Customer;

/**
 * Backing bean for Customer entities.
 * <p>
 * This class provides CRUD functionality for all Customer entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CustomerBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Customer entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Customer customer;

	public Customer getCustomer() {
		return this.customer;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		return "create?faces-redirect=true";
	}
	
	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.customer = this.search;
		} else {
			this.customer = this.entityManager.find(Customer.class, getId());
		}
	}

	/*
	 * Support updating and deleting Customer entities
	 */

	public String update() {
		this.conversation.end();
		
		try {
			if (this.id == null) {
				this.entityManager.persist(this.customer);
				return "search?faces-redirect=true";			
			} else {
				this.entityManager.merge(this.customer);
				return "view?faces-redirect=true&id=" + this.customer.getId();
			}
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(Customer.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch( Exception e ) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage( e.getMessage() ));
			return null;
		}
	}

	/*
	 * Support searching Customer entities with pagination
	 */

	private int page;
	private long count;
	private List<Customer> pageItems;
	
	private Customer search = new Customer();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Customer getSearch() {
		return this.search;
	}

	public void setSearch(Customer search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Customer> root = countCriteria.from(Customer.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Customer> criteria = builder.createQuery(Customer.class);
		root = criteria.from(Customer.class);
		TypedQuery<Customer> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Customer> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String firstName = this.search.getFirstName();
		if (firstName != null && !"".equals(firstName)) {
			predicatesList.add(builder.like(root.<String>get("firstName"), '%' + firstName + '%'));
		}
		String lastName = this.search.getLastName();
		if (lastName != null && !"".equals(lastName)) {
			predicatesList.add(builder.like(root.<String>get("lastName"), '%' + lastName + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Customer> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Customer entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Customer> getAll() {

		CriteriaQuery<Customer> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Customer.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Customer.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return CustomerBean.this.entityManager.find(Customer.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Customer) value).getId());
			}
		};
	}
	
	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */
	 
	private Customer add = new Customer();

	public Customer getAdd() {
		return this.add;
	}

	public Customer getAdded() {
		Customer added = this.add;
		this.add = new Customer();
		return added;
	}
}