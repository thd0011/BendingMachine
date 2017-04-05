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

	public Controller() { //������
		
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

	//�̺�Ʈ ��� �޼ҵ�
	public void eventUp() {

		// 0~11���� 12����ư 9:��ҹ�ư 11:Ȯ�ι�ư
		for (int i = 0; i < main.bt.length; i++) {
			main.bt[i].addActionListener(this);
		}

		main.bill1_la.addMouseListener(this);
		main.bill2_la.addMouseListener(this);
		main.coinLa1_la.addMouseListener(this);
		main.coinLa2_la.addMouseListener(this);
		main.coinSm1_la.addMouseListener(this);
		main.coinSm2_la.addMouseListener(this);

		main.bill_bt.addActionListener(this); // �������Ա�
		main.coinIn_bt.addActionListener(this); // �������Ա�
		main.outPut_bt.addActionListener(this); // ������ⱸ
		main.admin_bt.addActionListener(this); // �����ڸ��
		main.coinOut_bt.addActionListener(this); // �������ⱸ
		main.coinRe_bt.addActionListener(this); // �� ��ȯ��ư

		back.admin2_bt.addActionListener(this); // ����ڸ��
		back.moneyClear_bt.addActionListener(this);

		for (int i = 0; i < back.nums_bt.length; i++) {
			back.nums_bt[i].addActionListener(this);
		}
	}
	
	//�ܵ��� �ִ��� ������ Ȯ���ϴ� �޼ҵ�
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

	//��谡 �۵��ϴ� ������ �Լ�
	public void run() { 
		try {
			while ((vary + y) != lay || (varx + x) != lax) {
				Thread.sleep(50);// 0.05��

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
				Thread.sleep(50);// 0.05��

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
			main.displayNum_la.setText("0��");
		} catch (InterruptedException e) {
		}
	}

	/*�����ڿ� ����� ��ư�� ��� �̺�Ʈ��*/
	public void actionPerformed(ActionEvent e) { 
		JButton b = (JButton) e.getSource();

		//����� - ��� ��ư�� ������ ��
		if (b.getText().equals("���")) { 
			str = "";
			main.displayNum_la.setText("0��");
		} //����� - ��� ��ư
		
		//����� - Ȯ�� ��ư�� ������ ��
		else if (b.getText().equals("Ȯ��")) {
			if (str == "") { // change!! : ó��Ű�ڸ��� Ȯ�� �������� ����

			}
			else if (Integer.parseInt(str) > 17 || Integer.parseInt(str) == 0) {
				// 18�̻� 0�ϋ�
				return;
			} 
			else if (model.sum < model.itemCost[Integer.parseInt(str) - 1]) {
				// �ݾ��ʰ�
				JOptionPane.showMessageDialog(main, "���� �־��ּ���!!");
			} 
			else if (model.itemCheck(Integer.parseInt(str)) == 0) { // ������
				JOptionPane.showMessageDialog(main, "������!!");
			}
			else { // �����۵�
				model.itemOut(Integer.parseInt(str));
				for (int i = 0; i < 17; i++) {
					if (model.itemCheck(i + 1) == 0) {
						main.la2[i].setVisible(false);
					}
				}
				main.displayCost_la.setText(model.sum + "��");

				main.display_la1.setText("<html>�ݾ����� >> ��ȣ���� >> <font size=\"2.5\" color=\"red\">��    ��</font></html>");
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
		} //����� - Ȯ�� ��ư
		
		// ����� - �� ��ȯ��ư ������ ��
		else if (e.getSource() == main.coinRe_bt) {
			int returnArr[] = new int[3];
			returnArr = model.returnMoney();
			main.displayNum_la.setText("�ܵ� " + (returnArr[0] * 1000 + returnArr[1] * 500 + returnArr[2] * 100) + "��");
			returnflag=true;
			labelchack();
		}// ����� - �� ��ȯ��ư
		
		//����� -�������Ա� ��������
		else if (e.getSource() == main.bill_bt) { 
			if (billflag == false) {
				main.bill1_la.setVisible(true);
				billflag = true;
			} else {
				billflag = false;
				main.bill1_la.setVisible(false);
			}
		}//����� -�������Ա�
		
		//����� - �������Ա� ������ ��
		else if (e.getSource() == main.coinIn_bt) {
			if (coinflag == false) {//���� �̹��� ǥ��
				coinflag = true;
				main.coinLa1_la.setVisible(true); // 500��
				main.coinSm1_la.setVisible(true); // 100��
			} else { //���� �̹��� ��ǥ��
				coinflag = false;
				main.coinLa1_la.setVisible(false); // 500��
				main.coinSm1_la.setVisible(false); // 100��
			}
		}//����� - �������Ա�
		
		//����� - �������Ա� ������ �� 
		else if (e.getSource() == main.outPut_bt) {
			if(main.click_la.isVisible() == true){
				JOptionPane.showMessageDialog(main, str + "�� ȹ��");
				str = "";
				main.click_la.setVisible(false);
			}
		} //����� - �������Ա�
		
		// ������ ��ư Ŭ��
		else if (e.getSource() == main.admin_bt) { 
			String pwd = JOptionPane.showInputDialog("��й�ȣ �Է�");
			if ("1234".equals(pwd)) { //��й�ȣ�� ���� ��
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
			else { //��й�ȣ�� Ʋ�� ��
				JOptionPane.showMessageDialog(main, "��й�ȣ�� �ٸ��ϴ�");
			}
		} // ������ ��ư Ŭ��
		
		 // ���� ���ⱸ ��ư Ŭ��
		else if (e.getSource() == main.coinOut_bt) {
			if(returnflag==true){
				main.displayNum_la.setText("0��");
				main.displayCost_la.setText("���� �־��ּ���");
			}
			else{
				JOptionPane.showMessageDialog(main, "���� �����ϴ�!!");
			}
		}  // ���� ���ⱸ ��ư
		
		// ������->����ڷ� �Ѿ �� 
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
		} 	// ������->����ڷ� �Ѿ �� 
		
		//������ - ���ޱ� ��ư Ŭ���� ��
		else if (e.getSource() == back.moneyClear_bt) {
				model.adminManagement();
				JOptionPane.showMessageDialog(back, "����Ǿ����ϴ�.");
				returnflag=false;
		} //������ - ���ޱ� ��ư Ŭ��
		
		else { // ����� ��ư�� �Է��Ҷ��� ������ ��� ������ư Ŭ�� �� ��
			if (main.isVisible() == true) { //����� ��ȣ ��ưŬ��
				main.display_la1.setText("<html>�ݾ����� >> <font size=\"2.5\" color=\"red\">��ȣ����</font> >> ��    ��</html>");
				if (str.length() < 2) {
					str += b.getText();
					if (Integer.parseInt(str) <= 17&&Integer.parseInt(str) > 0) {
						main.displayNum_la.setText(str + "�� ���� : " + model.itemCost[Integer.parseInt(str) - 1] + "��");
					} else {
						main.displayNum_la.setText(str + "�� ���� : ��ȣ ����!!");
					}
				}
			} else { // ������ ��� ���� ��ưŬ��
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
		}// ����� ��ư�� �Է��Ҷ��� ������ ��� ������ư Ŭ��
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
	public void mouseExited(MouseEvent e) { // ��������

	}

	@Override
	public void mousePressed(MouseEvent e) { //���콺�� ��������
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
	public void mouseReleased(MouseEvent e) { //���콺�� ������ ��
		Object ob = e.getSource();
		if (ob == main.bill1_la) { // ����
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
		main.displayCost_la.setText(model.sum + "��");
	}

}
