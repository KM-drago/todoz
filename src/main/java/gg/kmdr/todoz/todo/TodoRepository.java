package gg.kmdr.todoz.todo;

import org.springframework.data.repository.ListCrudRepository;

public interface TodoRepository extends ListCrudRepository<Todo, Integer> {

}
