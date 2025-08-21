package business;

public interface Command<T, U> {
    T execute(U u);
} 