package ru.gribnoff.shop;


import ru.gribnoff.shop.ws.ProductServiceIWS;
import ru.gribnoff.shop.ws.ProductServiceWS;

import java.net.URL;

public class WSClient {

    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/f-ee-app/ProductServiceWS/ProductServiceWS?wsdl");
        ProductServiceWS productService = new ProductServiceWS(url);

        ProductServiceIWS productServiceWSPort = productService.getProductServiceWSPort();

        productServiceWSPort.findAll().forEach(product -> System.out.println(product.getDescription()));
    }
}
