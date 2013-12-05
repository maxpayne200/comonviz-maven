package database.model.data.program;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class ObligationIdentificationAndAssessment extends Program	{
	public ObligationIdentificationAndAssessment(String name){
		super(name);
	}

}
