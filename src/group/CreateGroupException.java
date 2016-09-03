package group;

/**
 *
 * @author Игорь
 */
public class CreateGroupException extends Exception {

    public CreateGroupException(String group_ids_is_undefined) {
        super(group_ids_is_undefined);
    }

    public CreateGroupException() {
        super();
    }

}
