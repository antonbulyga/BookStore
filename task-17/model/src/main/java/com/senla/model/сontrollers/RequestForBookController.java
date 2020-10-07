package com.senla.model.сontrollers;

import com.senla.model.dto.RequestDto;
import com.senla.model.entity.RequestForBook;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class RequestForBookController {
    @Autowired
    private RequestForBookService requestForBookService;
    @Autowired
    private DtoConverter dtoConverter;

    @GetMapping("requests/import")
    public String importRequestForBook() {
        requestForBookService.importRequestForBook();
        return "Requests has been import successfully";
    }

    @GetMapping("requests/export")
    public String exportRequestForBook() {
        requestForBookService.exportRequestForBook();
        return "Requests has been import successfully";
    }

    @GetMapping("requests/{id}")
    public RequestDto getRequestForBookById(@PathVariable String id) {
        int requestId = Integer.parseInt(id);
        RequestDto requestDto;
        try {
            RequestForBook requestForBook = requestForBookService.getRequestForBookById(requestId);
            requestDto = dtoConverter.requestEntityToDto(requestForBook);
        }
        catch (NoResultException e){
            throw new NoResultException("No request with this ID");
        }
        return requestDto;
    }

    @PostMapping("requests/update")
    public RequestDto updateRequestForBook(@RequestBody RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.updateRequestForBook(requestForBook);
        return requestDto;
    }

    @GetMapping("orders/sort/alphabet")
    public List<RequestDto> sortRequestByAlphabet() {
       List<RequestForBook> requestForBooks = requestForBookService.sortRequestByAlphabet();
       List<RequestDto> requestsDto = new ArrayList<>();
        for (int i = 0; i < requestForBooks.size(); i++) {
            RequestDto requestDto = dtoConverter.requestEntityToDto(requestForBooks.get(i));
            requestsDto.add(requestDto);
        }
        return requestsDto;
    }

    @GetMapping("requests")
    public List<RequestDto> getListOfRequestForBook() {
        List<RequestForBook> requestForBookList = requestForBookService.getListOfRequestForBook();
        List<RequestDto> dtoRequests = new ArrayList<>();
        for (int i = 0; i < requestForBookList.size(); i++) {
            RequestDto requestDto = dtoConverter.requestEntityToDto(requestForBookList.get(i));
            dtoRequests.add(requestDto);
        }
        return dtoRequests;
    }
    @PostMapping("requests/create")
    public RequestForBook create(@RequestBody RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.create(requestForBook);
        return requestForBook;
    }

    @DeleteMapping("requests/delete")
    public RequestForBook delete(@PathVariable RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.deleteRequestForBook(requestForBook);
        return requestForBook;
    }
}
