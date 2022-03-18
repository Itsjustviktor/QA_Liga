package ru.digitalleague.queue;

import java.util.Deque;

public class Boss2 {
    private Deque toDoDeque;
    public Boss2(Deque toDoDeque) {
        this.toDoDeque = toDoDeque;
    }
    public void giveTask(String task) {
        toDoDeque.add(task); // метод добавления не меняется
        System.out.println("Дал задачу " + task);
    }
}
