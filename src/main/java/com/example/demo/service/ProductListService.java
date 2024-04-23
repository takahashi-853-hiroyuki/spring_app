package com.example.demo.service;

import java.util.List;

import com.example.demo.constant.ProductDeleteResult;
import com.example.demo.dto.ProductList;
import com.example.demo.dto.ProductSearchInfo;

/**
 * 商品一覧画面Serviceインターフェース
 * 
 * @author ys-fj
 *
 */
public interface ProductListService {

	/**
	 * 商品情報を条件検索した結果を画面の表示用に変換して返却します。
	 * 
	 * @param dto 検索に使用するパラメーター
	 * @return 検索結果
	 */
	public List<ProductList> editProductListByParam(ProductSearchInfo dto);

	/**
	 * 指定されたIDの商品情報を削除します。
	 * 
	 * @param productNo 品番
	 * @return 実行結果(エラー有無)
	 */
	public ProductDeleteResult deleteProductInfoById(String productNo);
}
