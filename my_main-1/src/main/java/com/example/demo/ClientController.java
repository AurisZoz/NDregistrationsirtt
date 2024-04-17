package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping({"/","/clients"})
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public String showClients(Model model) {
        List<Client> clients = clientService.getAllClients();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/add")
    public String showAddClientForm(Model model) {
        model.addAttribute("client", new Client());
        return "add-client";
    }

    @PostMapping("/add")
    public String addClient(@ModelAttribute Client client) {
        clientService.addClient(client);
        return "redirect:/clients";
    }

    @GetMapping("/update/{id}")
    public String showUpdateClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "update-client";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute Client client) {
        client.setId(id); 
        clientService.updateClient(client); 
        return "redirect:/clients"; 
    }
    
    @GetMapping("/delete/{id}")
    public String showDeleteClientForm(@PathVariable Long id, Model model) {
        Client client = clientService.getClientById(id);
        model.addAttribute("client", client);
        return "delete-client";
    }

    @PostMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return "redirect:/clients";
    }
}