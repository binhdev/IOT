package com.smarthome.iot.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.smarthome.iot.R;

public class DialogMessage extends Dialog implements OnClickListener {
	Activity activity;
	ProcessDialogMessage processDialogMove;
	ImageView imgIcon;
	TextView txtTitle;
	TextView txtContent;
	Button btnOk;
	Button btnCancel;

	public DialogMessage(Context context, String title, String content,
						 boolean isShowOk, boolean isShowCancel,
						 ProcessDialogMessage processMove) {
		super(context);
		activity = (Activity) context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		setContentView(R.layout.dialog_message);

		processDialogMove = processMove;

		imgIcon = findViewById(R.id.dialog_msg_icon);
		txtTitle = findViewById(R.id.dialog_msg_title);
		txtContent = findViewById(R.id.dialog_msg_content);

		btnOk = findViewById(R.id.dialog_msg_btnOK);
		btnOk.setOnClickListener(this);
		btnCancel = findViewById(R.id.dialog_msg_btnCancel);
		btnCancel.setOnClickListener(this);

		this.setInfo(title, content, isShowOk, isShowCancel);
	}

	public DialogMessage(Context context, String title, String content,
						 boolean isShowOk, boolean isShowCancel) {
		super(context);
		activity = (Activity) context;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setBackgroundDrawableResource(
				android.R.color.transparent);
		setContentView(R.layout.dialog_message);
		processDialogMove = new ProcessDialogMessage() {
			public void click_Ok() {
				dismiss();
			}

			public void click_Cancel() {
				dismiss();
			}
		};

		imgIcon = (ImageView) findViewById(R.id.dialog_msg_icon);
		txtTitle = (TextView) findViewById(R.id.dialog_msg_title);
		txtContent = (TextView) findViewById(R.id.dialog_msg_content);
		btnOk = (Button) findViewById(R.id.dialog_msg_btnOK);
		btnCancel = (Button) findViewById(R.id.dialog_msg_btnCancel);

		this.setInfo(title, content, isShowOk, isShowCancel);
	}
	public void setInfo(String title, String content, boolean isShowOk,
						boolean isShowCancel) {
		imgIcon.setVisibility(View.GONE);
		if (title.length() > 0) {
			txtTitle.setText(title);
			txtTitle.setVisibility(View.VISIBLE);
		} else
			txtTitle.setVisibility(View.GONE);

		txtContent.setText(content);

		if (isShowOk) {
			btnOk.setOnClickListener(this);
			btnOk.setVisibility(View.VISIBLE);
		} else {
			btnOk.setVisibility(View.GONE);
		}
		if (isShowCancel) {
			if (!isShowOk) {
				btnCancel.setText("Close");
			}
			btnCancel.setOnClickListener(this);
			btnCancel.setVisibility(View.VISIBLE);
		} else {
			btnCancel.setVisibility(View.GONE);
		}
	}

	public void changeButtonName(String okName, String cancelName) {
		btnCancel.setText(cancelName);
		btnOk.setText(okName);
	}

	public void setIconTitle(int resource) {
		if (resource != -1) {
			imgIcon.setBackgroundResource(resource);
			imgIcon.setVisibility(View.VISIBLE);
		} else {
			imgIcon.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View v) {
		if (v == btnCancel) {
			processDialogMove.click_Cancel();
			dismiss();
		} else if (v == btnOk) {
			processDialogMove.click_Ok();
			dismiss();
		}
	}

	public static abstract class ProcessDialogMessage {
		public abstract void click_Ok();

		public abstract void click_Cancel();
	}
}
