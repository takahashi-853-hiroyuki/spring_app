package com.example.demo.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.constant.ProductDeleteResult;
import com.example.demo.dto.ProductList;
import com.example.demo.dto.ProductSearchInfo;
import com.example.demo.entity.ProductInfo;
import com.example.demo.repository.ProductInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

/**
 * ユーザー一覧画面Service実装クラス
 * 
 * @author ys-fj
 *
 */
@Service
@RequiredArgsConstructor
public class ProductListServiceImpl implements ProductListService {
	
	/** 新商品表示文言 */
	private static final String CHAR_NEW_RELEASE = "NEW!";
	
	/** 1年以内計算時の日数 */
	private static final Integer ONE_YEAR_DAYS = 365;

	/** 商品情報テーブルDAO */
	private final ProductInfoRepository repository;

	/** Dozer Mapper */
	private final Mapper mapper;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<ProductList> editProductListByParam(ProductSearchInfo dto) {
		return toProductListInfos(findProductInfoByParam(dto));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductDeleteResult deleteProductInfoById(String productNo) {
		var userInfo = repository.findById(productNo);
		if (userInfo.isEmpty()) {
			return ProductDeleteResult.ERROR;
		}

		repository.deleteById(productNo);

		return ProductDeleteResult.SUCCEED;
	}

	/**
	 * 商品情報の条件検索を行い、検索結果を返却します。
	 * 
	 * @param form 入力情報
	 * @return 検索結果
	 */
	private List<ProductInfo> findProductInfoByParam(ProductSearchInfo dto) {
		return repository.searchProduct(
				dto.getSearchProductNo()
				,dto.getSearchProductName()
				,dto.getSearchPriceFrom()
				,dto.getSearchPriceTo());
	}

	/**
	 * 商品情報EntityのListをユーザー一覧情報DTOのListに変換します。
	 * 
	 * @param userInfos 商品情報EntityのList
	 * @return 商品覧情報DTOのList
	 */
	private List<ProductList> toProductListInfos(List<ProductInfo> productInfos) {
		var productListInfos = new ArrayList<ProductList>();
		for (ProductInfo productInfo : productInfos) {
			var productListInfo = mapper.map(productInfo, ProductList.class);
			productListInfo.setNewRelease(displayNewRelease(productInfo));
			productListInfo.setPrice(String.format("%,d", productInfo.getPrice()));
			productListInfos.add(productListInfo);
		}

		return productListInfos;

	}
	
	/**
	 * 登録日が現在日付の1年以内であれば「NEW!」を表示する
	 */
	private String displayNewRelease(ProductInfo productInfo) {
		
		var now = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
		var releaseDate = productInfo.getReleaseDate().truncatedTo(ChronoUnit.DAYS);
		
		if (now.compareTo(releaseDate.plusDays(ONE_YEAR_DAYS)) < 0) {
			return CHAR_NEW_RELEASE;
		}
		
		return "";
	}

}
