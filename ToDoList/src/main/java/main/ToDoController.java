package main;

import main.model.ToDo;
import main.model.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class ToDoController {

    @Autowired
    private ToDoRepository toDoRepository;

    @GetMapping("/todos/")
    public List<ToDo> list() {
        Iterable<ToDo> toDoIterable = toDoRepository.findAll();
        ArrayList<ToDo> toDosList = new ArrayList<>();
        for (ToDo toDo : toDoIterable) {
            toDosList.add(toDo);
        }
        return toDosList;
    }

    @PostMapping("/todos/")
    public int add(ToDo toDo) {
        ToDo newToDo = toDoRepository.save(toDo);
        return newToDo.getId();
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (!toDoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(toDoOptional.get(), HttpStatus.OK);
    }

    @DeleteMapping("/todos/{id}")
    public void delete(@PathVariable int id) {
        toDoRepository.deleteById(id);

    }

    @DeleteMapping("/todos/")
    public void deleteAll() {
        toDoRepository.deleteAll();
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity change(@PathVariable int id, String name) {
        Optional<ToDo> toDoOptional = toDoRepository.findById(id);
        if (!toDoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        ToDo toDo = new ToDo();
        toDo.setId(id);
        toDo.setName(name);
        toDoRepository.save(toDo);
        return new ResponseEntity(toDo, HttpStatus.OK);
    }
}
