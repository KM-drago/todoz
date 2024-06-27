package gg.kmdr.todoz.todo;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;

public record Todo(
  @Id
  Integer id,

  @NotEmpty
  String title,

  LocalDateTime createdOn,
  
  @Future
  LocalDateTime deadlineOn,
  
  String description,

  @Version
  Integer Version
) {}
