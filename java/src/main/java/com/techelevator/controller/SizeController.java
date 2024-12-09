package com.techelevator.controller;

import com.techelevator.dao.SizeDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Size;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
@RestController
@CrossOrigin
public class SizeController {
    private final SizeDao sizeDao;
    private final UserDao userDao;

    public SizeController(SizeDao sizeDao, UserDao userDao) {
        this.sizeDao = sizeDao;
        this.userDao = userDao;
    }
    //method_24
    @GetMapping("/sizes")
    public List<Size> getSizes(){
        try {
            return sizeDao.getSizes();
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //method_25
    @PreAuthorize("hasRole('ADMIN')")
    public Size getSizeById(int id){
        try {
            return sizeDao.getSizeById(id);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   //method_26
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/sizes")
    public Size setSizeById(@RequestBody Size size){

        try {
            return sizeDao.setSizeMultiplierById(size.getSizeId(),size.getSizeMultiplier());
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //method_27
    private int getCurrentUserId(Principal principal) {
        return userDao.getUserByUsername(principal.getName()).getId();
    }
}
