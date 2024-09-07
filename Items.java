package p3_project;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Items {
	public Scanner scan = new Scanner(System.in);
	
	public boolean loop, condition;
	
	private String item_name;
	private int quantity;
	private int item_id;
	private String date_time;
        public String p = "";
        public String acc_LoggedIn = Main.acc_LoggedIn;
	
	public Items (String acc_LoggedIn, int item_id, String item_name, int quantity, String date_time) {
		Main.acc_LoggedIn = acc_LoggedIn;
		this.item_id = item_id;
		this.item_name = item_name;
		this.quantity = quantity;
		this.date_time = date_time;
	}
	
	
	public ArrayList <Items> getItems_arr () {
		return items_Arr;
	}
//	public String getAcc_login () {
//		return acc_LoggedIn;
//	}
	
	public int getItem_ID () {
		return item_id;
	}
	
	public String getItem_name () {
		return item_name;
	}
	
	public int getQuantity () {
		return quantity;
	}
//	public String getAcc_log {
//		return acc_LoggedIn;
//	}
	public String getDT () {
		return date_time;
	}
	
	public String assignDT () {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		LocalDateTime date_time = LocalDateTime.now();
		return date_time.format(formatter);
	}
	
	// count of total items added
	int item_added = 0;
	
	public int item_id_assign (int a) {
		item_added ++;
		item_id = item_added;
		return item_id;
	}
	private static ArrayList <Items> items_Arr = new ArrayList <Items>();
	private static ArrayList <Items> archive_Arr = new ArrayList <Items>();
        
	public void item_autoAdd () {
		Items item1 = new Items("", item_id_assign(item_added), "HP laptop", 5, assignDT());
		Items item2 = new Items("", item_id_assign(item_added), "Iphone 15 pro max", 5, assignDT());
		Items item3 = new Items("", item_id_assign(item_added), "Samsung A71", 1, assignDT());
		Items item4 = new Items("", item_id_assign(item_added), "Iphone 15", 10, assignDT());
		
		items_Arr.add(item1);
		items_Arr.add(item2);
		items_Arr.add(item3);
		items_Arr.add(item4);
	}
	
//	private Items item = new Items(null, 0, null);
	
	public void addItem () {
		loop = false;
		System.out.print("\tAdd item\n");
		do {
			System.out.print("Enter item name: ");
			item_name = scan.nextLine();
			for (int i = 0; i < items_Arr.size() ; i++) {
				Items item_name = items_Arr.get(i);
				if (this.item_name.equalsIgnoreCase(item_name.getItem_name())) {
					System.out.println("Item already existed\n\n");
					loop = true;
					break;
				}
			}
			for (int i = 0; i < archive_Arr.size() ; i++) {
				Items item_name = archive_Arr.get(i);
				if (this.item_name.equalsIgnoreCase(item_name.getItem_name())) {
					System.out.println("Item already existed\n\n");
					loop = true;
					break;
				}
			}
		} while (loop);
		
		quantity = 0;
		do {
			try {
				System.out.print("\nEnter quantity: ");
				p = scan.nextLine();
				quantity = Integer.parseInt(p);
				if (quantity <=0) {
					System.out.println("\n\nInvalid input, please enter number 1 or above");
					loop = true;
				}
				else {
					loop = false;
				}
			} catch (Exception e) {
				System.out.print("\n\nInvalid input, please try again");
				loop = true;
			}
		} while (loop);
		Items newItem = new Items (Main.acc_LoggedIn, item_id_assign(item_added), item_name, quantity, assignDT());
		items_Arr.add(newItem);
		System.out.println("Item Added Successfully!");
		
		do {
			System.out.print("\nAdd another item?\n"
					+ "[Y] Yes\t [N] no\n>>>");
			String choice = scan.nextLine();
			
			if (choice.equalsIgnoreCase("Y")) {
				addItem();
			}
			else if (choice.equalsIgnoreCase("N")) {
				loop = false;
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
			}
			else {
				System.out.println("Invalid input, please try again.");
				loop = true;
			}
		} while (loop);
		if (Main.condition) {
			Main.user_menu();
		}
		Main.admin_menu();
	}
	
	int item_no = 0;
	public int items_size = items_Arr.size();
	public void removeItem () {
		Accounts account = new Accounts (0, "", "");
//		items_size = items_Arr.size();
		condition = false;
		if (items_Arr.size() == 0) {
			System.out.println("\nNo Item Found");
			if (Main.condition) {
				Main.user_menu();
			}
			Main.admin_menu();
		}
		do {
		System.out.println("\n\n\tRemove item");
		
		System.out.println("  Item ID  |   Item Name           |   Quantity   |   Date/Time Added");
        System.out.println("-----------------------------------------------------------------------");
		int i = 0;
		int index_toRemove;
		// display purpose
		Items item_info = items_Arr.get(i);
		for (i = 0 ; i < items_Arr.size(); i++) {
			item_info = items_Arr.get(i);	
			System.out.printf("    %-7d|   %-20s|    %-10d|   %-20s\n", 
					item_info.getItem_ID(),
					item_info.getItem_name(),
					item_info.getQuantity(), 
					item_info.getDT());
		}
		do {
			try {
				System.out.print("\n>>>");
				p = scan.nextLine();
				item_no = Integer.parseInt(p);
				condition = true; // variable for error trapping
				for (i = 0 ; i < items_Arr.size(); i++) {
					item_info = items_Arr.get(i);	
				
				if (item_no == item_info.getItem_ID()) {
					index_toRemove = i;
					condition = false;
					System.out.printf("Are you sure to remove item '%s'?", item_info.getItem_name());
					System.out.print("\n\n[Y] Yes \t [N] No\n");
					do {
						loop = false;
					System.out.print(">>>");
					String choice = scan.nextLine();
					if (choice.equalsIgnoreCase("Y")) {
						Items archivedItem = new Items (Main.acc_LoggedIn, 
								item_info.getItem_ID(),
								item_info.getItem_name(),
								item_info.getQuantity(),
								item_info.assignDT());
						archive_Arr.add(archivedItem);
						items_Arr.remove(index_toRemove);
						System.out.println("Item Removed Successfully!\n\n");
						loop = false;
					}
					else if (choice.equalsIgnoreCase("N")) {
						System.out.println("Item Removal Canceled\n\n");
						loop = false;
						if (Main.condition) {
							Main.user_menu();
						}
						Main.admin_menu();
					}	
					else {
						System.out.print("Invalid input, please try again.\n\n");
						loop = true;
					}
					
				} while (loop);
					break;
				}
				}
				if (i == items_Arr.size() & condition) {
				System.out.println("\n\nInvalid input, please refer to\nthe item ID above.\n\n");
				System.out.println("  Item ID |   Item Name           |   Quantity    |   Date/Time Added");
				System.out.println("--------------------------------------------------------------------------");
				for (i = 0 ; i < items_Arr.size(); i++) {
					item_info = items_Arr.get(i);	
					System.out.printf("    %-6d|   %-20s|     %-10d|   %-20s\n", item_info.getItem_ID(), item_info.getItem_name(), item_info.getQuantity(), item_info.getDT());
				}
				loop = true;
				}
				
			} catch (Exception e) {
				System.out.print("\nInvalid input, please try again");
				loop = true;
			}
		} while (loop);
		
		System.out.print("\nRemove another item?\n"
				+ "[Y] Yes\t [N] no\n>>>");
		do {
			String choice = scan.nextLine();
			if (choice.equalsIgnoreCase("Y")) {
				removeItem();
			}
			else if (choice.equalsIgnoreCase("N")) {
				loop = false;
				if (Main.condition) {
					Main.user_menu();
				}
				Main.admin_menu();
			}
			else {
				System.out.println("Invalid input, please try again.\n\n>>>");
				loop = true;
			}
		} while (loop);
		
		} while (loop);
	}
	
	public void updateInfoname (String newName) {
		this.item_name = newName;
	}
	public int updateInfoquantity (int result) {
		this.quantity = result;
		return quantity;
	}
	
	public void updateItem () {
		int index_toEdit;
//		items_size = items_Arr.size();
		System.out.println("\n\n\tSelect Item to Update\n"
				+ "  Item ID  |   Item Name           |   Quantity   |   Date/Time Added");
		System.out.println("--------------------------------------------------------------------------");
		int i = 0;
		Items item_info = items_Arr.get(i);
		for (i = 0 ; i < items_Arr.size(); i++) {
			item_info = items_Arr.get(i);
			System.out.printf("    %-5d  |   %-20s|    %-10d|   %-20s\n",
					item_info.getItem_ID(), 
					item_info.getItem_name(),
					item_info.getQuantity(),
					item_info.getDT());
		}
		do {
			loop = false;
			try {
			System.out.print("\n\n>>>");
			p = scan.nextLine();
			item_no = Integer.parseInt(p);
			int num_input;
			String choice;
			condition = true;
			for (i = 0; i < items_Arr.size(); i++) {
				item_info = items_Arr.get(i);
				if (item_no == item_info.getItem_ID()) {
					index_toEdit = i; // variable for accessing the right index based on the user item selected
					condition = false;
					do {
						loop = false;
						System.out.print("\n\n\tItem Selected: " + item_info.getItem_name()
						+ "\n\tItem ID: \t" + item_info.getItem_ID()
						+ "\n\tItem Quantity: " + item_info.getQuantity()
						+ "\n\tDate/Time Added: " + item_info.getDT());
						
					System.out.print("\n\n[R] Rename Item \t[E] Edit Item Quantity \t[B] Back\n>>>");
					choice = scan.nextLine();
					// Rename
				if (choice.equalsIgnoreCase("R")) {
						
					System.out.print("Enter new item name: ");
					String newName = scan.nextLine();
					items_Arr.get(index_toEdit).updateInfoname(newName);
					System.out.println("Item Updated Successfully!");
					}
					// Edit quantity
				else if (choice.equalsIgnoreCase("E")) {
				do {
					loop = false;
				System.out.print("\n[A] Add/Increase Quantity\t[D] Decrease Quantity\t[S] Set Quantity\n>>>");
				choice = scan.nextLine().toUpperCase();
				
				switch (choice) {
				case "A":
					System.out.print("Enter number to increase: ");
					num_input = numberEdit(0);
					items_Arr.get(index_toEdit).updateInfoquantity(item_info.getQuantity() + num_input);
					System.out.println("Item Updated Successfully!");
					break;
					
				case "D":
					System.out.print("Enter number to decrease: ");
					do {
					num_input = numberEdit(0);
					if (num_input > items_Arr.get(index_toEdit).getQuantity()) {
						System.out.println("Invalid input, please enter number"
								+ "\nnot greater than current quantity\n");
						System.out.print("Enter Number: ");
						loop = true;
					}
					
					else {
					items_Arr.get(index_toEdit).updateInfoquantity(item_info.getQuantity() - num_input); }
					
					} while (loop);
					System.out.println("Item Updated Successfully!");
					
					break;
				case "S":
					System.out.print("Set new quantity: ");
					num_input = numberEdit(0);
					items_Arr.get(index_toEdit).updateInfoquantity(num_input);
					System.out.println("Item Updated Successfully!");
					break;
					
				default:
					System.out.println("Invalid input, please try again.");
					loop = true;
				}

				}while (loop); 
				}
				else if (choice.equalsIgnoreCase("B")) {
					if (Main.condition) {
						Main.user_menu();
					}
					Main.admin_menu();
					
				}
				
				else {
					System.out.println("\n\nInvalid input, please try again.");
					loop = true;
					}
				} while (loop);
					break;
			}
		}
			

			if (i == items_Arr.size() & condition) {
				System.out.println("\n\nInvalid input, please refer to\nthe item number above.");
				loop = true; }
			} catch (Exception E) {
				System.out.println("Invalid input, please try again.");
				loop = true; }
			} while (loop);
			
			
			
			do {
				loop = false;
				System.out.print("\nEdit another item?\n"
						+ "[Y] Yes\t [N] no\n>>>");
				String choice = scan.nextLine();
				if (choice.equalsIgnoreCase("Y")) {
					updateItem();
				}
				else if (choice.equalsIgnoreCase("N")) {
					if (Main.condition) {
						Main.user_menu();
					}
					Main.admin_menu();
				}
				else {
					System.out.println("Invalid input, please try again.\n>>>");
					loop = true;
				}
			} while (loop);
	}
		
	
	
	
	public int numberEdit (int quantity) {
		do {
			try {
			p = scan.nextLine();
			quantity = Integer.parseInt(p);
			loop = false;
			if (quantity <= 0) {
				System.out.print("Invalid input, please only\nenter number 1 or above\n"
					+ "\nEnter number: ");
					loop = true;
			}
			} catch (Exception e) {
				System.out.print("\nInvalid input, please try again"
						+ "\nEnter number: ");	
				loop = true;
			}
		} while (loop);
		return quantity;
	}	
    
	public void archive () {
		
		if (archive_Arr.isEmpty()) {
			System.out.println("Archive is currently empty.");
			System.out.println("\n\nPress 'Enter' to return."); 
			scan.nextLine();
			if (Main.condition) {
				Main.user_menu();
			}
			Main.admin_menu();
		}
		System.out.print("\tItem Archive\n"
						 + "  Item ID |   Item Name           |   Quantity    |  Removed by  |   Date/Time Removed\n");
		System.out.println("-----------------------------------------------------------------------------------------");
		for (int i = 0; i < archive_Arr.size() ; i++) {
    	   Items item_info = archive_Arr.get(i);
    	   System.out.printf("    %-6d|   %-20s|     %-10d|   %-10s |   %-20s\n", 
    			   item_info.getItem_ID(), 
    			   item_info.getItem_name(),
    			   item_info.getQuantity(), 
    			   item_info.acc_LoggedIn,
    			   item_info.getDT());              
            }
		
		System.out.println("\n\nPress 'Enter' to return."); 
		scan.nextLine();
		if (Main.condition) {
			Main.user_menu();
		}
		Main.admin_menu();
        }
    
}