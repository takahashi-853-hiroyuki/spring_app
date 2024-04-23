package com.example.demo.dto;

import lombok.Data;

/**
 * 商品一覧画面検索用DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class ProductSearchInfo {

	/** 品番 */
	private String searchProductNo;

	/** 品姪 */
	private String searchProductName;

	/** 価格From */
	private Integer searchPriceFrom;

	/** 価格To */
	private Integer searchPriceTo;

}
