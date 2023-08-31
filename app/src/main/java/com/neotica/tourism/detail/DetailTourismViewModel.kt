package com.neotica.tourism.detail

import androidx.lifecycle.ViewModel
import com.neotica.tourism.core.domain.model.Tourism
import com.neotica.tourism.core.domain.usecase.TourismUseCase
import javax.inject.Inject

class DetailTourismViewModel @Inject constructor(private val tourismUseCase: TourismUseCase) : ViewModel() {
    fun setFavoriteTourism(tourism: Tourism, newStatus:Boolean) =
        tourismUseCase.setFavoriteTourism(tourism, newStatus)
}

