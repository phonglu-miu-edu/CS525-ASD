package framework.model;

public interface INotification<E> {
	void sendNotification(E message);
}
