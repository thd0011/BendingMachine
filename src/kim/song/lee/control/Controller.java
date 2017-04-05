package kim.song.lee.control;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Thread.State;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import kim.song.lee.model.Model;
import kim.song.lee.view.BackView;
import kim.song.lee.view.MainView;


public class Controller implements ActionListener, Runnable, MouseListener {

	MainView main;
	BackView back;
	Model model;
	String str = "";
	Thread t;
	Point lap, varp, horp;
	int x = 0, y = 0;
	int varx, vary, lax, lay;
	int index_la;
	boolean billflag = false, coinflag = false, returnflag = false;

	public Controller() { //생성자
		
		main = new MainView();
		model = new Model();
		back = new BackView();
		labelchack();
		for (int i = 0; i < 17; i++) {
			if (model.itemCheck(i + 1) == 1) {
				main.la2[i].setVisible(false);
			}
			if (model.itemCheck(i + 1) == 0) {
				main.la[i].setVisible(false);
				main.la2[i].setVisible(false);
			}
		}
		eventUp();
	}

	//이벤트 등록 메소드
	public void eventUp() {

		// 0~11까지 12개버튼 9:취소버튼 11:확인버튼
		for (int i = 0; i < main.bt.length; i++) {
			main.bt[i].addActionListener(this);
		}

		main.bill1_la.addMouseListener(this);
		main.bill2_la.addMouseListener(this);
		main.coinLa1_la.addMouseListener(this);
		main.coinLa2_la.addMouseListener(this);
		main.coinSm1_la.addMouseListener(this);
		main.coinSm2_la.addMouseListener(this);

		main.bill_bt.addActionListener(this); // 지폐투입구
		main.coinIn_bt.addActionListener(this); // 동전투입구
		main.outPut_bt.addActionListener(this); // 음료배출구
		main.admin_bt.addActionListener(this); // 관리자모드
		main.coinOut_bt.addActionListener(this); // 동전배출구
		main.coinRe_bt.addActionListener(this); // 돈 반환버튼

		back.admin2_bt.addActionListener(this); // 사용자모드
		back.moneyClear_bt.addActionListener(this);

		for (int i = 0; i < back.nums_bt.length; i++) {
			back.nums_bt[i].addActionListener(this);
		}
	}
	
	//잔돈이 있는지 없는지 확인하는 메소드
	public void labelchack(){
		int arr[] = new int[4];
		arr = model.moneyAlert();
		for(int i=0;i<main.display_la3.length-1;i++){
			System.out.println(arr[i]);
			if(arr[i]==1){
				main.display_la3[i].setForeground(new Color(240, 0, 0));
			}
			else{
				main.display_la3[i].setForeground(new Color(152, 0, 0));
			}
		}
		if(arr[0]+arr[1]+arr[2]!=0){
			main.display_la3[3].setForeground(new Color(240, 0, 0));
		}
		else{
			main.display_la3[3].setForeground(new Color(152, 0, 0));
		}
		System.out.println();
	}

	//기계가 작동하는 스레드 함수
	public void run() { 
		try {
			while ((vary + y) != lay || (varx + x) != lax) {
				Thread.sleep(50);// 0.05초

				if (vary + y > lay) {
					y -= 5;
				} else if (varx + x < lax) {
					x += 5;
				}

				main.movingVer_la1.setLocation(varp.x + x, varp.y + y);
				main.movingVer_la2.setLocation(varp.x + x, varp.y + y);
				main.movingHor_la.setLocation(horp.x, horp.y + y);
			}
			main.movingVer_la1.setVisible(false);
			main.movingVer_la2.setVisible(true);
			Thread.sleep(500);

			while (x != 0 || y != 0) {
				Thread.sleep(50);// 0.05초

				if (varx + x > varx) {
					x -= 5;
				} else if (vary + y < vary) {
					y += 5;
				}
				main.movingVer_la1.setLocation(varp.x + x, varp.y + y);
				main.movingVer_la2.setLocation(varp.x + x, varp.y + y);
				main.movingHor_la.setLocation(horp.x, horp.y + y);
				main.la[index_la].setLocation((varx - main.la[index_la].getWidth() / 2) + x,
						(varp.y - main.la[index_la].getHeight() / 2) + y);
			}
			main.la[index_la].setLocation(lap);
			main.click_la.setVisible(true);
			main.movingVer_la1.setVisible(true);
			main.movingVer_la2.setVisible(false);
			for (int i = 0; i < 17; i++) {
				if (model.itemCheck(i + 1) == 0) {
					main.la[i].setVisible(false);
				}
			}
			main.displayNum_la.setText("0번");
		} catch (InterruptedException e) {
		}
	}

