package controller;

import view.MainView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class RootLayoutController extends NotAuthenticatedUseCaseController {

	@FXML void createUser(ActionEvent event) {
		mainView.pushPane(MainView.CREATE_USER);		
	}

	@FXML void createUnit(ActionEvent event) {
		mainView.pushPane(MainView.CREATE_UNIT);		
	}
}

