import za.ac.tut.dish.Dish;
import za.ac.tut.disha.DishA;
import za.ac.tut.order.Order;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PapasCollectionPc {

    public static String readData(Socket socket, Queue<Order> orders ) throws IOException, ClassNotFoundException {

        String data = "", name = "", records = "";
        int orderNo = 0,  qty = 0;
        Order order = null;
        double price = 0, payment = 0, change = 0;
        BufferedReader in = null;
        ObjectInputStream inObj = null;
        List<Dish> dishes = new ArrayList();

        inObj = new ObjectInputStream(socket.getInputStream());
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        order = (Order) inObj.readObject();
        data = in.readLine();

        String[] tokens = data.split("#");

        payment = Double.parseDouble(tokens[0]);
        change = Double.parseDouble(tokens[1]);

        dishes = order.getDishes();

        for (Dish dish : dishes) {

            orderNo = order.getOrderNo();
            name = dish.getName();
            price = dish.getPrice();
            qty = dish.getQty();

            System.out.println("Order number:" + orderNo + "\n"
                    + "Order Name: " + name + "\n"
                    + "Price : " + price + "\n"
                    + "Quantity: " + qty + "\n"
                    + "Payment: " + payment + "\n"
                    + "Change: " + change + "\n");


            records = records.concat( orderNo +"#"+ name +"#"+ price + "#"+ qty + "#"+ payment + "#"+ change);
        }
        System.out.println(records);
        return records;

    }

    public static Timestamp getTime() {

        Date date = new Date();
        long time = date.getTime();
        Timestamp ts;
        ts = new Timestamp(time);

        System.out.println("Current Time Stamp: " + ts);

        return ts;

    }

    public static void  addOrderToDataBase(String records,Timestamp ts,Connection connect) throws SQLException {
        String name = "";
        int orderNo = 0, qty = 0;
        double price = 0, payment = 0, change = 0;

        String [] token = records.split("#");
        orderNo = Integer.parseInt(token[0]);
        name = token[1];
        price = Double.parseDouble(token[2]);
        qty = Integer.parseInt(token[3]);
        payment =  Double.parseDouble(token[4]);
        change =  Double.parseDouble(token[5]);



            // getting the connection

            PreparedStatement insert = connect.prepareStatement("insert into order_details_tb values(?,?,?,?,?,?,?)");
            insert.executeUpdate();

            insert.setInt(1,orderNo);
            insert.setString(2,name);
            insert.setDouble(3,price);
            insert.setInt(4,qty);
            insert.setDouble(5,payment);
            insert.setDouble(6,change);
            insert.setTimestamp(7,ts);

        insert.executeUpdate();

    }

    public static void main(String[] args) {
        // TODO code application logic here

        ServerSocket s = null;
        Socket socket = null;
        Queue<Order> orders = new LinkedList();
        Timestamp ts;
        String records = "";
        // personal informations
        String URL="jdbc:mysql://localhost:3306/ drive_through_db";
        String PASSWD="Fifimbula@10";
        String USER = "root";
        Connection connect = null;

        try {

            s = new ServerSocket(9191);
            socket = s.accept();
            System.out.println("Connection established server is running on port: 9191");

            connect = DriverManager.getConnection(URL, USER, PASSWD);

            records = readData(socket, orders );
            ts = getTime();
            addOrderToDataBase(records,ts,connect);

        } catch (IOException ex) {
            Logger.getLogger(PapasCollectionPc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PapasCollectionPc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {

                System.out.println("Connection closing...");
                socket.close();

            } catch (IOException ex) {
                Logger.getLogger(PapasCollectionPc.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
