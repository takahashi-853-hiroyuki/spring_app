package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 商品情報テーブルEntityクラス
 * 
 * @author ys-fj
 *
 */
@Entity
@Table(name = "product_info")
@Data
@AllArgsConstructor
public class ProductInfo {

	/** 品番 */
	@Id
	@Column(name = "product_no")
	private String productNo;

	/** 品名 */
	@Column(name = "product_name")
	private String productName;

	/** カラー */
	private String color;

	/** 価格 */
	private int price;

	/** 登録日 */
	@Column(name = "release_date")
	private LocalDateTime releaseDate;

	/**
	 * デフォルトコンストラクタ
	 */
	public ProductInfo() {
	}

}
