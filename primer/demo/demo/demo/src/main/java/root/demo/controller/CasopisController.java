package root.demo.controller;


import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.form.FormField;
import org.camunda.bpm.engine.form.TaskFormData;
import org.camunda.bpm.engine.impl.form.type.EnumFormType;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import root.demo.model.*;
import root.demo.model.DTO.CasopisDTO;
import root.demo.model.DTO.PublishDTO;
import root.demo.repository.CasopisRepository;
import root.demo.repository.NaucnaOblastRepository;
import root.demo.security.TokenUtils;
import root.demo.services.CamundaService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("api/casopis")
public class CasopisController {


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

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    private CasopisRepository casopisRepository;

    @Autowired
    private NaucnaOblastRepository naucnaOblastRepository;



    @GetMapping(value = "")
    public ResponseEntity<PublishDTO> getCasopis() {

        String username = this.tokenUtils.getUsernameFromToken(this.tokenUtils.getToken(this.httpServletRequest));
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("Process_1q4ylyj");

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).list().get(0);
        System.out.println(task.getAssignee() + "ko je assigne");


        List<Casopis> casopisi = this.casopisRepository.findAll();

        PublishDTO dto= new PublishDTO(task.getId(),casopisi,pi.getProcessInstanceId());
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/get/{processInstanceId}")
    public ResponseEntity<Rad> getMagazine(@PathVariable String processInstanceId) {
       List<FormSubmissionDto> id = (List<FormSubmissionDto>) runtimeService.getVariable(processInstanceId,"rad");
     //   Casopis casopis = this.casopisRepository.findByCasopisId(Long.parseLong(id));

        Rad r= new Rad();
        r.setNaslov(id.get(0).getFieldValue());
        r.setApstract(id.get(1).getFieldValue());
        r.setKljucniPojmovi(id.get(2).getFieldValue());
        return new ResponseEntity<>(r, HttpStatus.OK);

    }

    @PostMapping(path = "/tasks/complete/{taskId}", produces = "application/json")
    public  @ResponseBody ResponseEntity completeNesto(@RequestBody List<FormSubmissionDto> dto,@PathVariable String taskId) {
        HashMap<String, Object> map = this.mapListToDto(dto);
        Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
        formService.submitTaskForm(taskId, map);

    //    taskService.complete(taskId);
        return new ResponseEntity<>(HttpStatus.OK);

    }


    @PostMapping(path = "/complete/{taskId}/{magazineId}", produces = "application/json")
    public @ResponseBody ResponseEntity<List<TaskDto>> complete(@PathVariable("taskId") String taskId, @PathVariable("magazineId") String magazineId) {

        Task taskTemp = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.complete(taskId);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(taskTemp.getProcessInstanceId()).list();
        runtimeService.setVariable(taskTemp.getProcessInstanceId(),"magazineId", magazineId);
        List<TaskDto> dtos = new ArrayList<TaskDto>();
        for (Task task : tasks) {
            TaskDto t = new TaskDto(task.getId(), task.getName(), task.getAssignee());
            dtos.add(t);
        }
        return new ResponseEntity<List<TaskDto>>(dtos, HttpStatus.OK);
    }


    @GetMapping(path = "/getForm/{instance}", produces = "application/json")
    public @ResponseBody
    FormFieldsDto getForm(@PathVariable String instance) {


        Task task = taskService.createTaskQuery().processInstanceId(instance).list().get(0);

        TaskFormData tfd = formService.getTaskFormData(task.getId());
        List<FormField> properties = tfd.getFormFields();
        List<NaucnaOblast> naucneOblasti= this.naucnaOblastRepository.findAll();

        for(FormField form: properties){
            if(form.getId().equals("naucnaOblast")){
                EnumFormType enumFormType = (EnumFormType) form.getType();
                Map<String, String> items = enumFormType.getValues();
              for(NaucnaOblast naucna: naucneOblasti){
                  items.put(naucna.getName(),naucna.getName());
              }
                runtimeService.setVariable(instance,"naucnaOblast", items);

            }
        }


        return new FormFieldsDto(task.getId(), instance, properties);
    }

    @PostMapping("submit/{taskId}")
    public ResponseEntity submit(@PathVariable String taskId, @RequestBody CasopisDTO paperDTO) {
        // OK, novo, submitovani potrebni podaci o radu
        System.out.println("Dodajem rad: " + paperDTO.toString() + " za task sa idom: " + taskId);
        final List<FormSubmissionDto> taskFormFieldDtos = new ArrayList<>();
        taskFormFieldDtos.add(new FormSubmissionDto("naslov", paperDTO.getNaslovRada()));
        taskFormFieldDtos.add(new FormSubmissionDto("apstrakt", paperDTO.getApstrakt()));
        taskFormFieldDtos.add(new FormSubmissionDto("kljucniPojmovi", paperDTO.getKljucniPojmovi()));
        taskFormFieldDtos.add(new FormSubmissionDto("naucnaOblast", paperDTO.getNaucnaOblast()));
        //  taskFormFieldDtos.add(new FormSubmissionDto("autori", paperDTO.getAutori()));
        taskFormFieldDtos.add(new FormSubmissionDto("filename", paperDTO.getFilename()));

        HashMap<String, Object> map = this.mapListToDto(taskFormFieldDtos);

        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        String processInstanceId = task.getProcessInstanceId();
        runtimeService.setVariable(processInstanceId, "rad", taskFormFieldDtos);
        runtimeService.setVariable(processInstanceId, "autori", paperDTO.getAutori());

        formService.submitTaskForm(taskId, map);

        System.out.println("Submitovani podaci za rad dosao dovde");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private HashMap<String, Object> mapListToDto(List<FormSubmissionDto> list)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        for(FormSubmissionDto temp : list){
            System.out.println(temp.getFieldId());
            if(temp.getFieldId().equals("naucnaOblast"))
                map.put(temp.getFieldId(), temp.getFieldListValue().get(0));
            else
                map.put(temp.getFieldId(), temp.getFieldValue());
        }

        return map;
    }

}
