package com.example.inventoryapp.restapi.controllers;

import com.example.inventoryapp.domain.documents.HardwareComponent;
import com.example.inventoryapp.domain.documents.NetworkElement;
import com.example.inventoryapp.domain.edges.NeToHw;
import com.example.inventoryapp.exceptions.ObjectNotFoundException;
import com.example.inventoryapp.repositories.documents.HardwareComponentRepository;
import com.example.inventoryapp.repositories.documents.NetworkElementRepository;
import com.example.inventoryapp.repositories.edges.NeToHwRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final NetworkElementRepository networkElementRepository;
    private final HardwareComponentRepository hardwareComponentRepository;
    private final NeToHwRepository neToHwRepository;

    @GetMapping("/network-elements")
    public Iterable<NetworkElement> getNetworkElements() {
        return networkElementRepository.findAll();
    }

    @PostMapping("/network-elements")
    public NetworkElement storeNetworkElement(@RequestBody NetworkElement networkElement) {
        return networkElementRepository.save(networkElement);
    }

    @PostMapping("/network-elements/{id}/hw-components")
    public HardwareComponent storeHardwareComponent(@PathVariable String id,
                                                    @RequestBody HardwareComponent hwComponent) {

        NetworkElement networkElement = networkElementRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Unable to find network element with id: " + id));

        if (networkElement.getHardwareComponent() != null) {
            throw new IllegalStateException("Network element with id: " + id + " already has assigned hardware");
        }

        HardwareComponent hardwareComponent = hardwareComponentRepository.save(hwComponent);

        NeToHw neToHw = new NeToHw(networkElement, hardwareComponent);

        neToHwRepository.save(neToHw);

        return hardwareComponent;
    }
}
