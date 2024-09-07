package p3_project;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;


public class Main {
	
	public static Accounts account = new Accounts(0, null, null);
	public static final Scanner scan = new Scanner(System.in);
	public static boolean loop, condition;
	public static int account_size = account.getAccounts_arr().size();
	public static Items item = new Items ("" , 0 ,"", 0, "");
	public static String acc_LoggedIn;
	public static void main (String [] args) {
		
		account.acc_autoAdd();
		item.item_autoAdd();
		login();
	}
	
	// login method
	static void login () {
		Accounts admin_acc = new Accounts(0,null, null);
		do {
		System.out.println("\n\tLogin");
		String login_user, login_pass;
		do {
			loop = false;
		System.out.print("Enter username: ");
		login_user = scan.nextLine();
		
		if (login_user.contains(" ")) {
			System.out.println("Username cannot contain spaces.\nPlease try again\n");
			loop = true;
		}
		} while (loop);
		
		do {
			loop = false;
		System.out.print("Enter password: ");
		login_pass = scan.nextLine();
		if (login_pass.contains(" ")) {
			System.out.println("Password cannot contain spaces.\nPlease try again\n");
			loop = true;
		}
		} while (loop);
		int account_size = account.getAccounts_arr().size();
		
		if (login_user.equals(admin_acc.getAdmin_user())& login_pass.equals(admin_acc.getAdmin_pass())) {
			acc_LoggedIn = admin_acc.getAdmin_pass();
			System.out.println("\n\n\t Welcome Admin!");
			loop = false;
			condition = false;
			admin_menu();
		}
		else if (account_size == 0) {
			System.out.println("\nWrong Username or Password, please try again\n\n");
			loop = true;
		}
		
		else {
			
			for(int i = 0; i < account_size; i++) {
				Accounts accountInfo = account.getAccounts_arr().get(i);
				if (login_user.equals(accountInfo.getUsername()) & login_pass.equals(accountInfo.getPassword())) {
					acc_LoggedIn = accountInfo.getUsername();
					System.out.printf("\n\n\tWelcome " + accountInfo.getUsername() + "!");
					user_menu();
				}
				else if (i == account_size-1) {
					System.out.println("Wrong Username or Password, please try again");
					loop = true;
					break;
				}
			}
		} 
		
		}while (loop);
	}
//	public static Items item = new Items (0 ,"", 0, "");
	
	
	static void admin_menu () {
		
		System.out.println("\n\n\tInventory System");
		System.out.print("\n1. Add/Remove Account"
				+ "\n2. Add/Remove Item "
				+ "\n3. Update"
				+ "\n4. Archive"
				+ "\n5. Display Item"
				+ "\n6. Logout/Exit"
				+ "\n\n>>>");
		do {
			loop = false;
		String choice = scan.next();
		scan.nextLine();
		
		switch (choice) {
		case "1":
			do {
				loop = false;
				System.out.print("\n\n[A] Add account \t[R] Remove account \t[C] Change account password\n[B] Back"
					+ "\n>>>");
				choice = scan.nextLine();
			if (choice.equalsIgnoreCase("A")) {
				System.out.print("\n\tAdd new Account");
				String username, password;
			
			do {
				loop = false;
				loop = false;
				System.out.print("\n\nEnter username: ");
				username = scan.nextLine();
				if (username.contains(" ")) {
					System.out.println("Username cannot contain spaces.\nPlease try again");
					loop = true;
				}
				for (int i = 0; i < account.getAccounts_arr().size(); i++) {
					Accounts accountInfo = account.getAccounts_arr().get(i);
					if (username.equals(accountInfo.getUsername())) {
						System.out.printf("'%s' is already taken.\nPlease use a different one"
							, accountInfo.getUsername());
						loop = true;
					}
				}
				
			} while (loop);
			
			do {
				loop = false;
				System.out.print("\nEnter password: ");
				password = scan.nextLine();
				if (password.contains(" ")) {
					System.out.println("Password cannot contain spaces.\nPlease try again");
					loop = true;
				}
			} while (loop);
				account.addAccount(account.getAccountID(),username, password);
			}
			
			else if (choice.equalsIgnoreCase("R")) {
				account.removeAccount();
			}
			else if (choice.equalsIgnoreCase("C")) {
			do {
				loop = false;
				try {
				String p = "";
				int acc_no, i = 0;
				System.out.print("Select account to change password:\n"
						+ "\n  Account ID |   Username\n");
				System.out.println("---------------------------");
				for (i = 0; i < account.getAccounts_arr().size(); i++) {
					Accounts accountInfo = account.getAccounts_arr().get(i);
					System.out.printf("      %-6s |    %-10s\n", 
							accountInfo.getAccountID(),
							accountInfo.getUsername());
				}
				System.out.print("\n>>>");
				p = scan.nextLine();
				acc_no = Integer.parseInt(p);
				
				for (i = 0; i < account.getAccounts_arr().size(); i++) {
					Accounts accountInfo = account.getAccounts_arr().get(i);
					
					if (acc_no == accountInfo.getAccountID()) {
					System.out.print("\n\tAccount Selected: "
						+ "\n\n\tUsername: " + accountInfo.getUsername()
								+ "\n\tPassword: " + accountInfo.getPassword());
					String newPass;
					do {
					loop = false;
					System.out.print("\n\nEnter new password: ");
					newPass = scan.nextLine();
					if (newPass.contains(" ")) {
						System.out.println("Passowrd cannot contain spaces.\nPlease try again\n");
						loop = true; }
					else if (newPass.equals(accountInfo.getPassword())) {
						System.out.println("Password cannot be the same as the current password.\n");
						loop = true;
					}
					} while (loop);
				do {
					loop = false;
					System.out.printf("Are you sure to change password for account '%s'?"
							, accountInfo.getUsername());
					System.out.print("\n[Y] Yes \t[N] No\n>>>");
					choice = scan.nextLine();
					if (choice.equalsIgnoreCase("Y")) {
						account.getAccounts_arr().get(i).changePass(newPass);
						System.out.println("Account password changed successfully.");
							
						Main.admin_menu();
					}
					else if (choice.equalsIgnoreCase("N")) {
						System.out.println("Change password cancelled.");
						Main.admin_menu();
					}
					else {loop = true;}
				} while (loop);
					
					}
				}
				if (i == account.getAccounts_arr().size()){
					System.out.print("Invalid input, Please choose among the Account ID\n\n");
					loop = true; }
				
				} catch (Exception e) {
					System.out.println("Invalid input, please try again.");
					loop = true;
					
				}
			} while (loop);
			}
			else if (choice.equalsIgnoreCase("B")) {
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
				
			}
			
			else {
				System.out.println("Invalid input, please try again");
				loop = true;
			}
			} while (loop);
			break;
			
		case "2":
			do {
			loop = false;
			System.out.print("\n\n[A] Add new item \t[R] Remove item \t[B] Back"
					+ "\n>>>");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("A")) {
				
				item.addItem();
			}
			else if (choice.equalsIgnoreCase("R")) {
				item.removeItem();
			}
			else if (choice.equalsIgnoreCase("B")) {
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
				
			}
			else {
				System.out.println("Invalid input, please try again");
				loop= true;
			}
			System.out.print("\n");
			
			} while (loop);
			break;
		case "3":
			item.updateItem();
			break;
			
		case "4":
			item.archive();
			break;
			
		case "5":
			if (item.getItems_arr().isEmpty()) {
				System.out.println("No item found");
				System.out.println("\n\nPress 'Enter' to return."); 
				scan.nextLine();
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
			}
			
			System.out.println("  Item ID |   Item Name           |   Quantity    |   Date/Time Added");
	        System.out.println("--------------------------------------------------------------------------");
			for (int i = 0; i < item.getItems_arr().size(); i++) {
				Items item_info = item.getItems_arr().get(i);
				System.out.printf("    %-6d|   %-20s|     %-10d|   %-20s\n",
						item_info.getItem_ID(), 
						item_info.getItem_name(),
						item_info.getQuantity(),
						item_info.getDT());
			}
			System.out.println("\n\nPress 'Enter' to return."); 
			scan.nextLine();
			Main.admin_menu();
			break;
		case "6":
			do {
			System.out.print("\n\n[L] Log out \t[E] Exit \t[B] Back \n>>>");
			choice = scan.nextLine();
			if (choice.equalsIgnoreCase("L")) {
				System.out.println("Logged Out.");
				login();
			}
			else if (choice.equalsIgnoreCase("E")) {
				System.exit(0);
			}
			else if (choice.equalsIgnoreCase("B")) {
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
				
			}
			else {
				loop = true; 
			System.out.println("Invalid input, please try again.");
			} 
			} while (loop);
			
			break;
			
		default:
			System.out.println("Invalid input, please try again.\n>>>");
			loop = true;
		}
		} while (loop);
	}
	
	static void user_menu () {
		condition = true;
		System.out.println("\n\n\tInventory System");
		System.out.print("\n1. Add/Remove Item"
				+ "\n2. Update"
				+ "\n3. Archive"
				+ "\n4. Display Item"
				+ "\n5. Logout/Exit"
				+ "\n\n>>>");
		do {
			loop = false;
		String choice = scan.next();
		scan.nextLine();
		
		switch (choice) {
		case "1":
			do {
				
				loop = false;
				System.out.print("\n\n[A] Add new item \t[R] Remove item \t[B] Back"
						+ "\n>>>");
				choice = scan.nextLine();
				if (choice.equalsIgnoreCase("A")) {
					item.addItem();
				}
				else if (choice.equalsIgnoreCase("R")) {
					item.removeItem();
				}
				else if (choice.equalsIgnoreCase("B")) {
					if (Main.condition) {
						Main.user_menu();
					}
					Main.admin_menu();
					
				}
				
				else {
					System.out.println("Invalid input, please try again");
					loop= true;
				}
				System.out.print("\n");
				
			} while (loop);
			break;
		case "2":
			item.updateItem();
			break;
			
		case "3":
			item.archive();
			break;
			
		case "4": // display
			if (item.getItems_arr().isEmpty()) {
				System.out.println("No item found");
				System.out.println("\n\nPress 'Enter' to return."); 
				scan.nextLine();
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
			}
			System.out.println("  Item ID |   Item Name           |   Quantity    |   Date/Time Added");
	        System.out.println("--------------------------------------------------------------------------");
			for (int i = 0; i < item.getItems_arr().size(); i++) {
				Items item_info = item.getItems_arr().get(i);
				System.out.printf("    %-6d|   %-20s|     %-10d|   %-20s\n",
						item_info.getItem_ID(), 
						item_info.getItem_name(),
						item_info.getQuantity(),
						item_info.getDT());
			}
			System.out.println("\n\nPress 'Enter' to return."); 
			scan.nextLine();
			if (Main.condition) {
				Main.user_menu();
			}
			Main.admin_menu();
			break;
		case "5":
			do {
				System.out.print("\n\n[L] Log out \t[E] Exit [B] Back\n>>>");
				choice = scan.nextLine();
				if (choice.equalsIgnoreCase("L")) {
					System.out.println("Logged Out.");
					login();
				}
				else if (choice.equalsIgnoreCase("E")) {
					System.exit(0);
				}
				else if (choice.equalsIgnoreCase("B")) {
					if (Main.condition) {
						Main.user_menu();
					}
					Main.admin_menu();
					
				}
				else {
					loop = true; 
				System.out.println("Invalid input, please try again.");
				} 
				} while (loop);
				break;
				
		default:
			System.out.println("Invalid input, please try again.\n\n>>>");
			loop = true;
		}
		} while (loop);
		
	}
}
