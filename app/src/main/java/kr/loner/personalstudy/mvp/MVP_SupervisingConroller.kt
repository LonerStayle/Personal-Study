package kr.loner.personalstudy.mvp

import kotlin.properties.Delegates

/** Supervising Controller:
- Supervising Controller는 Presentation Logic의 일부를 View로 이동시키는 패턴입니다.
- View는 UI 요소에 대한 처리와 일부 Presentation Logic을 담당합니다.
- Presenter는 View와 Model 간의 통신 및 비즈니스 로직을 수행합니다.
- Model은 데이터 처리와 관련된 로직을 처리합니다.
- View는 일부 Presentation Logic을 처리하므로, View와 Presenter 간의 결합이 강해집니다.
- View와 Presenter는 서로에 대해 알고 있으며, 협력하여 UI 처리 및 비즈니스 로직을 수행합니다.
- View는 Model 로부터 notify를 받습니다.
 */
fun mvpScvTest() {
    val view: ScView = ScViewImpl()
    val model = ScRepository { view.setDataUi(it) }
    val presenter = ScPresenter(view, model)

    view.setPresenter(presenter)
    view.showUiData()
    presenter.fetchData()
}

// View 인터페이스
private interface ScView {
    val uiData: String
    fun setPresenter(presenter: ScPresenter)
    fun setDataUi(uiData: String)
    fun showUiData()
    fun showProcessedData(msg: String)
}

// View 구현
private class ScViewImpl() : ScView {
    override lateinit var uiData: String

    //Presenter을 필요하면 사용
    private lateinit var presenter: ScPresenter
    override fun setPresenter(presenter: ScPresenter) {
        this.presenter = presenter
    }

    override fun setDataUi(uiData: String) {
        this.uiData = uiData
    }

    override fun showUiData() {
        if (::uiData.isLateinit) println(uiData)
    }

    override fun showProcessedData(msg: String) {
        println(msg)
    }

}

// Presenter 클래스
private class ScPresenter(private val view: ScView, private val model: ScRepository) {
    fun fetchData() {
        val processedData = processData(model.getData())
        view.showProcessedData(processedData)
    }

    private fun processData(data: String): String {
        return "Processed: $data"
    }
}

// Model 클래스
private class ScRepository(private val updateDataObserve: (String) -> Unit) {
    private var observeData by Delegates.observable("") { _, _, value ->
        updateDataObserve(value)
    }

    init {
        setData("Data From Model")
    }

    fun setData(data: String) {
        this.observeData = data
    }

    fun getData() = observeData


}
