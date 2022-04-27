package LinkedList;
//E : (Generic) 클래스 E를 받을 수 있음
//컬렉션 클래스와 같이 배열 기반으로 된 구조에는 E가 어울림
public class SLinkedList<E> implements List<E> {
	//클래스 및 생성자 구상
	private Node<E> head; //노드의 첫 부분
	private Node<E> tail; //노드의 마지막 부분
	private int size; // 요소 개수
	
	public SLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public static void main(String[] args) {
		SLinkedList<Integer> list = new SLinkedList<>();
		
		
	}
	//search 메소드
	private Node<E> search(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> x = head;
		
		for (int i = 0; i < index; i++) {
			x = x.next; //x노드의 다음 노드를 x에 저장
		}
		return x;
	}
	//add 메소드
	public void addFirst(E value) {
		Node<E> newNode = new Node<E>(value); //새 노드 생성
		newNode.next = head; //새 노드의 다음 노드로 head 노드를 연결
		head = newNode; //head가 가리키는 노드를 새 노드로 변경
		size++;
		
		//다음에 가리킬 노드가 없는 경우 새 노드는 처음이자 마지막 노드
		if(head.next == null) {
			tail = head;
		}
	}
	
	//add() = addLast()
	//새로운 노드 생성하고 이전 노드의 레퍼런스 변수가 새로운 노드를 가리키게 해줌
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value); //새 노드 생성
		
		if(size == 0 ) { //처음 넣는 노드일 경우 addFisrt로 추가
			addFirst(value);
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	@Override
	public void add(int index, E value) {
		
		//잘못된 인덱스를 참조할 경우 예외 발생
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//추가하려는  index가 가장 앞에 추가될 경우 addFisrt 호출
		if(index == 0) {
			addFirst(value);
			return;
		}
		//추가하려는 index가 맨 뒤인 경우 addLast 호출
		if(index == size) {
			addLast(value);
			return;
		}
		
		//추가하려는 위치의 이전 노드
		Node<E> prev_Node = search(index-1);
		
		//추가하려는 위치의 노드
		Node<E> next_Node = prev_Node.next;
		
		//추가하려는 노드
		Node<E> newNode = new Node<E>(value);
		
		
		/* 이전 노드가 가리키는 노드를 끊음
		 * 새 노드로 변경
		 * 새 노드가 가리키는 도느느 next_Node로 설정
		 */
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
		size++;
		//https://st-lab.tistory.com/167
	}
}
