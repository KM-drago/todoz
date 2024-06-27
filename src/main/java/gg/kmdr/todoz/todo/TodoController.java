package gg.kmdr.todoz.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/api/todos")
public class TodoController {

  private final TodoRepository todoRepository;

  public TodoController(TodoRepository todoRepository){
    this.todoRepository = todoRepository;
  }

  @GetMapping("")
  List<Todo> findAll(){
    return todoRepository.findAll();
  }

  @GetMapping("/{id}")
  Todo findById(@PathVariable Integer id) {
      Optional<Todo> todo = todoRepository.findById(id);

      if(todo.isEmpty()){
        throw new TodoNotFoundException();
      }
      return todo.get();
  }
  
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("")
  void create(@Valid @RequestBody Todo todo){
    todoRepository.save(todo);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @PutMapping("/{id}")
  void update(@Valid @RequestBody Todo todo, @PathVariable Integer id){
    todoRepository.save(todo);
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  void delete(@PathVariable Integer id){
    todoRepository.delete(todoRepository.findById(id).get());
  }
}
