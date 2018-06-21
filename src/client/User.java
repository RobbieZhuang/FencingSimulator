/* Player.java
 *
 * Version 1.0
 * Andi Li, Bill Li, Max Gao, Robbie Zhuang 
 * 01-23-2017
 *
 * User object stores the client data
 */

package client;

import java.util.ArrayList;

class User {

    // Defining variables
    private String name;
    private String status;
    private ArrayList<String> messages;
    private int unreadCount;

    /**
     * Constructor for user
     *
     * @param name   is the user name
     * @param status is the user's status
     */
    User(String name, String status) {
        this.name = name;
        this.status = status;
        this.messages = new ArrayList<>();
        this.unreadCount = 0;
    }

    /**
     * Gets all the messages in one string separated by new lines
     *
     * @return that one string
     */
    String getMessages() {
        String compiledMessage = "";
        for (String temp : this.messages) {
            compiledMessage += temp + "\n";
        }
        return compiledMessage;
    }

    /**
     * Gets the name of the user
     *
     * @return the name of the user
     */
    String getName() {
        return name;
    }

    /**
     * Changes the name of the user
     *
     * @param name is the new name
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the status of the user
     *
     * @return the status in string
     */
    String getStatus() {
        return status;
    }

    /**
     * Changes the status of the user
     *
     * @param status is the new status in string (0, 1, 2)
     */
    void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the status in words
     *
     * @return offline, available or busy
     */
    String getStatusInWords() {
        if (status.equals("0")) {
            return "Offline";
        } else if (status.equals("1")) {
            return "Available";
        } else if (status.equals("2")) {
            return "Busy";
        } else {
            return "Something is wrong";
        }
    }

    /**
     * Adds a message to this user
     *
     * @param s is the new message to be added
     */
    void appendMessage(String s) {
        this.messages.add(s);
    }

    /**
     * Add one more unread message count
     */
    void addUnreadCount () {
        this.unreadCount ++;
    }

    /**
     * Resets unread message count to 0
     */
    void resetUnreadCount () {
        this.unreadCount = 0;
    }

    /**
     * Gets the unread message count
     *
     * @return the count in int
     */
    int getUnreadCount () {
        return this.unreadCount;
    }
}
