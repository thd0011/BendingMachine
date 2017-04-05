package kim.song.lee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 ★ 김송이 벤딩머신 프로젝트 ★
  - MainView입니다.
  - Image는 불러와서 Label에 집어넣었으니 참고해주세요.
*/

public class BackView extends JFrame {

	public JButton nums_bt[];
	public JButton admin2_bt, moneyClear_bt;
	public JLabel nums[], la3[];
	JLabel moneyBox_la, bill2_la, coinSm1_la, coinSm2_la, coinLa1_la, coinLa2_la;
	JPanel main_pn, windowfront_pn, windowback_pn, barB_pn0, barB_pn1, barB_pn2, barB_pn3, barB_pn4;
	JPanel display4_pn;

	public BackView() {
		nums_bt = new JButton[17];
		la3 = new JLabel[17];
		main_pn = new JPanel(); // 제일 바깥 초록색배경

		imageGenerate(); // JLabel에 ImageIcon을 넣어 image생성
		windowGenerate(); // 쇼캐이스 생성 및 제품 부여
		buttonGenerate(); // 번호 버튼 생성
		moneyGenerate(); // 돈 이미지 생성

		// MainPanel - 가장 뒤 제일 큰 초록색
		main_pn.setLayout(null);
		main_pn.setBounds(10, 10, 700, 800);
		main_pn.setBackground(new Color(40, 40, 40));
		main_pn.add(windowback_pn);

		// Frame
		add(main_pn);
		setTitle("VendingMachine");
		setLayout(null);
		setBounds(700, 50, 740, 880);
		setBackground(Color.WHITE);
		setVisible(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	/*
	 * 평상시는 돈 사진들은 Visible(false)로 할것 돈 투입구를 눌렀을 때 지폐,코인이 Visible이 되어 클릭이 가능하며
	 * 클릭시 bill1->bill2이미지로 바뀌게(액션처럼보여주려고)
	 */
	private void moneyGenerate() {
		Image rszIcon1 = new ImageIcon("image/moneyBox.png").getImage();
		moneyBox_la = new JLabel(new ImageIcon(rszIcon1.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH)));

		moneyBox_la.setBounds(460, 200, 200, 200);

		main_pn.add(moneyBox_la);
		main_pn.add(admin2_bt);
		main_pn.add(moneyClear_bt);
		
	}

	/* showcase 판넬을 두개 겹쳐 보여주며 앞(물건이있는)판넬에 이미지화 했던 Label을 추가 */
	public void windowGenerate() {
		windowback_pn = new JPanel(); // 물건판넬과 main판넬 사이 경계를 나타내기위한 판넬
		windowfront_pn = new JPanel(); // 물건있는 판넬
		barB_pn0 = new JPanel(); // 물건 받침대 0
		barB_pn1 = new JPanel(); // 물건 받침대 1
		barB_pn2 = new JPanel(); // 물건 받침대 2
		barB_pn3 = new JPanel(); // 물건 받침대 3
		barB_pn4 = new JPanel(); // 물건 받침대 4
		
		nums = new JLabel[17];

		for(int i=0;i<nums.length;i++){
			nums[i] = new JLabel();
			
			nums[i].setForeground(Color.RED);
		}
		nums[0].setBounds(42, 0, 30, 30);
		nums[1].setBounds(102, 0, 30, 30);
		nums[2].setBounds(162, 0, 30, 30);
		nums[3].setBounds(222, 0, 30, 30);
		nums[4].setBounds(282, 0, 30, 30);
		nums[5].setBounds(342, 0, 30, 30);
		nums[6].setBounds(62, 0, 30, 30);
		nums[7].setBounds(152, 0, 30, 30);
		nums[8].setBounds(242, 0, 30, 30);
		nums[9].setBounds(332, 0, 30, 30);
		nums[10].setBounds(82, 0, 30, 30);
		nums[11].setBounds(237, 0, 30, 30);
		nums[12].setBounds(337, 0, 30, 30);
		nums[13].setBounds(34, 0, 30, 20);
		nums[14].setBounds(135, 0, 30, 20);
		nums[15].setBounds(236, 0, 30, 20);
		nums[16].setBounds(337, 0, 30, 20);

		barB_pn0.setLayout(null);
		barB_pn1.setLayout(null);
		barB_pn2.setLayout(null);
		barB_pn3.setLayout(null);
		barB_pn4.setLayout(null);
		barB_pn1.add(nums[0]);
		barB_pn1.add(nums[1]);
		barB_pn1.add(nums[2]);
		barB_pn1.add(nums[3]);
		barB_pn1.add(nums[4]);
		barB_pn1.add(nums[5]);
		barB_pn2.add(nums[6]);
		barB_pn2.add(nums[7]);
		barB_pn2.add(nums[8]);
		barB_pn2.add(nums[9]);
		barB_pn3.add(nums[10]);
		barB_pn3.add(nums[11]);
		barB_pn3.add(nums[12]);
		barB_pn4.add(nums[13]);
		barB_pn4.add(nums[14]);
		barB_pn4.add(nums[15]);
		barB_pn4.add(nums[16]);


		barB_pn0.setLayout(null);
		barB_pn1.setLayout(null);
		barB_pn2.setLayout(null);
		barB_pn3.setLayout(null);

		barB_pn0.setBounds(0, 0, 400, 40);
		barB_pn1.setBounds(0, 130, 400, 40);
		barB_pn2.setBounds(0, 300, 400, 40);
		barB_pn3.setBounds(0, 450, 400, 40);
		barB_pn4.setBounds(0, 650, 400, 40);
		barB_pn0.setBackground(Color.BLACK);
		barB_pn1.setBackground(Color.BLACK);
		barB_pn2.setBackground(Color.BLACK);
		barB_pn3.setBackground(Color.BLACK);
		barB_pn4.setBackground(Color.BLACK);

		windowback_pn.setLayout(null);
		windowback_pn.setBackground(new Color(0, 80, 0));
		windowback_pn.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK));
		windowback_pn.setBounds(10, 10, 430, 750);
		windowback_pn.add(windowfront_pn);

