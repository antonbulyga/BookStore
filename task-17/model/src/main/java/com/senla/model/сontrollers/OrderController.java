package com.senla.model.сontrollers;

import com.senla.model.dto.OrderDto;
import com.senla.model.entity.Order;
import com.senla.model.service.api.OrderService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;
    private DtoConverter dtoConverter;

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("import")
    public ResponseEntity<String> importOrder(){
        orderService.importOrder();
        return new ResponseEntity<>(
                "Orders has been import successfully",
                HttpStatus.OK);
    }

    @GetMapping("export")
    public ResponseEntity<String> exportOrder(){
        orderService.exportOrder();
        return new ResponseEntity<>(
                "Orders has been export successfully",
                HttpStatus.OK);
    }

    @GetMapping("show/sum")
    public List<OrderDto> sumOfMoneyPerPeriodOfTime(@PathVariable LocalDate date1,@PathVariable LocalDate date2) {
       List<Order> orders = orderService.sumOfMoneyPerPeriodOfTime(date1, date2);
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @GetMapping("")
    public List<OrderDto> getListOfOrders() {
        List<Order> orders = orderService.getListOfOrders();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
       return orderDtoList;
    }

    @PostMapping("create")
    public OrderDto addOrderToListOfOrders(@RequestBody OrderDto orderDto){
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.addOrderToListOfOrders(order);
        return orderDto;
    }

    @GetMapping("{id}")
    public OrderDto getOrderById(@PathVariable String id){
        int orderId = Integer.parseInt(id);
        Order order = orderService.getOrderById(orderId);
        OrderDto orderDto = dtoConverter.orderEntityToDto(order);
        return orderDto;
    }

    @PostMapping("update")
    public OrderDto updateOrder(@RequestBody OrderDto orderDto) {
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.updateOrder(order);
        return orderDto;
    }

    @GetMapping("sort/dateOfDone")
    public List<OrderDto> sortOrdersByDateOfDone(){
        List<Order> orders = orderService.sortOrdersByDateOfDone();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;

    }
    @GetMapping("sort/price")
    public List<OrderDto> sortOrdersByPrice(){
        List<Order> orders =  orderService.sortOrdersByPrice();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
    @GetMapping("sort/status")
    public List<OrderDto> sortOrdersByStatus(){
        List<Order> orders =  orderService.sortOrdersByStatus();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            OrderDto orderDto = dtoConverter.orderEntityToDto(orders.get(i));
            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }

    @DeleteMapping("delete")
    public Order deleteOrder(@RequestBody OrderDto orderDto){
        Order order = dtoConverter.orderDtoToEntity(orderDto);
        orderService.deleteOrder(order);
        return order;
    }
    @GetMapping("count_done_by_period")
    public int countOfDoneOrdersByPeriodOfTime(@PathVariable List<Order> orders,@PathVariable LocalDate date1, LocalDate date2){
       int count = orderService.countOfDoneOrdersByPeriodOfTime(orders, date1, date2);
       return count;
    }

}
