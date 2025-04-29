package com.todocode.bazar.service.implementation;

import com.todocode.bazar.dto.request.SaleRequestDto;
import com.todocode.bazar.dto.response.SaleResponseDto;
import com.todocode.bazar.exception.AlreadyExist;
import com.todocode.bazar.exception.NotFoundException;
import com.todocode.bazar.model.Client;
import com.todocode.bazar.model.Product;
import com.todocode.bazar.model.Sale;
import com.todocode.bazar.repository.ISaleRepository;
import com.todocode.bazar.service.inteface.IClientService;
import com.todocode.bazar.service.inteface.IProductService;
import com.todocode.bazar.service.inteface.ISaleService;
import com.todocode.bazar.utils.MapperUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleService implements ISaleService {
    private final ISaleRepository saleRepository;
    private final MapperUtils mapperUtils;
    private final IProductService productService;
    private final IClientService clientService;

    @Override
    public SaleResponseDto addSale(SaleRequestDto saleRequestDto) {

        Client client = getClientById(saleRequestDto);
        List<Product> productList = getProductsByIds(saleRequestDto);

        if (saleRepository.existsByClient_Id(client.getId())) {
            throw new AlreadyExist("This client" + client.getDni() + "already has a sale");
        }

        Sale sale = new Sale();
        sale.setSaleDate(saleRequestDto.getSaleDate());
        sale.setClient(client);
        sale.setTotal(saleRequestDto.getTotal());
        sale.setProductList(productList);

        saleRepository.save(sale);

        return mapperUtils.mapEntityToDto(sale, SaleResponseDto.class);
    }

    @Override
    public List<SaleResponseDto> getAllSales() {
        List<Sale> saleList = saleRepository.findAll();
        if (saleList.isEmpty()) {
            throw new NotFoundException("No sales found");
        }

        return mapperUtils.mapEntityListToDtoList(saleList, SaleResponseDto.class);
    }

    @Override
    public SaleResponseDto getSaleById(Long saleCode) {
        Sale sale = saleRepository.findById(saleCode)
                .orElseThrow(() -> new NotFoundException("No exists a sale with that code"));

        return mapperUtils.mapEntityToDto(sale, SaleResponseDto.class);
    }

    @Override
    public SaleResponseDto updateSale(Long saleCode, SaleRequestDto saleRequestDto) {
        Sale sale = saleRepository.findById(saleCode)
                .orElseThrow(() -> new NotFoundException("No exists a sale with that code"));

        Client client = getClientById(saleRequestDto);
        List<Product> productList = getProductsByIds(saleRequestDto);

        if (saleRepository.existsByClient_IdAndSaleCodeNot(client.getId(), saleCode)) {
            throw new AlreadyExist("This client " + client.getDni() + " already has a sale");
        }

        sale.setSaleDate(saleRequestDto.getSaleDate());
        sale.setClient(client);
        sale.setTotal(saleRequestDto.getTotal());
        sale.setProductList(productList);
        saleRepository.save(sale);

        return mapperUtils.mapEntityToDto(sale, SaleResponseDto.class);
    }

    @Override
    public void deleteSale(Long saleCode) {
        Sale sale = saleRepository.findById(saleCode)
                .orElseThrow(() -> new NotFoundException("No exists a sale with that code"));

        saleRepository.deleteById(sale.getSaleCode());
    }

    private Client getClientById(SaleRequestDto saleRequestDto) {
        return mapperUtils.mapDtoToEntity(
                clientService.getClientById(saleRequestDto.getClient()),
                Client.class
        );
    }

    private List<Product> getProductsByIds(SaleRequestDto saleRequestDto) {
        return mapperUtils.mapDtoListToEntityList(
                productService.getListByIds(saleRequestDto.getProductsIds()),
                Product.class
        );
    }

}
