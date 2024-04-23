package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;
import com.example.demo.service.UserEditService;
import com.github.dozermapper.core.Mapper;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

/**
 * 商品編集画面Controllerクラス
 * 
 * @author ys-fj
 *
 */
@Controller
@RequiredArgsConstructor
public class ProductEntryController {

	/** 画面で使用するフォームクラス名 */
	private static final String FORM_CLASS_NAME = "userEditForm";

	/** 商品編集画面Serviceクラス */
	private final UserEditService service;

	/** セッションオブジェクト */
	private final HttpSession session;

	/** Dozer Mapper */
	private final Mapper mapper;

	/** メッセージソース */
	private final MessageSource messageSource;

	/** リダイレクトパラメータ：エラー有 */
	private static final String REDIRECT_PRAM_ERR = "err";

	/**
	 * 前画面で選択されたログインIDに紐づく商品情報を画面に表示します。
	 * 
	 * @param model モデル
	 * @param form 入力情報
	 * @return 商品編集画面テンプレート名
	 * @throws Exception 
	 */
	@GetMapping(UrlConst.PRODUCT_ENTRY)
	public String view(Model model) throws Exception {
//		var loginId = (String) session.getAttribute(SessionKeyConst.SELECETED_LOGIN_ID);
//		var userInfoOpt = service.searchUserInfo(loginId);
//		if (userInfoOpt.isEmpty()) {
//			model.addAttribute(ModelKey.MESSAGE,
//					AppUtil.getMessage(messageSource, MessageConst.USEREDIT_NON_EXISTED_LOGIN_ID));
//			return ViewNameConst.USER_EDIT_ERROR;
//		}
//		
//		var userInfo = userInfoOpt.get();
//		boolean isFormDataSet = false; // DBから取得したユーザ情報でformをセットするか(True：yes)
//		if (!model.containsAttribute(ModelKey.IS_ERROR)) {
//			// IS_ERRORの判定値がなければ初回表示
//			isFormDataSet = true;
//		} else {
//			if (!(boolean)model.getAttribute(ModelKey.IS_ERROR)) {
//				// IS_ERRORがFALSEであれば登録成功
//				isFormDataSet = true;
//			}
//		}
//		
//		if (isFormDataSet) {
//			model.addAttribute("userEditForm", mapper.map(userInfo, UserEditForm.class));
//		}
//		model.addAttribute("userEditInfo", mapper.map(userInfo, UserEditInfo.class));
//		model.addAttribute("userStatusKindOptions", UserStatusKind.values());
//		model.addAttribute("authorityKindOptions", AuthorityKind.values());

		return ViewNameConst.PRODUCT_ENTRY;
	}

//	/**
//	 * 画面の更新エラー時にエラーメッセージを表示します。
//	 * 
//	 * @param model モデル
//	 * @return 商品編集エラー画面テンプレート名
//	 */
//	@GetMapping(value = UrlConst.USER_EDIT, params = REDIRECT_PRAM_ERR)
//	public String viewWithError(Model model) {
//		return ViewNameConst.USER_EDIT_ERROR;
//	}
//
//	/**
//	 * 画面の入力情報をもとに商品情報を更新します。
//	 * 
//	 * @param form 入力情報
//	 * @param user 認証済みユーザー情報
//	 * @param redirectAttributes リダイレクト用オブジェクト
//	 * @return リダイレクトURL
//	 */
//	@PostMapping(value = UrlConst.USER_EDIT, params = "update")
//	public String updateUser(@Validated UserEditForm form, BindingResult bdResult, @AuthenticationPrincipal User user,
//			RedirectAttributes redirectAttributes) {
//		if (bdResult.hasErrors()) {
//			editGuideMessage(form, bdResult, MessageConst.FORM_ERROR, redirectAttributes);
//			return AppUtil.doRedirect(UrlConst.USER_EDIT);
//		}
//		var updateDto = mapper.map(form, UserUpdateInfo.class);
//		updateDto.setLoginId((String) session.getAttribute(SessionKeyConst.SELECETED_LOGIN_ID));
//		updateDto.setUpdateUserId(user.getUsername());
//
//		var updateResult = service.updateUserInfo(updateDto);
//		var updateMessage = updateResult.getUpdateMessage();
//		if (updateMessage == UserEditMessage.FAILED) {
//			redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
//					AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
//			redirectAttributes.addAttribute(REDIRECT_PRAM_ERR, "");
//			return AppUtil.doRedirect(UrlConst.USER_EDIT);
//		}
//
//		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, false);
//		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE,
//				AppUtil.getMessage(messageSource, updateMessage.getMessageId()));
//
//		return AppUtil.doRedirect(UrlConst.USER_EDIT);
//	}
//
//	/**
//	 * メッセージIDを使ってプロパティファイルからメッセージを取得し、画面に表示します。
//	 * 
//	 * <p>また、画面でメッセージを表示する際に通常メッセージとエラーメッセージとで色分けをするため、<br>
//	 * その判定に必要な情報も画面に渡します。
//	 * 
//	 * @param form 入力情報
//	 * @param bdResult 入力内容の単項目チェック結果
//	 * @param messageId プロパティファイルから取得したいメッセージのID
//	 * @param redirectAttributes リダイレクト用モデル
//	 */
//	private void editGuideMessage(UserEditForm form, BindingResult bdResult, String messageId,
//			RedirectAttributes redirectAttributes) {
//		redirectAttributes.addFlashAttribute(ModelKey.MESSAGE, AppUtil.getMessage(messageSource, messageId));
//		redirectAttributes.addFlashAttribute(ModelKey.IS_ERROR, true);
//		redirectAttributes.addFlashAttribute(form);
//		redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + FORM_CLASS_NAME, bdResult);
//	}

}
