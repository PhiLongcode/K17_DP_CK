package Observer;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
    private final List<Subscriber> subscribers =  new ArrayList<>();



    public void addSubscriber(Subscriber s) {
        subscribers.add(s);
    }
    public void removeSubscriber(Subscriber s) {
        subscribers.remove(s);

    }
    public void notifySubscribers() {
        for (Subscriber s : subscribers) {
            try {
                s.update();
            } catch (Exception e) {
                System.err.println("Lỗi khi cập nhật subscriber: " + e.getMessage());
            }
        }
    }
}
