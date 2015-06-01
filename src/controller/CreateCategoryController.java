package controller;

import domain.interfaces.ICreateCategoryHandler;
import view.Constants;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateCategoryController extends AuthenticatedUseCaseController {

	@FXML private TextField nameField;
	
    @FXML 
    private void cancel() {
    	mainView.popPane();
    }
    
    @FXML
    private void create() {
       	if (isInputValid()) {
       		ICreateCategoryHandler cch = app.getCreateCategoryHandler();
       		// do not need the category list.
       		cch.newCategory();
       		boolean result = cch.createCategory(nameField.getText());
       		if (result) {
       			mainView.showInfo(Constants.CREATE_CATEGORY_SUCCESS);
       			mainView.popAndUpdate();  // since state has been changed!
       		} else
       			mainView.showError(Constants.CREATE_CATEGORY_ERROR, Constants.CREATE_CATEGORY_RECOVERY);
       	} else
   			mainView.showError(Constants.CREATE_CATEGORY_INVALID_CATEGORY, Constants.CREATE_CATEGORY_RECOVERY); 		
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        return !(nameField.getText() == null || nameField.getText().trim().length() == 0); 
    }
}
