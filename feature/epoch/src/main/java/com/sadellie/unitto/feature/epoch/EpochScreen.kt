/*
 * Unitto is a unit converter for Android
 * Copyright (c) 2023 Elshan Agaev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.sadellie.unitto.feature.epoch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sadellie.unitto.core.ui.common.UnittoTopAppBar
import com.sadellie.unitto.core.ui.common.PortraitLandscape
import com.sadellie.unitto.feature.epoch.component.DateTextField
import com.sadellie.unitto.feature.epoch.component.EpochKeyboard
import com.sadellie.unitto.feature.epoch.component.TopPart
import com.sadellie.unitto.feature.epoch.component.UnixTextField

@Composable
internal fun EpochRoute(
    navigateUpAction: () -> Unit,
    viewModel: EpochViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    EpochScreen(
        navigateUpAction = navigateUpAction,
        uiState = uiState.value,
        addSymbol = viewModel::addSymbol,
        deleteSymbol = viewModel::deleteSymbol,
        swap = viewModel::swap,
        clearSymbols = viewModel::clearSymbols
    )
}

@Composable
private fun EpochScreen(
    navigateUpAction: () -> Unit,
    uiState: EpochUIState,
    addSymbol: (String) -> Unit,
    deleteSymbol: () -> Unit,
    clearSymbols: () -> Unit,
    swap: () -> Unit
) {
    UnittoTopAppBar(
        title = stringResource(R.string.epoch_converter),
        navigateUpAction = navigateUpAction
    ) { padding ->
        PortraitLandscape(
            modifier = Modifier.padding(padding),
            content1 = {
                TopPart(
                    modifier = it,
                    swap = swap,
                    unixToDate = !uiState.dateToUnix,
                    dateField = {
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .animateItemPlacement()
                        ) {
                            DateTextField(
                                modifier = Modifier.fillMaxWidth(),
                                date = uiState.dateField
                            )
                            Text(
                                text = "date",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    },
                    unixField = {
                        Column(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.background)
                                .animateItemPlacement()
                        ) {
                            UnixTextField(
                                modifier = Modifier.fillMaxWidth(),
                                unix = uiState.unixField
                            )
                            Text(
                                text = "unix",
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.End
                            )
                        }
                    }
                )
            },
            content2 = {
                EpochKeyboard(
                    modifier = it,
                    addSymbol = addSymbol,
                    clearSymbols = clearSymbols,
                    deleteSymbol = deleteSymbol
                )
            }
        )
    }
}
