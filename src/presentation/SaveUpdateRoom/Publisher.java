package presentation.SaveUpdateRoom;

import java.util.ArrayList;
import java.util.List;

public class Publisher {
	 protected final List<Subscriber> subscribers = new ArrayList<>();

	    public void addSubscriber(Subscriber subscriber) {
	        if (subscriber != null && !subscribers.contains(subscriber)) {
	            subscribers.add(subscriber);
	        }
	    }

	    public void removeSubscriber(Subscriber subscriber) {
	        subscribers.remove(subscriber);
	    }

	    public void notifySubscribers() {
	        for (Subscriber s : subscribers) s.update();
	    }
}
