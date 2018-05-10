package com.smarthome.iot.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.graphics.Color;
import android.view.Gravity;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.smarthome.iot.R;

public class MessageUtil {

	/**
	 * Hien thi dang toast
	 * 
	 * @param context
	 * @param info
	 */
	public void showToast(Context context, String info) {
		TextView textView = new TextView(context);
		textView.setBackgroundColor(0xCC000000);
		textView.setTextColor(Color.WHITE);
		textView.setGravity(Gravity.CENTER);
		textView.setPadding(20, 20, 20, 20);
		textView.setText(info);
		textView.setTextSize(15);

		Toast toastView = new Toast(context);
		toastView.setView(textView);
		toastView.setDuration(Toast.LENGTH_SHORT);
		toastView.setGravity(Gravity.BOTTOM, 0, 0);
		toastView.show();
	}

	/**
	 * Show custom Dialog Progress
	 *
	 * @param context
	 * @param cancelListener
	 * @return
	 */
	public Dialog initCustomDialogProgress(Context context,
										   OnCancelListener cancelListener) {
		Dialog dialog = new Dialog(context);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_progress);
		dialog.setCancelable(true);
		if (cancelListener != null)
			dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);

		return dialog;
	}

	/**
	 * Show Dialog confirm
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @param isShowOk
	 * @param isShowCancel
	 *
	 */
	public void showDialogMessage(Context context, String title,
								  String content, boolean isShowOk, boolean isShowCancel) {
		DialogMessage dgMsg = new DialogMessage(context, title, content,
				isShowOk, isShowCancel);
		dgMsg.show();
	}

	/**
	 * Show Dialog confirm
	 *
	 * @param context
	 * @param title
	 * @param content
	 * @param isShowOk
	 * @param isShowCancel
	 * @param process
	 */
	public void showDialogMessage(Context context, String title,
								  String content, boolean isShowOk, boolean isShowCancel, String okName, String cancelName,
								  DialogMessage.ProcessDialogMessage process) {
		DialogMessage dgMsg = new DialogMessage(context, title, content,
				isShowOk, isShowCancel, process);
		dgMsg.changeButtonName(okName, cancelName);
		dgMsg.show();
	}
}
