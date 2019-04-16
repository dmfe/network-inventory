package com.example.inventoryapp.services.impl;

import com.example.inventoryapp.domain.documents.NetworkElement;
import com.example.inventoryapp.repositories.documents.HardwareComponentRepository;
import com.example.inventoryapp.repositories.documents.InterfaceRepository;
import com.example.inventoryapp.repositories.documents.NetworkElementRepository;
import com.example.inventoryapp.repositories.documents.SubInterfaceRepository;
import com.example.inventoryapp.repositories.edges.IntToSubRepository;
import com.example.inventoryapp.repositories.edges.LinkRepository;
import com.example.inventoryapp.repositories.edges.NeToHwRepository;
import com.example.inventoryapp.repositories.edges.NeToIntRepository;
import com.example.inventoryapp.services.api.InventoryService;
import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryCRUDService implements InventoryService {

    private final NetworkElementRepository networkElementRepository;
    private final HardwareComponentRepository hardwareComponentRepository;
    private final InterfaceRepository interfaceRepository;
    private final SubInterfaceRepository subInterfaceRepository;

    private final NeToHwRepository neToHwRepository;
    private final NeToIntRepository neToIntRepository;
    private final IntToSubRepository intToSubRepository;
    private final LinkRepository linkRepository;

    public List<NetworkElement> getAllNetworkElements() {
        return Lists.newArrayList(networkElementRepository.findAll());
    }

    public NetworkElement storeNetworkElement(NetworkElement networkElement) {
        return networkElementRepository.save(networkElement);
    }
}
