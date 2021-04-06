import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

class AdminModule {

    int Price;
    String Product;
    public HashMap<String, Integer> ProductUnitprice = new HashMap<>();

    public void AddProduct() {
        try {
            Product = JOptionPane.showInputDialog("Enter the Product name :");
            Price = Integer.parseInt(JOptionPane.showInputDialog("Enter the price of " + Product));
            ProductUnitprice.putIfAbsent(Product, Price);
        }//end of try block
        catch (HeadlessException | NumberFormatException e) {
        }//end of catch block

    }//end of AddProduct()

    public void UpdateProduct() {
        
            int n = Integer.parseInt(JOptionPane.showInputDialog("Do you want to Update the price of any product?\n"
                    + "Enter 1 if You want:\n" + "else Enter 0: "));
            switch (n) {
                case 1: {
                    String str = JOptionPane.showInputDialog("Update the price of product:");
                    try{
                    if (ProductUnitprice.containsKey(str)) {
                        Price = Integer.parseInt(JOptionPane.showInputDialog("Enter the Updated price of " + str+" in Rs."));
                        ProductUnitprice.put(str, Price);
                        System.out.println("Price of " + str + " is successfully Updated.");
                    }//end of if block
                    
                    
                    else {
                        System.out.println("The Product ,you Want to Update is not found in the Stock:");
                    }//end of else block
                    }
                    catch(HeadlessException | NumberFormatException e)
                    {}
                }//end of case 1
                break;
                default:
                    break;
            }//end of switch block

    } //end of UpdateProduct()

    public void RemoveProduct() {

        String str1 = JOptionPane.showInputDialog("Enter the Product you want to remove:");

        if (ProductUnitprice.containsKey(str1)) {
            Product = str1;
            Price = ProductUnitprice.remove(Product);
            System.out.println("Product " + str1 + " is removed successfully from the stocks:");
        }//end of if block
        else {

            System.out.println(" The Product you want to remove is not in the stocks : ");
        }//end of else block

    }//end of RemoveProduct()

    public void ViewStocks() {
        ProductUnitprice.entrySet().stream().map((entry) -> {
            
            return entry;
        }).forEach((entry) -> {
            System.out.println("\t\t  "+entry.getKey() + "    \t\t" + entry.getValue()+"Rs.");
        });
    }//end of ViewStocks()
//Starting of ConsumerModule()
    int quantity;
    String product;
    LinkedHashMap<String, Integer> productQuantity = new LinkedHashMap<>();

    public void addproduct() {
        String s = JOptionPane.showInputDialog("Enter the Product you wish to purchase:");
        if (ProductUnitprice.containsKey(s)) {
            quantity = Integer.parseInt(JOptionPane.showInputDialog("How many " + s + " you wish to purchage :"));
            productQuantity.put(s, quantity);
        }//end of if block
        else {
            System.out.println(" Sorry:\n"+s+" is not Available:\n ");
        }//end of else block
    }//end of addroduct()

    public void viewbasket() {
        productQuantity.entrySet().stream().map((entry) -> {
            return entry;
        }).forEach((entry) -> {
            System.out.println("\t\t  "+entry.getKey() + "    \t\t" + entry.getValue());
        });
    }//end of viewbasket()

    public void search() {

        String s = JOptionPane.showInputDialog("Search a product here :");
        if (ProductUnitprice.containsKey(s)) {
            int n = Integer.parseInt(JOptionPane.showInputDialog("Do you want to add buy this "+ s+" ?\n "
                    + "If yes :Enter 1 else 0 "));
            if (n == 1) {
                quantity = Integer.parseInt(JOptionPane.showInputDialog("How many " + s + " You want to buy:" + quantity));
                productQuantity.put(s, quantity);
                System.out.println(quantity);

            }//end of nested if block
        }//end of if block
        else {
            System.out.println("product is not found ");
        }//end of else block
    }//end of search method()
    public void removeproduct() {
        String s = JOptionPane.showInputDialog("Enter the Product you want to remove from basket");

        if (productQuantity.containsKey(s)) {
            product = s;
            quantity = productQuantity.remove(product);
            System.out.println("Product " + s + " is removed successfully from shopping basket:");
        }//end of if block
        else {
            System.out.println(" The Product you want to remove is not added yet in the basket :");
        }//end of else block
    }//end of removeproduct()

