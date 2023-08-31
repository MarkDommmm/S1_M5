package rikkei.academy.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import rikkei.academy.Model.Customer;
import rikkei.academy.Service.ICustomerService;

@Controller
public class CustomerController {
    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/")
    public ModelAndView listCustomer() {
        ModelAndView modelAndView = new ModelAndView("/Customer/list");
        modelAndView.addObject("customers", iCustomerService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public String showFormCreate(Model model) {
        Customer customer = new Customer();
        model.addAttribute("customerForm", customer);
        System.out.println("name" + customer.getName());
        return "/Customer/add";
    }


    @PostMapping("/create/customer")
    public String createCustomer(@ModelAttribute("customerForm") Customer customer) {
        iCustomerService.save(customer);
        return "redirect:/";
    }

    @GetMapping("detail/{id}")
    public String showFormForAdd(@PathVariable("id") Long id, Model themodel) {
        System.out.println("id ====" + id);
        Customer thecustomer = iCustomerService.findById(id);
        themodel.addAttribute("customer", thecustomer);
        return "Customer/detail";
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showFormDelete(@PathVariable long id) {
        Customer customer = iCustomerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("Customer/delete");
        modelAndView.addObject("deleteForm", customer);
        return modelAndView;
    }
    @PostMapping("delete")
    public  String deleteById(@ModelAttribute("deleteForm") Customer customer){
        iCustomerService.deleteById(customer.getId());
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public ModelAndView showFormEdit(@PathVariable long id){
        Customer customer = iCustomerService.findById(id);
        ModelAndView modelAndView = new ModelAndView("Customer/edit");
        modelAndView.addObject("editForm",customer);
        return  modelAndView;
    }
    @PostMapping("/edit/customer")
    public String editCustomer(@ModelAttribute("editForm") Customer customer ){
        iCustomerService.save(customer);
        return "redirect:/";
    }


}

