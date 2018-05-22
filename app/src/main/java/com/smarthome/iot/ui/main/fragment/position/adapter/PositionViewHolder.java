package com.smarthome.iot.ui.main.fragment.position.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.johnkil.print.PrintView;
import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.utils.rx.SchedulerProvider;
import com.unnamed.b.atv.model.TreeNode;

public class PositionViewHolder extends TreeNode.BaseNodeViewHolder<PositionViewHolder.IconTreeItem> implements PositionHolderContract.View {
    private TextView tvValue;
    private PrintView arrowView;
    private PositionHolderContract.Presenter mPresenter;
    private Context context;

    public PositionViewHolder(Context context) {
        super(context);
        this.context = context;
        PositionRepository positionRepository = PositionRepository.getInstance(PositionLocalDataSource.getInstance(),
                PositionRemoteDataSource.getInstance(context));
        mPresenter = new PositionHolderPresenter(context, positionRepository, SchedulerProvider.getInstance(), this);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_icon_node, null, false);
        tvValue = view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        final PrintView iconView = view.findViewById(R.id.icon);
        iconView.setIconText(context.getResources().getString(value.icon));

        arrowView = view.findViewById(R.id.arrow_icon);

        view.findViewById(R.id.btn_addFolder).setOnClickListener(v -> {
            mPresenter.createPosition(value.mPosition);
        });

        view.findViewById(R.id.btn_delete).setOnClickListener(v -> {
            mPresenter.deletePosition(value.mPosition);
        });

        //if My computer
        if (node.getLevel() == 1) {
            view.findViewById(R.id.btn_delete).setVisibility(View.GONE);
        }

        return view;
    }

    @Override
    public void toggle(boolean active) {
        arrowView.setIconText(context.getResources().getString(active ? R.string.ic_keyboard_arrow_down : R.string.ic_keyboard_arrow_right));
    }

    public static class IconTreeItem {
        public int icon;
        public String text;
        public Position mPosition;

        public IconTreeItem(int icon, String text, Position position) {
            this.icon = icon;
            this.text = text;
            this.mPosition = position;
        }
    }

    @Override
    public void updatePositionList() {

    }

    @Override
    public void deletePositionSuccess() {
        Toast.makeText(context, "Delete Position Success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void deletePositionFailed() {
        Toast.makeText(context, "Delete Position Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void createPositionSuccess() {

    }

    @Override
    public void createPositionFailed() {

    }
}