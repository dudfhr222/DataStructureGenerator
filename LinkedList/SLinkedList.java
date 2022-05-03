package LinkedList;

import java.util.NoSuchElementException;

public class SLinkedList<E> implements List<E> {

	private Node<E> head; 
	private Node<E> tail; 
	private int size;
	
	public SLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	private Node<E> search(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> x = head;
		
		for (int i = 0; i < index; i++) {
			x = x.next; 
		}
		return x;
	}
	
	public void addFirst(E value) {
		Node<E> newNode = new Node<E>(value);
		newNode.next = head; 
		head = newNode; 
		size++;
		
		if(head.next == null) {
			tail = head;
		}
	}
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value); 
		
		if(size == 0 ) { 
			addFirst(value);
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	@Override
	public void add(int index, E value) {
		
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		if(index == 0) {
			addFirst(value);
			return;
		}
		if(index == size) {
			addLast(value);
			return;
		}
		
		Node<E> prev_Node = search(index-1);
		Node<E> next_Node = prev_Node.next;
		Node<E> newNode = new Node<E>(value);
		
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
		size++;
	}
	
	//���� �տ��ִ� ��� ����
		public E remove() {
			
			Node<E> headNode = head;
			if(headNode == null) {
				throw new NoSuchElementException();
			}
			//������ ��带 ��ȯ�ϱ� ���� �ӽ� ����
			E element = headNode.data;
			//head�� ���� ���
			Node<E> nextNode = head.next;
			
			head.data = null;
			head.next = null;
			
			//head�� ���� ��带 ����Ű���� ������Ʈ
			head = nextNode;
			size--;
			
			if(size==0) {
				tail = null;
			}
			return element;
		}
	
		@Override
		public E remove(int index) {
			
			if(index == 0) {
				return remove();
			}
			
			if(index >= size || index < 0) {
				throw new NoSuchElementException();
			}
			Node<E> prevNode = search(index-1);
			Node<E> removedNode = prevNode.next;
			Node<E> nextNode = removedNode.next;
			
			E element = removedNode.data; //�����Ǵ� ����� �����͸� ��ȯ�ϱ� ���� �ӽ� ����
			
			//���� ��尡 ����Ű�� ��带 �����Ϸ��� ����� �������� ����
			prevNode.next = nextNode;
			
			//���� �����ߴ� ��尡 ������ ����� tail�� prevNode�� ����
			if(prevNode.next == null) {
				tail = prevNode;
			}
			//������ ����
			removedNode.next = null;
			removedNode.data = null;
			size--;
			return element;
		}
		
		@Override
		public boolean remove(Object value) {
			
			Node<E> prevNode = head;
			boolean hasValue = false;
			Node<E> x = head; //removedNode

			//value�� ��ġ�ϴ� ��带 ã��
			for(;x!=null;x = x.next) {
				if(value.equals(x.data)) {
					hasValue = true;
					break;
				}
				prevNode = x;
			}
			
			if(x == null) {
				return false;
			}
			
			//�����Ϸ��� ��尡 head��� ���� remove()���
			if(x.equals(head)) {
				remove();
				return true;
			}else {
				//���� ����� ��ũ�� �����Ϸ��� ����� ���� ���� ����
				prevNode.next = x.next;
				
				if(prevNode.next == null) {
					tail = prevNode;
				}
				x.data = null;
				x.next = null;
				size--;
			}
			return true;
		}
		@Override
		public E get(int index) {
			return search(index).data;
		}
		//set : ���� index�� ��ġ�� �����͸� ���ο� �����ͷ� ��ü
		public void set(int index, E value) {
			Node<E> replaceNode = search(index); //ã�� �ε����� ���
			replaceNode.data = null;
			replaceNode.data = value;
		}
		
		//indexOf : ����ϰ��� �ϴ� ����� �ε����� ��ȯ
		//������ -1 ��ȯ
 		@Override
 		public int indexOf(Object value) {
 			int index = 0;
 			for(Node<E> x = head; x!= null; x = x.next) {
 				if(value.equals(x.data)) {
 					return index;
 				}
 				index++;
 			}
 			return -1;
 		}
		//contains : ã�����ϴ� ��Ұ� �����ϴ��� ���ϴ��� ��ȯ
 		//������ true, ������ false
 		@Override
 		public boolean contains(Object item) {
 			return indexOf(item) >=0; //���� boolean�� �̷��� ǥ���ϳ�
 		}
 		
 		@Override
 		public int size() {
 			return size;
 		}
		
 		//size == 0 => true / size != 0 => false
 		@Override
 		public boolean isEmpty() {
 			return size == 0;
 		}
 		
 		@Override
 		public void clear() {
 			for(Node<E> x = head; x != null;) {
 				Node<E> nextNode = x.next;
 				x.data = null;
 				x.next = null;
 				x = nextNode;
 			}
 			head = tail = null;
 			size = 0;
 		}
 		//��������� �⺻���� �޼ҵ�
 		
 		/*CLONE*/
 		// '='�� ��ü�� �����ϸ� �ּҸ� �����ϴ� ���̹Ƿ�, ������ ��ü���� ������ ������ �� ���� ��ü���� ������ ��ħ
 		//�̷��� ������ �������� �ʿ��� ���� clone() -> ���� �����ڰ� protected�� �Ǿ�����
 		
 		
		//https://st-lab.tistory.com/167
}
