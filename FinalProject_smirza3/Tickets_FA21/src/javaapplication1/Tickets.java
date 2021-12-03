package javaapplication1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

@SuppressWarnings("serial")
public class Tickets extends JFrame implements ActionListener {

	// class level member objects
	Dao dao = new Dao(); // for CRUD operations
	Boolean chkIfAdmin = null;

	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuTickets = new JMenu("Tickets");

	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItemExit;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem mnuItemOpenTicket;
	JMenuItem mnuItemViewTicket;

	//add an option for users to close their ticket if they fix their own issue
	JMenuItem mnuItemCloseTicket;

	public Tickets(Boolean isAdmin) {

		chkIfAdmin = isAdmin; // checks if the user that logged in is admin (1) or not (0)
		createMenu(); // runs the create menu method to add menu items
		prepareGUI(); // creates a gui

	}

	private void createMenu() {

		/* Initialize sub menu items **************************************/

		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);

		// initialize first sub menu items for Admin main menu
		mnuItemUpdate = new JMenuItem("Update Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemUpdate);

		// initialize second sub menu items for Admin main menu
		mnuItemDelete = new JMenuItem("Delete Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemDelete);

		// initialize first sub menu item for Tickets main menu
		mnuItemOpenTicket = new JMenuItem("Open Ticket");
		// add to Ticket Main menu item
		mnuTickets.add(mnuItemOpenTicket);

		// initialize second sub menu item for Tickets main menu
		mnuItemViewTicket = new JMenuItem("View Ticket");
		// add to Ticket Main menu item
		mnuAdmin.add(mnuItemViewTicket);

		// initialize any more desired sub menu items below
		// ADDED:
		mnuItemCloseTicket = new JMenuItem("Close Ticket");
		//add to Ticket Main menu item
		mnuTickets.add(mnuItemCloseTicket);


		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(this);
		mnuItemUpdate.addActionListener(this);
		mnuItemDelete.addActionListener(this);
		mnuItemOpenTicket.addActionListener(this);
		mnuItemViewTicket.addActionListener(this);
		//added:
		mnuItemCloseTicket.addActionListener(this);

		 /*
		  * continue implementing any other desired sub menu items (like 
		  * for update and delete sub menus for example) with similar 
		  * syntax & logic as shown above*
		 */


	}

	private void prepareGUI() {

		// create JMenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		if(chkIfAdmin == true) {
			bar.add(mnuAdmin);
		}
		else {
			//empty
		}
		bar.add(mnuTickets);
		// add menu bar components to frame
		setJMenuBar(bar);

		addWindowListener(new WindowAdapter() {
			// define a window close operation
			public void windowClosing(WindowEvent wE) {
				System.exit(0);
			}
		});
		// set frame options
		setSize(400, 400);
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// implement actions for sub menu items
		if (e.getSource() == mnuItemExit) {
			System.exit(0);
		}
		else if (e.getSource() == mnuItemOpenTicket) {

			// get ticket information
			String ticketName = JOptionPane.showInputDialog(null, "Enter your name");
			String ticketDesc = JOptionPane.showInputDialog(null, "Enter a ticket description");

			// insert ticket information to database

			int id = dao.insertRecords(ticketName, ticketDesc);

			// display results if successful or not to console / dialog box
			if (id != 0) {
				System.out.println("Ticket ID : " + id + " created successfully!!!");
				JOptionPane.showMessageDialog(null, "Ticket id: " + id + " created");
			} else
				System.out.println("Ticket cannot be created!!!");
		}

		else if (e.getSource() == mnuItemViewTicket) {

			// retrieve all tickets details for viewing in JTable
			try {

				// Use JTable built in functionality to build a table model and
				// display the table model off your result set!!!
				JTable jt = new JTable(ticketsJTable.buildTableModel(dao.readRecords()));
				jt.setBounds(30, 40, 200, 400);
				JScrollPane sp = new JScrollPane(jt);
				add(sp);
				setVisible(true); // refreshes or repaints frame on screen

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		else if (e.getSource() == mnuItemDelete) {

			// delete a ticket
			try {
				// get id of ticket
				String ticketID = JOptionPane.showInputDialog(null,
						"Enter ticket ID to be deleted");

				//check to see if user wants to continue with delete
				int response = JOptionPane.showConfirmDialog(null,
						"Do you want to continue with this action?",
						"Delete This Ticket",
						JOptionPane.YES_NO_OPTION);
				if(response == JOptionPane.YES_OPTION) {
					// delete ticket associated with ID
					dao.deleteRecords(ticketID);
					JOptionPane.showMessageDialog(null,
							"Ticket ID: " + ticketID + " was deleted.");
					System.out.println("Record has been deleted");
				}
				else {
					dispose();
				}
			}
			catch (Exception se) {
				se.printStackTrace();
			}
		}

		else if (e.getSource() == mnuItemUpdate) {

			// update a ticket
			try {

				String ticketID = JOptionPane.showInputDialog(null, "Enter ticket ID to be updated");

				//check for which parameter wants to be updated
				// array with parameter choices
				String[] choices = {"Update Name", "Update Description"};
				String response = (String) JOptionPane.showInputDialog(null,
						"What would you like to update?",
						"Update a Ticket",
						JOptionPane.QUESTION_MESSAGE,
						null,
						choices,
						choices[0]);

				//set input to oldparam value
				String oldParam;
				String newParam;
				if(response.equals("Update Name")) {
					oldParam = "ticket_issuer";
					newParam = JOptionPane.showInputDialog(null, "Enter new name");
				}
				else {
					oldParam = "ticket_description";
					newParam = JOptionPane.showInputDialog(null, "Enter new description");
				}

				// run updateRecords
				dao.updateRecords(ticketID, oldParam, newParam);
				System.out.println("Changes were made to ticket " + ticketID + "...");
				JOptionPane.showMessageDialog(null, "Ticket ID: " + ticketID + " was updated.");
			}
			catch (Exception se) {
				se.printStackTrace();
			}

		}

		else if (e.getSource() == mnuItemCloseTicket) {
			try {
				// get id of ticket
				String ticketID = JOptionPane.showInputDialog(null, "Enter ticket to be closed");

				//check to see if user wants to continue with close
				int response = JOptionPane.showConfirmDialog(null,
						"Do you want to continue with this action?",
						"Close This Ticket",
						JOptionPane.YES_NO_OPTION);
				if (response == JOptionPane.YES_OPTION) {
					// run close records
					dao.closeRecords(ticketID);
					JOptionPane.showMessageDialog(null,
							"Ticket ID: " + ticketID + " was closed.");
					System.out.println("Record has been closed");
				}
				else {
					dispose();
				}
			}
			catch (Exception se) {
				se.printStackTrace();
			}
		}
	}

}
