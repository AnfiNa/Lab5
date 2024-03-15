package managerscollection;

import java.util.AbstractCollection;

public interface CollectionManager<T extends AbstractCollection<E>, E> {
    T getCollection();
    void setCollection(T value);
    void addElementToCollection(E value);
    void clearCollection();
}
