package gitRepository;

/**
 * define a Java class called MySet
 *
 * @author kevin Yang
 * @version January 7th, 2022
 */
public class MySet<T> // implements Iterable<T>
{
    private T[] array; // static array
    private int length = 0; // length user thinks array is
    private int capacity = 0; // Actual array size

    public MySet() {
        this(16);
    }

    public MySet(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
    }

    public int size() {
        return this.length;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        return this.array[index];
    }

    public void set(int index, T element) {
        this.array[index] = element;
    }

    public void makeEmpty()
    {
        for (int i = 0; i < this.length; i++)
        {
            this.array[i] = null;
        }
        this.length = 0;
    }

    public void insert(T element) {
        if (!isPresent(element)) {
            if (this.length + 1 >= this.capacity)
            {
                if (this.capacity == 0)
                {
                    this.capacity = 1;
                }
                else
                {
                    this.capacity *= 2;
                }
                T[] newArray = (T[]) new Object[this.capacity];
                // create a new array after double the size,
                for (int i = 0; i < this.length; i++)
                {
                    newArray[i] = this.array[i]; // copy everything into the new array
                }
                this.array = newArray; // set new array to original array
            }
            this.array[this.length++] = element; // add new element to the last after resizing
        }
    }

    /**
     * removes an element at the given index and return that removed element
     * but original array will not have that element
     */
    private T removeAt(int removeIndex)
    {
        if (removeIndex > this.length || removeIndex < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        T data = this.array[removeIndex]; // grab data at the remove index

        // initial a new array with length - 1
        T[] newArray = (T[]) new Object[this.length - 1];

        for (int i = 0, j = 0; i < this.length; i++, j++) {
            if (i == removeIndex) {
                j--; // skip over when at removeIndex
            } else // copy everything into the new array, which i is 1 index bigger than j
            {
                newArray[j] = this.array[i]; // for every index j except at removeIndex
                // gets stored in new array
            }
        }
        this.array = newArray; // set new array to be the original array
        this.capacity = --this.length;
        return data;
    }

    public boolean remove(Object obj) {
        int index = indexOf(obj);  // get removing object's index
        if (index == -1)  // if not found
        {
            return false;
        }
        removeAt(index); // we remove it at the index
        return true;
    }

    private int indexOf(Object obj) {
        for (int i = 0; i < this.length; i++) {
            if (obj == null) // if passing in a null object
            {
                if (this.array[i] == null) {
                    return i;  // check null object and return null at index
                }
            } else {
                if (obj.equals(this.array[i])) {
                    return i;
                }
            }
        }
        return -1;  // not found
    }

    public boolean isPresent(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public String toString() {
        if (this.length == 0) {
            return "[]";
        } else {
            StringBuilder sb = new StringBuilder(this.length).append("[");
            for (int i = 0; i < this.length - 1; i++) {
                sb.append(this.array[i] + ", ");
            }
            return sb.append(this.array[this.length - 1] + "]").toString();
        }
    }
}
