package by.fstk.shelf.app.model;

import java.util.List;

public interface ProjectRepository<T> {

    List<T> retrieveAll();
    void store(T item);

    boolean removeItemById(String itemIdToRemove);
}
