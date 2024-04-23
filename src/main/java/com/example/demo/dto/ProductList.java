package com.example.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

/**
 * 商品一覧画面DTOクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class ProductList{

	/** 新商品 */
	private String newRelease;

	/** 品番 */
	private String productNo;

	/** 品名 */
	private String productName;

	/** カラー */
	private String color;

	/** 発売日 */
	private LocalDateTime releaseDate;

	/** 価格 */
	private String price;

}
