package kim.song.lee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 �� ����� �����ӽ� ������Ʈ ��
  - MainView�Դϴ�.
  - Image�� �ҷ��ͼ� Label�� ����־����� �������ּ���.
*/

public class MainView extends JFrame {

	public JLabel display_la1, displayNum_la, displayCost_la;
	public JLabel la[], la2[], display_la3[];
	JLabel num1, num2, num3, num4, num5, num6, num7, num8, num9, num10, num11, num12, num13, num14, num15, num16, num17;
	public JLabel bill1_la, bill2_la, coinSm1_la, coinSm2_la, coinLa1_la, coinLa2_la, click_la;
	public JLabel movingVer_la1, movingVer_la2, movingHor_la;
	public JButton bill_bt, coinRe_bt, coinIn_bt, admin_bt, coinOut_bt, outPut_bt;
	public JButton bt[];
	JPanel main_pn, windowfront_pn, windowback_pn, bar_pn0, bar_pn1, bar_pn2, bar_pn3, bar_pn4;
	JPanel buttonIn_pn, buttonOut_pn, display_pn, displayIn_pn, display_la3p;
	Font cancle_ft, result_ft, font;

	public MainView() {
		la = new JLabel[17];
		la2 = new JLabel[17];
		
		bt = new JButton[12];
		main_pn = new JPanel(); // ���� �ٱ� �ʷϻ����
		display_pn = new JPanel(); // �ݾ��� �ߴ� ���� ȭ��
		displayIn_pn = new JPanel(); // �ݾ��� �ߴ� ���� ȭ�� �ȿ� �߰� �ǳ�
		buttonIn_pn = new JPanel(); // 0~9 ��ȣ������ �ǳ�
		buttonOut_pn = new JPanel(); // ��ȣ �ǳ� �ٱ� �ǳ�

		imageGenerate(); // JLabel�� ImageIcon�� �־� image����
		buttonGenerate(); // ��ȣ ��ư ����
		windowGenerate(); // ��ĳ�̽� ���� �� ��ǰ �ο�
		moneyGenerate(); // �� �̹��� ����

		// DisplayPanel - ������ �� ȭ��
		display_la1 = new JLabel("<html><font size=\"2.5\" color=\"red\">�ݾ�����</font> >> ��ȣ���� >> ��    ��</html>",
				JLabel.CENTER);
		displayCost_la = new JLabel("���� �־��ּ���", JLabel.CENTER);
		
		display_la3 = new JLabel[4];
		display_la3[0] = new JLabel("1000��", JLabel.CENTER);
		display_la3[1] = new JLabel("500��", JLabel.CENTER);
		display_la3[2] = new JLabel("100��", JLabel.CENTER);
		display_la3[3] = new JLabel("�ܰ����", JLabel.CENTER);
		display_la3p = new JPanel();
		display_la3p.setLayout(new FlowLayout());
		display_la3p.add(display_la3[0]);
		display_la3p.add(display_la3[1]);
		display_la3p.add(display_la3[2]);
		display_la3p.add(display_la3[3]);
		display_la3p.setBackground(Color.BLACK);
		
		displayNum_la = new JLabel("0��", JLabel.CENTER);
		display_la1.setForeground(new Color(152, 0, 0));
		displayCost_la.setForeground(new Color(240, 0, 0));
		
		display_la3[0].setForeground(new Color(152, 0, 0));
		display_la3[1].setForeground(new Color(152, 0, 0));
		display_la3[2].setForeground(new Color(152, 0, 0));
		display_la3[3].setForeground(new Color(152, 0, 0));
		displayNum_la.setForeground(new Color(240, 0, 0));
		
		font = new Font("����", Font.PLAIN, 15);
		displayCost_la.setFont(font);
		displayNum_la.setFont(font);
		

		displayIn_pn.setLayout(new GridLayout(2,1));
		displayIn_pn.setBackground(Color.BLACK);
		displayIn_pn.add(displayNum_la);
		displayIn_pn.add(displayCost_la);

		display_pn.setLayout(new BorderLayout());
		display_pn.setBackground(Color.BLACK);
		display_pn.setBounds(460, 50, 200, 80);
		display_pn.add("North", display_la1);
		display_pn.add("Center", displayIn_pn);
		display_pn.add("South", display_la3p);

		// ButtonPanel - ���ù�ư
		buttonIn_pn.setLayout(new GridLayout(4, 3, 10, 10));
		buttonIn_pn.setBounds(450, 140, 200, 200);
		buttonIn_pn.setBackground(new Color(255, 255, 255));
		buttonOut_pn.setLayout(new BorderLayout());
		buttonOut_pn.setBounds(460, 150, 210, 210);
		buttonOut_pn.add("Center", buttonIn_pn);
		buttonOut_pn.add("North", new Panel());
		buttonOut_pn.add("South", new Panel());
		buttonOut_pn.add("East", new Panel());
		buttonOut_pn.add("West", new Panel());
		buttonOut_pn.setBackground(new Color(255, 255, 255));
		buttonOut_pn.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));

		// MainPanel - ���� �� ���� ū �ʷϻ�
		main_pn.setLayout(null);
		main_pn.setBounds(10, 10, 700, 800);
		main_pn.setBackground(new Color(16, 98, 10));
		main_pn.add(display_pn);
		main_pn.add(windowback_pn);
		main_pn.add(buttonOut_pn);
		main_pn.add(click_la);
		main_pn.add(outPut_bt);
		

		// Frame
		add(main_pn);
		setTitle("VendingMachine");
		setLayout(null);
		setBounds(700, 50, 740, 880);
		setBackground(Color.WHITE);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/*
	 * ���ô� �� �������� Visible(false)�� �Ұ� �� ���Ա��� ������ �� ����,������ Visible�� �Ǿ� Ŭ���� �����ϸ�
	 * Ŭ���� bill1->bill2�̹����� �ٲ��(�׼�ó�������ַ���)
	 */
	private void moneyGenerate() {
		Image rszIcon1 = new ImageIcon("image/bill1.png").getImage();
		bill1_la = new JLabel(new ImageIcon(rszIcon1.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));
		bill1_la.setVisible(false);
		;

		Image rszIcon2 = new ImageIcon("image/bill2.png").getImage();
		bill2_la = new JLabel(new ImageIcon(rszIcon2.getScaledInstance(100, 50, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon3 = new ImageIcon("image/coinSm1.png").getImage();
		coinSm1_la = new JLabel(new ImageIcon(rszIcon3.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		coinSm1_la.setVisible(false);

		Image rszIcon4 = new ImageIcon("image/coinSm2.png").getImage();
		coinSm2_la = new JLabel(new ImageIcon(rszIcon4.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon5 = new ImageIcon("image/coinLa1.png").getImage();
		coinLa1_la = new JLabel(new ImageIcon(rszIcon5.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		coinLa1_la.setVisible(false);

		Image rszIcon6 = new ImageIcon("image/coinLa2.png").getImage();
		coinLa2_la = new JLabel(new ImageIcon(rszIcon6.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));
		
		Image rszIcon7 = new ImageIcon("image/click.png").getImage();
		click_la = new JLabel(new ImageIcon(rszIcon7.getScaledInstance(130, 70, java.awt.Image.SCALE_SMOOTH)));

		bill1_la.setBounds(460, 410, 100, 50);
		bill2_la.setBounds(460, 410, 100, 50);
		coinSm1_la.setBounds(610, 410, 30, 30);
		coinSm2_la.setBounds(610, 410, 30, 30);
		coinLa1_la.setBounds(650, 410, 30, 30);
		coinLa2_la.setBounds(650, 410, 30, 30);
		click_la.setBounds(510, 550, 130, 70);
		click_la.setVisible(false);
		bill2_la.setVisible(false);
		coinSm2_la.setVisible(false);
		coinLa2_la.setVisible(false);

		main_pn.add(bill1_la);
		main_pn.add(bill2_la);
		main_pn.add(coinSm1_la);
		main_pn.add(coinSm2_la);
		main_pn.add(coinLa1_la);
		main_pn.add(coinLa2_la);
		
	}

	/* showcase �ǳ��� �ΰ� ���� �����ָ� ��(�������ִ�)�ǳڿ� �̹���ȭ �ߴ� Label�� �߰� */
	public void windowGenerate() {
		windowback_pn = new JPanel(); // �����ǳڰ� main�ǳ� ���� ��踦 ��Ÿ�������� �ǳ�
		windowfront_pn = new JPanel(); // �����ִ� �ǳ�
		bar_pn0 = new JPanel(); // ���� ��ħ�� 0
		bar_pn1 = new JPanel(); // ���� ��ħ�� 1
		bar_pn2 = new JPanel(); // ���� ��ħ�� 2
		bar_pn3 = new JPanel(); // ���� ��ħ�� 3
		bar_pn4 = new JPanel(); // ���� ��ħ�� 4

		Image rszIcon1 = new ImageIcon("image/num1.png").getImage();
		num1 = new JLabel(new ImageIcon(rszIcon1.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon2 = new ImageIcon("image/num2.png").getImage();
		num2 = new JLabel(new ImageIcon(rszIcon2.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon3 = new ImageIcon("image/num3.png").getImage();
		num3 = new JLabel(new ImageIcon(rszIcon3.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon4 = new ImageIcon("image/num4.png").getImage();
		num4 = new JLabel(new ImageIcon(rszIcon4.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon5 = new ImageIcon("image/num5.png").getImage();
		num5 = new JLabel(new ImageIcon(rszIcon5.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon6 = new ImageIcon("image/num6.png").getImage();
		num6 = new JLabel(new ImageIcon(rszIcon6.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon7 = new ImageIcon("image/num7.png").getImage();
		num7 = new JLabel(new ImageIcon(rszIcon7.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon8 = new ImageIcon("image/num8.png").getImage();
		num8 = new JLabel(new ImageIcon(rszIcon8.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon9 = new ImageIcon("image/num9.png").getImage();
		num9 = new JLabel(new ImageIcon(rszIcon9.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon10 = new ImageIcon("image/num10.png").getImage();
		num10 = new JLabel(new ImageIcon(rszIcon10.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon11 = new ImageIcon("image/num11.png").getImage();
		num11 = new JLabel(new ImageIcon(rszIcon11.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon12 = new ImageIcon("image/num12.png").getImage();
		num12 = new JLabel(new ImageIcon(rszIcon12.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon13 = new ImageIcon("image/num13.png").getImage();
		num13 = new JLabel(new ImageIcon(rszIcon13.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon14 = new ImageIcon("image/num14.png").getImage();
		num14 = new JLabel(new ImageIcon(rszIcon14.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon15 = new ImageIcon("image/num15.png").getImage();
		num15 = new JLabel(new ImageIcon(rszIcon15.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon16 = new ImageIcon("image/num16.png").getImage();
		num16 = new JLabel(new ImageIcon(rszIcon16.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon17 = new ImageIcon("image/num17.png").getImage();
		num17 = new JLabel(new ImageIcon(rszIcon17.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH)));

		num1.setBounds(35, 5, 30, 30);
		num2.setBounds(95, 5, 30, 30);
		num3.setBounds(155, 5, 30, 30);
		num4.setBounds(215, 5, 30, 30);
		num5.setBounds(275, 5, 30, 30);
		num6.setBounds(335, 5, 30, 30);
		num7.setBounds(55, 5, 30, 30);
		num8.setBounds(145, 5, 30, 30);
		num9.setBounds(235, 5, 30, 30);
		num10.setBounds(325, 5, 30, 30);
		num11.setBounds(75, 5, 30, 30);
		num12.setBounds(230, 5, 30, 30);
		num13.setBounds(330, 5, 30, 30);
		num14.setBounds(27, 5, 30, 30);
		num15.setBounds(128, 5, 30, 30);
		num16.setBounds(229, 5, 30, 30);
		num17.setBounds(330, 5, 30, 30);

		bar_pn0.setLayout(null);
		bar_pn1.setLayout(null);
		bar_pn2.setLayout(null);
		bar_pn3.setLayout(null);
		bar_pn0.add(num1);
		bar_pn0.add(num2);
		bar_pn0.add(num3);
		bar_pn0.add(num4);
		bar_pn0.add(num5);
		bar_pn0.add(num6);
		bar_pn1.add(num7);
		bar_pn1.add(num8);
		bar_pn1.add(num9);
		bar_pn1.add(num10);
		bar_pn2.add(num11);
		bar_pn2.add(num12);
		bar_pn2.add(num13);
		bar_pn3.add(num14);
		bar_pn3.add(num15);
		bar_pn3.add(num16);
		bar_pn3.add(num17);

		Image rszIcon01 = new ImageIcon("image/movingVer1.png").getImage();
		movingVer_la1 = new JLabel(new ImageIcon(rszIcon01.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH))); // ����
																														// �ű��
																														// �ǳ�(��������)
		Image rszIcon02 = new ImageIcon("image/movingVer2.png").getImage();
		movingVer_la2 = new JLabel(new ImageIcon(rszIcon02.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH))); // ����
																														// �ű��
																														// �ǳ�(��������)
		Image rszIcon03 = new ImageIcon("image/movingHor.png").getImage();
		movingHor_la = new JLabel(new ImageIcon(rszIcon03.getScaledInstance(400, 45, java.awt.Image.SCALE_SMOOTH))); // ����
																														// �ű��
																														// �ǳ�(���򹫺�)
		movingVer_la2.setVisible(false);
		movingVer_la1.setBounds(10, 600, 60, 60);
		movingVer_la2.setBounds(10, 600, 60, 60);
		movingHor_la.setBounds(0, 650, 400, 45);

		bar_pn0.setBounds(0, 0, 400, 40);
		bar_pn1.setBounds(0, 130, 400, 40);
		bar_pn2.setBounds(0, 300, 400, 40);
		bar_pn3.setBounds(0, 450, 400, 40);
		bar_pn4.setBounds(0, 650, 400, 40);
		bar_pn0.setBackground(Color.BLACK);
		bar_pn1.setBackground(Color.BLACK);
		bar_pn2.setBackground(Color.BLACK);
		bar_pn3.setBackground(Color.BLACK);
		bar_pn4.setBackground(Color.BLACK);

		windowback_pn.setLayout(null);
		windowback_pn.setBackground(new Color(0, 80, 0));
		windowback_pn.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		windowback_pn.setBounds(10, 10, 430, 750);
		windowback_pn.add(windowfront_pn);

		windowfront_pn.setLayout(null);
		windowfront_pn.setBackground(new Color(53, 53, 53));
		windowfront_pn.setBounds(15, 20, 400, 680);

		windowfront_pn.add(movingHor_la);
		windowfront_pn.add(movingVer_la1);
		windowfront_pn.add(movingVer_la2);
		windowfront_pn.add(bar_pn0);
		windowfront_pn.add(bar_pn1);
		windowfront_pn.add(bar_pn2);
		windowfront_pn.add(bar_pn3);
		windowfront_pn.add(bar_pn4);
		windowfront_pn.add(la[5]);
		windowfront_pn.add(la[4]);
		windowfront_pn.add(la[3]);
		windowfront_pn.add(la[2]);
		windowfront_pn.add(la[1]);
		windowfront_pn.add(la[0]);
		windowfront_pn.add(la[9]);
		windowfront_pn.add(la[8]);
		windowfront_pn.add(la[7]);
		windowfront_pn.add(la[6]);
		windowfront_pn.add(la[11]);
		windowfront_pn.add(la[12]);
		windowfront_pn.add(la[10]);
		windowfront_pn.add(la[16]);
		windowfront_pn.add(la[15]);
		windowfront_pn.add(la[14]);
		windowfront_pn.add(la[13]);
		windowfront_pn.add(la2[0]);
		windowfront_pn.add(la2[1]);
		windowfront_pn.add(la2[2]);
		windowfront_pn.add(la2[3]);
		windowfront_pn.add(la2[4]);
		windowfront_pn.add(la2[5]);
		windowfront_pn.add(la2[6]);
		windowfront_pn.add(la2[7]);
		windowfront_pn.add(la2[8]);
		windowfront_pn.add(la2[9]);
		windowfront_pn.add(la2[10]);
		windowfront_pn.add(la2[11]);
		windowfront_pn.add(la2[12]);
		windowfront_pn.add(la2[13]);
		windowfront_pn.add(la2[14]);
		windowfront_pn.add(la2[15]);
		windowfront_pn.add(la2[16]);

	}

	/* Panel���� ��ư ���� */
	public void buttonGenerate() {

		Image rszIcon1 = new ImageIcon("image/billIn.png").getImage();
		bill_bt = new JButton(new ImageIcon(rszIcon1.getScaledInstance(70, 20, java.awt.Image.SCALE_SMOOTH))); // �������Ա�

		Image rszIcon2 = new ImageIcon("image/coinRe.png").getImage();
		coinRe_bt = new JButton(new ImageIcon(rszIcon2.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH))); // ���ι�ȯ��ư

		Image rszIcon3 = new ImageIcon("image/coinIn.png").getImage();
		coinIn_bt = new JButton(new ImageIcon(rszIcon3.getScaledInstance(30, 30, java.awt.Image.SCALE_SMOOTH))); // �������Ա�

		coinOut_bt = new JButton(""); // ���ι��ⱸ
		admin_bt = new JButton(""); // �����ڹ�ư

		Image rszIcon6 = new ImageIcon("image/outPut.png").getImage();
		outPut_bt = new JButton(new ImageIcon(rszIcon6.getScaledInstance(150, 80, java.awt.Image.SCALE_SMOOTH))); // ������ⱸ

		main_pn.add(bill_bt);
		main_pn.add(coinRe_bt);
		main_pn.add(coinIn_bt);
		main_pn.add(coinOut_bt);
		main_pn.add(admin_bt);
		main_pn.add(outPut_bt);

		bill_bt.setBounds(460, 380, 70, 20);
		coinRe_bt.setBounds(580, 380, 30, 30);
		coinIn_bt.setBounds(630, 380, 30, 30);
		coinOut_bt.setBounds(600, 500, 40, 40);
		coinOut_bt.setBackground(new Color(33, 33, 33));
		admin_bt.setBounds(460, 480, 10, 50);
		outPut_bt.setBounds(500, 620, 150, 80);

		bt[0] = new JButton("1");
		bt[1] = new JButton("2");
		bt[2] = new JButton("3");
		bt[3] = new JButton("4");
		bt[4] = new JButton("5");
		bt[5] = new JButton("6");
		bt[6] = new JButton("7");
		bt[7] = new JButton("8");
		bt[8] = new JButton("9");
		cancle_ft = new Font("�������", Font.PLAIN, 10);
		bt[9] = new JButton("���");
		bt[9].setFont(cancle_ft);
		bt[10] = new JButton("0");
		result_ft = new Font("�������", Font.PLAIN, 10);
		bt[11] = new JButton("Ȯ��");
		bt[11].setFont(result_ft);

		buttonIn_pn.add(bt[0]);
		buttonIn_pn.add(bt[1]);
		buttonIn_pn.add(bt[2]);
		buttonIn_pn.add(bt[3]);
		buttonIn_pn.add(bt[4]);
		buttonIn_pn.add(bt[5]);
		buttonIn_pn.add(bt[6]);
		buttonIn_pn.add(bt[7]);
		buttonIn_pn.add(bt[8]);
		buttonIn_pn.add(bt[9]);
		buttonIn_pn.add(bt[10]);
		buttonIn_pn.add(bt[11]);

	}

	/*
	 * �����̽� �ȿ� ������ Image�� �����ֱ� ���Ͽ� image������ �ִ� image�� �ҷ��ͼ� JLabel�� ���� / 17���� ǰ��
	 */
	public void imageGenerate() {
		ImageIcon getIcon1 = new ImageIcon("image/beverag1.png");
		// ImageIcon Ŭ������ �̿��Ͽ� image�� �Ҿ����
		Image chgIcon1 = getIcon1.getImage(); // ImageIcon�� Image�� ��ȯ
		Image rszIcon1 = chgIcon1.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH);
		// Image���¿��� width, height ������ ����
		ImageIcon icon1 = new ImageIcon(rszIcon1); // �ٽ� Image�� ImageIcon���� ����
		la[0] = new JLabel(icon1); // JLabel�� icon�� ���� ����

		Image rszIcon2 = new ImageIcon("image/beverag2.png").getImage();
		la[1] = new JLabel(new ImageIcon(rszIcon2.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon3 = new ImageIcon("image/beverag3.png").getImage();
		la[2] = new JLabel(new ImageIcon(rszIcon3.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon4 = new ImageIcon("image/beverag4.png").getImage();
		la[3] = new JLabel(new ImageIcon(rszIcon4.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon5 = new ImageIcon("image/beverag5.png").getImage();
		la[4] = new JLabel(new ImageIcon(rszIcon5.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon6 = new ImageIcon("image/beverag6.png").getImage();
		la[5] = new JLabel(new ImageIcon(rszIcon6.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon7 = new ImageIcon("image/snak1.png").getImage();
		la[6] = new JLabel(new ImageIcon(rszIcon7.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon8 = new ImageIcon("image/snak2.png").getImage();
		la[7] = new JLabel(new ImageIcon(rszIcon8.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon9 = new ImageIcon("image/snak3.png").getImage();
		la[8] = new JLabel(new ImageIcon(rszIcon9.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon10 = new ImageIcon("image/snak4.png").getImage();
		la[9] = new JLabel(new ImageIcon(rszIcon10.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon11 = new ImageIcon("image/snackLa1.png").getImage();
		la[10] = new JLabel(new ImageIcon(rszIcon11.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon12 = new ImageIcon("image/snackLa2.png").getImage();
		la[11] = new JLabel(new ImageIcon(rszIcon12.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon13 = new ImageIcon("image/snackLa3.png").getImage();
		la[12] = new JLabel(new ImageIcon(rszIcon13.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon14 = new ImageIcon("image/beveragLa1.png").getImage();
		la[13] = new JLabel(new ImageIcon(rszIcon14.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon15 = new ImageIcon("image/beveragLa2.png").getImage();
		la[14] = new JLabel(new ImageIcon(rszIcon15.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon16 = new ImageIcon("image/beveragLa3.png").getImage();
		la[15] = new JLabel(new ImageIcon(rszIcon16.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon17 = new ImageIcon("image/beveragLa4.png").getImage();
		la[16] = new JLabel(new ImageIcon(rszIcon17.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon18 = new ImageIcon("image/beverag1.png").getImage();
		la2[0] = new JLabel(new ImageIcon(rszIcon18.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon19 = new ImageIcon("image/beverag2.png").getImage();
		la2[1] = new JLabel(new ImageIcon(rszIcon19.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon20 = new ImageIcon("image/beverag3.png").getImage();
		la2[2] = new JLabel(new ImageIcon(rszIcon20.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon21 = new ImageIcon("image/beverag4.png").getImage();
		la2[3] = new JLabel(new ImageIcon(rszIcon21.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon22 = new ImageIcon("image/beverag5.png").getImage();
		la2[4] = new JLabel(new ImageIcon(rszIcon22.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon23 = new ImageIcon("image/beverag6.png").getImage();
		la2[5] = new JLabel(new ImageIcon(rszIcon23.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon24 = new ImageIcon("image/snak1.png").getImage();
		la2[6] = new JLabel(new ImageIcon(rszIcon24.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon25 = new ImageIcon("image/snak2.png").getImage();
		la2[7] = new JLabel(new ImageIcon(rszIcon25.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon26 = new ImageIcon("image/snak3.png").getImage();
		la2[8] = new JLabel(new ImageIcon(rszIcon26.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon27 = new ImageIcon("image/snak4.png").getImage();
		la2[9] = new JLabel(new ImageIcon(rszIcon27.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon28 = new ImageIcon("image/snackLa1.png").getImage();
		la2[10] = new JLabel(new ImageIcon(rszIcon28.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon29 = new ImageIcon("image/snackLa2.png").getImage();
		la2[11] = new JLabel(new ImageIcon(rszIcon29.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon30 = new ImageIcon("image/snackLa3.png").getImage();
		la2[12] = new JLabel(new ImageIcon(rszIcon30.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon31 = new ImageIcon("image/beveragLa1.png").getImage();
		la2[13] = new JLabel(new ImageIcon(rszIcon31.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon32 = new ImageIcon("image/beveragLa2.png").getImage();
		la2[14] = new JLabel(new ImageIcon(rszIcon32.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon33 = new ImageIcon("image/beveragLa3.png").getImage();
		la2[15] = new JLabel(new ImageIcon(rszIcon33.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon34 = new ImageIcon("image/beveragLa4.png").getImage();
		la2[16] = new JLabel(new ImageIcon(rszIcon34.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		la[0].setBounds(0, 50, 100, 100);
		la[1].setBounds(60, 50, 100, 100);
		la[2].setBounds(120, 50, 100, 100);
		la[3].setBounds(180, 50, 100, 100);
		la[4].setBounds(240, 50, 100, 100);
		la[5].setBounds(300, 50, 100, 100);
		la[6].setBounds(30, 200, 80, 130);
		la[7].setBounds(120, 200, 80, 130);
		la[8].setBounds(210, 200, 80, 130);
		la[9].setBounds(300, 200, 80, 130);
		la[10].setBounds(0, 360, 180, 100);
		la[11].setBounds(190, 360, 100, 100);
		la[12].setBounds(290, 360, 100, 100);
		la[13].setBounds(10, 500, 70, 170);
		la[14].setBounds(110, 500, 70, 170);
		la[15].setBounds(210, 500, 70, 170);
		la[16].setBounds(310, 500, 70, 170);

		la2[0].setBounds(0, 50, 100, 100);
		la2[1].setBounds(60, 50, 100, 100);
		la2[2].setBounds(120, 50, 100, 100);
		la2[3].setBounds(180, 50, 100, 100);
		la2[4].setBounds(240, 50, 100, 100);
		la2[5].setBounds(300, 50, 100, 100);
		la2[6].setBounds(30, 200, 80, 130);
		la2[7].setBounds(120, 200, 80, 130);
		la2[8].setBounds(210, 200, 80, 130);
		la2[9].setBounds(300, 200, 80, 130);
		la2[10].setBounds(0, 360, 180, 100);
		la2[11].setBounds(190, 360, 100, 100);
		la2[12].setBounds(290, 360, 100, 100);
		la2[13].setBounds(10, 500, 70, 170);
		la2[14].setBounds(110, 500, 70, 170);
		la2[15].setBounds(210, 500, 70, 170);
		la2[16].setBounds(310, 500, 70, 170);
		
	}

}
