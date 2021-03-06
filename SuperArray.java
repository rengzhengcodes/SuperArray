public class SuperArray {

  private String[] data;
  private int size = 0; //current array size

  public SuperArray() {
    this.size = 0;
    this.data = new String[8];
  }

  public int size()  {
    return this.size;
  }

  public boolean add(String element) {
    if (this.size == this.data.length) {
      this.resize();
    }

    this.data[this.size] = element;
    this.size++;
    return true;
  }

  public String get(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
    }
    if (index < this.size) {
      return this.data[index];
    } else {
      return null;
    }
  }

  public String set(int index, String element) throws IndexOutOfBoundsException {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
    } else if (element == null) {
      this.remove(index);
      return null;
    } else {
      String replaced = this.data[index];
      this.data[index] = element;
      return replaced;
    }
  }

  private void resize() {
    String[] dataNew = new String[Math.max(1, this.data.length * 2)];

    for (int index = 0; index < this.data.length; index++) {
      dataNew[index] = this.data[index];
    }

    this.data = dataNew;
  }

  public void clear() {
    this.data = new String[8];
    this.size = 0;
  }

  public boolean isEmpty() {
    return this.size == 0;
  }

  public String toString() {
    String output = "[";
    String spacer = ", ";
    for (int i = 0; i < this.data.length; i++) {
      if (data[i] != null) {
        output += this.data[i] + spacer;
      }
    }

    if (output.contains(", ")) {
      output = output.substring(0, output.lastIndexOf(", "));
    }

    output += "]";
    return output;
  }

  public boolean contains(String s) {
    if (s == null || this.size == 0) {
      return false;
    }

    for (String element : this.data) {
      if (element != null && element.equals(s)) {
        return true;
      }
    }
    return false;
  }

  public SuperArray(int initialCapacity) throws IllegalArgumentException {
    if (initialCapacity < 0) {
      throw new IllegalArgumentException("Initial capacity " + initialCapacity + " cannot be negative.");
    }
    this.size = 0;
    this.data = new String[initialCapacity];
  }

  public void add(int index, String element) {
    if (index < 0 || index > this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
    }
    int dynamicIndex = 0;
    String[] dataNew = new String[this.size + 1];
    dataNew[index] = element;
    for (int i = 0; i < this.data.length; i++) {
      if (dynamicIndex == index) {
        dynamicIndex++;
      }
      if (data[i] != null) {
        dataNew[dynamicIndex] = this.data[i];
        dynamicIndex++;
      }
    }

    this.size++;
    this.data = dataNew;
  }

  public String remove(int index) throws IndexOutOfBoundsException {
    if (index < 0 || index >= this.size) {
      throw new IndexOutOfBoundsException("Index " + index + " out of bounds.");
    }
    String[] dataNew = new String[this.size - 1];
    int dynamicIndex = 0;
    String popped = "";
    for (int i = 0; i < this.data.length; i++) {
      if (data[i] != null) {
        if (dynamicIndex == index) {
          popped = data[i];
          index--;                                                              //avoids using a boolean. if we set the index to before the current dynamic array index, it'll never be encountered again
        } else {
          dataNew[dynamicIndex] = data[i];
          dynamicIndex++;
        }
      }
    }

    this.size--;
    this.data = dataNew;
    return popped;
  }

  public int indexOf(String s) {
    int dynamicIndex = -1;
    for (String element : this.data) {
      if (element != null) {
        dynamicIndex++;
        if (element.equals(s)) {
          return dynamicIndex;
        }
      }
    }

    return -1;
  }

  public String[] toArray() {
    String[] output = new String[this.size];
    int dynamicIndex = 0;
    for (String element : this.data) {
      if (element != null) {
        output[dynamicIndex] = element;
        dynamicIndex++;
      }
    }
    if (dynamicIndex < this.size - 1) {
      String[] output2 = new String[dynamicIndex];                               //dynamic index always reflects length of output array at the end.
      for (int index = 0; index < dynamicIndex; index++) {
        output2[index] = output[index];
      }
      output = output2;
    }
    return output;
  }

  public static void removeDuplicates(SuperArray s) {

    for (int index = s.size - 1; index >= 0; index--) {
      String element = s.data[index];
      if (s.indexOf(element) != index) {
        s.remove(index);
      }
    }

  }

  public int lastIndexOf(String s) {
    int dynamicIndex = this.size();
    for (int i = this.data.length - 1; i >= 0; i--) {
      String element = this.data[i];
      if (element != null) {
        dynamicIndex--;
        if (element.equals(s)) {
          return dynamicIndex;
        }
      }
    }

    return -1;
  }

  public boolean equals(SuperArray other) {
    String[] thisElements = this.toArray();
    String[] otherElements = other.toArray();
    if (thisElements.length != otherElements.length) {
      return false;
    }

    for (int index = 0; index < thisElements.length; index++) {
      if (!thisElements[index].equals(otherElements[index])) {
        return false;
      }
    }

    return true;
  }

}
