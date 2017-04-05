package kim.song.lee.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Model{

	BufferedReader br;
	BufferedWriter bw;
	StringBuffer sb;
	String bankFileName = "C:\\Users\\kosta\\workspace\\JavaApp\\Bank.txt";
	// �� �ʱⰪ - 50 30 200
	String itemFileName = "C:\\Users\\kosta\\workspace\\JavaApp\\Items.txt";
	// ����� ���� �ʱⰪ - 20 20 20 20 20 20 10 10 10 10 5 10 10 10 10 10 10
	public int itemCost[] = new int[] { 500, 900, 1100, 1100, 1000, 1200, 1500, 1800, 1500, 1500, 4800, 3000, 2500, 2800, 2800,
			2200, 2200 };
	int returnCost[];
	public int itemplus[] = new int[17];
	int co100 = 0, co500 = 0, co1000 = 0;
	public int labelarr[];
	public int sum=0;
	public String itemArry[];
	public String moneyArry[]; //���� ����

	/*
	 * 1. ���� ������ �ø��� -> moneyInsert()
	 * 2. ��ȣ ������ Ȯ�� ������ �� -> , itemCheck(),moneyCheck()
	 * 3. check() �Ѵ� �����ϸ� -> itemOut()
	 * 4. ��ȯ ��ư ������ �� ->returnMoney()
	 * 5. ������ ��ư ������ �� ->adminItemCount(), ������ ��ư ������ ��(�ǵ��ư���) ->adminClose()
	 * 6. ������ ���� ��ư Ŭ�� �� -> adminManagement()
	 * �߰�. moneyAlert()
	 */

	/* �������� ������ �ִ��� üũ */
	
	public Model() {
	}
	
	public int itemCheck(int num) {
		num--;

		itemArry = readFile(itemFileName);
		if (Integer.parseInt(itemArry[num]) > 1) {
			closeFile();
			return 2;
		} else if (Integer.parseInt(itemArry[num]) == 1){
			closeFile();
			return 1;
		} else{
			closeFile();
			return 0;
		}
	}

	/*
	 * �������� ���ݺ��� ���� �ݾ��� ������!! ���� �����ϸ� 0����!! ���� �ȴٰ� �ϸ� ������ ����!!
	 */
	public int moneyCheck(int money, int num) {
		num--;

		if (money >= itemCost[num]) {
			money -= itemCost[num];
			return money;
		} else {
			return 0;
		}
	}

	/* ���� �ݾ��� ���������� ���뿡 �߰� */
	public void moneyInsert(int money) {

		moneyArry = readFile(bankFileName);

		switch(money){
	      case 0:
	         moneyArry[0] = String.valueOf(Integer.parseInt(moneyArry[0]) + 1);
	         co1000=1000;
	         break;
	      case 1:
	         moneyArry[1] = String.valueOf(Integer.parseInt(moneyArry[1]) + 1);
	         co500=500;
	         break;
	      case 2:
	         moneyArry[2] = String.valueOf(Integer.parseInt(moneyArry[2]) + 1);
	         co100=100;
	         break;
	      }

		sum = sum + co100 + co500 + co1000;
		co100=0;
		co500=0;
		co1000=0;
		sb = new StringBuffer();
		for (int i = 0; i < moneyArry.length; i++) {
			sb.append(moneyArry[i] + " ");
		}

		try {
			bw = new BufferedWriter(new FileWriter(bankFileName));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		closeFile();
	}
	

	/* ȯ�� ��, �������ִ� ����� ������ ���� ����Ͽ� ��ȯ ���� ��� */
	public int[] returnMoney() { // 5600
		int money = sum;
		returnCost = new int[] { 0, 0, 0 };
		moneyArry = readFile(bankFileName);

		int bill = money / 1000;
		if (bill >= Integer.parseInt(moneyArry[0])) {
			bill = Integer.parseInt(moneyArry[0]);
		}
		int coinLa = (money - bill * 1000) / 500;
		if (coinLa >= Integer.parseInt(moneyArry[1])) {
			coinLa = Integer.parseInt(moneyArry[1]);
		}
		int coinSm = ((money - bill * 1000) - coinLa * 500) / 100;

		returnCost[0] = bill;
		returnCost[1] = coinLa;
		returnCost[2] = coinSm;
		for (int i = 0; i < moneyArry.length; i++) {
			moneyArry[i] = String.valueOf(Integer.parseInt(moneyArry[i]) - returnCost[i]);
		}
		sb = new StringBuffer();
		for (int i = 0; i < moneyArry.length; i++) {
			sb.append(moneyArry[i] + " ");
		}
		
		try {
			bw = new BufferedWriter(new FileWriter(bankFileName));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sum=0;
		closeFile();
		return returnCost;
	}

	/*
	 * ���Ǳ� �ȿ� �ִ� �ݾ׿� ���� ��� !! õ��4������,�����1������,���4������,�ѱݾ�5000������!! int �迭�� return;
	 * 0=>���� 1=>���
	 */
	
	public int[] moneyAlert() {

		int alert[] = new int[] { 0, 0, 0, 0 };
		moneyArry = readFile(bankFileName);

		if (Integer.parseInt(moneyArry[0]) <= 4) {
			alert[0] = 1;
		}
		if (Integer.parseInt(moneyArry[1]) <= 1) {
			alert[1] = 1;
		}
		if (Integer.parseInt(moneyArry[2]) <= 4) {
			alert[2] = 1;
		}

		int sumMoney = 0;
		sumMoney += Integer.parseInt(moneyArry[0]) * 1000;
		sumMoney += Integer.parseInt(moneyArry[1]) * 500;
		sumMoney += Integer.parseInt(moneyArry[2]) * 100;
		if (sumMoney <= 5000) {
			alert[3] = 1;
		}

		closeFile();
		return alert;
	}

	/* ������ �������� ��, ��� -1 �۾� */
	public void itemOut(int num) {
		num--;
		itemArry = readFile(itemFileName);

		int chgItemNum = Integer.parseInt(itemArry[num]) - 1;
		itemArry[num] = String.valueOf(chgItemNum);
		sb = new StringBuffer();
		for (int i = 0; i < itemArry.length; i++) {
			sb.append(itemArry[i] + " ");
		}
		sum -= itemCost[num];
		try {
			bw = new BufferedWriter(new FileWriter(itemFileName));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		closeFile();
	}

	/* ������ �並 ó�� ������ �� ������ ������ �����ֱ� ���Ͽ� */
	public String[] adminItemCount() {
		itemArry = readFile(itemFileName);
		closeFile();
		return itemArry;
	}
	
	public int[] adminItemCount2() {
	      itemArry = readFile(itemFileName);
	      int returnArry[] = new int[17];
	      for (int i = 0; i < itemArry.length; i++) {
	         returnArry[i] = Integer.parseInt(itemArry[i]);
	      }
	      closeFile();
	      return returnArry;
	   }

	/* ������ �並 �ݾ��� ��, �߰��Ǿ��� ��ǰ ������ ������ ���� */
	public void adminClose(int count[]) {
		
		itemArry = readFile(itemFileName);
		for (int i = 0; i < itemArry.length; i++) {
			itemArry[i] = String.valueOf(Integer.parseInt(itemArry[i]) + count[i]);
		}

		sb = new StringBuffer();
		for (int i = 0; i < itemArry.length; i++) {
			sb.append(itemArry[i] + " ");
		}

		try {
			bw = new BufferedWriter(new FileWriter(itemFileName));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		itemplus = new int[17];
		closeFile();
	}

	/* �� ���� */
	public void adminManagement() {
		moneyArry = readFile(bankFileName);
		moneyArry[0] = "50";
		moneyArry[1] = "30";
		moneyArry[2] = "200";
		
		sb = new StringBuffer();
		for (int i = 0; i < moneyArry.length; i++) {
			sb.append(moneyArry[i] + " ");
		}
		
		try {
			bw = new BufferedWriter(new FileWriter(bankFileName));
			bw.write(sb.toString());
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		closeFile();
	}

	/* ���� �о���� �޽�� */
	public String[] readFile(String fileName) {
		String str = null;
		try {
			br = new BufferedReader(new FileReader(fileName));
			str = br.readLine();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String arr[] = str.split(" ");
		return arr;
	}

	/* BufferedReader �ڿ���ȯ */
	public void closeFile() {
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Model();
	}
}