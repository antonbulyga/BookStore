package com.senla.model.сontrollers;

import com.senla.model.dto.OrderDto;
import com.senla.model.entity.Order;
import com.senla.model.service.api.OrderService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping("orders/import")
    public String importOrder(){
        orderService.importOrder();
        return "Order has been imported successfully";
    }

    @GetMapping("orders/export")
    public String exportOrder(){
        orderService.exportOrder();
        return "Order has been exported successfully";
    }

    @GetMapping("orders/sum_of_money")
    public List<OrderDto> sumOfMoneyPerPeriodOfTime(@PathVariable LocalDate date1,@PathVariable LocalDate date2) {
       List<Order> orders = orderService.sumOfMoneyPerPeriodOfTime(date1, date2);
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @GetMapping("orders")
    public List<OrderDto> getListOfOrders() {
        List<Order> orders = orderService.getListOfOrders();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
       return orderDtoList;
    }

    @PostMapping("orders/create")
    public OrderDto addOrderToListOfOrders(@RequestBody OrderDto orderDto){
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.addOrderToListOfOrders(order);
        return orderDto;
    }

    @GetMapping("orders/{id}")
    public OrderDto getOrderById(@PathVariable String id){
        int orderId = Integer.parseInt(id);
        OrderDto orderDto;
        try {
            Order order = orderService.getOrderById(orderId);
            orderDto = dtoConverter.orderEntityToDto(order);
        }
        catch (NoResultException e){
            throw new NoResultException("No Order with this ID");
        }

        return orderDto;
    }

    @PostMapping("orders/update")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.updateOrder(order);
        return orderDto;
    }

    @GetMapping("orders/sort/date_of_done")
    public List<OrderDto> sortOrdersByDateOfDone(){
        List<Order> orders = orderService.sortOrdersByDateOfDone();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;

    }
    @GetMapping("orders/sort/price")
    public List<OrderDto> sortOrdersByPrice(){
        List<Order> orders =  orderService.sortOrdersByPrice();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
    @GetMapping("orders/sort/status")
    public List<OrderDto> sortOrdersByStatus(){
        List<Order> orders =  orderService.sortOrdersByStatus();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @DeleteMapping("orders/delete")
    public Order deleteOrder(@RequestBody OrderDto orderDto){
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.deleteOrder(order);
        return order;
    }
/*
    @GetMapping("orders/change_status_to_cancel")
    public void changeOrderStatusToCancelled(@PathVariable Order order){
        orderService.changeOrderStatusToCancelled(order);
    }*/

    @GetMapping("orders/count_done_by_period")
    public int countOfDoneOrdersByPeriodOfTime(@PathVariable List<Order> orders,@PathVariable LocalDate date1, LocalDate date2){
       int count = orderService.countOfDoneOrdersByPeriodOfTime(orders, date1, date2);
       return count;
    }


}
