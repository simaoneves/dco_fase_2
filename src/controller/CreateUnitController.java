package controller;

import domain.interfaces.ICreateUnitHandler;
import view.Constants;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CreateUnitController extends AuthenticatedUseCaseController {

    @FXML private TextField descriptionField;
    @FXML private TextField unitAbbrevField;
    @FXML private TableView<CompatibleUnit> compatibleUnitsTable;
    @FXML private TableColumn<CompatibleUnit, String> abbrevUnitColumn;
    @FXML private ComboBox<String> abbrevInputField;

    private ObservableList<CompatibleUnit> data;

	private class CompatibleUnit {
		private StringProperty abbrevUnit;		

		public CompatibleUnit(String abbrevUnit) {
			this.abbrevUnit = new SimpleStringProperty(abbrevUnit);
		}

		public StringProperty abbrevUnitProperty() {
			return abbrevUnit;
		}

		public String getAbbrevUnit() {
			return abbrevUnit.get();
		}
	}

    private static final int NEW_UNIT_SENT = 2;
    private static final int REGISTER_UNIT_SENT = 3;
	private static final int ADD_COMPATIBLE_UNIT_SENT = 4;
	private static final int END_CREATE_SENT = 5;

	private ICreateUnitHandler cuh;
	
	private boolean alreadyCancelled;
	private boolean addUnit;
	private String sentName;
	private String sentNick;
	private static boolean createUnitUnderway;
    
    @FXML
    void addUnit(ActionEvent event) {
    	addUnit = true;
    	executeUseCase(ADD_COMPATIBLE_UNIT_SENT);
    }

    @FXML
    void cancel(ActionEvent event) {
		cuh.cancel();
		createUnitUnderway = false;
		mainView.popPane();
    }

    @FXML
    void confirm(ActionEvent event) {
    	addUnit = false;
		if (executeUseCase(END_CREATE_SENT)) {
			mainView.showInfo(Constants.CREATE_UNIT_SUCCESS);
			mainView.popAndUpdate();
		}
    }

    @FXML
    void descriptionChanged(ActionEvent event) {
		if (textChanged(sentName, descriptionField))
			executeUseCase(REGISTER_UNIT_SENT);
    }

    @FXML
    void unitAbbrevChanged(ActionEvent event) {
		if (textChanged(sentNick, unitAbbrevField))
			executeUseCase(REGISTER_UNIT_SENT);
    }
	
	@Override
	public boolean preConditionVerified() {
		return super.preConditionVerified() && !createUnitUnderway;
	}
	
	@Override
	public void preConditionError() {
		if (!super.preConditionVerified())
			super.preConditionError();
		else
			mainView.showError(Constants.CREATE_UNIT_UNDERWAY_ERROR, Constants.CREATE_UNIT_UNDERWAY_RECOVERY);		
	}


	@Override
	public void init() {
		// execute the super class init
		super.init();
		
		// get the indicators handler from the application main class
		cuh = app.getCreateUnitHandler();
		
		// set state transitions
		stateTransitions();

		data = FXCollections.observableArrayList();
		abbrevUnitColumn.setCellValueFactory(cellData -> cellData.getValue().abbrevUnitProperty());
		compatibleUnitsTable.setItems(data);
		
		executeUseCase(NEW_UNIT_SENT);
		sentName = "";
		sentNick = "";
	}

	@Override
	boolean executeUseCase(int stateToChangeTo) {
		alreadyCancelled = false;
		return super.executeUseCase(stateToChangeTo);
	}
	
	private void stateTransitions() {
		addState(new StateTransition(BEGIN, NEW_UNIT_SENT) {
			@Override
			public void changeToNextState() {
				cuh.newUnit();
				createUnitUnderway = true;
			}
		});
		
		addState(new StateTransition(NEW_UNIT_SENT, REGISTER_UNIT_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return isValid(descriptionField) && isValid(unitAbbrevField);
			}

			@Override
			public void changeToNextState() {
				sentName = descriptionField.getText();
				sentNick = unitAbbrevField.getText();
				cuh.registerUnit(sentName, sentNick);
				loadUnitNames();
			}
			
			@Override
			public void emitError() {
				mainView.showError(Constants.CREATE_UNIT_INVALID_DESCRIPTION_NICK, 
	    				Constants.CREATE_INDICATOR_INVALID_DESCRIPTION_NICK_RECOVERY);
			}

		});
		
		addState(new StateTransition(REGISTER_UNIT_SENT, ADD_COMPATIBLE_UNIT_SENT) {
			@Override
			public void changeToNextState() {
				// send any compatible units already loaded
				for (CompatibleUnit c : data)
					cuh.addCompatibleUnit(c.getAbbrevUnit());
				if (addUnit && abbrevInputField.getValue() != null) {
					cuh.addCompatibleUnit(abbrevInputField.getValue());
					addCompatibleUnitToTable();
					abbrevInputField.setValue(null);
				}		
			}
		});
		
		addState(new StateTransition(ADD_COMPATIBLE_UNIT_SENT, ADD_COMPATIBLE_UNIT_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return addUnit && abbrevInputField.getValue() != null;
			}

			@Override
			public void changeToNextState() {
				cuh.addCompatibleUnit(abbrevInputField.getValue());
				addCompatibleUnitToTable();
				abbrevInputField.setValue(null);
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_INVALID_DATA, 
	    				Constants.ADD_OBSERVATIONS_INVALID_DATA_RECOVERY);
			}
		});

		addState(new StateTransition(ADD_COMPATIBLE_UNIT_SENT, END_CREATE_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return !addUnit;
			}

			@Override
			public void changeToNextState() {
				cuh.endCreate();
				createUnitUnderway = false;
			}
		});
		
		addState(new StateTransition(ANY_STATE, BEGIN) {

			@Override
			public boolean canChangeToNextState() {
				return !alreadyCancelled && currentState() >= ADD_COMPATIBLE_UNIT_SENT &&
					(textChanged(sentNick, unitAbbrevField) || 
					textChanged(sentName, descriptionField));
			}

			@Override
			public void changeToNextState() {
				cuh.cancel();
				alreadyCancelled = true;
			}
		});
	}
	
	private boolean isValid(TextField text) {
		return !(text.getText() == null || text.getText().trim().length() == 0);
	}
	
	private boolean textChanged(String sent, TextField actual) {
		return !sent.equals(actual.getText());
	}

	private void addCompatibleUnitToTable() {
		data.add(new CompatibleUnit(abbrevInputField.getValue()));
	}

	private void loadUnitNames() {
		abbrevInputField.getItems().clear();		
		for (String unitName : cuh.getAllUnits())
			abbrevInputField.getItems().add(unitName);
	}
}

