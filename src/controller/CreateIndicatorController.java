package controller;

import domain.interfaces.ICreateIndicatorHandler;
import view.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class CreateIndicatorController extends AuthenticatedUseCaseController {

	@FXML private ComboBox<String> categoryField;
	@FXML private TextField nameField;
	@FXML private ComboBox<String> extensionsField;
	@FXML private ComboBox<String> unitsField;
	@FXML private RadioButton manualModeField;
	@FXML private ToggleGroup mode;
	@FXML private RadioButton automaticModeField;

	private static final int CREATE_SENT = 2;	
	private static final int CATEGORY_SENT = 3;
	private static final int NAME_AND_MODE_SENT = 4;
	private static final int EXTENSION_SENT = 5;
	private static final int UNITS_SENT = 6;
	private static final int CONFIRMATION_SENT = 7;
	
	private static final String MANUAL = "MANUAL";
	private static final String AUTOMATIC = "AUTOMATIC";
 	
	private String sentName;
	private ICreateIndicatorHandler cih;
	private boolean alreadyCancelled;
	private String selectedCategory;
	private String selectedUnit;
	private String selectedExtension;

	@FXML 
	void automaticModeChanged(ActionEvent event) {
		extensionsField.setDisable(false);
		executeUseCase(NAME_AND_MODE_SENT);
	}
	
	@FXML
	void manualModeChanged(ActionEvent event) {
		extensionsField.setDisable(true);
		executeUseCase(NAME_AND_MODE_SENT);
	}

	@FXML
	void nameAction(ActionEvent event) {
		if (nameChanged())
			executeUseCase(NAME_AND_MODE_SENT);
	}
	
	@FXML
	void categoryChanged(ActionEvent event) {
		if (!executingUseCase())   // avoid preemptive calls
			executeUseCase(CATEGORY_SENT);
	}

	@FXML
	void extensionsChanged(ActionEvent event) {
		if (!executingUseCase())   // avoid preemptive calls
			executeUseCase(EXTENSION_SENT);
	}

	@FXML
	void unitsChanged(ActionEvent event) {
		if (!executingUseCase())   // avoid preemptive calls
			executeUseCase(UNITS_SENT);
	}

	@FXML
	void cancel(ActionEvent event) {
		cih.cancel();
		mainView.popPane();
	}
	
	@FXML
	void create(ActionEvent event) {
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
		cih = app.getCreateIndicatorHandler();
		
		// set state transitions
		stateTransitions();

		// make use of the selected category and execute the first 
		// two steps of the use case
		executeUseCase(CATEGORY_SENT);
		sentName = "";
	}

	@Override
	boolean executeUseCase(int stateToChangeTo) {
		alreadyCancelled = false;
		return super.executeUseCase(stateToChangeTo);
	}
	
	private void stateTransitions() {
		addState(new StateTransition(BEGIN, CREATE_SENT) {
			@Override
			public void changeToNextState() {
				cih.newIndicator();
				loadCategoryNames();
			}
		});
		
		addState(new StateTransition(CREATE_SENT, CATEGORY_SENT) {
			@Override
			public void changeToNextState() {
				cih.selectCategory(selectedCategory);
			}
		});
		
		addState(new StateTransition(CATEGORY_SENT, NAME_AND_MODE_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return isValidName() && isValidMode();
			}

			@Override
			public void changeToNextState() {
				sentName = nameField.getText();
				succeeded = cih.supplyNameAndMode(sentName, selectedMode());
				if (succeeded) {
					if (selectedMode() == AUTOMATIC)
						loadExtensionNames();
					else
						loadUnitNames();
				} else 
	        		mainView.showError(Constants.CREATE_INDICATOR_NAME_EXISTS, 
	        				Constants.CREATE_INDICATOR_NOME_EXISTS_RECOVERY);
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.CREATE_INDICATOR_INVALID_NAME, 
					Constants.CREATE_INDICATOR_INVALID_NAME_RECOVERY);
			}
		});
					
		addState(new StateTransition(NAME_AND_MODE_SENT, EXTENSION_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return (selectedMode() == MANUAL) || 
					(selectedMode() == AUTOMATIC && extensionsField.getValue() != null);
			}

			@Override
			public void changeToNextState() {
				if (selectedMode() == AUTOMATIC) {
					cih.selectDevice(extensionsField.getValue());
					loadUnitNames();
				}
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.CREATE_INDICATOR_INVALID_EXTENSION, 
						Constants.CREATE_INDICATOR_INVALID_EXTENSION_RECOVERY);
			}
		});

		addState(new StateTransition(EXTENSION_SENT, UNITS_SENT) {
			@Override
			public boolean canChangeToNextState() {
				return unitsField.getValue() != null;
			}

			@Override
			public void changeToNextState() {
				cih.selectUnit(unitsField.getValue());
			}

			@Override
			public void emitError() {
				mainView.showError(Constants.CREATE_INDICATOR_INVALID_UNITS, 
	    				Constants.CREATE_INDICATOR_INVALID_UNITS_RECOVERY);
			}
		});
		
		addState(new StateTransition(UNITS_SENT, CONFIRMATION_SENT) {
			@Override
			public void changeToNextState() {
				cih.confirm();
			}
		});
		
		addState(new StateTransition(ANY_STATE, BEGIN) {

			@Override
			public boolean canChangeToNextState() {
				return  !alreadyCancelled && 
						currentState() != BEGIN &&
						(stateToChangeTo() <= currentState() || 
						currentState() >= NAME_AND_MODE_SENT && nameChanged());
			}

			@Override
			public void changeToNextState() {
				cih.cancel();
				alreadyCancelled = true;
			}
		});
	}

	private String selectedMode() {
		return manualModeField.isSelected() ? MANUAL : 
			automaticModeField.isSelected() ? AUTOMATIC : "";
	}

	private boolean nameChanged() {
		return !sentName.equals(nameField.getText());
	}

	private boolean isValidName() {
		return !(nameField.getText() == null || nameField.getText().trim().length() == 0);
	}

	private boolean isValidMode() {
		return manualModeField.isSelected() || automaticModeField.isSelected();
	}

	private void loadCategoryNames() {
		if (selectedCategory == null)
			selectedCategory = mainView.getCurrentCategory();
		else
			selectedCategory = categoryField.getValue();
		categoryField.getItems().clear();
		for (String category : cih.getCategoriesAuthenticatedUser())
			categoryField.getItems().add(category);
		categoryField.setValue(selectedCategory);
	}	
	
	private void loadExtensionNames() {
		selectedExtension = extensionsField.getValue();
		extensionsField.getItems().clear();		
		for (String extensionName : cih.getDeviceNames())
			extensionsField.getItems().add(extensionName);
		extensionsField.setValue(selectedExtension);
	}
	
	private void loadUnitNames() {
		selectedUnit = unitsField.getValue();
		unitsField.getItems().clear();		
		for (String unitName : cih.getAllUnits())
			unitsField.getItems().add(unitName);
		unitsField.setValue(selectedUnit);
	}
}

