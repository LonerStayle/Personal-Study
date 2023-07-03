package kr.loner.personalstudy.mvp
/**Passive View:
- Passive View는 Presentation Logic을 완전히 View로 이동시키는 패턴입니다.
- View는 UI 요소에 대한 처리, 데이터 표시 등을 담당합니다.
- Presenter는 단순히 View와 Model 사이의 중개자 역할을 수행합니다.
- Model은 비즈니스 로직과 데이터를 처리합니다.
- View와 Model은 서로에 대해 알지 못하고, Presenter가 중재 역할을 수행합니다.
- View와 Model 간의 결합이 약해지므로 테스트 용이성과 유지 보수성이 향상될 수 있습니다.
 */
fun main() {
    val view: PsView = PsViewImpl()
    val presenter = PsPresenter()
    presenter.attachView(view)
    presenter.fetchData()

    //.. 뷰가 종료 될 때
    presenter.detachView()
}

// View 인터페이스
private interface PsView {
    fun showResult(result: String)
}

// View 구현
private class PsViewImpl : PsView {
    override fun showResult(result: String) {
        println(result)
    }
}

// Presenter 클래스
private class PsPresenter {
    private var view: PsView? = null
    private val model = PsRepository()

    fun attachView(view: PsView) {
        this.view = view
    }

    fun detachView() {
        view = null
    }

    fun fetchData() {
        val data = model.getData()
        val processedData = processData(data)
        view?.showResult(processedData)
    }

    private fun processData(data: String): String {
        return "Processed: $data"
    }
}

// Model 클래스
private class PsRepository {
    fun getData(): String {
        // 데이터 로드 로직
        return "Data from Model"
    }
}

