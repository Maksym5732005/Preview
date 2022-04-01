import com.preview.R
import com.preview.base.LcenState
import com.preview.base.ResourceReader
import com.preview.base.extensions.mapContent
import com.preview.feature.market.domain.model.MarketInfo
import com.preview.feature.market.presentation.model.MarketInfoItemUiState
import org.joda.time.format.DateTimeFormat

private const val DATE_TIME_PATTERN = "E d MMM-yyyy HH:mm:ss"

fun LcenState<MarketInfo>.convertToUiState(resourceReader: ResourceReader): LcenState<MarketInfoItemUiState> {
    return mapContent { info ->
        MarketInfoItemUiState(
            dateTime = DateTimeFormat.forPattern(DATE_TIME_PATTERN).print(info.time),
            openText = if (info.isMarketOpen) {
                resourceReader.getString(R.string.markets_item_market_state_open)
            } else {
                resourceReader.getString(R.string.markets_item_market_state_close)
            },
            workingTime = info.closeAt,
        )
    }
}