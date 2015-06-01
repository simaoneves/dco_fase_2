package view;

import domain.interfaces.IDCO;
import javafx.application.Application;
	
public class StartUp  {

	public StartUp(IDCO app) {
		MainView.setApp(app);
		Application.launch(MainView.class);
	}
	
}
