/**
 * ユーザー編集画面
 */
$(function() {
	// 更新ボタンをクリックしたときの処理
	$('input[name="update"]').on('click', function() {
		// パスワードが空欄の場合、Validation無効にするためdisabledにする
		if ($('#password').val() == "") {
			$('#password').prop('disabled', true);
		}
	});
});
