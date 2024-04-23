package com.example.demo.controller;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constant.ModelKey;
import com.example.demo.constant.ProductDeleteResult;
import com.example.demo.constant.SessionKeyConst;
import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.dto.ProductList;
import com.example.demo.dto.ProductSearchInfo;
import com.example.demo.form.ProductListForm;
import com.example.demo.service.ProductListService;
import com.example.demo.util.AppUtil;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 商品一覧画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class ProductListController {

	/** 商品一覧画面Serviceクラス */
	private final ProductListService service;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** モデルキー：商品情報リストフォーム */
	private static final String KEY_PRODUCTLIST_FORM = "productListForm";

	/** モデルキー：商品情報リスト */
	private static final String KEY_PRODUCTLIST = "productList";

	/** モデルキー：操作種別 */
	private static final String KEY_OPERATION_KIND = "operationKind";
	
	/** 検索条件 デフォルト値 */
	private static final String  INIT_PARAM_PRODUCT_NO = "";
	private static final String  INIT_PARAM_PRODUCT_NAME = "";
	private static final Integer INIT_PARAM_PRICE_FROM = 0;
	private static final Integer INIT_PARAM_PRICE_TO = 999999999;


	/**
	 * 画面の初期表示を行います。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 
	 */
	@GetMapping(UrlConst.PRODUCT_LIST)
	public String view(Model model, ProductListForm form) {
		session.removeAttribute(SessionKeyConst.SELECETED_PRODUCT_NO);

		model.addAttribute(KEY_PRODUCTLIST, editProductList(model));

		return ViewNameConst.PRODUCT_LIST;
	}

	/**
	 * 初期表示、検索後や削除後のリダイレクトによる再表示のいずれかかを判定して画面に表示する一覧情報を作成します。
	 * 
	 * @param model モデル
	 * @return 商品一覧情報
	 */
	@SuppressWarnings("unchecked")
	private List<ProductList> editProductList(Model model) {
		var doneSearchOrDelete = model.containsAttribute(KEY_OPERATION_KIND);
		if (doneSearchOrDelete) {
			var operationKind = (OperationKind) model.getAttribute(KEY_OPERATION_KIND);
			if (operationKind == OperationKind.SEARCH) {
				return (List<ProductList>) model.getAttribute(KEY_PRODUCTLIST);
			}
			if (operationKind == OperationKind.DELETE) {
				var searchDto = mapper.map((ProductListForm) model.getAttribute(KEY_PRODUCTLIST_FORM), ProductSearchInfo.class);
				return service.editProductListByParam(searchDto);
			}
		} 
		
		var searchDto = new ProductSearchInfo();
		searchDto.setSearchProductNo(INIT_PARAM_PRODUCT_NO);
		searchDto.setSearchProductName(INIT_PARAM_PRODUCT_NAME);
		searchDto.setSearchPriceFrom(INIT_PARAM_PRICE_FROM);
		searchDto.setSearchPriceTo(INIT_PARAM_PRICE_TO);

		return service.editProductListByParam(searchDto);
	}

	/**
	 * 検索条件に合致する商品情報を画面に表示します。
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.PRODUCT_LIST, params = "search")
	public String searchProduct(ProductListForm form, RedirectAttributes redirectAttributes) {
		var searchDto = mapper.map(form, ProductSearchInfo.class);
		if(searchDto.getSearchPriceFrom() == null) {
			searchDto.setSearchPriceFrom(INIT_PARAM_PRICE_FROM);
		}
		if(searchDto.getSearchPriceTo() == null) {
			searchDto.setSearchPriceTo(INIT_PARAM_PRICE_TO);
		}
		var productInfos = service.editProductListByParam(searchDto);
		redirectAttributes.addFlashAttribute(KEY_PRODUCTLIST, productInfos);
		redirectAttributes.addFlashAttribute(KEY_PRODUCTLIST_FORM, form);
		redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.SEARCH);

		return AppUtil.doRedirect(UrlConst.PRODUCT_LIST);
	}

	/**
	 * 選択行の商品情報を削除して、最新情報で画面を再表示します。
	 * 
	 * @param form 入力情報
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.PRODUCT_LIST, params = "edit")
	public String updateProduct(ProductListForm form) {
		session.setAttribute(SessionKeyConst.SELECETED_LOGIN_ID, form.getSelectedProductNo());
		return AppUtil.doRedirect(UrlConst.PRODUCT_ENTRY);
	}

	/**
	 * 選択行の商品情報を削除して、最新情報で画面を再表示します。
	 * 
	 * @param form 入力情報
	 * @param redirectAttributes リダイレクト用オブジェクト
	 * @return リダイレクトURL
	 */
	@PostMapping(value = UrlConst.PRODUCT_LIST, params = "delete")
	public String deleteProduct(ProductListForm form, RedirectAttributes redirectAttributes) {
		var executeResult = service.deleteProductInfoById(form.getSelectedProductNo());
		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, executeResult == ProductDeleteResult.ERROR);
		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
				AppUtil.getMessage(messageSource, executeResult.getMessageId()));
		// 削除後、フォーム情報の「選択された品番」は不要になるため、クリアします。
		redirectAttributes.addFlashAttribute(KEY_PRODUCTLIST_FORM, form.clearSelectedProductNo());
		redirectAttributes.addFlashAttribute(KEY_OPERATION_KIND, OperationKind.DELETE);

		return AppUtil.doRedirect(UrlConst.PRODUCT_LIST);
	}

	/**
	 * 操作種別Enum
	 * 
	 * @author ys-fj
	 */
	public enum OperationKind {
		SEARCH, DELETE
	}
}
