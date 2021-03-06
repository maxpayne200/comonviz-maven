package database.service;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.Citizen;
import database.model.Town;


/**
 * Hello world!
 *
 */
public class MyApp 
{
	private static CitizenService citizenService;
	private static TownService townService;
	
    public static void main( String[] args )
    {
    	
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        
        citizenService = (CitizenService) ctx.getBean("citizenService");
        townService = (TownService) ctx.getBean("townService");
        
        
       	Town nottingham = new Town("Nottingham", 126);
    	//nottingham.setCitizens(new HashSet<Citizen>());
    	townService.save(nottingham);
    	
    	Citizen butcher = new Citizen("Tom Butcher", "butcher", nottingham);
    	nottingham.getCitizens().add(butcher);
    	
    	Citizen baker = new Citizen("Dick Baker", "baker", nottingham);
    	nottingham.getCitizens().add(baker);
    	
    	Citizen chandler = new Citizen("Harry Chandler", "candlestick maker", nottingham);
    	nottingham.getCitizens().add(chandler);
    	
    	citizenService.save(butcher);
    	citizenService.save(baker);
    	citizenService.save(chandler);
     	
    	for (Town town : townService.findAll()) {
    		town = townService.findByName(town.getName(), town.getClass());
			int a = 1;
    	}
    	
    	
    	for (Citizen citizen : citizenService.findAll()) {
    		System.out.println(" - " + citizen.getName() + " is a " + citizen.getJob());
    	}
    	
    	System.out.println("Done.");
    	
 //   	ctx.close();
    }
    
}
