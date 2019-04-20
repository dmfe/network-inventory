package com.example.inventoryapp.services.api;

import com.example.inventoryapp.domain.documents.HardwareComponent;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.NetworkElement;
import com.example.inventoryapp.domain.documents.SubInterface;
import com.example.inventoryapp.domain.edges.Link;

import java.util.List;

public interface InventoryService {

    List<NetworkElement> getNetworkElements();

    NetworkElement storeNetworkElement(NetworkElement networkElement);

    HardwareComponent getHardwareComponent(String networkElementId);

    HardwareComponent storeHardwareComponent(String networkElementId, HardwareComponent component);

    List<Interface> getInterfaces(String networkElementId);

    Interface storeInterface(String networkElementId, Interface inter);

    List<SubInterface> getSubInterfaces(String interfaceId);

    SubInterface storeSubInterface(String interfaceId, SubInterface subInterface);

    List<Link> getLinks(String networkElementId);

    Link storeLink(Link link);
}
