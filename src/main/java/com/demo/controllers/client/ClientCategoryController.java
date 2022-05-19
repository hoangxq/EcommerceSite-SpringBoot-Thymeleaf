package com.demo.controllers.client;

import com.demo.DTO.CategoryDTO;
import com.demo.DTO.ProductDTO;
import com.demo.service.CategoryService;
import com.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class ClientCategoryController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{id}")
    public String getProductOfCategory (Model model, HttpSession session,
                              @PathVariable(name = "id") String name){

        CategoryDTO categoryDTO = categoryService.getCategoryByName(name).toDTO();

        List<ProductDTO> productDTOS = productService.getAllProduct().stream()
                .filter(e -> e.getCategory().getName().equals(name)).collect(Collectors.toList())
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        Collections.shuffle(productDTOS);

        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("categoryDTO", categoryDTO);
        model.addAttribute("productDTOs", productDTOS);

        return "/client/home";

    }

}
