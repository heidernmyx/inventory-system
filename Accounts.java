package p3_project;
import java.util.ArrayList;
import java.util.Scanner;
public class Accounts {
	public Scanner scan = new Scanner(System.in);	
	public boolean loop = false;
	private String admin_user = "admin";
	private String admin_pass = "admin";
	
	// Constructor
	private int account_id;
	private String username;
	private String password;
	
	
	public Accounts (int account_id, String username, String password) {
		this.account_id = account_id;
		this.username = username;
		this.password = password;
	}
	
	//Getters
	public String getAdmin_user () {
		return admin_user;
	}
	public String getAdmin_pass() {
		return admin_pass; 
	}
	
	public String getUsername () {
		return username;
	}
	public String getPassword() {
		return password; 
	}
	
	public int getAccountID () {
		return account_id;
	}
	
	int account_added = 0;
	
	public int acc_id_assign (int a) {
		account_added += 1;
		account_id = account_added;
		return account_id;
	}
	
	// Set ac
	public void acc_autoAdd() {

		Accounts account1 = new Accounts(acc_id_assign(account_added),"user1", "user1");
		Accounts account2 = new Accounts(acc_id_assign(account_added),"user2", "user2");
		Accounts account3 = new Accounts(acc_id_assign(account_added),"user3", "user3");
		Accounts account4 = new Accounts(acc_id_assign(account_added),"user4", "user4");
		accounts_Arr.add(account1);
		accounts_Arr.add(account2);
		accounts_Arr.add(account3);
		accounts_Arr.add(account4);
		
	}
	private static ArrayList <Accounts> accounts_Arr = new ArrayList<Accounts>();
	
	public ArrayList<Accounts> getAccounts_arr () {
		return accounts_Arr;
	}
	
	// add/remove account method for the admin's use
	public void addAccount (int account_id,String username, String password) {
		Accounts newAcc = new Accounts(acc_id_assign(account_added),username, password);
		accounts_Arr.add(newAcc);
		System.out.println("Account Added Successfully!");
		// after adding account return to admin menu
		Main.admin_menu();
		
	}
	
	public int account_size;
	
	int acc_no = 0;
	String p = "";
//	Accounts accountInfo = accounts_Arr.get(i);
	public void removeAccount () {
		int i = 0;
		account_size = accounts_Arr.size();
		if (account_size == 0) {
			System.out.println("No account found\n\n");
			Main.admin_menu();	
		}
		else {
		System.out.println("\n\n\tRemove account\n\n"
				+ "  Account ID |   Username");
		System.out.println("---------------------------");
		Accounts accountInfo = accounts_Arr.get(i);
		// for loop for display purposes
		int index_toRemove;
		for (i = 0; i < account_size; i++) {
			accountInfo = accounts_Arr.get(i);
			System.out.printf("      %-6s |    %-10s\n",
					accountInfo.getAccountID(),
					accountInfo.getUsername());
		}
		acc_no = 0;
		p = "";
		do {
			try {
			System.out.print("\n>>>");
			p = scan.nextLine();
			acc_no = Integer.parseInt(p);
			
			for (i = 0; i < account_size; i++) {
				accountInfo = accounts_Arr.get(i);
				
				if (acc_no == accountInfo.getAccountID()) {
					index_toRemove = i;
					System.out.print("Are you sure to remove account '" + accountInfo.getUsername() + "'?"
							+ "\n\n[Y] Yes \t [N] No");
					do {
						System.out.print("\n>>>");
						String choice = scan.nextLine();
						if (choice.equalsIgnoreCase("Y")) {
							accounts_Arr.remove(index_toRemove);
							System.out.println("Account Removed Successfully!\n\n");
							loop = false;
							Main.admin_menu();	
						}
						else if (choice.equalsIgnoreCase("N")) {
							System.out.println("Account Removal Canceled\n\n");
							loop = false;
							Main.admin_menu();
						}	
						else {
							System.out.print("Invalid input, please try again.\n\n");
							loop = true;
						}
					} while(loop);
				}
			}
			
			if (i == accounts_Arr.size()){
				System.out.print("Invalid input, Please choose among the Account ID");
				loop = false;
				removeAccount();
			}
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				loop = true;
			}
		} while (loop);
		
		}
	}
	public String changePass (String newPass) {
		this.password = newPass;
		return password;
	}
	
}
	