    public void printinvoice() {

        int sum =0;
        long Total=0;
        System.out.println("---------------------------------------------------------------------"
                + "-------------------------------------------");
        System.out.println("*******************************************"
                + "******************************************");
        System.out.println("                          ***Your Invoice***");
        System.out.println("                      --------------------------");
        String name = JOptionPane.showInputDialog("Enter your Name:");
        
        long mobile = Long.parseLong(JOptionPane.showInputDialog("Enter your Mobile No."));
        System.out.println("\tName::"+name+"\t\t\t\tCont."+mobile);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"+ "   "+" HH:mm:ss");
        Date date = new Date();
        System.out.println("\tTime:" + java.time.LocalTime.now() + "     \t\tDate:" + java.time.LocalDate.now());
        System.out.println("\t"+"Product ::"+" Quantity "+" Unitprice "+" Sum");
        for (Iterator<Map.Entry<String, Integer>> it = productQuantity.entrySet().iterator(); it.hasNext();) {
            HashMap.Entry<String, Integer> entry = it.next();
            sum = ProductUnitprice.get(entry.getKey()) * entry.getValue();
            System.out.println("\t"+entry.getKey()+"::"+" "+entry.getValue()+" "
                    +" "+ProductUnitprice.get(entry.getKey())+"Rs."+" =  " +sum+"Rs.");
            Total += sum;
        }
        System.out.println("*************************************************"
                + "********************************************");
        System.out.println("\t\t\t   Total=" + Total+"Rs.");
    }//end of printinvoice()
}//end of AdminModule class

public class RetailEncore {

    public static void main(String[] args) throws Exception {
        int num = 0;
        int num2 = 0;

        AdminModule obj1 = new AdminModule();
        JOptionPane.showInputDialog("**WELCOME TO RETAIL ENCORE**\n"
                + " >>DEVELOPED BY ANUJPRj :\n Enter a Character to proceed: ");
        do {
            try {
                num = Integer.parseInt(JOptionPane.showInputDialog("WELCOME TO ADMIN MODULE:\n" 
                        + "\nPress:\n"
                        + " 1. Add Product in Stock(ProductName ,UnitPrice)\n"
                        + " 2. Update Product in the Stock\n"
                        + " 3. Remove Product from Stock\n"
                        + " 4. View all the Product in the Stock\n"
                        + " 5. Logout the AdminModule"));
           
            switch (num) {
                
                case 1: {
                    obj1.AddProduct();
                }
                break;
                case 2: {
                    obj1.UpdateProduct();
                }
                break;
                case 3: {
                    obj1.RemoveProduct();
                }
                break;
                case 4: {
                    System.out.println("Products in the stocks are:");
                    System.out.println("\t\t  "+"Product"+"\t\t"+"UnitPrice");
                    obj1.ViewStocks();
                }
                break;
                case 5: {
                    System.out.println("You logout successfully from AdminModule:");
                }
                default:
                    JOptionPane.showInputDialog("Invalid Entry:\nRetry Again\nPlease Enter a Digit:");
                    break;
            }//end of switch case
             }//end of try block
            catch (HeadlessException | NumberFormatException ex) {
                System.out.println("Wrong Input");
            }//end of catch block
        } while (num != 5);//end of dowhile loop
//end of Admin module
        System.out.println("---------------*************************************");
        System.out.println("---------------*************************************");

       
            do {
                try {
                num2 = Integer.parseInt(JOptionPane.showInputDialog("***Welcome to ConsumerModule***" + "\n"
                        + "You are at level" + num2
                        + "\nEnter:"
                        + " 1:view all the Products in the Stock: \n"
                        + " 2:add Products to Shoping basket: \n"
                        + " 3:View all Products in the basket: \n"
                        + " 4:Search Product in the Stock: \n"
                        + " 5:Remove Product from basket: \n"
                        + " 6: To print invoice:\n"
                        + " 7:Signout:\n \n"));
                }//end of try block
                catch (HeadlessException | NumberFormatException ex) {
                } //end of catch block
                switch (num2) {
                    case 1: {
                        System.out.println("Available products in the stocks are: ");
                        System.out.println("\t\t  "+"Product"+"\t\t"+"UnitPrice");
                        obj1.ViewStocks();
                    }
                    break;
                    case 2: {
                        obj1.addproduct();
                    }
                    break;
                    case 3: {
                        System.out.println("Products in your basket are:");
                        System.out.println("\t\t  "+"Product"+"\t\t"+"Quantity");
                        obj1.viewbasket();
                    }
                    break;
                    case 4: {
                        obj1.search();
                    }
                    break;
                    case 5: {
                        obj1.removeproduct();
                    }
                    break;
                    case 6: {
                        obj1.printinvoice();
                    }
                    break;
                    case 7: {
                        System.out.println("        \t\t******Thanyou for Shopping******");
                    }
                    break;

                    default:
                        System.out.println("Invalid Entry : Retry Again");
                }

            } while (num2 != 7);//end of dowhile loop
        
    }//end of main()
}//end of REtail Encore class
//end of Program
