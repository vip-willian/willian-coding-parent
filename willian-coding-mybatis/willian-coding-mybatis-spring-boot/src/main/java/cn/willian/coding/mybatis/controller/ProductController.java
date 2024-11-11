package cn.willian.coding.mybatis.controller;

import cn.willian.coding.mybatis.dao.ProductMapper;
import cn.willian.coding.mybatis.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="mailto:willian.wyann@gmail.com">willian</a>
 * @datetime 2024-11-08 00:21:39
 */
@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Resource
    private ProductMapper productMapper;

    @GetMapping(value = "/all")
    public List<Product> getProducts() {

        return productMapper.getAll();
    }
}
