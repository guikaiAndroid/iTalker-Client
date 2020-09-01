package net.guikai.italker.push.frags.panel;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import net.guikai.italker.common.app.BaseFragment;
import net.guikai.italker.common.tools.UiTool;
import net.guikai.italker.common.widget.recycler.BaseRecyclerAdapter;
import net.guikai.italker.face.Face;
import net.guikai.italker.push.R;
import net.qiujuer.genius.ui.Ui;

import java.util.List;

/**
 * Description: 底部面板实现
 * Crete by Anding on 2020/8/27
 */
public class PanelFragment extends BaseFragment {
    private View mFacePanel, mGalleryPanel, mRecordPanel;
    private PanelCallback mCallback;

    public PanelFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        initFace(root);
    }

    // 初始化表情
    private void initFace(View root) {
        final View facePanel = mFacePanel = root.findViewById(R.id.lay_panel_face);

        View backspace = facePanel.findViewById(R.id.im_backspace);
        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除逻辑
                PanelCallback callback = mCallback;
                if (callback == null)
                    return;

                // 模拟一个键盘删除点击
                KeyEvent event = new KeyEvent(0, 0, 0, KeyEvent.KEYCODE_DEL,
                        0, 0, 0, 0, KeyEvent.KEYCODE_ENDCALL);
                callback.getInputEditText().dispatchKeyEvent(event);
            }
        });

        TabLayout tabLayout = (TabLayout) facePanel.findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) facePanel.findViewById(R.id.pager);
        tabLayout.setupWithViewPager(viewPager);

        // 每一表情显示48dp
        final int minFaceSize = (int) Ui.dipToPx(getResources(), 48);
        final int totalScreen = UiTool.getScreenWidth(getActivity());
        final int spanCount = totalScreen / minFaceSize;

        viewPager.setAdapter(new PagerAdapter() {

            @Override
            public int getCount() {
                return Face.all(getContext()).size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                // 添加的
                LayoutInflater inflater = LayoutInflater.from(getContext());
                RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.lay_face_content, container, false);
                recyclerView.setLayoutManager(new GridLayoutManager(getContext(), spanCount));

                // 设置Adapter
                List<Face.Bean> faces = Face.all(getContext()).get(position).faces;
                FaceAdapter adapter = new FaceAdapter(faces, new BaseRecyclerAdapter.AdapterListenerImpl<Face.Bean>() {
                    @Override
                    public void onItemClick(BaseRecyclerAdapter.ViewHolder holder, Face.Bean bean) {
                        if (mCallback == null)
                            return;
                        // 表情添加到输入框
                        EditText editText = mCallback.getInputEditText();
                        Face.inputFace(getContext(), editText.getText(), bean, (int)
                                (editText.getTextSize() + Ui.dipToPx(getResources(), 2)));
                    }
                });
                recyclerView.setAdapter(adapter);

                // 添加
                container.addView(recyclerView);

                return recyclerView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                // 移除的
                container.removeView((View) object);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                // 拿到表情盘的描述
                return Face.all(getContext()).get(position).name;
            }
        });
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_panel;
    }

    // 开始初始化方法
    public void setup(PanelCallback callback) {
        mCallback = callback;
    }

    public void showFace() {
//        mRecordPanel.setVisibility(View.GONE);
//        mGalleryPanel.setVisibility(View.GONE);
        mFacePanel.setVisibility(View.VISIBLE);
    }

    // 回调聊天界面的Callback
    public interface PanelCallback {
        EditText getInputEditText();

        // 返回需要发送的图片
        void onSendGallery(String[] paths);

    }

}
