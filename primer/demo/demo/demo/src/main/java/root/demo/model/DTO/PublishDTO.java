package root.demo.model.DTO;

import root.demo.model.Casopis;

import java.util.List;

public class PublishDTO {

    String taskId;
    List<Casopis> casopisi;
    String processInstanceId;


    public PublishDTO(String taskId, List<Casopis> casopisi, String processInstanceId) {
        this.taskId = taskId;
        this.casopisi = casopisi;
        this.processInstanceId = processInstanceId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public List<Casopis> getCasopisi() {
        return casopisi;
    }

    public void setCasopisi(List<Casopis> casopisi) {
        this.casopisi = casopisi;
    }

    public PublishDTO(String taskId) {

        this.taskId = taskId;
    }

    public PublishDTO() {
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
}
