package com.example.demo.repository;

import java.util.List;

//import org.springframework.data.jdbc.repository.query.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.ProductInfo;

/**
 * 商品情報テーブルRepositoryクラス
 * 
 * @author ys-fj
 *
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo, String> {

	/**
	 * 検索を行います。
	 * 
	 * @param searchProductNo 品番
	 * @param searchProductName 品名
	 * @param searchPriceFrom 価格From
	 * @param searchPriceTo 価格To
	 * @return 検索でヒットした商品情報のリスト
	 */
	@Query(value = "SELECT * FROM product_info "
			+ "WHERE product_no like %:searchProductNo%"
			+ " AND product_name like %:searchProductName%"
			+ " AND price BETWEEN :searchPriceFrom AND :searchPriceTo"
			+ " ORDER BY product_no", nativeQuery = true)
	List<ProductInfo> searchProduct(@Param("searchProductNo") String searchProductNo, 
			@Param("searchProductName") String searchProductName, 
			@Param("searchPriceFrom") Integer searchPriceFrom, 
			@Param("searchPriceTo") Integer searchPriceTo
			);

}