	/*관리자와 사용자 버튼의 모든 이벤트들*/
	public void actionPerformed(ActionEvent e) { 
		JButton b = (JButton) e.getSource();

		//사용자 - 취소 버튼을 눌렀을 때
		if (b.getText().equals("취소")) { 
			str = "";
			main.displayNum_la.setText("0번");
		} //사용자 - 취소 버튼
		
		//사용자 - 확인 버튼을 눌렀을 때
		else if (b.getText().equals("확인")) {
			if (str == "") { // change!! : 처음키자마자 확인 눌렀을때 에러

			}
			else if (Integer.parseInt(str) > 17 || Integer.parseInt(str) == 0) {
				// 18이상 0일떄
				return;
			} 
			else if (model.sum < model.itemCost[Integer.parseInt(str) - 1]) {
				// 금액초과
				JOptionPane.showMessageDialog(main, "돈을 넣어주세요!!");
			} 
			else if (model.itemCheck(Integer.parseInt(str)) == 0) { // 재고소진
				JOptionPane.showMessageDialog(main, "재고없음!!");
			}
			else { // 정상작동
				model.itemOut(Integer.parseInt(str));
				for (int i = 0; i < 17; i++) {
					if (model.itemCheck(i + 1) == 0) {
						main.la2[i].setVisible(false);
					}
				}
				main.displayCost_la.setText(model.sum + "원");

				main.display_la1.setText("<html>금액투입 >> 번호선택 >> <font size=\"2.5\" color=\"red\">결    정</font></html>");
				index_la = Integer.parseInt(str) - 1;
				lap = main.la[index_la].getLocation();
				lax = lap.x + main.la[index_la].getWidth() / 2;
				lay = lap.y + main.la[index_la].getHeight() / 2;

				varp = main.movingVer_la1.getLocation();
				varx = varp.x + main.movingVer_la1.getWidth() / 2;
				vary = varp.y;

				horp = main.movingHor_la.getLocation();
				if (t == null || t.getState() == State.TERMINATED) {
					t = new Thread(this);
					t.start();
				}
			}
		} //사용자 - 확인 버튼
		
		// 사용자 - 돈 반환버튼 눌렀을 시
		else if (e.getSource() == main.coinRe_bt) {
			int returnArr[] = new int[3];
			returnArr = model.returnMoney();
			main.displayNum_la.setText("잔돈 " + (returnArr[0] * 1000 + returnArr[1] * 500 + returnArr[2] * 100) + "원");
			returnflag=true;
			labelchack();
		}// 사용자 - 돈 반환버튼
		
		//사용자 -지폐투입구 눌렀을때
		else if (e.getSource() == main.bill_bt) { 
			if (billflag == false) {
				main.bill1_la.setVisible(true);
				billflag = true;
			} else {
				billflag = false;
				main.bill1_la.setVisible(false);
			}
		}//사용자 -지폐투입구
		
		//사용자 - 동전투입구 눌렀을 때
		else if (e.getSource() == main.coinIn_bt) {
			if (coinflag == false) {//동전 이미지 표시
				coinflag = true;
				main.coinLa1_la.setVisible(true); // 500원
				main.coinSm1_la.setVisible(true); // 100원
			} else { //동전 이미지 미표시
				coinflag = false;
				main.coinLa1_la.setVisible(false); // 500원
				main.coinSm1_la.setVisible(false); // 100원
			}
		}//사용자 - 동전투입구
		
		//사용자 - 음료투입구 눌렀을 때 
		else if (e.getSource() == main.outPut_bt) {
			if(main.click_la.isVisible() == true){
				JOptionPane.showMessageDialog(main, str + "번 획득");
				str = "";
				main.click_la.setVisible(false);
			}
		} //사용자 - 음료투입구
		
		// 관리자 버튼 클릭
		else if (e.getSource() == main.admin_bt) { 
			String pwd = JOptionPane.showInputDialog("비밀번호 입력");
			if ("1234".equals(pwd)) { //비밀번호가 맞을 때
				main.setVisible(false);
				back.setVisible(true);
				String[] strarr = new String[17];
				strarr = model.adminItemCount();
				for (int i = 0; i < back.nums.length; i++) {
					back.nums[i].setText(strarr[i]);
				}
				for (int i = 0; i < 17; i++) {
					if (model.itemCheck(i + 1) == 0) {
						back.la3[i].setVisible(false);
					}
				}

			} 
			else { //비밀번호가 틀릴 때
				JOptionPane.showMessageDialog(main, "비밀번호가 다릅니다");
			}
		} // 관리자 버튼 클릭
		
		 // 동전 배출구 버튼 클릭
		else if (e.getSource() == main.coinOut_bt) {
			if(returnflag==true){
				main.displayNum_la.setText("0번");
				main.displayCost_la.setText("돈을 넣어주세요");
			}
			else{
				JOptionPane.showMessageDialog(main, "돈이 없습니다!!");
			}
		}  // 동전 배출구 버튼
		
		// 관리자->사용자로 넘어갈 때 
		else if (e.getSource() == back.admin2_bt) { 
			model.adminClose(model.itemplus);
			for (int i = 0; i < 17; i++) {
				if (model.itemCheck(i + 1) > 1) {
					main.la[i].setVisible(true);
					main.la2[i].setVisible(true);
				} else if (model.itemCheck(i + 1) == 1){
					main.la[i].setVisible(true);
				}
			}
			main.setVisible(true);
			back.setVisible(false);
		} 	// 관리자->사용자로 넘어갈 때 
		
		//관리자 - 돈받기 버튼 클릭할 때
		else if (e.getSource() == back.moneyClear_bt) {
				model.adminManagement();
				JOptionPane.showMessageDialog(back, "정산되었습니다.");
				returnflag=false;
		} //관리자 - 돈받기 버튼 클릭
		
		else { // 사용자 버튼과 입력할때와 관리자 재고 증가버튼 클릭 할 때
			if (main.isVisible() == true) { //사용자 번호 버튼클릭
				main.display_la1.setText("<html>금액투입 >> <font size=\"2.5\" color=\"red\">번호선택</font> >> 결    정</html>");
				if (str.length() < 2) {
					str += b.getText();
					if (Integer.parseInt(str) <= 17&&Integer.parseInt(str) > 0) {
						main.displayNum_la.setText(str + "번 가격 : " + model.itemCost[Integer.parseInt(str) - 1] + "원");
					} else {
						main.displayNum_la.setText(str + "번 가격 : 번호 없음!!");
					}
				}
			} else { // 관리자 재고 증가 버튼클릭
				int index = Integer.parseInt(b.getText()) - 1;
				model.itemplus[index]++;
				int intarr[] = new int[17];
				intarr = model.adminItemCount2();
				back.nums[index].setText(String.valueOf(intarr[index] + model.itemplus[index]));
				for (int i = 0; i < 17; i++) {
					if (model.itemplus[i] >= 1) {
						back.la3[i].setVisible(true);
					}
				}
			}
		}// 사용자 버튼과 입력할때와 관리자 재고 증가버튼 클릭
	}

