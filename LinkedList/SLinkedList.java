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
	
	//가장 앞에있는 요소 제거
		public E remove() {
			
			Node<E> headNode = head;
			if(headNode == null) {
				throw new NoSuchElementException();
			}
			//삭제된 노드를 반환하기 위한 임시 변수
			E element = headNode.data;
			//head의 다음 노드
			Node<E> nextNode = head.next;
			
			head.data = null;
			head.next = null;
			
			//head가 다음 노드를 가리키도록 업데이트
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
			
			E element = removedNode.data; //삭제되는 노드의 데이터를 반환하기 위한 임시 변수
			
			//이전 노드가 가리키는 노드를 삭제하려는 노드의 다음노드로 변경
			prevNode.next = nextNode;
			
			//만약 삭제했던 노드가 마지막 노드라면 tail을 prevNode로 갱신
			if(prevNode.next == null) {
				tail = prevNode;
			}
			//데이터 삭제
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

			//value와 일치하는 노드를 찾음
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
			
			//삭제하려는 노드가 head라면 기존 remove()사용
			if(x.equals(head)) {
				remove();
				return true;
			}else {
				//이전 노드의 링크를 삭제하려는 노드의 다음 노드로 연결
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
		//set : 기존 index에 위치한 데이터를 새로운 데이터로 교체
		public void set(int index, E value) {
			Node<E> replaceNode = search(index); //찾는 인덱스의 노드
			replaceNode.data = null;
			replaceNode.data = value;
		}
		
		//indexOf : 사용하고자 하는 요소의 인덱스를 반환
		//없으면 -1 반환
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
		//contains : 찾고자하는 요소가 존재하는지 안하는지 반환
 		//있으면 true, 없으면 false
 		@Override
 		public boolean contains(Object item) {
 			return indexOf(item) >=0; //ㅁㅊ boolean을 이렇게 표현하네
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
 		//여기까지가 기본적인 메소드
 		
 		/*CLONE*/
 		// '='로 객체를 복사하면 주소를 복사하는 것이므로, 복사한 객체에서 데이터 조작할 시 원본 객체까지 영향을 미침
 		//이러한 문제를 막기위해 필요한 것이 clone() -> 접근 제어자가 protected로 되어있음
 		
 		
		//https://st-lab.tistory.com/167
}
