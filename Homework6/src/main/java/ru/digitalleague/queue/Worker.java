package ru.digitalleague.queue;

import java.util.Queue;

public class Worker {
    private Queue toDoQueue;
    public Worker(Queue toDoQueue) {
        this.toDoQueue = toDoQueue;
    }
    public void takeTask() {
        Object task = toDoQueue.poll();
        if (task != null) {
            System.out.println("Выполняю задачу: " + task);
        } else {
            System.out.println("Нет работы! Можно идти домой");
        }
    }
}