	public static void main(String[] args) {
		new Controller();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) { // 기존상태

	}

	@Override
	public void mousePressed(MouseEvent e) { //마우스를 눌렀을때
		Object ob = e.getSource();
		if (ob == main.bill1_la) {
			main.bill1_la.setVisible(false);
			main.bill2_la.setVisible(true);
		} else if (ob == main.coinLa1_la) {
			main.coinLa1_la.setVisible(false);
			main.coinLa2_la.setVisible(true);
		} else if (ob == main.coinSm1_la) {
			main.coinSm1_la.setVisible(false);
			main.coinSm2_la.setVisible(true);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) { //마우스를 때었을 때
		Object ob = e.getSource();
		if (ob == main.bill1_la) { // 지폐
			main.bill1_la.setVisible(true);
			main.bill2_la.setVisible(false);
			model.moneyInsert(0);
		} else if (ob == main.coinLa1_la) { // 500
			main.coinLa1_la.setVisible(true);
			main.coinLa2_la.setVisible(false);
			model.moneyInsert(1);
		} else if (ob == main.coinSm1_la) { // 100
			main.coinSm1_la.setVisible(true);
			main.coinSm2_la.setVisible(false);
			model.moneyInsert(2);
		}
		labelchack();
		main.displayCost_la.setText(model.sum + "원");
	}

}
