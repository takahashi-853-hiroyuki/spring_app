package com.example.demo.form;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import com.example.demo.constant.db.AuthorityKind;
import com.example.demo.constant.db.UserStatusKind;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

/**
 * ユーザー編集画面Formクラス
 * 
 * @author ys-fj
 *
 */
@Data
public class UserEditForm {
	
	/** ログイン失敗状況をリセットするか(リセットするならtrue) */
	private boolean resetsLoginFailure;

	/** アカウント状態種別 */
	private UserStatusKind userStatusKind;

	/** ユーザー権限種別 */
	private AuthorityKind authorityKind;
	
	/** パスワード */
	@Length(min = 3, max = 20)
	private String password;
	
	/** パスワード(確認) */
	private String passwordConfirm;
	
	/**
	 * パスワード(確認)との整合チェック
	 * @return 判定結果(パスワード(確認)と一致・true、不一致：false
	 */
	@AssertTrue(message="パスワード(確認)と一致していません")
	public boolean isPasswordCheck() {
		if (StringUtils.defaultString(getPassword()).equals(getPasswordConfirm())) {
			return true;
		}
		return false;
	}

}
