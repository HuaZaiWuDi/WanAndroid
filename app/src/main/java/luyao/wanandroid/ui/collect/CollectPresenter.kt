package luyao.wanandroid.ui.collect

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import luyao.wanandroid.api.WanRetrofitClient

/**
 * Created by Lu
 * on 2018/4/10 22:12
 */
class CollectPresenter(
        private val mView: CollectContract.View
) : CollectContract.Presenter {

    override fun start() {

    }

    init {
        mView.mPresenter = this
    }

    override fun getCollectArticles(page: Int) {
        val job = CoroutineScope(Dispatchers.Main).launch {
            val result = WanRetrofitClient.service.getCollectArticles(page).await()
            if (result.errorCode != 0) mView.getCollectArticlesError(result.errorMsg) else mView.getCollectArticles(result.data)
        }
        if (job.isActive)
            job.cancel()

    }

}