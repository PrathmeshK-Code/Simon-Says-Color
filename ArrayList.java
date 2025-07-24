import java.util.Iterator;
import java.util.NoSuchElementException;
public class ArrayList<E> implements List<E> {
	public static final int capacity = 4;
	private E[] data;
	private int size;
	
	public ArrayList() {
		this(capacity);
	}
	public ArrayList(int c) {
		data = (E[]) new Object[c];
	}
    public int size() {
    	return size;
    }

    public boolean isEmpty() {
    	return size==0;
    }
   
    public E get(int i) throws IndexOutOfBoundsException{
    	checkIndex(i,size);
    	return data[i];
    }
    
    public E set(int i, E e) throws IndexOutOfBoundsException{
    	checkIndex(i, size);
    	E temp = data[i];
    	data[i] = e;
    	return temp;
    }
    
    public void add(int i, E e) throws IndexOutOfBoundsException{
    	checkIndex(i, size+1);
    	if(size==data.length) {
    		resize(2*data.length);
    	}
    	for(int k=size-1; k>=i; k--) {
    		data[k+1] = data[k];
    	}
    	data[i]=e;
    	size++;
    }
    
    public void add(E e) throws IndexOutOfBoundsException{
    	if(size==data.length) {
    		resize(2*data.length);
    	}
    	if(size<data.length/4) {
    		resize(data.length/2);
    	}
    	data[size]=e;
    	size++;
    }
    
    protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
    	if(i<0||i>=n) {
    		throw new IndexOutOfBoundsException("Illegal index: "+i);
    	}
    }
    
    public E remove(int i) throws IndexOutOfBoundsException{
    	checkIndex(i,size);
    	E temp = data[i];
    	for(int k=i; k<size-1; k++) {
    		data[k]=data[k+1];
    	}
    	data[size-1]=null;
    	size--;
    	if(size<data.length/4) {
    		resize(data.length/2);
    	}
    	return temp;
    }
    
    protected void resize(int capacity) {
    	E[] temp = (E[]) new Object[capacity];
    	for(int k=0; k<size; k++) {
    		temp[k] = data[k];
    	}
    	data=temp;
    }
    
    public boolean equals(ArrayList<E> o) {
    	if(o == null) return false;
    	if(o.getClass()!=getClass()) return false;
    	ArrayList<E> other = (ArrayList<E>) o;
    	if(size!=other.size()) return false;
    	for(int k=0; k<size(); k++) {
    		if(!data[k].equals(other.data[k])) return false;
    	}
    	return true;
    }
    
    public Iterator<E> iterator(){
    	return new ArrayIterator();
    }
    
    public String toString() {
    	if(size==0) return "";
    	StringBuilder sb = new StringBuilder();
    	sb.append(data[0]);
    	for(int i=1; i<size;i++) {
    		sb.append("\n"+data[i]);
    	}
    	return sb.toString();
    }
    
    private class ArrayIterator implements Iterator<E>{
    	private int j=0;
    	private boolean removable = false;
    	
    	public boolean hasNext() {
    		return j<size;
    	}
    	
    	public E next() throws NoSuchElementException{
    		if(j==size) throw new NoSuchElementException("No next Element");
    		removable = true;
    		return data[j++];
    	}
    	
    	public void remove() throws IllegalStateException{
    		if(!removable) throw new IllegalStateException("Nothing to remove");
    		ArrayList.this.remove(--j);
    		removable = false;
    	}
    }

}