package com.example.springapp.service;

import com.example.springapp.entity.Task;
import com.example.springapp.exception.RecordNotFoundException;
import com.example.springapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Iterable<Task> getAllTask() {
        return taskRepository.findAll();
    }
    public Task getTaskById(Integer id) throws RecordNotFoundException {

        Optional<Task> task = taskRepository.findById(id);
        if(task.isPresent()) {
            return task.get();
        }
        else
        {
            throw new RecordNotFoundException("Not found");
        }
    }
    public void saveOrUpdateTask(Task task) {
        if(task.getId() == null) {
            taskRepository.save(task);
        }
        else {
            Optional<Task> sOptional = taskRepository.findById(task.getId());
            if(sOptional.isPresent()) {
                Task task2 = sOptional.get();
                task2.setTitle(task.getTitle());
                task2.setDescription(task.getDescription());
                task2.setCompleted(task.isCompleted());
                task2 = taskRepository.save(task2);
            }
            else {
                task = taskRepository.save(task);

            }
        }
    }
    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}

