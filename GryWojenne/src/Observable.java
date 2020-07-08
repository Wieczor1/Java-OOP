public interface Observable {
    void attach(Observer observer);
    void notifyObservers(String note, Object... o);
}
