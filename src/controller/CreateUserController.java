package controller;

import view.Constants;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class CreateUserController extends NotAuthenticatedUseCaseController {

	@FXML private ComboBox<String> userTypeField;
	@FXML private TextField passwordField;
	@FXML private TextField usernameField;
    @FXML private TextField passwordAgainField;

	private String errorMessage;
	
    /**
     * Called when the user clicks the cancel button.
     */
	@FXML
	void cancel(ActionEvent event) {
		mainView.popPane();
    }

    /**
     * Called when the user clicks the create button.
     */
    @FXML
    void create(ActionEvent event) {
    	if (isInputValid())
        	if (app.getNewUserHandler().registerUser(userTypeField.getValue(),
        			usernameField.getText(), passwordField.getText())) {
        		mainView.showInfo(Constants.CREATE_USER_SUCCESS);
        		mainView.popPane();
        	} else 
        		mainView.showError(Constants.CREATE_USER_FAIL_TO_CREATE, 
        				Constants.CREATE_USER_FAIL_TO_CREATE_RECOVERY);     		
        else
        	mainView.showError(errorMessage, Constants.CREATE_USER_ERROR_VALIDATE_RECOVERY);
    }
	        
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        errorMessage = "";

        if (usernameField.getText() == null || usernameField.getText().length() == 0) 
            errorMessage += Constants.CREATE_USER_INVALID_USERNAME + "\n"; 
        if (passwordField.getText() == null || passwordField.getText().length() == 0) 
            errorMessage += Constants.CREATE_USER_INVALID_PASSWORD + "\n"; 
        if (passwordAgainField.getText() == null || passwordAgainField.getText().length() == 0) 
            errorMessage += Constants.CREATE_USER_INVALID_PASSWORD_AGAIN + "\n"; 
        if (passwordField.getText() != null && passwordAgainField.getText() != null &&
        		!passwordField.getText().equals(passwordAgainField.getText())) 
            errorMessage += Constants.CREATE_USER_PASSWORD_MISMATCH + "\n"; 

        return errorMessage.length() == 0;
    }
    
	@Override
	public void init() {
		// execute the super class init
		super.init();

		userTypeField.getItems().clear();
		userTypeField.getItems().addAll(Constants.USER_TYPE_SUPERVISED,
				Constants.USER_TYPE_SUPERVISOR);
		userTypeField.setValue(Constants.USER_TYPE_SUPERVISED);
	}
}
