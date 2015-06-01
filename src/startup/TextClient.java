package startup;
/*package startup;

import java.util.Calendar;

import domain.Category;
import domain.DCO;
import domain.Indicator;
import domain.Observation;
import domain.Unit;
import domain.UnitCatalog;
import domain.User;
import domain.facade.IAddObservationsHandler;
import domain.facade.ICreateCategoryHandler;
import domain.facade.ICreateIndicatorHandler;
import domain.facade.ICreateUnitHandler;
import domain.facade.ILoginHandler;
import domain.facade.INewUserHandler;

public class TextClient {
	static DCO initObject;
	static User authentic;
	//
	// @param args
	//
	public static void main(String[] args) {
		
		startup();
		createUsers();
		loginUser();
		createCategories();
		createUnits();
		convertUnits();
		createIndicators();
		addObservations();

	}
	
	/////////////////////////////////////////
	// STARTUP : CRIACAO DO OBJECTO INICIAL
	////////////////////////////////////////
	private static void startup() {
		initObject = new DCO();
	}
		
		
	//////////////////////////////
	// CRIACAO DE NOVOS USERS
	/////////////////////////////
	private static void createUsers() {
    	System.out.println("Criacao de 3 users");		
		INewUserHandler newUser = initObject.getNewUserHandler();
		boolean sucesso = newUser.registerUser("Orientado", "Maria", "password");
		if (!sucesso)
			System.out.println("Nao criou a Maria!");
		newUser.registerUser("Orientado", "Pedro", "1234");
		newUser.registerUser("Supervisor", "Joao", "4321");		    	
	}
	
	/////////////////////////////
	// LOGIN DE UM USER
	/////////////////////////////
	private static void loginUser() {
    	System.out.println();	
    	System.out.println("===============================");		
		ILoginHandler login = initObject.getLoginHandler();
		boolean sucesso = login.login("Maria", "1234");
		if (!sucesso)
			System.out.println("Login da Maria falhou");
		else 
			 authentic = initObject.getAuthenticatedUser();	    	
			sucesso = login.login("Pedro", "1234");
		if (!sucesso)
			System.out.println("Login falhou");
		else
			authentic = initObject.getAuthenticatedUser();	    	
    	System.out.println("O current user eh: " + authentic.getName() + 
    			" " + authentic.getPwd());
	}
	
		
	/////////////////////////////
	// CRIACAO DE CATEGORIAS
	/////////////////////////////
	private static void createCategories() {
    	System.out.println();	
    	System.out.println("===============================");		
    	System.out.println("Criacao de categorias:");
		ICreateCategoryHandler createCateg = initObject.getCreateCategoryHandler();
		createCateg.newCategory();
		Iterable<String> categs = createCateg.getCategoriesAuthenticatedUser();
    	System.out.println("Categorias do " + authentic.getName());
        for (String c : categs)
        	System.out.println(c);
    	System.out.println("Criar categorias Saude e Desporto");
        createCateg.createCategory("Saude");
        createCateg.createCategory("Desporto");   
	}

    /////////////////////////////
	// CRIACAO DE UNIDADES
	/////////////////////////////
	private static void createUnits() {
    	System.out.println();	
    	System.out.println("===============================");		
    	System.out.println("Criacao de unidades:");
    	ICreateUnitHandler createUnit = initObject.getCreateUnitHandler();

    	// 1a operacao do sistema
    	createUnit.newUnit();
    	System.out.println("Unidades existentes");
        for (String c : createUnit.getAllUnits())
        	System.out.println(c);   	
    	boolean b;
    	// 2a operacao do sistema
		b = createUnit.registerUnit("Kilometer", "Km");
		if (b)
	    	System.out.println("Criou Km");			
    	// 3a operacao do sistema
		createUnit.endCreate();

    	// 1a operacao do sistema
    	createUnit.newUnit();
    	System.out.println("Unidades existentes");
        for (String c : createUnit.getAllUnits())
        	System.out.println(c);   	
    	// 2a operacao do sistema
		b = createUnit.registerUnit("Mile", "Mile");
		if (b)
	    	System.out.println("Criou Mile");			
    	// 3a operacao do sistema
		b = createUnit.addCompatibleUnit("Km");	
		if (b)
	    	System.out.println("Adicionou compativel Km");			
		else
	    	System.out.println("Erro a criar compativel ");
    	// Ultima operacao do sistema
		createUnit.endCreate();

    	// 1a operacao do sistema
    	createUnit.newUnit();
    	System.out.println("Unidades existentes");
        for (String c : createUnit.getAllUnits())
        	System.out.println(c);   	
    	// 2a operacao do sistema
		b = createUnit.registerUnit("Meter", "m");
		if (b)
	    	System.out.println("Criou m");			
    	// 3a operacao do sistema
		b = createUnit.addCompatibleUnit("Km");	
		if (b)
	    	System.out.println("Adicionou compativel Km");			
		else
	    	System.out.println("Erro a criar compativel Km");
    	// 3a operacao do sistema
		b = createUnit.addCompatibleUnit("Mile");
		if (b)
	    	System.out.println("Adicionou compativel Mile");			
		else
	    	System.out.println("Erro a criar compativel Mile");
    	// Ultima operacao do sistema
		createUnit.endCreate();
		
    	System.out.println("m ");		
    	System.out.println("Unidades compativeis: ");
    	UnitCatalog unitCat = initObject.getUnitCatalog();
    	Unit u = unitCat.getUnit("m");
        for (Unit c : u.getCompatibleUnits())
        	System.out.println(c.getNick());
    	System.out.println("Conversores: ");
        for (String c : u.getConverters())
        	System.out.println(c);
	}
        
	/////////////////////////////
    // CONVERSAO ENTRE UNIDADES
	/////////////////////////////
	private static void convertUnits() {
    	System.out.println();	
    	System.out.println("===============================");	
    	System.out.println("Exemplo de conversoes entre unidades");		
    	UnitCatalog unitCat = initObject.getUnitCatalog();
        Unit [] units = new Unit[3];
        units[0] = unitCat.getUnit("Km");
        units[1] = unitCat.getUnit("Mile");
        units[2] = unitCat.getUnit("m");
        System.out.println(3 + " " + units[0].getNick() + " = " + 
        		units[0].convertTo(units[1], 3) + " " + units[1].getNick());
        System.out.println(3 + " " + units[1].getNick() + " = " + 
        		units[1].convertTo(units[0], 3) + " " + units[0].getNick());
        System.out.println(3 + " " + units[0].getNick() + " = " + 
        		units[0].convertTo(units[2], 3) + " " + units[2].getNick());
        System.out.println(3 + " " + units[2].getNick() + " = " + 
        		units[2].convertTo(units[0], 3) + " " + units[0].getNick());
	}

	/////////////////////////////
    // CRIACAO DE INDICADORES
	/////////////////////////////
	private static void createIndicators() {
    	System.out.println();	
    	System.out.println("===============================");		
    	System.out.println("Criacao de indicadores:");
		ICreateIndicatorHandler createIndic = initObject.getCreateIndicatorHandler();
		// chamar 1a operacao do sistema
		createIndic.newIndicator();
		// pedir Handler as categorias do user e mostra-las
		// Fmartins: null pointer exception. Estranho.!
    	System.out.println("Categorias do " + authentic.getName());
        for (String c : createIndic.getCategoriesAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Escolhe Desporto");  
    	// chamar 2a operacao do sistema
    	createIndic.selectCategory("Desporto");
		// pedir Handler os indicadores da categoria e mostra-los
    	System.out.println("Indicadores do Desporto");
        for (String c : createIndic.getIndicatorsAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Criar Corrida, manual");  
    	// chamar 3a operacao do sistema
    	createIndic.supplyNameAndMode("Corrida", "MANUAL");
		// pedir Handler as categorias do user e mostra-las
    	System.out.println("Unidades existentes");
        for (String c : createIndic.getAllUnits())
        	System.out.println(c);
    	System.out.println("Escolhe Km");  
    	// chamar 5a operacao do sistema
    	createIndic.selectUnit("Km");
    	// chamar 6a operacao do sistema
    	createIndic.confirm();
 
    	System.out.println();	
    	System.out.println("===============================");		
    	System.out.println("Criacao de indicadores:");
		createIndic = initObject.getCreateIndicatorHandler();
		// chamar 1a operacao do sistema
		createIndic.newIndicator();
		// pedir Handler as categorias do user e mostra-as
    	System.out.println("Categorias do " + authentic.getName());
        for (String c : createIndic.getCategoriesAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Escolhe Desporto");  
    	// chamar 2a operacao do sistema
    	createIndic.selectCategory("Desporto");
		// pedir Handler os indicadores da categoria e mostra-los
    	System.out.println("Indicadores do Desporto"); 
        for (String c : createIndic.getIndicatorsAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Criar Marcha, automatico");  
    	// chamar 3a operacao do sistema
    	createIndic.supplyNameAndMode("Marcha", "AUTOMATIC");
		// pedir Handler os devices existentes e mostra-os
    	System.out.println("Devices existentes");
        for (String c : createIndic.getDeviceNames())
        	System.out.println(c);
    	System.out.println("Escolhe Pedometer");  
    	// chamar 4a operacao do sistema
    	createIndic.selectDevice("Pedometer");   	
		// pedir Handler as unidades e mostra-las
    	System.out.println("Unidades existentes");
        for (String c : createIndic.getAllUnits())
        	System.out.println(c);
    	System.out.println("Escolhe Mile");  
    	// chamar 5a operacao do sistema
    	createIndic.selectUnit("Mile");
    	// chamar 6a operacao do sistema
    	createIndic.confirm();
	}

	/////////////////////////////////////////
    // RECOLHER OBSERVACOES DE INDICADORES
	////////////////////////////////////////
	private static void addObservations() {
    	System.out.println();	
    	System.out.println("===============================");		
    	System.out.println("Recolher observacoes de indicadores:");
		IAddObservationsHandler addObserv = initObject.getAddObservationsHandler();
		// chamar 1a operacao do sistema
		addObserv.initiateRegister();
		// pedir Handler as categorias do user e mostra-las
    	System.out.println("Categorias do " + authentic.getName());
        for (String c : addObserv.getCategoriesAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Escolhe Desporto");  
    	// chamar 2a operacao do sistema
    	addObserv.selectCategory("Desporto");
		// pedir Handler os indicadores do user e mostra-os
    	System.out.println("Indicadores do " + authentic.getName());
        for (String c : addObserv.getIndicatorsAuthenticatedUser())
        	System.out.println(c);
    	System.out.println("Escolhe Corrida");  
    	// chamar 3a operacao do sistema
    	addObserv.selectIndicator("Corrida");
		// pedir Handler as unidades para o indicador e mostra-as
    	System.out.println("Unidades do indicador Corrida");
        for (String c : addObserv.getAllUnits())
        	System.out.println(c);
    	System.out.println("Escolhe m");  
    	// chamar 4a operacao do sistema
    	addObserv.selectUnit("m");
    	// chamar 5a operacao do sistema para varias observacoes
    	addObserv.newObservation(2015, 05, 01, 1010);
    	addObserv.newObservation(2015, 05, 02, 20200);
    	addObserv.newObservation(2015, 05, 03, 30);
    	addObserv.newObservation(2015, 05, 04, 404);
    	// chamar 6a operacao do sistema
    	int nObserv = addObserv.confirmObservations();
    	System.out.println("Registou " + nObserv + " observacoes");     	
    	// imprime observacoes do current user 
    	System.out.println("Observacoes do " + authentic.getName());
    	Category catDesp = authentic.getCategory("Desporto");
    	Indicator ind = catDesp.getIndicator("Corrida");
        for (Observation o : ind.getObservations())
        	System.out.println(o.getDate().get(Calendar.YEAR) + "  " + 
        			o.getDate().get(Calendar.MONTH) + "  " + 
        			o.getDate().get(Calendar.DAY_OF_MONTH) + "  " + o.getValue() +
        			" " + ind.getUnit().getNick());

    	
    	System.out.println();
	}

}
*/
