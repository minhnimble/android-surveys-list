package co.nimblehq.usecase.survey

import co.nimblehq.data.error.SurveyError.GetSurveysListError
import co.nimblehq.data.lib.schedulers.RxSchedulerProvider
import co.nimblehq.data.model.Survey
import co.nimblehq.data.repository.SurveyRepository
import co.nimblehq.usecase.base.SingleUseCase
import io.reactivex.Single
import javax.inject.Inject

class LoadMoreSurveysListSingleUseCase @Inject constructor(
    rxSchedulerProvider: RxSchedulerProvider,
    private val surveyRepository: SurveyRepository
) : SingleUseCase<LoadMoreSurveysListSingleUseCase.Input, List<Survey>>(
    rxSchedulerProvider.io(),
    rxSchedulerProvider.main(),
    ::GetSurveysListError
) {

    data class Input(
        val pageNumber: Int,
        val pageSize: Int
    )

    override fun create(input: Input): Single<List<Survey>> {
        return with(input) {
            surveyRepository.loadMoreSurveysList(
                pageNumber,
                pageSize
            )
        }
    }
}