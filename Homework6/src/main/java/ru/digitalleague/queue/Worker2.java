package ru.digitalleague.queue;

import java.util.Deque;

public class Worker2 {
    private Deque toDoDeque;
    public Worker2(Deque toDoDeque) {
        this.toDoDeque = toDoDeque;
    }
    public void takeTask() {
        Object task = toDoDeque.poll(); // вынимаем задание из конца Deque
        if (task != null) {
            System.out.println("Выполняю задачу: " + task);
        } else {
            System.out.println("Нет работы! Можно идти домой");
        }
    }

}
