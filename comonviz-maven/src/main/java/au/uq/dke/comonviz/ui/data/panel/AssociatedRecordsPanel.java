package au.uq.dke.comonviz.ui.data.panel;

import java.awt.Dimension;
import java.util.Set;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.metawidget.inspector.annotation.UiAction;

import au.uq.dke.comonviz.ui.data.dialog.ForeignRecordsChooseDialog;
import au.uq.dke.comonviz.ui.data.tableModel.AssociatedRecordsTableModel;
import au.uq.dke.comonviz.ui.data.tableModel.BasicTableModel;
import au.uq.dke.comonviz.utils.DatabaseUtils;
import database.model.data.BasicRecord;

/**
 * this panel is in the record dialog this shows a list of associated records in
 * a table buttons are add and delete, but operate differently from
 * {@PKRecordBeanDialog}
 * 
 * @author uqwwan10
 * 
 */
public class AssociatedRecordsPanel extends ButtonedTablePanel {
	private BasicRecord primaryRecord;
	private Set<?> set;
	private Class<?> setElementType;

	public AssociatedRecordsPanel(BasicRecord primaryRecord, Set<?> set, Class<?> setElementType) {
		super();//no effect
		this.primaryRecord = primaryRecord;
		this.set = set;
		this.setElementType = setElementType;
		
		
		//init
		TableModel tableModel = new AssociatedRecordsTableModel(this.primaryRecord,
				set, setElementType);
		JTable table = new JTable(tableModel);
		table.setPreferredSize(new Dimension(200, 50));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		super.init(table);
		
	}

	/**
	 * select a new associated record(s) to add to the current primery record
	 * this will open a {@AssociationChoosePanel} of
	 * the associated type and let user choose one or multiple associations
	 */
	@UiAction
	public void addAssociation() {

		// we can get the primary record, the clazz, and all associations
		// we want to open a Primary style dialog to display all the foreign
		// records
		// we shall pass the primary record to the chooseDialog so it can add
		// the selected foreign records to it

		AssociatedRecordsTableModel associatedTableModel = (AssociatedRecordsTableModel) this
				.getTable().getModel();
		BasicRecord primaryRecord = associatedTableModel.getPrimaryRecord();
		Class foreignClass = associatedTableModel.getmClass();

		// class is the foreign record class
		
		new ForeignRecordsChooseDialog(primaryRecord,
				foreignClass, this);

	}

	@UiAction
	// @UiComesAfter("save")
	public void deleteAssication() {
		BasicRecord record = (BasicRecord) ((BasicTableModel<?>) this
				.getTable().getModel()).getValueAt(this.getTable()
				.getSelectedRow());

		//unlink the relation
		this.set.remove(record);
		//update database
		DatabaseUtils.getSession().save(primaryRecord);
		DatabaseUtils.getSession().save(record);
		//update the table
		((BasicTableModel<BasicRecord>) this.getTable().getModel())
				.delete(record);
		
		return;

	}

}