		windowfront_pn.setLayout(null);
		windowfront_pn.setBackground(new Color(53, 53, 53));
		windowfront_pn.setBounds(15, 20, 400, 680);

		windowfront_pn.add(barB_pn0);
		windowfront_pn.add(barB_pn1);
		windowfront_pn.add(barB_pn2);
		windowfront_pn.add(barB_pn3);
		windowfront_pn.add(barB_pn4);
		windowfront_pn.add(la3[0]);
		windowfront_pn.add(la3[1]);
		windowfront_pn.add(la3[2]);
		windowfront_pn.add(la3[3]);
		windowfront_pn.add(la3[4]);
		windowfront_pn.add(la3[5]);
		windowfront_pn.add(la3[6]);
		windowfront_pn.add(la3[7]);
		windowfront_pn.add(la3[8]);
		windowfront_pn.add(la3[9]);
		windowfront_pn.add(la3[10]);
		windowfront_pn.add(la3[11]);
		windowfront_pn.add(la3[12]);
		windowfront_pn.add(la3[13]);
		windowfront_pn.add(la3[14]);
		windowfront_pn.add(la3[15]);
		windowfront_pn.add(la3[16]);

	}

	/* Panel에서 버튼 모음 */
	public void buttonGenerate() {
		admin2_bt = new JButton(""); // 관리자버튼
		admin2_bt.setBounds(460, 480, 10, 50);
		
		moneyClear_bt = new JButton("정산");
		moneyClear_bt.setBounds(520, 430, 80, 40);
		
		nums_bt[0] = new JButton("1");
		nums_bt[1] = new JButton("2");
		nums_bt[2] = new JButton("3");
		nums_bt[3] = new JButton("4");
		nums_bt[4] = new JButton("5");
		nums_bt[5] = new JButton("6");
		nums_bt[6] = new JButton("7");
		nums_bt[7] = new JButton("8");
		nums_bt[8] = new JButton("9");
		nums_bt[9] = new JButton("10");
		nums_bt[10] = new JButton("11");
		nums_bt[11] = new JButton("12");
		nums_bt[12] = new JButton("13");
		nums_bt[13] = new JButton("14");
		nums_bt[14] = new JButton("15");
		nums_bt[15] = new JButton("16");
		nums_bt[16] = new JButton("17");
		
		nums_bt[0].setBounds(35, 25, 30, 10);
		nums_bt[1].setBounds(95, 25, 30, 10);
		nums_bt[2].setBounds(155, 25, 30, 10);
		nums_bt[3].setBounds(215, 25, 30, 10);
		nums_bt[4].setBounds(275, 25, 30, 10);
		nums_bt[5].setBounds(335, 25, 30, 10);
		nums_bt[6].setBounds(55, 25, 30, 10);
		nums_bt[7].setBounds(145, 25, 30, 10);
		nums_bt[8].setBounds(235, 25, 30, 10);
		nums_bt[9].setBounds(325, 25, 30, 10);
		nums_bt[10].setBounds(75, 25, 30, 10);
		nums_bt[11].setBounds(230, 25, 30, 10);
		nums_bt[12].setBounds(330, 25, 30, 10);
		nums_bt[13].setBounds(27, 15, 30, 10);
		nums_bt[14].setBounds(128, 15, 30, 10);
		nums_bt[15].setBounds(229, 15, 30, 10);
		nums_bt[16].setBounds(330, 15, 30, 10);

		barB_pn1.add(nums_bt[0]);
		barB_pn1.add(nums_bt[1]);
		barB_pn1.add(nums_bt[2]);
		barB_pn1.add(nums_bt[3]);
		barB_pn1.add(nums_bt[4]);
		barB_pn1.add(nums_bt[5]);
		barB_pn2.add(nums_bt[6]);
		barB_pn2.add(nums_bt[7]);
		barB_pn2.add(nums_bt[8]);
		barB_pn2.add(nums_bt[9]);
		barB_pn3.add(nums_bt[10]);
		barB_pn3.add(nums_bt[11]);
		barB_pn3.add(nums_bt[12]);
		barB_pn4.add(nums_bt[13]);
		barB_pn4.add(nums_bt[14]);
		barB_pn4.add(nums_bt[15]);
		barB_pn4.add(nums_bt[16]);
		
	}

	/*
	 * 쇼케이스 안에 물건을 Image로 보여주기 위하여 image폴더에 있는 image를 불러와서 JLabel로 구현 / 17가지 품목
	 */
	public void imageGenerate() {
		Image rszIcon18 = new ImageIcon("image/beverag1.png").getImage();
		la3[0] = new JLabel(new ImageIcon(rszIcon18.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon19 = new ImageIcon("image/beverag2.png").getImage();
		la3[1] = new JLabel(new ImageIcon(rszIcon19.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon20 = new ImageIcon("image/beverag3.png").getImage();
		la3[2] = new JLabel(new ImageIcon(rszIcon20.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon21 = new ImageIcon("image/beverag4.png").getImage();
		la3[3] = new JLabel(new ImageIcon(rszIcon21.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon22 = new ImageIcon("image/beverag5.png").getImage();
		la3[4] = new JLabel(new ImageIcon(rszIcon22.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon23 = new ImageIcon("image/beverag6.png").getImage();
		la3[5] = new JLabel(new ImageIcon(rszIcon23.getScaledInstance(50, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon24 = new ImageIcon("image/snak1.png").getImage();
		la3[6] = new JLabel(new ImageIcon(rszIcon24.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon25 = new ImageIcon("image/snak2.png").getImage();
		la3[7] = new JLabel(new ImageIcon(rszIcon25.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon26 = new ImageIcon("image/snak3.png").getImage();
		la3[8] = new JLabel(new ImageIcon(rszIcon26.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon27 = new ImageIcon("image/snak4.png").getImage();
		la3[9] = new JLabel(new ImageIcon(rszIcon27.getScaledInstance(80, 130, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon28 = new ImageIcon("image/snackLa1.png").getImage();
		la3[10] = new JLabel(new ImageIcon(rszIcon28.getScaledInstance(140, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon29 = new ImageIcon("image/snackla2.png").getImage();
		la3[11] = new JLabel(new ImageIcon(rszIcon29.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon30 = new ImageIcon("image/snackLa3.png").getImage();
		la3[12] = new JLabel(new ImageIcon(rszIcon30.getScaledInstance(70, 100, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon31 = new ImageIcon("image/beveragLa1.png").getImage();
		la3[13] = new JLabel(new ImageIcon(rszIcon31.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon32 = new ImageIcon("image/beveragla2.png").getImage();
		la3[14] = new JLabel(new ImageIcon(rszIcon32.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon33 = new ImageIcon("image/beveragLa3.png").getImage();
		la3[15] = new JLabel(new ImageIcon(rszIcon33.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		Image rszIcon34 = new ImageIcon("image/beveragLa4.png").getImage();
		la3[16] = new JLabel(new ImageIcon(rszIcon34.getScaledInstance(50, 170, java.awt.Image.SCALE_SMOOTH)));

		la3[0].setBounds(0, 50, 100, 100);
		la3[1].setBounds(60, 50, 100, 100);
		la3[2].setBounds(120, 50, 100, 100);
		la3[3].setBounds(180, 50, 100, 100);
		la3[4].setBounds(240, 50, 100, 100);
		la3[5].setBounds(300, 50, 100, 100);
		la3[6].setBounds(30, 200, 80, 130);
		la3[7].setBounds(120, 200, 80, 130);
		la3[8].setBounds(210, 200, 80, 130);
		la3[9].setBounds(300, 200, 80, 130);
		la3[10].setBounds(0, 360, 180, 100);
		la3[11].setBounds(190, 360, 100, 100);
		la3[12].setBounds(290, 360, 100, 100);
		la3[13].setBounds(10, 500, 70, 170);
		la3[14].setBounds(110, 500, 70, 170);
		la3[15].setBounds(210, 500, 70, 170);
		la3[16].setBounds(310, 500, 70, 170);
	}

	public static void main(String[] args) {
		new BackView();
	}
}
