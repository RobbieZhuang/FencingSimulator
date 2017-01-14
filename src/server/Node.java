/* Node.java
 *
 * Version 1.0
 * Bill Li
 * 12-23-2016
 *
 * The node object for the queue.
 */

package server;

class Node<I> {
    private Node previous;
    private Node next;
    private I item;

    /**
     * Constructor for the node
     *
     * @param item is the item that the node holds
     */
    Node(I item) {
        this.item = item;
        this.next = null;
    }

    /**
     * Constructor for node
     *
     * @param item is the item that the node holds
     * @param next is the next node
     */
    public Node(I item, Node next) {
        this.item = item;
        this.next = next;
    }

    /**
     * Constructor for node
     *
     * @param item     is the item that the node holds
     * @param previous is the node that comes before this node
     * @param next     is the next node
     */
    public Node(I item, Node previous, Node next) {
        this.item = item;
        this.previous = previous;
        this.next = next;
    }

    /**
     * Gets the previous node
     *
     * @return the previous node
     */
    public Node getPrevious() {
        return previous;
    }

    /**
     * Changes the previous node to previous
     *
     * @param previous is the new previous node
     */
    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    /**
     * Gets the next node
     *
     * @return the next node
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * Sets the next node
     *
     * @param next is the next node
     */
    public void setNext(Node next) {
        this.next = next;
    }

    /**
     * Gets the item from node
     *
     * @return the iteam
     */
    public I getItem() {
        return item;
    }

    /**
     * Changes the item in node
     *
     * @param item is the new item
     */
    public void setItem(I item) {
        this.item = item;
    }
}
