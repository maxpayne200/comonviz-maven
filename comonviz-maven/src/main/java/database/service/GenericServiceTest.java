package database.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import au.uq.dke.comonviz.model.OntologyModelListener;

import com.googlecode.genericdao.dao.hibernate.GenericDAO;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.SearchResult;

//@Service
//@Transactional
public class GenericServiceTest<M, D extends GenericDAO<M, Long>> {

	D dao;

	List<OntologyModelListener> listeners = new ArrayList<OntologyModelListener>();
	protected void fireAxiomAddedEvent(M axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomAdded(axiom);
		}
	}

	protected void fireAxiomUpdatedEvent(M axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomUpdated(axiom);
		}
	}

	protected void fireAxiomRemovedEvent(M axiom){
		for(OntologyModelListener listener: listeners){
			listener.databaseAxiomRemoved(axiom);
		}
	}

	public void deleteAll() {
		List<M> axiomList = dao.findAll();
		for(M axiom: axiomList){
			dao.remove(axiom);
		}
	}
	
	public void delete(M axiom){
		dao.remove(axiom);
		fireAxiomRemovedEvent(axiom);
	}

	@Autowired
	public void setDao(D dao) {
		this.dao = dao;
	}

	public void save(M ontologyAxiom) {
		boolean isCreate = dao.save(ontologyAxiom);
		if (isCreate) {
			fireAxiomAddedEvent(ontologyAxiom);
		} else {
			fireAxiomUpdatedEvent(ontologyAxiom);
		}
	}

	public List<M> findAll() {
		return dao.findAll();
	}

	public M findByName(String name) {
		if (name == null)
			return null;
		return dao.searchUnique(new Search().addFilterEqual("name", name));
	}

	public void flush() {
		dao.flush();
	}

	public List<M> search(ISearch search) {
		return dao.search(search);
	}

	public M findById(Long id) {
		return dao.find(id);
	}

	public void delete(Long id) {
		dao.removeById(id);
	}

	public SearchResult<M> searchAndCount(ISearch search) {
		return dao.searchAndCount(search);
	}
	public List<OntologyModelListener> getListeners() {
		return listeners;
	}

}

