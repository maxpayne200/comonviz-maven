package database.model.data.cultureManagement;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import database.model.data.BasicRecord;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PenaltyAvoidance extends ComplianceDriver {

	private String penalty;

	public PenaltyAvoidance(String name) {
		super(name);
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}
public PenaltyAvoidance() {
}
}
