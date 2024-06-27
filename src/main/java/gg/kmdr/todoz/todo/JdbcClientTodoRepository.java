package gg.kmdr.todoz.todo;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import jakarta.annotation.PostConstruct;

@Repository
public class JdbcClientTodoRepository {

  private final JdbcClient jdbcClient;

  public JdbcClientTodoRepository(JdbcClient jdbcClient){
    this.jdbcClient = jdbcClient;
  }

  public List<Todo> findAll(){
    return jdbcClient.sql("select * from todo")
                     .query(Todo.class)
                     .list();
  }
  

  public Optional<Todo> findById(Integer id){
    return jdbcClient.sql("select * from todo where id = :id")
                     .param("id", id)
                     .query(Todo.class)
                     .optional();
  }

  public void create(Todo todo){
    var updated = jdbcClient.sql("INSERT INTO Todo (id, title, created_on, deadline_on, description) VALUES (?,?,?,?,?)")
                            .params(List.of(todo.id(), todo.title(), todo.createdOn(), todo.deadlineOn(), todo.description()))
                            .update();

    Assert.state(updated == 1, "Failed to create todo" + todo.title());
  }

  public void update(Todo todo, Integer id){
    var updated = jdbcClient.sql("UPDATE todo set title = ?, created_on = ?, deadline_on = ?, description = ? where id = ?")
                            .params(List.of(todo.title(), todo.createdOn(), todo.deadlineOn(), todo.description(), id))
                            .update();

    Assert.state(updated == 1, "Failed to update todo" + todo.title());
  }

  public void delete(Integer id){
    var updated = jdbcClient.sql("delete from todo where id = :id")
                            .param("id", id)
                            .update();

    Assert.state(updated == 1, "Failed to delete todo" + id);
  }

}
