package com.example.inventoryapp.services.impl;

import static com.example.inventoryapp.domain.Collections.INTERFACE;
import static com.example.inventoryapp.domain.Collections.NETWORK_ELEMENT;

import com.arangodb.ArangoCursor;
import com.arangodb.springframework.annotation.Document;
import com.arangodb.springframework.repository.ArangoRepository;
import com.example.inventoryapp.domain.documents.DocumentEntity;
import com.example.inventoryapp.domain.documents.HardwareComponent;
import com.example.inventoryapp.domain.documents.Interface;
import com.example.inventoryapp.domain.documents.NetworkElement;
import com.example.inventoryapp.domain.documents.SubInterface;
import com.example.inventoryapp.domain.edges.IntToSub;
import com.example.inventoryapp.domain.edges.Link;
import com.example.inventoryapp.domain.edges.NeToHw;
import com.example.inventoryapp.domain.edges.NeToInt;
import com.example.inventoryapp.exceptions.ObjectNotFoundException;
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
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
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

    @Override
    public List<NetworkElement> getNetworkElements() {
        return Lists.newArrayList(networkElementRepository.findAll());
    }

    @Override
    public NetworkElement storeNetworkElement(NetworkElement networkElement) {
        return networkElementRepository.save(networkElement);
    }

    @Override
    public HardwareComponent getHardwareComponent(String networkElementId) {
        String neArangoId = arangoId(NETWORK_ELEMENT, networkElementId);
        ArangoCursor<HardwareComponent> cursor = hardwareComponentRepository.getHwComponentByNeId(neArangoId);

        return cursor.asListRemaining().stream()
                .findFirst().orElseThrow(() -> new ObjectNotFoundException(
                        "Unable to found hardware component for network element with id: " + networkElementId)
                );
    }

    @Override
    public HardwareComponent storeHardwareComponent(String neId, HardwareComponent hwComponent) {

        NetworkElement networkElement =
                (NetworkElement) findEntityById(neId, networkElementRepository, NetworkElement.class);

        if (networkElement.getHardwareComponent() != null) {
            throw new IllegalStateException("Network element with id: " + neId + " already has assigned hardware");
        }

        HardwareComponent storedHwComponent = hardwareComponentRepository.save(hwComponent);

        NeToHw neToHw = new NeToHw(networkElement, storedHwComponent);

        neToHwRepository.save(neToHw);

        return storedHwComponent;
    }

    @Override
    public List<Interface> getInterfaces(String networkElementId) {
        String neArangoId = arangoId(NETWORK_ELEMENT, networkElementId);

        return interfaceRepository.getInterfacesByNeId(neArangoId).asListRemaining();
    }

    @Override
    public Interface storeInterface(String neId, Interface inter) {

        NetworkElement networkElement =
                (NetworkElement) findEntityById(neId, networkElementRepository, NetworkElement.class);

        Interface storedInterface = interfaceRepository.save(inter);

        NeToInt neToInt = new NeToInt(networkElement, storedInterface);

        neToIntRepository.save(neToInt);

        return storedInterface;
    }

    @Override
    public List<SubInterface> getSubInterfaces(String interfaceId) {
        String intArangoId = arangoId(INTERFACE, interfaceId);

        return subInterfaceRepository.getSubInterfacesByIntId(intArangoId).asListRemaining();
    }

    @Override
    public SubInterface storeSubInterface(String intId, SubInterface subInterface) {
        Interface inter =
                (Interface) findEntityById(intId, interfaceRepository, Interface.class);

        SubInterface storedSubInterface = subInterfaceRepository.save(subInterface);

        IntToSub intToSub = new IntToSub(inter, storedSubInterface);

        intToSubRepository.save(intToSub);

        return storedSubInterface;
    }

    @Override
    public List<Link> getLinks(String networkElementId) {
        return null;
    }

    @Override
    public Link storeLink(Link link) {
        String aNeName = link.getANetworkElementName();
        String zNeName = link.getZNetworkElementName();
        String aIntName = link.getAInterfaceName();
        String zIntName = link.getZInterfaceName();

        linkRepository.findLinkByEndpoints(aNeName, zNeName, aIntName, zIntName)
                .asListRemaining().stream()
                .findFirst().ifPresent(existedLink -> {
            throw new IllegalStateException("Link between " + aNeName + "|" + aIntName +
                    " and " + zNeName + "|" + zIntName + " already present.");
        });

        NetworkElement aNe = findNeByName(aNeName);
        NetworkElement zNe = findNeByName(zNeName);
        Interface aInterface = findInterfaceByNeAndName(aNe.getId(), aIntName);
        Interface zInterface = findInterfaceByNeAndName(zNe.getId(), zIntName);

        return linkRepository.save(Link.builder()
                .aInterface(aInterface)
                .zInterface(zInterface)
                .aNetworkElementName(aNeName)
                .zNetworkElementName(zNeName)
                .aInterfaceName(aIntName)
                .zInterfaceName(zIntName)
                .build());
    }

    private NetworkElement findNeByName(String name) {
        return networkElementRepository.findByName(name).asListRemaining()
                .stream()
                .findFirst().orElseThrow(() ->
                        new ObjectNotFoundException("Unable to find network element with name: " + name));
    }

    private Interface findInterfaceByNeAndName(String neId, String intName) {
        String neArangoId = arangoId(NETWORK_ELEMENT, neId);

        return interfaceRepository.findByNeAndName(neArangoId, intName).asListRemaining()
                .stream().findFirst()
                .orElseThrow(() ->
                        new ObjectNotFoundException("Unable to find interface with name: " + intName));
    }

    private <R extends DocumentEntity> DocumentEntity findEntityById(
            String id,
            ArangoRepository<? extends DocumentEntity, String> repository,
            Class<R> clazz) {

        String entityName = findEntityNameByDocumentAnnotation(clazz);

        return repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Unable to find " + entityName + " with id: " + id));
    }

    private static <R> String findEntityNameByDocumentAnnotation(Class<R> clazz) {
        return Arrays.stream(clazz.getAnnotations())
                .filter(annotation -> annotation.annotationType().equals(Document.class))
                .map(Document.class::cast)
                .map(Document::value)
                .findFirst().orElse(StringUtils.EMPTY);
    }

    private static String arangoId(String collection, String key) {
        return collection + "/" + key;
    }
}
