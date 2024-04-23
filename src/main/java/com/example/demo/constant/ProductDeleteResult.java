package com.example.demo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 処理結果種別
 * 
 * @author ys-fj
 */
@Getter
@AllArgsConstructor
public enum ProductDeleteResult {

	/* エラーなし */
	SUCCEED(MessageConst.PRODUCTLIST_DELETE_SUCCEED),

	/* エラーあり */
	ERROR(MessageConst.PRODUCTLIST_NON_EXISTED_LOGIN_ID);

	/** メッセージID */
	private String messageId;

}
