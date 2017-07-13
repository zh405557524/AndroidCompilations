package com.soul.androidcompilptions.rxandretrofi.ui.picture;

import com.soul.androidcompilptions.rxandretrofi.bean.MeiZhiBean;
import com.soul.androidcompilptions.rxandretrofi.entity.MeiZhi;
import com.soul.androidcompilptions.rxandretrofi.ui.meizi.MeiZhiModel;
import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IView;

import java.util.List;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.ui.picture
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/6/16 17:02
 */

public interface PictureContract {

    interface View extends IView {
        void showLocalData(List<MeiZhi> data, int position);

        void showMoreData(MeiZhiBean meiZhiBean);
    }

    abstract class Presenter extends BasePresenter<View, MeiZhiModel> {

        public abstract void showLocalData(int position);
    }
}
