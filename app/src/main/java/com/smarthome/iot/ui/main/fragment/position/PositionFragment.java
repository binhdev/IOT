package com.smarthome.iot.ui.main.fragment.position;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smarthome.iot.R;
import com.smarthome.iot.data.model.Position;
import com.smarthome.iot.data.repository.PositionRepository;
import com.smarthome.iot.data.source.local.PositionLocalDataSource;
import com.smarthome.iot.data.source.remote.PositionRemoteDataSource;
import com.smarthome.iot.utils.rx.SchedulerProvider;
import com.smarthome.iot.ui.main.fragment.position.adapter.PositionViewHolder;
import com.unnamed.b.atv.model.TreeNode;
import com.unnamed.b.atv.view.AndroidTreeView;

import java.util.List;

public class PositionFragment extends Fragment implements PositionContract.View {

    private PositionContract.Presenter mPresenter;
    private ViewGroup containerView;
    private AndroidTreeView tView;

    public static PositionFragment newInstance(){
        PositionFragment fragment = new PositionFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initGUI(view);
        containerView = view.findViewById(R.id.main);

        if (savedInstanceState != null) {
            String state = savedInstanceState.getString("tState");
            if (!TextUtils.isEmpty(state)) {
                tView.restoreState(state);
            }
        }

        return view;
    }

    private void initGUI(View view){
        initData();
    }

    private void initData(){
        PositionRepository positionRepository = PositionRepository.getInstance(PositionLocalDataSource.getInstance(),
                PositionRemoteDataSource.getInstance(getContext()));
        mPresenter = new PositionPresenter(getContext(), positionRepository, SchedulerProvider.getInstance());
        mPresenter.setView(this);
        mPresenter.positionList();
    }

    @Override
    public void setPositionList(List<Position> positionList) {
        TreeNode root = TreeNode.root();
        TreeNode computerRoot = new TreeNode(new PositionViewHolder.IconTreeItem(R.string.ic_folder, "All Position", null));
        root.addChildren(computerRoot);

        tView = new AndroidTreeView(getActivity(), root);
        tView.setDefaultAnimation(true);
        tView.setDefaultContainerStyle(R.style.TreeNodeStyleCustom);
        tView.setDefaultViewHolder(PositionViewHolder.class);
        tView.setDefaultNodeClickListener(nodeClickListener);
        tView.setDefaultNodeLongClickListener(nodeLongClickListener);

        for(int i = 0; i < positionList.size(); i++){
            computerRoot.addChild(makeTree(positionList.get(i)));
        }

        containerView.addView(tView.getView());
    }

    private TreeNode makeTree(Position position){
        TreeNode node = new TreeNode(new PositionViewHolder.IconTreeItem(R.string.ic_folder, position.getName(), position));
        if(position.getChild().size() > 0){
            for(int i = 0; i < position.getChild().size(); i++){
                node.addChildren(makeTree(position.getChild().get(i)));
            }
        }
        return node;
    }

    @Override
    public void showLoadingIndicator() {

    }

    @Override
    public void hideLoadingIndicator() {

    }

    @Override
    public void showLoginError(Throwable throwable) {

    }

    @Override
    public void startDashboardActivity() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.expandAll:
                tView.expandAll();
                break;

            case R.id.collapseAll:
                tView.collapseAll();
                break;
        }
        return true;
    }

    private int counter = 0;

    private TreeNode.TreeNodeClickListener nodeClickListener = new TreeNode.TreeNodeClickListener() {
        @Override
        public void onClick(TreeNode node, Object value) {
            PositionViewHolder.IconTreeItem item = (PositionViewHolder.IconTreeItem) value;
        }
    };

    private TreeNode.TreeNodeLongClickListener nodeLongClickListener = new TreeNode.TreeNodeLongClickListener() {
        @Override
        public boolean onLongClick(TreeNode node, Object value) {
            PositionViewHolder.IconTreeItem item = (PositionViewHolder.IconTreeItem) value;
            Toast.makeText(getActivity(), "Long click: " + item.text, Toast.LENGTH_SHORT).show();
            return true;
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tState", tView.getSaveState());
    }
}