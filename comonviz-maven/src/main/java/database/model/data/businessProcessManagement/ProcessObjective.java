package database.model.data.businessProcessManagement;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

@Entity
public class ProcessObjective extends BusinessProcess {


	public ProcessObjective(){
		
	}
	public ProcessObjective(String name) {
		super(name);
	}


}
