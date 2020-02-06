package root.demo.controller;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import root.demo.model.DTO.ActivateRecenzentDTO;
import root.demo.model.FormFieldsDto;
import root.demo.model.FormSubmissionDto;
import root.demo.model.TaskDto;
import root.demo.services.CamundaService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/welcome")
public class DummyController {
	@Autowired
	IdentityService identityService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	TaskService taskService;
	
	@Autowired
	FormService formService;

	@Autowired
	CamundaService camundaService;
	
	@GetMapping(path = "/get", produces = "application/json")
    public @ResponseBody FormFieldsDto get() {
		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_101ojh2");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
		
		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();

		
        return new FormFieldsDto(task.getId(), pi.getId(), properties);
    }

	@GetMapping(path = "/getUredniciRecenzenti/{instance}", produces = "application/json")
	public @ResponseBody FormFieldsDto getUredniciRec(@PathVariable String instance) {


		System.out.println("USAOOO" + instance);
		Task task = taskService.createTaskQuery().processInstanceId(instance).list().get(0);

		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();


		return new FormFieldsDto(task.getId(), instance, properties);
	}


	@GetMapping(path = "/getMagazineForm", produces = "application/json")
	public @ResponseBody FormFieldsDto getMagazineForm() {

		ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_0qp1c5e");

		Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);

		TaskFormData tfd = formService.getTaskFormData(task.getId());
		List<FormField> properties = tfd.getFormFields();
		for(FormField fp : properties) {
			System.out.println(fp.getId() + fp.getType());
		}

		return new FormFieldsDto(task.getId(), pi.getId(), properties);
	}


	@GetMapping(path = "/get/tasks/{processInstanceId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> get(@PathVariable String processInstanceId) {
		
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(processInstanceId).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
		
        return new ResponseEntity(dtos,  HttpStatus.OK);
    }
	
	@PostMapping(path = "/post/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity post(@RequestBody List<FormSubmissionDto> dto, @PathVariable String taskId) {
		HashMap<String, Object> map = this.mapListToDto(dto);

		
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "registration", dto);
		formService.submitTaskForm(taskId, map);
        return new ResponseEntity<>(HttpStatus.OK);
    }


	@PostMapping(path = "/postMagazine/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity postMagazine(@RequestBody List<FormSubmissionDto> dto, @PathVariable("taskId") String taskId) {


		HashMap<String, Object> map = this.mapListToDto(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "magazine", dto);
		runtimeService.setVariable(processInstanceId, "username", "jovana@yahoo.com");

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(path = "/postUrednici/{taskId}", produces = "application/json")
	public @ResponseBody ResponseEntity postUrednici(@RequestBody List<FormSubmissionDto> dto, @PathVariable("taskId") String taskId) {


		System.out.println("lalalalal" + dto.size());
		HashMap<String, Object> map = this.mapiranje(dto);

		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		runtimeService.setVariable(processInstanceId, "varijabla", dto);
		runtimeService.setVariable(processInstanceId, "username", "jovana@yahoo.com");

		formService.submitTaskForm(taskId, map);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/tasks/claim/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity claim(@PathVariable String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		String processInstanceId = task.getProcessInstanceId();
		String user = (String) runtimeService.getVariable(processInstanceId, "username");
		taskService.claim(taskId, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
	
	@PostMapping(path = "/tasks/complete/{taskId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> complete(@PathVariable String taskId) {
		Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
		taskService.complete(taskId);
		List<Task> tasks = taskService.createTaskQuery().processInstanceId(taskTemp.getProcessInstanceId()).list();
		List<TaskDto> dtos = new ArrayList<TaskDto>();
		for (Task task : tasks) {
			TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
			dtos.add(t);
		}
        return new ResponseEntity<List<TaskDto>>(dtos, HttpStatus.OK);
    }
	
	private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			System.out.println(temp.getFieldId());
			if(temp.getFieldId().equals("naucna_oblast"))
				map.put(temp.getFieldId(), temp.getFieldListValue().get(0));
			else
				map.put(temp.getFieldId(), temp.getFieldValue());
		}
		
		return map;
	}

	private HashMap<String, Object> mapiranje(List<FormSubmissionDto> list)
	{
		HashMap<String, Object> map = new HashMap<String, Object>();
		for(FormSubmissionDto temp : list){
			if(temp.getFieldId().equals("recenzenti"))
				map.put(temp.getFieldId(), temp.getFieldListValue().get(0));
			else if (temp.getFieldId().equals("urednici"))
				map.put(temp.getFieldId(), temp.getFieldListValue().get(0));

			else
				map.put(temp.getFieldId(), temp.getFieldValue());
		}

		return map;
	}




	@PutMapping("/verify/{id}/{processInstanceId}")
	public ResponseEntity verifyAccount(@PathVariable long id, @PathVariable String processInstanceId) {
		System.out.println("eve meeeeeeeeeeeeeeeeeee "+processInstanceId);
		//ProcessInstance instance = runtimeService.createProcessInstanceQuery()
		//      .processDefinitionKey(processInstanceId).list().get(0);
		//.singleResult();
		Execution execution = runtimeService.createExecutionQuery()
				.processInstanceId(processInstanceId)
				.activityId("Task_1dwausg")
				.singleResult();
		System.out.println("saljeeeeeeeeeeem "+execution.getId());

		Map<String, Object> vars = new HashMap<>();
		vars.put("userId", id);
		System.out.println("saljeeeeeeeeeeem "+processInstanceId);

		runtimeService.signal(execution.getId(), vars);
		System.out.println("zavrsio verify ");

		return new ResponseEntity(HttpStatus.OK);
	}


	@GetMapping("/tasks/{username}")
	ResponseEntity getUserTasks(@PathVariable String username) {
		System.out.println("Trazim taskove za: " + username);

		List<Task> tasks = taskService.createTaskQuery()
				.taskAssignee(username)
				.list();

			return new ResponseEntity<>(tasks, HttpStatus.OK);

		//return new ResponseEntity(HttpStatus.OK);
	}

	@PutMapping("/activateRecenzent")
	public ResponseEntity<?> activateRecenzent(@RequestBody ActivateRecenzentDTO activateRecenzentDTO){
		System.out.println("LALALA" + activateRecenzentDTO.getUserId() + activateRecenzentDTO.isActivate());

		return ResponseEntity.ok(camundaService.activateRecenzent(activateRecenzentDTO));
	}




}
