package ru.digitalleague.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class QueueTest {
    public static void queueTest() {
        int numberOfTasks = 5;
        Queue toDoQueue = new ArrayDeque(numberOfTasks);
        Boss boss = new Boss(toDoQueue);

        Worker worker = new Worker(toDoQueue);
        for (int i = 1; i <= numberOfTasks; i++) {
            boss.giveTask("Задание " + i);
        }
        System.out.println("Начальник закончил давать задания");
        for (int i = 1; i <= numberOfTasks + 1; i++) {
            worker.takeTask();
        }
    }

    public static void dequeTest() {
        int numberOfTasks = 5;
        Deque toDoDeque = new ArrayDeque(numberOfTasks); // имплементация
        // остается прежней, ArrayDeque работает и как очередь, и как стек
        Boss2 boss = new Boss2(toDoDeque);
        Worker2 worker = new Worker2(toDoDeque);

        for (int i = 1; i <= numberOfTasks; i++) {
            boss.giveTask("Задание " + i);
        }

        System.out.println("Начальник закончил давать задания");

        for (int i = 1; i <= numberOfTasks + 1; i++) {
            worker.takeTask();
        }

    }
}
