package com.example.final_project.Model;

/**
 * Represents a Manager entity.
 * <p>
 * The Manager class contains details about the manager, including
 * a unique identifier and a reference to the associated user.
 * </p>
 *
 * @since 1.0
 */
public class Manager {

    /**
     * The unique identifier for the manager.
     */
    private int managerId;

    /**
     * The unique identifier of the associated user (foreign key).
     */
    private int userId;

    /**
     * Constructs a new Manager with the specified ID and user ID.
     *
     * @param managerId the unique ID for the manager.
     *                 Must be a positive integer.
     * @param userId    the unique ID for the associated user.
     *                 Must be a positive integer.
     * @throws IllegalArgumentException if managerId or userId is not positive.
     */
    public Manager(int managerId, int userId) {
        setManagerId(managerId);
        setUserId(userId);
    }

    /**
     * Gets the unique identifier of the manager.
     *
     * @return the manager's unique ID.
     */
    public int getManagerId() {
        return managerId;
    }

    /**
     * Sets the unique identifier of the manager.
     *
     * @param managerId the unique ID for the manager.
     *                 Must be a positive integer.
     * @throws IllegalArgumentException if the managerId is not positive.
     */
    public void setManagerId(int managerId) {
        if (managerId <= 0) {
            throw new IllegalArgumentException("Manager ID must be a positive integer.");
        }
        this.managerId = managerId;
    }

    /**
     * Gets the unique identifier of the associated user.
     *
     * @return the user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the unique identifier of the associated user.
     *
     * @param userId the unique ID for the user.
     *              Must be a positive integer.
     * @throws IllegalArgumentException if the userId is not positive.
     */
    public void setUserId(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be a positive integer.");
        }
        this.userId = userId;
    }
}
