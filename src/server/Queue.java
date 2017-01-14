/* Queue.java
 *
 * Version 1.0
 * Bill Li
 * 12-23-2016
 *
 * The queue data structure for managing messages.
 */

package server;

public class Queue<I> {
    volatile Node<I> head;

    /** Checks if the queue is empty
     *
     * @return true if empty, false if not
     */
    boolean isEmpty() {
        return (this.head == null);
    }

    /** Enqueues an item
     *
     * @param item is the item to be enqueued
     * @return false if anything went wrong
     */
    boolean enqueue(I item) {
        Node temp = this.head;
        boolean repeat = true;

        // If head is null
        if (this.head == null) {
            this.head = new Node<I>(item);
            return true;
        }

        // Traversing to the end
        while (repeat) {
            repeat = false;
            if (temp.getNext() != null) {
                temp = temp.getNext();
                repeat = true;
            }
        }

        Node nextNode = new Node<I>(item);
        nextNode.setPrevious(temp);
        temp.setNext(nextNode);
        return true;
    }

    /** Dequeues an item
     *
     * @return the item
     */
    I dequeue() {
        Node<I> temp = this.head;
        this.head = temp.getNext();
        return temp.getItem();
    }
}