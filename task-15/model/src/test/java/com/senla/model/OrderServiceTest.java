package com.senla.model;

import com.senla.model.entity.Book;
import com.senla.model.entity.Customer;
import com.senla.model.entity.Order;
import com.senla.model.enumeration.OrderStatus;
import com.senla.model.repository.api.OrderRepository;
import com.senla.model.service.OrderServiceImpl;
import com.senla.model.service.api.BookService;
import com.senla.model.service.api.CustomerService;
import com.senla.model.service.api.RequestForBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @InjectMocks
    private OrderServiceImpl orderService;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private RequestForBookService requestForBookService;
    @Mock
    private CustomerService customerService;
    @Mock
    private BookService bookService;
    @BeforeEach
    public void setUp(){
        ReflectionTestUtils.setField(orderService, "path", "model/src/test/resources/files/Books.csv");
    }
    private final List<Book> bookList = new ArrayList<>();
    private final Customer customer = new Customer();
    private final Order order = new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23);
    private final LocalDate date1 = LocalDate.of(2018,9,22);
    private final LocalDate date2 = LocalDate.of(2019,9,22);

    @Test
    public void shouldSortOrdersByStatus(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> result = orderService.sortOrdersByStatus();
        Assertions.assertEquals(orderList,result);
    }

    @Test
    public void shouldSortOrdersByDateOfDone(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> result = orderService.sortOrdersByDateOfDone();
        Assertions.assertEquals(orderList,result);
    }

    @Test
    public void shouldSortOrdersByPrice() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> result = orderService.sortOrdersByPrice();
        Assertions.assertEquals(orderList,result);
    }

    @Test
    public void shouldGetAll(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> result = orderService.getListOfOrders();
        Assertions.assertEquals(orderList,result);
    }

    @Test
    public void shouldGetListOfDoneOrdersByPeriodOfTime(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        List<Order> returnOrders = orderService.listOfDoneOrdersByPeriodOfTime(date1, date2);
        Assertions.assertNotNull(returnOrders);
        Assertions.assertTrue(returnOrders.size() > 0);
    }
    @Test
    public void getOrderById(){
        when(orderRepository.read(order.getId())).thenReturn(order);
        Order returnOrder = orderService.getOrderById(order.getId());
        Assertions.assertEquals(order, returnOrder);
    }
    @Test
    public void createOrderTest() throws SQLException {
        orderService.addOrderToListOfOrders(order);
        verify(orderRepository, times(1)).create(order);
    }

    @Test
    public void shouldBeDelete(){
        orderService.deleteOrder(order);
        verify(orderRepository, times(1)).delete(order);
    }

    @Test
    public void completingRequestAfterArrivingNewBookDoesNotThrow() {
        Book book = new Book(1, "Гоголь Николай", "Мертвые души", 4, LocalDate.of(2012, 05, 13), LocalDate.of(1998, 01, 05));
        assertDoesNotThrow(() -> bookService.completingRequestAfterArrivingNewBook(book));
    }

    @Test
    public void executeOrderDoesNotThrow() {
        assertDoesNotThrow(() -> orderService.executeOrder(order));
    }

    @Test
    public void exportOrderDoesNotThrow(){
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order( 1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        orderList.add(new Order(1, LocalDate.of(2018,9,22),LocalDate.of(2019,9,22), bookList, OrderStatus.NEW, customer, 23));
        when(orderRepository.getAll()).thenReturn(orderList);
        assertDoesNotThrow(() -> orderService.exportOrder());
    }

    @Test
    public void updateOrderDoesNotThrow(){
        assertDoesNotThrow(() -> orderService.updateOrder(any(Order.class)));
    }

    @Test
    public void sumOfMoneyPeriodOfTimerDoesNotThrow(){
        assertDoesNotThrow(() -> orderService.sumOfMoneyPerPeriodOfTime(date1,date2));
    }
}
