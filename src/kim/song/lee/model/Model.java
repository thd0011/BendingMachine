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
	// 돈 초기값 - 50 30 200
	String itemFileName = "C:\\Users\\kosta\\workspace\\JavaApp\\Items.txt";
	// 음료수 갯수 초기값 - 20 20 20 20 20 20 10 10 10 10 5 10 10 10 10 10 10
	public int itemCost[] = new int[] { 500, 900, 1100, 1100, 1000, 1200, 1500, 1800, 1500, 1500, 4800, 3000, 2500, 2800, 2800,
			2200, 2200 };
	int returnCost[];
	public int itemplus[] = new int[17];
	int co100 = 0, co500 = 0, co1000 = 0;
	public int labelarr[];
	public int sum=0;
	public String itemArry[];
	public String moneyArry[]; //현재 돈값

	/*
	 * 1. 돈을 투입할 시마다 -> moneyInsert()
	 * 2. 번호 누르고 확인 눌렀을 시 -> , itemCheck(),moneyCheck()
	 * 3. check() 둘다 만족하면 -> itemOut()
	 * 4. 반환 버튼 눌렀을 시 ->returnMoney()
	 * 5. 관리자 버튼 눌렀을 시 ->adminItemCount(), 관리자 버튼 눌렀을 시(되돌아갈때) ->adminClose()
	 * 6. 관리자 정산 버튼 클릭 시 -> adminManagement()
	 * 추가. moneyAlert()
	 */

	/* 아이템의 갯수가 있는지 체크 */
	
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
	 * 아이템의 가격보다 넣은 금액이 많은지!! 돈이 부족하면 0리턴!! 돈이 된다고 하면 남은돈 리턴!!
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

	/* 고객이 금액을 넣을때마다 돈통에 추가 */
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
	

	/* 환불 시, 가지고있는 지폐와 동전의 수를 계산하여 반환 숫자 계산 */
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
	 * 자판기 안에 있는 금액에 따라 경고 !! 천원4장이하,오백원1개이하,백원4개이하,총금액5000원이하!! int 배열로 return;
	 * 0=>정상 1=>경고
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

	/* 물건을 배출했을 시, 재고 -1 작업 */
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

	/* 관리자 뷰를 처음 열었을 시 아이탬 갯수를 보여주기 위하여 */
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

	/* 관리자 뷰를 닫았을 시, 추가되었던 물품 갯수를 가져와 저장 */
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

	/* 돈 정산 */
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

	/* 파일 읽어오는 메쏘드 */
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

	/* BufferedReader 자원반환 */
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