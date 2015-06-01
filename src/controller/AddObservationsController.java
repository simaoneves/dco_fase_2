package controller;

import java.time.LocalDate;

import domain.interfaces.IAddObservationsHandler;
import view.Constants;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AddObservationsController extends AuthenticatedUseCaseController {
	
	@FXML private ComboBox<String> categoryField;
    @FXML private ComboBox<String> indicatorField;
    @FXML private ComboBox<String> unitField;
	@FXML private DatePicker dateField; 
    @FXML private TextField valueField;
    @FXML private TableView<Observation> observationsTable;
    @FXML private TableColumn<Observation, LocalDate> dateColumn;
    @FXML private TableColumn<Observation, Double> valueColumn;

    private ObservableList<Observation> data;
    
	private class Observation {
		private ObjectProperty<LocalDate> date;		
		private DoubleProperty value;

		public Observation(LocalDate date, double value) {
			this.date = new SimpleObjectProperty<LocalDate>(date);
			this.value = new SimpleDoubleProperty(value);
		}

		public ObjectProperty<LocalDate> dateProperty() {
			return date;
		}

		public DoubleProperty valueProperty () {
			return value;
		}
		
		public LocalDate getDate() {
			return date.get();
		}
		
		public double getValue() {
			return value.get();
		}
	}
    
    private static final int INITIATE_REGISTER_SENT = 2;
    private static final int CATEGORY_SENT = 3;
	private static final int INDICATOR_SENT = 4;
	private static final int UNIT_SENT = 5;
	private static final int ADD_OBSERVATIONS_SENT = 6;
	private static final int CONFIRMATION_SENT = 7;
	
	private IAddObservationsHandler aoh;
	private boolean alreadyCancelled;
	private String selectedCategory;
	private String selectedIndicator;
	private String selectedUnit;
	private boolean addObservation;
	
	@FXML
	void categoryChanged(ActionEvent event) {
		if (!executingUseCase()) {
			selectedCategory = categoryField.getValue();		
			executeUseCase(CATEGORY_SENT);
		}
	}

	@FXML
	void indicatorChanged(ActionEvent event) {
		if (!executingUseCase()) {  // avoid preemptive calls
			selectedIndicator = indicatorField.getValue();
			executeUseCase(INDICATOR_SENT);
		}
	}

	@FXML
	void unitChanged(ActionEvent event) {
		if (!executingUseCase()) {  // avoid preemptive calls
			selectedUnit = unitField.getValue();		
			executeUseCase(UNIT_SENT);
		}
	}

    @FXML 
    void addObservation(ActionEvent event) {
    	addObservation = true;
    	executeUseCase(ADD_OBSERVATIONS_SENT);
    }

	@FXML
	void cancel(ActionEvent event) {
		aoh.cancel();
		mainView.popPane();
	}
	
	@FXML
	void create(ActionEvent event) {
		addObservation = false;
		if (executeUseCase(CONFIRMATION_SENT)) {
			mainView.showInfo(Constants.CREATE_INDICATOR_SUCCESS);
			mainView.popAndUpdate();
		}
	}

	@Override
	public void init() {
		// execute the super class init
		super.init();
		
		// get the indicators handler from the application main class
		aoh = app.getAddObservationsHandler();
		
		// set state transitions
		stateTransitions();

		data = FXCollections.observableArrayList();
		dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
		valueColumn.setCellValueFactory(cellData -> cellData.getValue().valueProperty().asObject());
		observationsTable.setItems(data);
		
		// make use of the selected category and indicator, and execute 
		// the first two steps of the use case
		selectedCategory = mainView.getCurrentCategory();
		selectedIndicator = mainView.getCurrentIndicator();
		executeUseCase(INDICATOR_SENT);
	}

	@Override
	boolean executeUseCase(int stateToChangeTo) {
		alreadyCancelled = false;
		return super.executeUseCase(stateToChangeTo);
	}
	
	private void stateTransitions() {
		addState(new StateTransition(BEGIN, INITIATE_REGISTER_SENT) {
			@Override
			public void changeToNextState() {
				aoh.initiateRegister();
				loadCategoryNames();
			}
		});
		
		addState(new StateTransition(INITIATE_REGISTER_SENT, CATEGORY_SENT) {
			@Override
			public void changeToNextState() {
				aoh.selectCategory(selectedCategory);
				if (stateToChangeTo() == CATEGORY_SENT) {
					selectedIndicator = null;
					selectedUnit = null;
					unitField.getItems().clear();		
					unitField.setValue(selectedUnit);					
				}
				loadIndicatorNames();
			}
		});
		
		addState(new StateTransition(CATEGORY_SENT, INDICATOR_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return indicatorField.getValue() != null;
			}

			@Override
			public void changeToNextState() {
				aoh.selectIndicator(selectedIndicator);
				if (stateToChangeTo() == INDICATOR_SENT) 
					selectedUnit = null;					
				loadUnitNames();
			}
			
			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_EMPTY_INDICATOR, 
	    				Constants.ADD_OBSERVATIONS_INVALID_EMPTY_INDICATOR_RECOVERY);
			}
		});
					
		addState(new StateTransition(INDICATOR_SENT, UNIT_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return unitField.getValue() != null;
			}

			@Override
			public void changeToNextState() {
				aoh.selectUnit(selectedUnit);
				// send any observations already loaded
				for (Observation o : data)
					aoh.newObservation(o.getDate().getYear(),
							o.getDate().getMonthValue(),
							o.getDate().getDayOfMonth(),
							o.getValue());
			}
			
			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_EMPTY_UNIT, 
	    				Constants.ADD_OBSERVATIONS_INVALID_EMPTY_UNIT_RECOVERY);
			}
		});

		addState(new StateTransition(UNIT_SENT, ADD_OBSERVATIONS_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return !addObservation && data.size() > 0;
			}

			@Override
			public void changeToNextState() {
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_NO_DATA, 
	    				Constants.ADD_OBSERVATIONS_NO_DATA_RECOVERY);
			}
		});

		addState(new StateTransition(UNIT_SENT, ADD_OBSERVATIONS_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return dateField.getValue() != null && 
						isDouble(valueField.getText());
			}

			@Override
			public void changeToNextState() {
				LocalDate date = dateField.getValue();
				aoh.newObservation(date.getYear(), date.getMonthValue(), 
						date.getDayOfMonth(), Double.parseDouble(valueField.getText()));
				addObservationToTable();
				dateField.setValue(null);
				valueField.setText(null);
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_INVALID_DATA, 
	    				Constants.ADD_OBSERVATIONS_INVALID_DATA_RECOVERY);
			}
		});
		
		addState(new StateTransition(ADD_OBSERVATIONS_SENT, ADD_OBSERVATIONS_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return addObservation && dateField.getValue() != null && 
						isDouble(valueField.getText());
			}

			@Override
			public void changeToNextState() {
				LocalDate date = dateField.getValue();
				aoh.newObservation(date.getYear(), date.getMonthValue(), 
						date.getDayOfMonth(), Double.parseDouble(valueField.getText()));
				addObservationToTable();
				dateField.setValue(null);
				valueField.setText(null);
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.ADD_OBSERVATIONS_INVALID_DATA, 
	    				Constants.ADD_OBSERVATIONS_INVALID_DATA_RECOVERY);
			}
		});

		addState(new StateTransition(ADD_OBSERVATIONS_SENT, CONFIRMATION_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return !addObservation;
			}

			@Override
			public void changeToNextState() {
				aoh.confirmObservations();
			}
		});
		
		addState(new StateTransition(ANY_STATE, BEGIN) {

			@Override
			public boolean canChangeToNextState() {
				if (stateToChangeTo() == ADD_OBSERVATIONS_SENT)
					return false;
				else
					return  !alreadyCancelled && 
						currentState() != BEGIN &&
						stateToChangeTo() <= currentState();
			}

			@Override
			public void changeToNextState() {
				aoh.cancel();
				alreadyCancelled = true;
			}
		});
	}
	
	private boolean isDouble(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private void addObservationToTable() {
		data.add(new Observation(dateField.getValue(), 
				Double.parseDouble(valueField.getText())));
	}

	private void loadCategoryNames() {
		categoryField.getItems().clear();
		for (String category : aoh.getCategoriesAuthenticatedUser())
			categoryField.getItems().add(category);
		categoryField.setValue(selectedCategory);
	}	
	
	private void loadIndicatorNames() {
		indicatorField.getItems().clear();
		for (String indicator : aoh.getIndicatorsAuthenticatedUser())
			indicatorField.getItems().add(indicator);
		indicatorField.setValue(selectedIndicator);
	}	
 
	private void loadUnitNames() {
		unitField.getItems().clear();		
		for (String unitName : aoh.getAllUnits())
			unitField.getItems().add(unitName);
		unitField.setValue(selectedUnit);
	}
}

