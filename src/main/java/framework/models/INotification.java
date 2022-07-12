package framework.models;

public interface INotification<E> {
	void sendNotification(E message);
}
