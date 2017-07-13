package com.soul.androidcompilptions.rxandretrofi;

import com.soul.library.base.BasePresenter;
import com.soul.library.mvp.IModel;
import com.soul.library.mvp.IView;

/**
 * * @author soul
 *
 * @项目名:Compilations
 * @包名: com.soul.androidcompilptions.rxandretrofi.contract
 * @作者：祝明
 * @描述：TODO
 * @创建时间：2017/1/12 12:59
 */

public interface RxRetrofitContract {

    interface ShowNetPhotoView extends IView {

    }

    interface Model extends IModel {


    }

    abstract class ShowNetPhotoPresenter extends BasePresenter<ShowNetPhotoView, Model> {

    }

}
