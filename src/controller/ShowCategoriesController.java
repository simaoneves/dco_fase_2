package controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;


public class ShowCategoriesController extends AuthenticatedUseCaseController {

	@FXML private FlowPane categoriesPane;
	
	@Override
	public void init() {
		loadCategories();
	}

    @Override
	public void update() {
    	loadCategories();
	}

	private void loadCategories() {
		categoriesPane.getChildren().clear();
    	for (String category : app.getObtainCategoriesHandler().getCategoriesAuthenticatedUser())
    		createCategoryLabel(category, new EventHandler<MouseEvent>() {
    			@Override
    			public void handle(MouseEvent event) {
    				mainView.setCurrentCategory(((Label) event.getSource()).getText());
    				mainView.pushPane("ShowIndicators.fxml");
    			}});
    	createCategoryLabel("New category...", new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mainView.pushPane("CreateCategory.fxml");
			}});
	}

	private void createCategoryLabel(String category, EventHandler<? super MouseEvent> action) {
		Label b = new Label(category);
		b.setPadding(new Insets(30, 15, 30, 15));
		b.getStyleClass().add("category");
		b.setOnMouseClicked(action);
		categoriesPane.getChildren().add(b);
	}
}
