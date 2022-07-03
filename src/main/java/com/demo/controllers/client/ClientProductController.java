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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("")
public class ClientProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;
    
    @GetMapping("/search")
    public String searchProduct (Model model, HttpSession session,
                  @RequestParam(value = "productName", required = false) String productName){

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        List<ProductDTO> productDTOS = productService.getAllProduct().stream()
                .map(e -> e.toDTO()).collect(Collectors.toList()).stream()
                .filter(e -> e.getName().toLowerCase().contains(productName.toLowerCase()))
                .collect(Collectors.toList());
        
        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("productDTOs", productDTOS);

        return "/client/home";

    }

    @GetMapping("/product-detail/{id}")
    public String getProduct (Model model, HttpSession session,
                              @PathVariable(name = "id") Long id){

        List<CategoryDTO> categoryDTOs = categoryService.getAllCategory()
                .stream().map(e -> e.toDTO()).collect(Collectors.toList());

        List<ProductDTO> productDTOS = productService.getAllProduct().stream()
                .map(e -> e.toDTO()).collect(Collectors.toList());

        ProductDTO productDTO = productService.getProductById(id).toDTO();

        model.addAttribute("productDTO", productDTO);
        model.addAttribute("categoryDTOs", categoryDTOs);
        model.addAttribute("productDTOs", productDTOS);

        return "/client/product-detail";

    }

}
