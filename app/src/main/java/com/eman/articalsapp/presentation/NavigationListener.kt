package com.eman.articalsapp.presentation

import com.eman.articalsapp.domain.model.Artical

interface NavigationListener {
    fun onClickArtical(artical: Artical)
}