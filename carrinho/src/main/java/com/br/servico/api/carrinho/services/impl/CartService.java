package com.br.servico.api.carrinho.services.impl;

import com.br.servico.api.carrinho.models.response.CartResponseDTO;
import com.br.servico.api.carrinho.models.response.ProductResponseDTO;
import com.br.servico.api.carrinho.models.entity.Cart;
import com.br.servico.api.carrinho.models.entity.Product;
import com.br.servico.api.carrinho.models.entity.User;
import com.br.servico.api.carrinho.models.request.ProductRequestDTO;
import com.br.servico.api.carrinho.repositorys.CartRepository;
import com.br.servico.api.carrinho.services.IProductService;
import com.br.servico.api.carrinho.services.ICartService;
import com.br.servico.api.carrinho.services.IUserServiceRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICartService {

    @Autowired
    private IUserServiceRest userServiceRest;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private IProductService productService;

    private static final String CART_MAX_20 = "Carrinho pode ter no maximo 20 itens";
    private static final String USER_NOT_CART = "Usuario não possui carrinho";
    private static final String PRODUCT_QUANTITY_ERROR = "Produto não encontrado ou quantidade para remoção maior que o valor existente";
    private static final String PRODUCT_ERROR = "Produto não encontrado";

    @Override
    public ProductResponseDTO addProductInCart(ProductRequestDTO productRequestDTO) {
        User user = userServiceRest.getUser();
        Product product = productService.findProduct(productRequestDTO);
        Cart cart = validateCartToSave(productRequestDTO, user, product);
        cartRepository.saveCart(cart);
        return ProductResponseDTO.of(product);
    }

    @Override
    public CartResponseDTO getCartUser() {
        User user = userServiceRest.getUser();
        Cart cart = cartRepository.findCart(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_CART));
        return CartResponseDTO.of(cart);
    }

    @Override
    public CartResponseDTO removeProductInCart(ProductRequestDTO productRequestDTO) {
        Cart cart = findCart();
        Cart cartUpdate = removeProduct(cart, productRequestDTO);
        return CartResponseDTO.of(cartUpdate);
    }

    @Override
    public CartResponseDTO removeProductInCart(String idProduct) {
        Cart cart = findCart();
        List<Product> products = verifyProductExist(idProduct, cart);
        cart.removeProduct(products);
        Cart saveCart = cartRepository.saveCart(cart);
        return CartResponseDTO.of(saveCart);
    }

    private List<Product> verifyProductExist(String idProduct, Cart cart) {
        List<Product> products = cart.getProducts().stream()
                .filter(p -> p.getId().equals(idProduct))
                .collect(Collectors.toList());
        if(products.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, PRODUCT_ERROR);
        }
        return products;
    }

    private Cart findCart(){
        User user = userServiceRest.getUser();
        return cartRepository.findCart(user).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, USER_NOT_CART));
    }

    private Cart removeProduct(Cart cart, ProductRequestDTO productRequestDTO){
        Product product = verifyProductExist(cart, productRequestDTO);
        cart.removeProduct(product, productRequestDTO.getQtd());
        return cartRepository.saveCart(cart);
    }

    private Product verifyProductExist(Cart cart, ProductRequestDTO productRequestDTO) {
        return cart.getProducts().stream()
                .filter(p -> p.getId().equals(productRequestDTO.getId()) && p.getQtd() < productRequestDTO.getQtd())
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, PRODUCT_QUANTITY_ERROR));
    }

    private Cart validateCartToSave(ProductRequestDTO productRequestDTO, User user, Product product) {
        Cart cart = cartRepository.findCart(user).orElse(Cart.of(user));
        if(cart.getQtdProduct() + productRequestDTO.getQtd() > 20){
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, CART_MAX_20);
        }
        cart.addProduct(product);
        return cart;
    }

}
