package Stack;

import java.util.Stack;

public class StackEx1 {
	public static Stack back = new Stack();
	public static Stack forward = new Stack();

	public static void main(String[] args) {
		goURL("1.네이트");
		goURL("2.야후");
		goURL("3.네이버");
		goURL("4.다음");
		
		printStatus();
		
		goBack();
		System.out.println("뒤로가기 버튼을 누른 후");
		printStatus(); //back.peek : 네이버 <- 4:pop, 사라짐 
		
		goBack();
		System.out.println("뒤로가기 버튼을 누른 후");
		printStatus(); //back.peek : 야흐 <- 3:pop, 사라짐 
		
		goForward();
		System.out.println("앞으로 버튼을 누른 후");
		printStatus(); //forward.peek : 네이버 <- 3:pop, 사라짐 
		
		goForward();
		System.out.println("앞으로 버튼을 누른 후");
		printStatus(); //원래대로 
	}
	
	public static void printStatus() {
		System.out.println("back: " + back); //반복문 안돌려도 요소 다 뽑아냄
		System.out.println("forward: " + forward);
		System.out.println("현재 화면은 : '" +back.peek()+"'입니다.");
		System.out.println();
	}
	
	public static void goURL(String url) {
		back.push(url);
		if(!forward.empty())
			forward.clear();
	}
	public static void goForward() {
		if(!forward.empty())
			back.push(forward.pop());
	}
	public static void goBack() {
		if(!back.empty())
			forward.push(back.pop());
	}
	
}
