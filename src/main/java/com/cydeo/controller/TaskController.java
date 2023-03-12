package com.cydeo.controller;

import com.cydeo.dto.TaskDTO;
import com.cydeo.entity.ResponseWrapper;
import com.cydeo.enums.Status;
import com.cydeo.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @RolesAllowed({"Manager"})
    @GetMapping
    public ResponseEntity<ResponseWrapper> getTasks() {
        List<TaskDTO> tasks = taskService.listAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Tasks are successfully retrieved", tasks, HttpStatus.OK));
    }

    @RolesAllowed({"Manager"})
    @GetMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> getTaskById(@PathVariable Long taskId) {
        TaskDTO task = taskService.findById(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Task is successfully retrieved", task, HttpStatus.OK));
    }

    @RolesAllowed({"Manager"})
    @PostMapping
    public ResponseEntity<ResponseWrapper> createTask(@RequestBody TaskDTO taskDTO) {
        taskService.save(taskDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseWrapper("Task is successfully created", taskDTO, HttpStatus.CREATED));
    }

    @RolesAllowed({"Manager"})
    @DeleteMapping("/{taskId}")
    public ResponseEntity<ResponseWrapper> deleteTask(@PathVariable("taskId") Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Task is successfully deleted",  HttpStatus.OK));
    }

    @RolesAllowed({"Manager"})
    @PutMapping
    public ResponseEntity<ResponseWrapper> updateTask(@RequestBody TaskDTO taskDTO) {
        taskService.update(taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Task is successfully updated", taskDTO, HttpStatus.OK));
    }

    @RolesAllowed({"Employee"})
    @GetMapping("/employee/pending-tasks")
    public ResponseEntity<ResponseWrapper> employeePendingTasks() {
        List<TaskDTO> tasks = taskService.listAllTasksByStatusIsNot(Status.COMPLETE);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Non completed Tasks are successfully retrieved", tasks, HttpStatus.OK));
    }

    @RolesAllowed({"Employee"})
    @PutMapping("/employee/update/")
    public ResponseEntity<ResponseWrapper> employeeUpdateTask(@RequestBody TaskDTO taskDTO) {
        taskService.updateStatus(taskDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Tasks is successfully retrieved", taskDTO, HttpStatus.OK));
    }

    @RolesAllowed({"Employee"})
    @GetMapping("/employee/archive")
    public ResponseEntity<ResponseWrapper> employeeArchivedTasks() {
        List<TaskDTO> taskList = taskService.listAllTasksByStatus(Status.COMPLETE);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseWrapper("Completed Tasks are successfully retrieved", taskList, HttpStatus.OK));
    }

}