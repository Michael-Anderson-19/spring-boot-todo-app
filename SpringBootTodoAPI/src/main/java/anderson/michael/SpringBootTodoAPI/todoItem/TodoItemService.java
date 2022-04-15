package anderson.michael.SpringBootTodoAPI.todoItem;

import anderson.michael.SpringBootTodoAPI.user.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO illegalStateException with custom error and implement exception handlers

@Service
public class TodoItemService {

    private final TodoItemRepository itemRepository;

    public TodoItemService(TodoItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    //Read
    public TodoItem getTodoItem(Long itemId, AppUser user)
    {
        return this.itemRepository.findByIdAndUser(itemId, user).orElseThrow( () -> new IllegalStateException("item not found"));
    }

    public List<TodoItem> getAllTodoItems(AppUser user)
    {
        return this.itemRepository.findAllByUser(user);
    }

    //Create
    public TodoItem createNewTodoItem(TodoItem newItem, AppUser user)
    {
        newItem.setUser(user);
        return this.itemRepository.save(newItem);
    }

    //update - update details of existing item or or create item if not found
    public TodoItem updateTodoItem(Long itemId, TodoItem newItem, AppUser user)
    {
        return this.itemRepository.findByIdAndUser(itemId, user).map( existingItem -> {
            existingItem.setTitle(newItem.getTitle());
            existingItem.setDescription(newItem.getDescription());
            existingItem.setComplete(newItem.isComplete());
            existingItem.setUser(user);
            return this.itemRepository.save(existingItem);
        }).orElseGet ( () -> {
            newItem.setId(itemId);
            newItem.setUser(user);
            return this.itemRepository.save(newItem);
        });
    }

    //delte item
    public void deleteItem(Long itemId, AppUser user)
    {
        TodoItem item = this.itemRepository.findByIdAndUser(itemId, user).orElseThrow( () -> new IllegalStateException("unable to delete item that does not exist"));
        this.itemRepository.delete(item);
    }
}
