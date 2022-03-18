package Stack;

/**
 * The Node class used implements an application that
 * will store and process the stack data.
 *
 * @author Randeep Singh Virk
 * @version 1.0
 * @since 2022-02-18
 */
public class Node<T> {

    /**
     * Private variables to store element, and previousNode.
     */
    private T element;
    private Node<T> previousNode;

    //region Methods

    /**
     * Default constructor (or empty constructor).
     */
    public Node() {
    }

    /**
     * Instance constructor with one parameter element.
     *
     * @param element The new stack element.
     */
    public Node(T element) {
        this.element = element;
    }

    /**
     * Instance constructor with two parameters element, and previousNode.
     *
     * @param element      The new stack element.
     * @param previousNode The reference to previous Node in the Stack.
     */
    public Node(T element, Node<T> previousNode) {
        this.element = element;
        this.previousNode = previousNode;
    }

    /**
     * Gets the element.
     *
     * @return Returns the element.
     */
    public T getElement() {
        return element;
    }

    /**
     * Sets the element.
     *
     * @param element The element to be set in the stack.
     */
    public void setElement(T element) {
        this.element = element;
    }

    /**
     * Gets the previousNode.
     *
     * @return Returns the previousNode.
     */
    public Node<T> getPrevious() {
        return previousNode;
    }

    /**
     * Sets the previousNode.
     *
     * @param prev The reference to previous Node.
     */
    public void setPrevious(Node<T> prev) {
        this.previousNode = prev;
    }

    //endregion
}

