package database.model.data.solution;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class CompetencyAndTrainingTool extends Tool	{
	public CompetencyAndTrainingTool(String name){
		super(name);
	}

}