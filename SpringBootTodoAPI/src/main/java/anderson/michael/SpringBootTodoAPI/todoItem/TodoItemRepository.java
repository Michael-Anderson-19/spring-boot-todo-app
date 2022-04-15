package anderson.michael.SpringBootTodoAPI.todoItem;

import anderson.michael.SpringBootTodoAPI.user.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    Optional<TodoItem> findByIdAndUser(Long id, AppUser user);
    Optional<TodoItem> findByTitleAndUser(String title,AppUser user);
    List<TodoItem> findAllByUser(AppUser user);


}
