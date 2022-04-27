package LinkedList;
//E : (Generic) Ŭ���� E�� ���� �� ����
//�÷��� Ŭ������ ���� �迭 ������� �� �������� E�� ��︲
public class SLinkedList<E> implements List<E> {
	//Ŭ���� �� ������ ����
	private Node<E> head; //����� ù �κ�
	private Node<E> tail; //����� ������ �κ�
	private int size; // ��� ����
	
	public SLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	public static void main(String[] args) {
		SLinkedList<Integer> list = new SLinkedList<>();
		
		
	}
	//search �޼ҵ�
	private Node<E> search(int index){
		if(index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node<E> x = head;
		
		for (int i = 0; i < index; i++) {
			x = x.next; //x����� ���� ��带 x�� ����
		}
		return x;
	}
	//add �޼ҵ�
	public void addFirst(E value) {
		Node<E> newNode = new Node<E>(value); //�� ��� ����
		newNode.next = head; //�� ����� ���� ���� head ��带 ����
		head = newNode; //head�� ����Ű�� ��带 �� ���� ����
		size++;
		
		//������ ����ų ��尡 ���� ��� �� ���� ó������ ������ ���
		if(head.next == null) {
			tail = head;
		}
	}
	
	//add() = addLast()
	//���ο� ��� �����ϰ� ���� ����� ���۷��� ������ ���ο� ��带 ����Ű�� ����
	
	@Override
	public boolean add(E value) {
		addLast(value);
		return true;
	}
	public void addLast(E value) {
		Node<E> newNode = new Node<E>(value); //�� ��� ����
		
		if(size == 0 ) { //ó�� �ִ� ����� ��� addFisrt�� �߰�
			addFirst(value);
			return;
		}
		tail.next = newNode;
		tail = newNode;
		size++;
	}
	
	@Override
	public void add(int index, E value) {
		
		//�߸��� �ε����� ������ ��� ���� �߻�
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//�߰��Ϸ���  index�� ���� �տ� �߰��� ��� addFisrt ȣ��
		if(index == 0) {
			addFirst(value);
			return;
		}
		//�߰��Ϸ��� index�� �� ���� ��� addLast ȣ��
		if(index == size) {
			addLast(value);
			return;
		}
		
		//�߰��Ϸ��� ��ġ�� ���� ���
		Node<E> prev_Node = search(index-1);
		
		//�߰��Ϸ��� ��ġ�� ���
		Node<E> next_Node = prev_Node.next;
		
		//�߰��Ϸ��� ���
		Node<E> newNode = new Node<E>(value);
		
		
		/* ���� ��尡 ����Ű�� ��带 ����
		 * �� ���� ����
		 * �� ��尡 ����Ű�� ������ next_Node�� ����
		 */
		prev_Node.next = null;
		prev_Node.next = newNode;
		newNode.next = next_Node;
		size++;
		//https://st-lab.tistory.com/167
	}
}
