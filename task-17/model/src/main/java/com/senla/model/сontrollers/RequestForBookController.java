package com.senla.model.сontrollers;

import com.senla.model.dto.RequestDto;
import com.senla.model.entity.RequestForBook;
import com.senla.model.service.api.RequestForBookService;
import com.senla.model.utils.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestForBookController {
    private RequestForBookService requestForBookService;
    private DtoConverter dtoConverter;

    @Autowired
    public void setRequestForBookService(RequestForBookService requestForBookService) {
        this.requestForBookService = requestForBookService;
    }

    @Autowired
    public void setDtoConverter(DtoConverter dtoConverter) {
        this.dtoConverter = dtoConverter;
    }

    @GetMapping("import")
    public ResponseEntity<String> importRequestForBook() {
        requestForBookService.importRequestForBook();
        return new ResponseEntity<>(
                "Requests has been import successfully",
                HttpStatus.OK);
    }

    @GetMapping("export")
    public ResponseEntity<String> exportRequestForBook() {
        requestForBookService.exportRequestForBook();
        return new ResponseEntity<>(
                "Requests has been export successfully",
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public RequestDto getRequestForBookById(@PathVariable String id) {
        int requestId = Integer.parseInt(id);
        RequestForBook requestForBook = requestForBookService.getRequestForBookById(requestId);
        RequestDto requestDto = dtoConverter.requestEntityToDto(requestForBook);
        return requestDto;
    }

    @PostMapping("update")
    public RequestDto updateRequestForBook(@RequestBody RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.updateRequestForBook(requestForBook);
        return requestDto;
    }

    @GetMapping("sort/alphabet")
    public List<RequestDto> sortRequestByAlphabet() {
        List<RequestForBook> requestForBooks = requestForBookService.sortRequestByAlphabet();
        List<RequestDto> requestsDto = new ArrayList<>();
        for (int i = 0; i < requestForBooks.size(); i++) {
            RequestDto requestDto = dtoConverter.requestEntityToDto(requestForBooks.get(i));
            requestsDto.add(requestDto);
        }
        return requestsDto;
    }

    @GetMapping("")
    public List<RequestDto> getListOfRequestForBook() {
        List<RequestForBook> requestForBookList = requestForBookService.getListOfRequestForBook();
        List<RequestDto> dtoRequests = new ArrayList<>();
        for (int i = 0; i < requestForBookList.size(); i++) {
            RequestDto requestDto = dtoConverter.requestEntityToDto(requestForBookList.get(i));
            dtoRequests.add(requestDto);
        }
        return dtoRequests;
    }

    @PostMapping("create")
    public RequestForBook create(@RequestBody RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.create(requestForBook);
        return requestForBook;
    }

    @DeleteMapping("delete")
    public RequestForBook delete(@PathVariable RequestDto requestDto) {
        RequestForBook requestForBook = dtoConverter.requestDtoToEntity(requestDto);
        requestForBookService.deleteRequestForBook(requestForBook);
        return requestForBook;
    }
}
