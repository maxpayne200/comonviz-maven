package database.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import database.model.data.bussinesProcessManagement.ProcessActivity;
import database.model.data.bussinesProcessManagement.ProcessObjective;

public class TestOneToManyMappingUsingGenericService {

	private static ActivityService activityService;
	private static ObjectiveService objectiveService;

	public static void main(String args[]) {

		ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		activityService = (ActivityService) ctx.getBean("activityService");
		objectiveService = (ObjectiveService) ctx.getBean("objectiveService");

		ProcessActivity activity1 = new ProcessActivity();
		activity1.setName("activity 1");
		activity1.setObjectives(new HashSet<ProcessObjective>());
		activityService.save(activity1);

		ProcessObjective objective1 = new ProcessObjective();
		objective1.setName("objective 1");
		objective1.setActivity(activity1);
		activity1.getObjectives().add(objective1);


		objectiveService.save(objective1);

		for (ProcessActivity activity : activityService.findAll()) {
			activity = activityService.findByName(activity.getName(),
					activity.getClass());
			int a = 1;
		}

		List<ProcessObjective> objectives = objectiveService.findAll();

		return;

	}
}