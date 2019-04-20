package com.example.inventoryapp.restapi.controllers;

import com.example.inventoryapp.domain.documents.HardwareComponent;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.NetworkElement;
import com.example.inventoryapp.domain.documents.SubInterface;
import com.example.inventoryapp.domain.edges.Link;
import com.example.inventoryapp.services.api.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/network-elements")
    public List<NetworkElement> getNetworkElements() {
        return inventoryService.getNetworkElements();
    }

    @PostMapping("/network-elements")
    @ResponseStatus(HttpStatus.CREATED)
    public NetworkElement storeNetworkElement(@RequestBody NetworkElement networkElement) {
        return inventoryService.storeNetworkElement(networkElement);
    }

    @GetMapping("/hw-components/{id}")
    public HardwareComponent storeHardwareComponent(@PathVariable("id") String neId) {
        return inventoryService.getHardwareComponent(neId);
    }

    @PostMapping("/hw-components/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HardwareComponent storeHardwareComponent(@PathVariable("id") String neId,
                                                    @RequestBody HardwareComponent hwComponent) {
        return inventoryService.storeHardwareComponent(neId, hwComponent);
    }

    @GetMapping("/interfaces/{id}")
    public List<Interface> getInterfaces(@PathVariable("id") String neId) {
        return inventoryService.getInterfaces(neId);
    }

    @PostMapping("/interfaces/{id}")
    public Interface storeInterface(@PathVariable("id") String neId,
                                    @RequestBody Interface inter) {
        return inventoryService.storeInterface(neId, inter);
    }

    @GetMapping("sub-interfaces/{id}")
    public List<SubInterface> getSubInterfaces(@PathVariable("id") String interId) {
        return inventoryService.getSubInterfaces(interId);
    }

    @PostMapping("sub-interfaces/{id}")
    public SubInterface storeSubInterface(@PathVariable("id") String interId,
                                          @RequestBody SubInterface subInterface) {
        return inventoryService.storeSubInterface(interId, subInterface);
    }

    @GetMapping("/links/{id}")
    public List<Link> getLinks(@PathVariable("id") String neId) {
        return inventoryService.getLinks(neId);
    }

    @PostMapping("/links")
    public Link storeLink(@RequestBody Link link) {
        return inventoryService.storeLink(link);
    }
}
