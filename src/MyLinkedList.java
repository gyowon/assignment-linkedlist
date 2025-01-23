import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements Iterable{
    private Node<T> head;
    private int size = 0;

    public void add(T data) {
        Node<T> node = new Node<>(data);
        if (size == 0) {
            this.head = node;
        } else {
            // 마지막 노드를 찾는다.
            Node<T> current = this.head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            // current에 가장 마지막 노드가 있음
            current.setNext(node);
        }
        this.size++;
    }

    public T get(int index) {
        // 예외 처리
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        // index번째 노드를 찾는다.
        Node<T> current = this.head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    public void delete(int index) {
        // 예외 처리
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            head = head.getNext();
        } else {
            // index번째 노드를 찾는다.
            Node<T> current = this.head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            current.setNext(current.getNext().getNext());
        }
        this.size--;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> current = head;
            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                // 예외 처리
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.getData(); // 현재 데이터를 꺼내준다.
                current = current.getNext(); // 그 다음으로 넘겨준다.
                return data;
            }
        };
    }
}
