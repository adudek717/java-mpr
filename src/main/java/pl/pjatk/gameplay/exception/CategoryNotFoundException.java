package pl.pjatk.gameplay.exception;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String name){
        super("Category " + name + " not found");
    }
}
