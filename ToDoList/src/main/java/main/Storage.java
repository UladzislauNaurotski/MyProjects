package main;

import main.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage {

    private static Map<Integer, ToDo> toDos = new ConcurrentHashMap<>();
    private static AtomicInteger currentId = new AtomicInteger(1);

    public static int addToDo(ToDo toDo) {
        int id = currentId.incrementAndGet();
        toDo.setId(id);
        toDos.put(id, toDo);
        return id;
    }

    public static List<ToDo> getAllToDos() {
        List<ToDo> allToDo = new ArrayList<>();
        allToDo.addAll(toDos.values());
        return allToDo;
    }

    public static ToDo getToDo(int id) {
        if (toDos.containsKey(id)) {
            return toDos.get(id);
        }
        return null;
    }

    public static void removeToDo(int id) {
        if (toDos.containsKey(id)) {
            toDos.remove(id);
        }
    }

    public static void removeAllToDo() {
        toDos.clear();
    }

    public static ToDo changeToDo(int id, String name) {
        if (toDos.containsKey(id)) {
            ToDo toDo = new ToDo();
            toDo.setId(id);
            toDo.setName(name);
            toDos.put(id, toDo);
            return toDos.get(id);
        }
        return null;
    }
}
