package anderson.michael.SpringBootTodoAPI.todoItem;

import anderson.michael.SpringBootTodoAPI.user.AppUser;

import javax.persistence.*;

@Entity
public class TodoItem {


    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private AppUser user;

    public TodoItem() {
    }

    public TodoItem(Long id, String title, String description, boolean isComplete, AppUser user) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isComplete=" + isComplete +
                ", user=" + user +
                '}';
    }
}
