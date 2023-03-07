package com.example.springapp.controller;

import com.example.springapp.entity.Task;
import com.example.springapp.exception.RecordNotFoundException;
import com.example.springapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskService taskService;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("task", taskService.getAllTask());
        return "index";
    }
    @GetMapping("/add")
    public String showAddForm(Task task, Model model) {
        return "add-task";
    }
    @PostMapping("/save")
    public String create(Task task, Model model) {
        taskService.saveOrUpdateTask(task);
        return "redirect:/";
    }
    @RequestMapping(path = { "/update","/update/{id}"})
    public String update(Model model,@PathVariable("id") Integer id) throws RecordNotFoundException {
        if(id!=null) {
            Task task2 = taskService.getTaskById(id);
            model.addAttribute("task", task2);
        }else {
            model.addAttribute("task", new Task());
        }
        return "update-task";
    }
    @RequestMapping(path = { "/delete/{id}"})
    public String delete(Model model, @PathVariable("id") Integer id) {
        taskService.deleteTask(id);
        return "redirect:/";
    }
}
