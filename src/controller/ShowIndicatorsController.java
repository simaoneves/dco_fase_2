package controller;

import domain.interfaces.IObtainIndicatorsHandler;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;


public class ShowIndicatorsController extends AuthenticatedUseCaseController {

	@FXML 
	private FlowPane indicatorsPane;
	
	@Override
	public void init() {
		loadIndicators();
    }

    @Override
	public void update() {
    	loadIndicators();
	}

	private void loadIndicators() {
		indicatorsPane.getChildren().clear();
		IObtainIndicatorsHandler oih = app.getObtainIndicatorsHandler();
		oih.selectCategory(mainView.getCurrentCategory());
    	for (String indicator : oih.getIndicatorsAuthenticatedUser())
    		createIndicatorLabel(indicator, new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent event) {
    				mainView.setCurrentIndicator(((Label) event.getSource()).getText());
    				mainView.pushPane("AddObservations.fxml");
    			}});
    	createIndicatorLabel("New indicator...", new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainView.pushPane("CreateIndicator.fxml");
			}});
	}	
	
	private void createIndicatorLabel(String indicator, EventHandler<? super MouseEvent> action) {
		Label b = new Label(indicator);
		b.setPadding(new Insets(30, 15, 30, 15));
		b.getStyleClass().add("indicator");
		b.setOnMouseClicked(action);
		indicatorsPane.getChildren().add(b);
	}
	
	@FXML
	public void swipeLeft() {
		mainView.popPane();
	}
}
