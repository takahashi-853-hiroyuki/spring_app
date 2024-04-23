package com.example.demo.form;

import lombok.Data;

/**
 * 商品登録画面Formクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class ProductListForm {

	/** 品番 */
	private String searchProductNo;

	/** 品姪 */
	private String searchProductName;

	/** 価格From */
	private String searchPriceFrom;

	/** 価格To */
	private String searchPriceTo;

	/** 商品一覧情報から選択された品番 */
	private String selectedProductNo;

	/**
	 * 商品一覧情報から選択された品番をクリアします。
	 * 
	 * @return 商品一覧情報から選択された品番クリア後のインスタンス
	 */
	public ProductListForm clearSelectedProductNo() {
		this.selectedProductNo = null;

		return this;
	}
}
