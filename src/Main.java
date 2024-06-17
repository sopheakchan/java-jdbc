import model.dao.CustomerDaoImpl;
import model.dao.OrderDaoImpl;
import model.entity.Customer;
import model.entity.Order;
import model.entity.Product;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        In case we make a product order more than one, try to loop it
        List<Integer> productId = new ArrayList<>(List.of(1,2));
        for (Integer i: productId){
            new OrderDaoImpl()
                    .addNewOrder(Order.builder()
                            .id(1)
                            .orderName("Green tea")
                            .orderDescription("Tasty")
                            .orderAt(Date.valueOf(LocalDate.now()))
                            .customer(Customer.builder()
                                    .id(4)
                                    .build())
                            .productList(new ArrayList<>(
                                    List.of(Product.builder()
                                            .id(i)
                                            .build())
                            ))
                            .build());

        }

//      #Insert order linked with product id
//        new OrderDaoImpl()
//                .addNewOrder(Order.builder()
//                        .id(1)
//                        .orderName("Green tea")
//                        .orderDescription("Tasty")
//                        .orderAt(Date.valueOf(LocalDate.now()))
//                        .customer(Customer.builder()
//                                .id(4)
//                                .build())
//                        .productList(new ArrayList<>(
//                                List.of(Product.builder()
//                                                .id(2)
//                                        .build())
//                        ))
//                        .build());

//      #For Update
//        new OrderDaoImpl()
//                .updateOrderById(1);

//        #Delete an order
//        new OrderDaoImpl()
//                .deleteOrderById(2);


////      #Display all listed orders
//        new OrderDaoImpl()
//                .queryAllOrders()
//                .forEach(System.out::println);


//        #For making new order
//        new OrderDaoImpl()
//                .addNewOrder(
//                        Order.builder()
//                                .id(2)
//                                .orderName("Pizza Company")
//                                .orderDescription("Highly recommended")
//     //On customer we need to input the object customer which is the one is exist
//                                .customer(Customer.builder()
//                                        .id(2)
//                                        .build())
//                                .orderAt(Date.valueOf(LocalDate.of(2002,10,1)))
//                                .build()
//                );

//        #Update customer
//        new CustomerDaoImpl()
//                .updateAllCustomersById(1);
//        -------------------------------------
//          new CustomerDaoImpl()
//                  .updateAllCustomersById(2);

//        #Delete by ID
//        new CustomerDaoImpl()
//                .deleteAllCustomersById(1);
//

//        #Display all the list
//        new CustomerDaoImpl()
//                .queryAllCustomers()
//                .forEach(System.out::println);

//        #Add new customer using builder
//            new CustomerDaoImpl()
//                    .addNewCustomersById(
//                            Customer.builder()
//                                    .name("Lanith")
//                                    .email("lanith1101@gmail.com")
//                                    .password("11012024")
//                                    .isDeleted(false)
//                                    .createdDate(Date.valueOf(LocalDate.now()))
//                                    .build()
//                    );

    }
}